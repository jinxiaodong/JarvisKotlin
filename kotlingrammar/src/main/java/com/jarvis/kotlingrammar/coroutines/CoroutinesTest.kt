package com.jarvis.kotlingrammar.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.suspendCoroutine

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/9/23
 */

fun main(args: Array<String>) {
    println("Coroutines Camp:${Thread.currentThread().name}")
    GlobalScope.launch {
        test()
    }
    Thread.sleep(2000L)
}

suspend fun test(){
    println("Coroutines Camp:${Thread.currentThread().name}")

}
