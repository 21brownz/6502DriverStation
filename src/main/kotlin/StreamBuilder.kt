import java.lang.IllegalArgumentException
import java.nio.ByteBuffer

/**
 * Class to convert joystick and button input into a serialized bytestream to be recieved by the microcontroller
 * @author zbrown
 */
class StreamBuilder {

    /**
     * the stream being built by the class
     */
    var stream = mutableListOf<Byte>()

    /**
     * builds the button and axis stream.
     * @author zbrown
     */
    fun buildStream(buttons: MutableList<Boolean>, axes: MutableList<Float>){
        stream.addAll(floatToByte(axes[0])) //left stick x
        stream.addAll(floatToByte(axes[1])) //left stick y
        stream.addAll(floatToByte(axes[2])) //right stick x
        stream.addAll(floatToByte(axes[3])) //right stick y
        stream.add(boolToByte(buttons.subList(0,7))) //abxy and d pad
        stream.add(boolToByte(buttons.subList(8,15))) //other buttons
    }

    /**
     * function to convert 8 booleans into a single byte
     * @return Byte that represents 8 booleans
     * @author zbrown
     * @throws IllegalArgumentException if more than 8 booleans are given
     */
    private fun boolToByte(bool: List<Boolean>): Byte {
        if(bool.size>8){
            throw IllegalArgumentException("Input List Too Large!")
        }
        return ((if (bool[0]) 1 shl 7 else 0) + (if (bool[1]) 1 shl 6 else 0) + (if (bool[2]) 1 shl 5 else 0) +
                (if (bool[3]) 1 shl 4 else 0) + (if (bool[4]) 1 shl 3 else 0) + (if (bool[5]) 1 shl 2 else 0) +
                (if (bool[6]) 1 shl 1 else 0) + if (bool[7]) 1 else 0).toByte()
    }

    /**
     * function that converts float to a list of it's four constituent bytes
     * @return list of bytes that represents the float
     * @author zbrown
     */
    private fun floatToByte(float: Float): List<Byte> {
        val buffer = ByteBuffer.allocate(4).putFloat(float).array()
        val list = mutableListOf<Byte>()
        for (value in buffer){
            list.add(value)
        }
        return list
    }
}