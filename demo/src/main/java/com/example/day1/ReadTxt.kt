package com.example.day1

import rx.Observable
import java.io.File


/**
 *
 *
 * @author:lh
 */
fun main(args: Array<String>) {

    val text = File(ClassLoader.getSystemResource("info").path).readText()

    Observable.from(text.toCharArray()
            .asIterable())
            .filter{!it.isWhitespace()}
//            .distinct()
            .groupBy { it }
            .map {
               o-> o.count().subscribe{
                    println("${o.key}->$it")
                }
            }
            .subscribe()


}