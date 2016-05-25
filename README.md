### What's this?

This repo demonstrates a failure condition in [copycat][copycat] / [catalyst][catalyst]
that occurs sporadically.  The symptom is an unhandled `java.net.ConnectException`,
which seems to "short-circuit" copycat's reconnect / retry logic.

The exception can be immediately triggered by sending a fairly large
response to a Query (just under 64 kb seems to do the trick).  However, in our
testing, even small payloads will eventually trigger the bug after enough requests
are sent, with the number of requests needed seemingly dependent on the CPU load.

The failure is triggered here by returning a `Block` of a few hundred `Entry`s,
each of which has a 512-byte array of randomly generated data.  Returning the
`Block` from a query kills the client.

### Running

This project is written in scala and uses [sbt][sbt].  Once sbt is installed:

Start the server:
```shell
sbt 'run-main io.mediachain.testapps.RunServer 127.0.0.1:45678 /tmp/copycat-logs'
```

Start the sample client:
```shell
sbt 'run-main io.mediachain.testapps.RunClient 127.0.0.1:45678'
```

After the client connects to the server, it should log that a 'ping' query 
completes successfully, then die with a `ConnectException`

To enable copycat debug logging, you can add `-Dorg.slf4j.simpleLogger.defaultLogLevel=debug`
to the `SBT_OPTS` environment variable before running sbt.

[copycat]: https://github.com/atomix/copycat
[catalyst]: https://github.com/atomix/catalyst
[sbt]: http://www.scala-sbt.org/