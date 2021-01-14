import com.studiohartman.jamepad.*
import GamepadReader


//main module for the entire project. Put the stuff you want to run in fun main()

fun main(){
    val manager = ControllerManager()
    manager.initSDLGamepad()
    val reader = GamepadReader(0, manager)
    while(true) {
        reader.update()
        println(reader.axes)
        println(reader.buttons)
        Thread.sleep(100)
    }
}