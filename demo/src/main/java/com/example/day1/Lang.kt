package com.example.day1


/**
 *
 *
 * @author:lh
 */
enum class Lang(val hello:String) {
    ENGLISH("hello"),
    CHINESE("你好"),
    JAPANESE("ssss"),
    KOREAN("aaaa");

    fun sayHello(){
        print(hello)
    }

    init {

    }

    companion object{
        fun parse(name:String):Lang{
            return Lang.valueOf(name.toUpperCase())
        }
    }
}

fun main(args: Array<String>) {

    val lang =Lang.parse(args[0])
    print(lang)
    lang.sayHello()
    lang.sayBye()

}

fun Lang.sayBye(){

    val bye =when(this){
        Lang.ENGLISH->"byebye"
        Lang.CHINESE->"再见"
        else -> {
            "日韩"
        }
    }
    print(bye)
}