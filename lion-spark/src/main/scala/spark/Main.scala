package spark

object Main {

  def time() = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }
  def delayed( t: => Long ) = {
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }

  def say(msg: => String): Unit = {
    println(msg)
  }

  def msg(): String = {
    "我是方法"
  }
  def msg(msg: String): String = {
    msg
  }
  def main(args: Array[String]): Unit = {
    say("hello")
    say(msg())
    say(msg("带参数的方法"))

    val res = delayed(time())
    println(res)

  }

}
