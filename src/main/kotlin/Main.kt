
import com.studiohartman.jamepad.*

//main module for the entire project. Put the stuff you want to run in fun main()

fun main(){
    val controllers = ControllerManager()
    controllers.initSDLGamepad()
    while (true) {
        val currState: ControllerState = controllers.getState(0)
        when {
            currState.a -> {
                println("\"A\" on \"" + currState.controllerType + "\" is pressed")
            }
            currState.b -> {
                println("\"B\" on \"" + currState.controllerType + "\" is pressed")
            }
            currState.x -> {
                println("\"X\" on \"" + currState.controllerType + "\" is pressed")
            }
            currState.y -> {
                println("\"Y\" on \"" + currState.controllerType + "\" is pressed")
            }
        }
        println(currState.leftStickX)
    }
}