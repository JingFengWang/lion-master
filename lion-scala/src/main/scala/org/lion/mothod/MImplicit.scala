package org.lion.mothod

object MImplicit {

  def sayHello(age: Int)(implicit name: String) = println("my name is:" + name + ",my age is:" + age)
  def main(args: Array[String]): Unit = {
//    implicit val name = "cyony"
    implicit val sex = "男"

    sayHello(30)


  }

}
