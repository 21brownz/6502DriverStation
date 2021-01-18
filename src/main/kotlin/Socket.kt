import java.net.Socket
import java.io.*
//import java.nio.charset.StandardCharsets
import net.razorvine.pickle.Unpickler;
import net.razorvine.pickle.Pickler
import java.net.ServerSocket

class Messenger {
    /**
     * Java -> python websocket wrapper to send and receive Hashmaps<String, Any>
     *
     * Message Protocol:
     * All messages be a dictionary / HashMap with an entry being "id"

    Controller info:
    -id=Controller{n} (ie Controller1) -> input name, new input value
    Dashboard:
    -id=DashboardCreate -> unit name, [unit type, unit location info]  # todo: check this
    -id=DashboardUpdate -> name, value
    Serial:
    -id=msg -> "value", string message
     */
    private var server: ServerSocket ?= null
    private var socket: Socket ?= null
    private val pickler: Pickler = Pickler()
    private var input: inputThread ?= null

    fun send(obj: HashMap<String, Any>) {
        val pickled = pickler.dumps(obj)
        socket!!.getOutputStream().write(pickled)
    }

    fun receive(data: Any) {  // this get called by the input thread
        println("data: ${data.toString()}")
        val dict = data as Map<*, *>
        println(dict["id"])  // process the information
    }

     fun connect(ip: String, port: Int) {
        try {
            socket = Socket(ip, port)
            input = inputThread(socket!!, this)
            input!!.start()
        } catch (u: IOException) {
            println(u.toString())
            close()
        }
    }

    fun host(ip: String, port: Int) {
        server = ServerSocket(port)
        socket = server!!.accept()
    }

    fun close() {
        if (socket != null) {
            socket!!.close()
            socket!!.shutdownInput()
            socket!!.shutdownOutput()
        }
        input!!.running = false
    }
}


internal class inputThread(s: Socket, main: Messenger) : Thread() {
    var running = true
    private val input: InputStream = s.getInputStream()
    private val mainMessenger = main;
    private val unpickler = Unpickler()

    override fun run() {
        while (running) {
            try {
                if (input.available() > 0) {
                    val raw = input.readNBytes(input.available())
                    val data = unpickler.loads(raw)
                    mainMessenger.receive(data)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                running = false
                mainMessenger.close()
            }
        }
    }
}


fun main() {
    val m = Messenger()
    m.connect("localhost", 1235)
    var message: String
    do {
        message = readLine().toString()
        val map = HashMap<String, Any>()
        map["msg"] = message
        m.send(map)
    } while(message != "q")
    m.close()
}

/*
Things to send:
- enable / disable
- 14 bools per remote
- 6 floats per remote
- one extra port to handle raw data
 */