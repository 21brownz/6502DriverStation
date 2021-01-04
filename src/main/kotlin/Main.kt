

//main module for the entire project. Put the stuff you want to run in fun main()

fun main(){
    val builder = StreamBuilder()
    val bool = mutableListOf(false, false, true, false, true, false, true, false)
    println(builder.boolToByte(bool))
}