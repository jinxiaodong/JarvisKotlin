package com.jarvis.kotlingrammar

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/9/7
 */

class Book constructor(var bookName: String) {



    fun getBookSize(size:String): String = bookName + size

}