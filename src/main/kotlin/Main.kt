import com.studiohartman.jamepad.*


//main module for the entire project. Put the stuff you want to run in fun main()

fun main(){
    val manager = ControllerManager()
    manager.initSDLGamepad()
    val reader = GamepadReader(0, manager)
    val messenger = Messenger()
    val clock = ClockSender(messenger, reader)
    clock.run()

}