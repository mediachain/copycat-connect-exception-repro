package io.mediachain.copycat

import io.atomix.copycat.Query
import io.atomix.copycat.server.{Commit, StateMachine}
import org.slf4j.LoggerFactory

import scala.util.Random

case class Entry(contents: Array[Byte])
case class Block(entries: Array[Entry])

case class GetBlock() extends Query[Block]


class TestStateMachine extends StateMachine {
  private val logger = LoggerFactory.getLogger(classOf[TestStateMachine])
  val constantBlock = {
    val numEntries = 600
    val entries = for (i <- 0 to numEntries) yield {
      val bytes = new Array[Byte](512)
      Random.nextBytes(bytes)
      Entry(bytes)
    }

    Block(entries.toArray)
  }


  def getBlock(commit: Commit[GetBlock]) : Block = {
    try {
      constantBlock
    } finally {
      commit.release()
    }
  }



}
