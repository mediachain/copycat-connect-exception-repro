package io.mediachain.testapps

import io.mediachain.copycat.TestServer
import org.slf4j.LoggerFactory

object RunServer {
  val logger = LoggerFactory.getLogger(RunServer.getClass)

  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      println("arguments: <server-address:port> <log-dir>")
      System.exit(1)
    }

    val address = args(0)
    val logDir = args(1)
    val server = TestServer.build(address, logDir)

    logger.info(s"bootstrapping server at $address")
    server.bootstrap().join()

    while (server.isRunning) { Thread.sleep(1000) }
  }

}
