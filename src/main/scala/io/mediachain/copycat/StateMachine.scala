package io.mediachain.copycat

import io.atomix.copycat.Query
import io.atomix.copycat.server.{Commit, StateMachine}
import org.slf4j.LoggerFactory

import scala.util.Random



// simple query that succeeds, to verify that the connection has been established
case class Pong()
case class Ping() extends Query[Pong]


case class Entry(contents: Array[Byte])
case class Block(entries: Array[Entry])

// Failing query
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

  def ping(commit: Commit[Ping]): Pong = {
    try {
      Pong()
    } finally {
      commit.release()
    }
  }



}
