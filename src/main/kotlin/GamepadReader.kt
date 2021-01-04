import com.studiohartman.jamepad.ControllerManager
import com.studiohartman.jamepad.ControllerState

class GamepadReader(private val index: Int, private val manager: ControllerManager) {


    /**
     * mutable list containing the state of all the buttons on a gamepad
     */
    var buttons = mutableListOf<Boolean>()

    /**
     * mutable list of axes on the controller
     */
    var axes = mutableListOf<Float>()

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

    fun update(){
        //get the latest state
        updateState()

        if(buttons.size == 0){
            buttons.add(state.a)
            buttons.add(state.b)
            buttons.add(state.x)
            buttons.add(state.y)
            buttons.add(state.dpadUp)
            buttons.add(state.dpadDown)
            buttons.add(state.dpadLeft)
            buttons.add(state.dpadRight)
            buttons.add(state.lb)
            buttons.add(state.rb)
            buttons.add(state.back)
            buttons.add(state.start)
            buttons.add(state.guide)
            buttons.add(state.leftStickJustClicked)
            buttons.add(state.rightStickJustClicked)
            //update all the axes
            axes.add(state.leftStickX)
            axes.add(state.leftStickY)
            axes.add(state.rightStickX)
            axes.add(state.rightStickY)
            axes.add(state.leftTrigger)
            axes.add(state.rightTrigger)
        }
        //update all the buttons
        buttons[0] = state.a
        buttons[1] = state.b
        buttons[2] = state.x
        buttons[3] = state.y
        buttons[4] = state.dpadUp
        buttons[5] = state.dpadDown
        buttons[6] = state.dpadLeft
        buttons[7] = state.dpadRight
        buttons[8] = state.lb
        buttons[9] = state.rb
        buttons[10] = state.back
        buttons[11] = state.start
        buttons[12] = state.guide
        buttons[13] = state.leftStickJustClicked
        buttons[14] = state.rightStickJustClicked
        //update all the axes
        axes[0] = state.leftStickX
        axes[1] = state.leftStickY
        axes[2] = state.rightStickX
        axes[3] = state.rightStickY
        axes[4] = state.leftTrigger
        axes[5] = state.rightTrigger
    }
}