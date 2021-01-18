
class ClockSender(messenger: Messenger, gamepadReader: GamepadReader) : Thread() {
    private val gamepad = gamepadReader
    private val messenger = messenger
    private var running = true;  // set to false to end loop
    private val pauseTime = 100;  // millisecond wait time between sends
    val data = HashMap<String, Any>()  // data that is sent

    fun end() {running=false}

    override fun run() {
        while (running) {
            sleep(pauseTime.toLong())
            messenger.send(data)
            gamepad.update()
            messenger.send(gamepad.data)
        }
    }
}
