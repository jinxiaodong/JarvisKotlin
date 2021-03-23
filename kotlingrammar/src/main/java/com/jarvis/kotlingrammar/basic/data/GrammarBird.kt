package com.jarvis.kotlingrammar.basic.data

/**
 * @author jinxiaodong
 * @description：Kotlin中的类
 * @date 2020/9/10
 */
class GrammarBird {

    val weight: Double = 500.0
    val color: String = "blue"
    val age: Int = 1

    fun fly(): Unit {

    }

}


interface Flyer {

    val speed: Int

    val height
        get() = 1000

    fun kind()

    fun fly(): Unit {
        println("I can fly")
    }

}