package com.jarvis.kotlingrammar.headfirst

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/9/14
 */

fun main(args: Array<String>) {
//    println("Hello Kotlin")

//    whileMethod()
    IfElseMethod()
}

//while 循环
private fun whileMethod() {
    var x = 1
    println("Before the loop. x = $x")
    while (x < 4) {
        println("In the loop. x = $x")
        x++
    }
    println("After the loop. x = $x")
}

//if else 条件分支

private fun IfElseMethod() {

    val x = 3
    val y = 1
    println(if (x > y) "x is greater than y" else "x is no greater than y")

    var res = listOf<Int>()

    val mutableListOf = mutableListOf<Int>()
}

class TestList() {

    fun start() {
        val list = mutableListOf<Int>()


    }

    fun add(list: MutableList<Int>) {
        list.add(1)
    }
}