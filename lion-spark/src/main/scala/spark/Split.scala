package spark

/**
  * RDD 的一个分区
  */
trait Split extends Serializable {
  /**
    * 获取父RDD中 split 的索引
    * @return
    */
  def index: Integer

  /**
    * hashCode 的更好默认实现
    * @return
    */
  override def hashCode(): Int = index

}
