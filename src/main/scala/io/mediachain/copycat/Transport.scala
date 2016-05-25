package io.mediachain.copycat

import io.atomix.catalyst.transport.{NettyTransport, Transport => CopycatTransport}

object Transport {
  def build(threads: Int): CopycatTransport = {
    NettyTransport.builder().withThreads(threads).build()
  }
}
