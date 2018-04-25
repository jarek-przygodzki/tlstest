# tlstest
A tool to test TLS/SSL connections to servers using JVM's own trust store

The JRE comes with it's own set of trusted root authorities, it does not use trusted certificates from the OS.  It's a problem, because many companies use their own PKI.

If application attempts to communicate via TLS/SSL to a remote host that does not have a trusted chain of security it  will get the all too famous `SSLHandshakeException: PKIX path building failed` exception. This tool is created to help diagnose this kind of issues.

```
$ gradle build

$ java -jar build\libs\tlstest-1.0-SNAPSHOT.jar --help
Usage: tlstest [OPTIONS]

Options:
  --host TEXT        Host
  --port INT         Port
  --debug [all|ssl]  Enable debug info
  -h, --help         Show this message and exit

```
