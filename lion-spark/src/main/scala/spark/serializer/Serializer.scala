package spark.serializer

import java.io.{EOFException, InputStream, OutputStream}
import java.nio.ByteBuffer

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream
import spark.util.ByteBufferInputStream

/**
  * 一个序列化器。 因为一些序列化库是非线程安全的， 这个类用于创建 spark.serializer.SerializerInstance 对象， 这个对象做实际
  * 的序列化工作并保证在同一时间只有一个线程调用
  */
trait Serializer {
  def newInstance(): SerializerInstance
}

/**
  * 一个序列化器的示例， 在同一时间被一个线程使用
  */
trait SerializerInstance {

  def serialize[T](t: T): ByteBuffer

  def deserialize[T](bytes: ByteBuffer): T

  def deserialize[T](bytes: ByteBuffer, loader: ClassLoader): T

  def serializeStream(s: OutputStream): SerializationStream

  def deserializeStream(s: InputStream): DeserializationStream

  def serializeMany[T](iterator: Iterator[T]): ByteBuffer = {
    /**  默认实现使用 serializeStrem */
    val stream = new FastByteArrayOutputStream()
    serializeStream(stream).writeAll(iterator)
    val buffer = ByteBuffer.allocate(stream.position.toInt)
    buffer.put(stream.array, 0, stream.position.toInt)
    buffer.flip()
    buffer
  }

  def deserializedMany(buffer: ByteBuffer): Iterator[Any] = {
    /**  默认实现使用 deserializeStream */
    buffer.rewind()
    deserializeStream(new ByteBufferInputStream(buffer)).asIterator
  }
}

/**
  * 一个写序列化对象的流
  */
trait SerializationStream {

  def writeObject[T](t: T): SerializationStream

  def flush(): Unit

  def close(): Unit

  def writeAll[T](iter: Iterator[T]): SerializationStream = {
    while (iter.hasNext) {
      writeObject(iter.next())
    }
    this
  }
}

/**
  * 一个读序列化对象的流
  */
trait DeserializationStream {

  def readObject[T](): T

  def close(): Unit

  /**
    * 通过一个迭代器读取这个流的元素。 这个方法只能被调用一次， 因为读取每个元素会消费输入源的所有数据
    * @return
    */
  def asIterator: Iterator[Any] = new Iterator[Any] {

    var gotNext = false
    var finished = false
    var nextValue: Any = null

    private def getNext(): Unit = {
      try {
        nextValue = readObject[Any]()
      } catch {
        case eof: EOFException => finished = true
      }
      gotNext = true
    }

    override def hasNext: Boolean = {
      if (!gotNext) {
        getNext()
      }
      if (finished) {
        close()
      }
      !finished
    }

    override def next(): Any = {
      if (!gotNext) {
        getNext()
      }
      if (finished) {
        throw new NoSuchElementException("End of stream")
      }
      gotNext = false
      nextValue
    }
  }
}

