import com.studiohartman.jamepad.ControllerManager
import com.studiohartman.jamepad.ControllerState

/**
 * class for converting gamepad input from a bunch of individual booleans and floats into two MutableLists of them.
 * @author zbrown
 * @param index the index of the controller to be read
 * @param manager the ControllerManager object provided by Jamepad
 */
class GamepadReader(private val index: Int, private val manager: ControllerManager) {

    /**
     * A hashmap of name to bool/float for buttons and axises
     */
    val data = HashMap<String, Any>()
    /**
     * controller state variable. I put it in this scope to try and prevent wasting memory, but this might not actually help
     */
    private var state: ControllerState = manager.getState(index)

    /**
     * updates the controller state. just a nice little wrapper.
     */
    private fun updateState(){
        state = manager.getState(index)
    }

    /**
     * updates the state of all the data on the gamepad. call this before reading the contents of data or axes
     */
    fun update(){
        val data = HashMap<String, Any>()
        //get the latest state
        updateState()

        //it hasn't been run, so do this
        //update all the data
        data["a"] = state.a
        data["b"] = state.b
        data["x"] = state.x
        data["y"] = state.y
        data["up"] = state.dpadUp
        data["down"] = state.dpadDown
        data["left"] = state.dpadLeft
        data["right"] = state.dpadRight
        data["leftBumper"] = state.lb
        data["rightBumper"] = state.rb
        data["back"] = state.back
        data["start"] = state.start
        data["guide"] = state.guide
        data["leftStickButton"] = state.leftStickJustClicked
        data["leftStickButton"] = state.rightStickJustClicked
        //update all the axes
        data["leftX"] = state.leftStickX
        data["leftY"] = state.leftStickY
        data["rightX"] = state.rightStickX
        data["rightY"] = state.rightStickY
        data["leftTrigger"] = state.leftTrigger
        data["rightTrigger"] = state.rightTrigger
    }
}

/*
buttons = ['a', 'x', 'y', 'b',
           'rightBumper', 'leftBumper', 'leftStickButton', 'rightStickButton',
           'guide', 'back', 'start'
           'up', 'down', 'left', 'right'
        ]
axes = [
    "leftX", "leftY", "rightX", "rightY",
    "leftTrigger", "rightTrigger"
]
 */