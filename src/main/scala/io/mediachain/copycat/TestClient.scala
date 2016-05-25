package io.mediachain.copycat

import io.atomix.catalyst.transport.Address
import io.atomix.copycat.client.CopycatClient

import scala.compat.java8.FutureConverters
import scala.concurrent.Future

class TestClient(copycatClient: CopycatClient) {

  def connect(address: String): Unit =
    copycatClient.connect(new Address(address)).join()

  def close(): Unit = copycatClient.close().join()

  def getBlock: Future[Block] = {
    val future = copycatClient.submit(GetBlock())
    FutureConverters.toScala(future)
  }
}


object TestClient {
  def build(): TestClient = {
    val client = CopycatClient.builder()
      .withTransport(Transport.build(2))
      .build()

    Serializers.register(client.serializer)
    new TestClient(client)
  }
}
