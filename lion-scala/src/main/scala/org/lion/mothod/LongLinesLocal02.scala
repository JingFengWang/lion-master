package org.lion.mothod

import scala.io.Source

object LongLinesLocal02 {
  def processFile(filename: String, width: Int): Unit = {
    def processLine(line: String): Unit = {
      if (line.length > width) {
        println(filename + ":" +line.trim)
      }
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines()) {
      processLine(line)
    }
  }
}
