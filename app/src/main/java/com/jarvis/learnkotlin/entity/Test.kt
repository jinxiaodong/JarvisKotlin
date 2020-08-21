package com.jarvis.learnkotlin.entity


/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */

var user = User("aa", "bb", "cc")
var userCopy = user.copy()

data class Response(var code: Int, var message: String, var body: User)
//class Response(var code: Int, var message: String, var body: User) {
//    operator fun component1(): Int {
//        return code
//    }
//
//    operator fun component2(): String {
//        return message
//    }
//
//    operator fun component3(): User {
//        return body
//    }
//}

fun main() {
//    println(user)
//    println(userCopy)
//    // == 调用equals
//    println(user == userCopy)
//    println(user === userCopy)


//    val execute = execute()
//    val body1 = execute.body
//    val code1 = execute.code
//    val message1 = execute.message
//    //解构
//    val (code2, message2, body2) = execute()
//    println(body2)
//    println(code2)
//    println(message2)
//
//    println(body1)
//    println(code1)
//    println(message1)

    //循环
//    repeat(100) {
//        println(it)
//    }
//
//    for (i in 0..99){
//        println(i)
//    }
//    val array = intArrayOf(1, 2, 3, 4)
//    for (i in 0.until(array.size)) {
//        println(i)
//    }
//    for (i in array.indices){
//        println(i)
//    }

    fieldTest(::Test)
}


fun execute(): Response {

    println("正在请求网络")
    println("请求网络成功")

    val code = 200
    val message = "ok"
    val user = User("aa", "bb", "cc")

    return Response(code, message, user)

}


fun Test(ss: String) {
    println(ss)
}

//内联函数配合「函数类型」，可以减少「函数类型」生成的对象
inline fun fieldTest(test: (String) -> Unit) {
    test("ss")
}