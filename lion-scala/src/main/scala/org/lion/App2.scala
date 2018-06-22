package org.lion

import java.util

import org.lion.mothod.{LongLines, LongLinesLocal02}
//import collection.JavaConversions._
//import collection.convert.wrapAll._
//import collection.convert.wrapAsJava._
//import collection.convert.wrapAsScala._

//import collection.convert.decorateAll._
import collection.convert.decorateAsJava._
import collection.convert.decorateAsScala._



object App2 {
  var increase = (x: Int) => x + 1 // 函数字面量
  var increase01 = () => 1 // 函数字面量
  def main(args: Array[String]): Unit = {
    val orderIds: java.util.List[String] = Seq("sss", "bbb").asJava
    val orderIds2: java.util.List[String] = Seq("sss", "bbb").asJava
    val name: Seq[String] = new util.ArrayList[String]().asScala
    val mlist = new MList()
    val list = new util.ArrayList[String]();
    list.add("hello");
    mlist.process(list)
    mlist.process(List("aaa", "bbb").asJava)
    println("hello")

//    LongLines.processFile("F:\\编码测试.txt", 10)
    LongLinesLocal02.processFile("F:\\编码测试.txt", 10)
//    println(App2.increase(1))
//    println(App2.increase(1))
//    println(App2.increase(1))
//    println(App2.increase(1))
//    println(App2.increase01())
    val someNumbers = List ( -11, -10, - 5, 0, 5, 10)
    someNumbers.foreach((x: Int) => println(x))
    someNumbers.filter(x => x > 0)
  }
}
