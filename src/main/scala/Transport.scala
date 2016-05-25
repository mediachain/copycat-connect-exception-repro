import io.atomix.catalyst.transport.{Transport => CopycatTransport, NettyTransport}

object Transport {
  def build(threads: Int): CopycatTransport = {
    NettyTransport.builder().withThreads(threads).build()
  }
}
