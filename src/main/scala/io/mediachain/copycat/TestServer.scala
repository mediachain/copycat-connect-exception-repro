package io.mediachain.copycat

import java.io.File
import java.util.function.Supplier

import io.atomix.catalyst.transport.Address
import io.atomix.copycat.server.storage.{Storage, StorageLevel}
import io.atomix.copycat.server.{CopycatServer, StateMachine}


object TestServer {
  def build(address: String, logdir: String)
  : CopycatServer = {

    def stateMachineSupplier() = {
      new Supplier[StateMachine] {
        override def get: StateMachine = {
          new TestStateMachine
        }
      }
    }

    val server = CopycatServer.builder(new Address(address))
      .withStateMachine(stateMachineSupplier())
      .withStorage(Storage.builder()
        .withDirectory(new File(logdir))
        .withStorageLevel(StorageLevel.DISK)
        .build())
      .withTransport(Transport.build(4))
      .build()

    Serializers.register(server.serializer)
    server
  }
}