package spark.deploy.master

import spark.deploy.ExecutorState

private[spark] class ExecutorInfo(
    val id: Int,
    val job: JobInfo,
    val worker: WorkerInfo,
    val cores: Int,
    val memory: Int) {

  var state = ExecutorState.LAUNCHING

  def fullId: String = job.id + "/" + id
}
