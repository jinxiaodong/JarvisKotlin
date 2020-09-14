package com.jarvis.kotlingrammar

/**
 * @author jinxiaodong
 * @description：Kotlin基本语法
 * @date 2020/9/2
 */

fun main() {
//    variableStatement()
    testMethodMemberRef()
    testAnonymousfun()
    testAnonymousfun2()
    testFor()
}

/**
 * 1.类型声明
 * Java： String a = "I am Java";
 * Kotlin: val a :String = "I am Kotlin"
 *
 * why: 可以使得代码的可读性更好。同时，这也有利于使用一些良好的语法特性，比如省略类型声明
 * */
fun variableStatement() {
    val a: String = "I am Kotlin"
    //增强的类型推导
    val string = "I am Kotlin"
    val int = 1314
    val long = 1314L
    val float = 13.14f
    val double = 13.14
    val double2 = 10.1e6
    println(string.javaClass.name)
    println(int.javaClass.name)
    println(long.javaClass.name)
    println(float.javaClass.name)
    println(double.javaClass.name)
    println(double2.javaClass.name)

}

/**
 * 2. fun 关键字声明函数
 *    函数返回值声明
 *
 *    以下情况必须使用显示声明返回类型：
 *         是一个函数的参数
 *         是一个非表达式定义的函数，且返回值不是Unit
 *         是一个递归的函数
 *         是一个公有方法的返回值
 *
 */
//代码块函数体
fun funStatement(x: Int, y: Int): Int {
    return x + y
}

//表达式函数体
fun funStatement1(x: Int, y: Int): Int = x + y

//表达式函数体 下 可以省略返回类型
fun funStatement2(x: Int, y: Int) = x + y

//递归必须显示声明 返回类型
//fun funStatement3(n: Int) = if (n == 0) 1 else n*funStatement3(n-1)
fun funStatement3(n: Int): Int = if (n == 0) 1 else n * funStatement3(n - 1)

/**
 * 高阶函数：
 * 3.函数类型：
 *      ❑ 通过->符号来组织参数类型和返回值类型，左边是参数类型，右边是返回值类型；
 *      ❑ 必须用一个括号来包裹参数类型；
 *      ❑ 返回值类型即使是Unit，也必须显式声明。
 *
 *      无参 ：()->Unit
 *      多参数，用逗号分隔：(Int,String)->Unit
 *      指定参数名称：（errorCode:Int,errMsg:String）->Unit
 *
 */

/**
 * 高阶函数：
 * 4.方法成员引用：
 *       使用双冒号引用对象中的方法  book:: getBookName
 *       可以通过"::"，定义一个类的构造方法的引用变量。
 */
fun testMethodMemberRef() {
    val getBook = ::Book //获取Book构造方法的引用
    var book1 = getBook("Think in Kotlin")
    println(testRef(100, book1::getBookSize))
}

//参数是函数类型的 函数方法
fun testRef(size: Int, test: (String) -> String) = test("$size")

/**
 * 高阶函数：
 * 4.匿名函数
 *      fun(params:String):String{
 *          return "返回参数"
 *      }
 *
 */
fun testAnonymousfun() {
    println("testAnonymousfun" + testRef(101, fun(test: String): String {
        return test
    }))
}

/**
 * 高阶函数：
 * 5. Lambda是语法糖
 *      ❑  fun(test: String)显得比较啰唆，因为编译器会推导类型，所以只需要一个代表变量的test就行了；
 *      ❑ return关键字也可以省略，这里返回的是一个有值的表达式；
 *      ❑ 模仿函数类型的语法，我们可以用->把参数和返回值连接在一起。
 *
 */
fun testAnonymousfun2() {
    println("testAnonymousfun2" +
            testRef(101) { test -> test }
    )
}

fun foo(x: Int): (Int) -> Int = { y: Int -> x + y }


/**
 * for循环
 */
fun testFor() {
    for (i: Char in 'a'..'z') {
        print(i)
    }
}