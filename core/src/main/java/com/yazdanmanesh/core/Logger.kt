package com.yazdanmanesh.core

class Logger(
    private val tag:String,
    private val isDebug:Boolean = true,
) {
    fun log(msg:String){
        if (!isDebug){
            //Production logging
        }else
        {
            printLogD(tag,msg)
        }
    }
companion object Factory{
    fun buildRelease(tag:String):Logger{
        return Logger(
            tag = tag,
            isDebug = false
        )
    }

    fun buildDebug(tag:String):Logger{
        return Logger(
            tag = tag,
            isDebug = true
        )
    }
}

}
private fun printLogD(tag: String, msg: String) {
    println("$tag: $msg")
}