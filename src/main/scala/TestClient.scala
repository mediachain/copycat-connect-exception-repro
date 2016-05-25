import io.atomix.copycat.client.CopycatClient

import scala.concurrent.Future
import scala.compat.java8.FutureConverters

class TestClient(copycatClient: CopycatClient) {
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
