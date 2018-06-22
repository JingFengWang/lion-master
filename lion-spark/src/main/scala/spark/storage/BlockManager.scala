package spark.storage

import java.nio.{ByteBuffer, MappedByteBuffer}

import spark.Logging
import sun.nio.ch.DirectBuffer


class BlockManager extends Logging {



}

private[spark]
object BlockManager extends Logging {

  def getMaxMemoryFromSystemProperties: Long = {
    val memoryFraction = System.getProperty("spark.storage.memoryFraction", "0.66").toDouble
    (Runtime.getRuntime.maxMemory * memoryFraction).toLong
  }

  /**
    * Attempt to clean up a ByteBuffer if it is memory-mapped. This uses an *unsafe* Sun API that
    * might cause errors if one attempts to read from the unmapped buffer, but it's better than
    * waiting for the GC to find it because that could lead to huge numbers of open files. There's
    * unfortunately no standard API to do this.
    */
  def dispose(buffer: ByteBuffer): Unit = {
    if (buffer != null && buffer.isInstanceOf[MappedByteBuffer]) {
      logDebug("Unmapping " + buffer)
      if (buffer.asInstanceOf[DirectBuffer].cleaner() != null) {
        buffer.asInstanceOf[DirectBuffer].cleaner().clean()
      }
    }
  }
}
