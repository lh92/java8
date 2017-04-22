package com.example.day1

/**
 *a_b_c_d_e_f_h_i_j
 *
 * @author:lh
 */
//fun main(args:Array<out String>){

fun main(vararg args: String) {
//    args.map(::println)


    args.flatMap {
        it.split("_")
    }.map { print("$it") }


//    print("hello world")
//
//    print(Main(20,"liuhuan"))
}

data class Main(val age: Int, val name: String)