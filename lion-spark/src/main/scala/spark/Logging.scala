package spark

import org.slf4j.{Logger, LoggerFactory}

/**
  * Utility trait for classes that want to log data. Creates a SLF4J logger for the class and allows
  * logging messages at different levels using methods that only evaluate parameters lazily if the
  * log level is enabled.
  */
trait Logging {

  // 将 log 属性设置成静态的以便继承了Logging的对象可以被序列化，然后在其他机器上使用
  @transient
  private var log_ : Logger = null

  // 当前对象获取或创建 logger 的方法
  protected def log: Logger = {
    if (log_ == null) {
      var className = this.getClass.getName
      // 忽略 Scala 对象后的 $ 符号结尾
      if (className.endsWith("$")) {
        className = className.substring(0, className.length - 1)
      }
      log_ = LoggerFactory.getLogger(className)
    }
    return log_
  }

  // 直接收字符串的 Log 方法
  // 函数传名调用 msg: => String
  protected def logInfo(msg: => String): Unit = {
    if (log.isInfoEnabled) log.info(msg)
  }

  protected def logDebug(msg: => String): Unit = {
    if (log.isDebugEnabled) log.debug(msg)
  }

  protected def logTrace(msg: => String): Unit = {
    if (log.isTraceEnabled) log.trace(msg)
  }

  protected def logWarning(msg: => String): Unit = {
    if (log.isWarnEnabled) log.warn(msg)
  }

  protected def logError(msg: => String): Unit = {
    if (log.isErrorEnabled) log.error(msg)
  }

  // 带 Throwable (Exception/ Errors) 的 Log 方法
  protected def logInfo(msg: => String, throwable: Throwable): Unit = {
    if (log.isInfoEnabled) log.info(msg, throwable)
  }

  protected def logDebug(msg: => String, throwable: Throwable): Unit = {
    if (log.isDebugEnabled) log.debug(msg, throwable)
  }

  protected def logTrace(msg: => String, throwable: Throwable): Unit = {
    if (log.isTraceEnabled) log.trace(msg, throwable)
  }

  protected def logWarning(msg: => String, throwable: Throwable): Unit = {
    if (log.isWarnEnabled) log.warn(msg, throwable)
  }

  protected def logError(msg: => String, throwable: Throwable): Unit = {
    if (log.isErrorEnabled) log.error(msg, throwable)
  }

  // SLF4J 序列化是非线程安全的, 确认 logging 已经初始化的方法， 避免多线程并发初始化
  protected def initLogging() { log }
}
