package tlstest

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.int
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class Handshake : CliktCommand(name = "tlstest") {

    val host: String by option(help = "Host").required()

    val port: Int  by option(help = "Port").int().required()

    // See sun.security.ssl.Debug
    val debug: String? by option(help = "Enable debug info").choice("all", "ssl")

    override fun run() {
        if (debug != null) {
            System.setProperty("javax.net.debug", debug)
        }
        val factory = SSLSocketFactory.getDefault() as SSLSocketFactory
        TermUi.echo("Connecting to ${host}:${port} ...")

        val socket = factory.createSocket(host, port) as SSLSocket
        try {
            socket.use {
                socket.startHandshake();
            }
            TermUi.echo("Handshake succeeded")
        } catch (e: SSLHandshakeException) {
            TermUi.echo("Handshake failed: ${e.message}", err = true)
        }


    }
}

fun main(args: Array<String>) = Handshake().main(args)