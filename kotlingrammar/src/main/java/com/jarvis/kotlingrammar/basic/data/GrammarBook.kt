package com.jarvis.kotlingrammar.basic.data

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/9/7
 */

class GrammarBook constructor(var bookName: String) {


    fun getBookSize(size: String): String = bookName + size


    fun printBookName(): Unit {
        println(bookName)
    }
}