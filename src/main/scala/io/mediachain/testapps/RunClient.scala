package io.mediachain.testapps

import java.net.ConnectException

import io.mediachain.copycat.TestClient

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object RunClient {

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Expected arguments: server-address")
      System.exit(1)
    }

    val server = args(0)
    val client = TestClient.build()
    client.connect(server)
    val block = try {
      Await.result(client.getBlock, Duration.Inf)
    } catch {
      case e: ConnectException =>
        println(s"got ConnectException: $e")
        throw e
      case e: Throwable => throw e
    }
    println(s"Got block successfully")
    client.close()
  }

}
