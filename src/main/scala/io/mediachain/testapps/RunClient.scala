package io.mediachain.testapps

import java.net.ConnectException

import io.mediachain.copycat.TestClient
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object RunClient {
  private val logger = LoggerFactory.getLogger(RunClient.getClass)

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Expected arguments: server-address")
      System.exit(1)
    }

    val server = args(0)
    val client = TestClient.build()
    client.connect(server)

    // first try a simple ping command to verify that the connection works
    val pong = Await.result(client.ping, Duration.Inf)
    logger.info(s"received ping response from server: $pong")

    try {

      Await.result(client.getBlock, Duration.Inf)
      logger.info(s"Got block successfully")
    } catch {
      case e: ConnectException =>
        logger.info(s"got ConnectException: $e")
        throw e
      case e: Throwable => throw e
    } finally {
      client.close()
    }
  }

}
