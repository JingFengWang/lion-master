package org.lion

import java.util

import org.lion.mothod.{LongLines, LongLinesLocal02}

import scala.collection.mutable.ListBuffer
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
//    val orderIds: java.util.List[String] = Seq("sss", "bbb").asJava
//    val orderIds2: java.util.List[String] = Seq("sss", "bbb").asJava
//    val name: Seq[String] = new util.ArrayList[String]().asScala
//    val mlist = new MList()
//    val list = new util.ArrayList[String]();
//    list.add("hello");
//    mlist.process(list)
//    mlist.process(List("aaa", "bbb").asJava)
//    println("hello")

//    LongLines.processFile("F:\\编码测试.txt", 10)
//    LongLinesLocal02.processFile("F:\\编码测试.txt", 10)
//    println(App2.increase(1))
//    println(App2.increase(1))
//    println(App2.increase(1))
//    println(App2.increase(1))
//    println(App2.increase01())
//    val someNumbers = List ( -11, -10, - 5, 0, 5, 10)
//    someNumbers.foreach((x: Int) => println(x))
//    someNumbers.filter(x => x > 0)

    val map: Map[String, String] = Map("aa" -> "11" , "bb" -> "22")
    println(map.size)
//
////    println(map.get("aa").get)
////    println(map.get("cc").get)
////    if (map.get("cc") == None) println("NO") else println("yes")
////    val spaces = "                                  "
////    var num = "99"
////    num = num + spaces.substring(0, 4-num.length)
////    println("|" + num + "|")
//    val str = "中国人民|解放军"
////    println(str.substring(1,3))
////    println(str.charAt(3))
////    println(str.charAt(1))
//    println(str.indexOf("人"))
//    println(str.indexOf("中"))
//    println(str.indexOf("爱"))
//    var list: ListBuffer[(String, String)] = new ListBuffer[(String, String)]
//    val strs = str.split("|")
//    println(strs)
//    println(list)
    val x = "0|1,的:0   3:1   ,的:0   元:1   "
    val str = x.split("\\|")
    val k = str(0)
    val v = str(1)
    var a = 0
    a |= 8
    a |= 4
    println(a&8)
    println(a&4)
    println(a&2)
  }
}
