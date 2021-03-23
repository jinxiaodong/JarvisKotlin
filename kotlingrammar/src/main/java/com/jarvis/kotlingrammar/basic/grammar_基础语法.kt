package com.jarvis.kotlingrammar.basic

import com.jarvis.kotlingrammar.basic.data.Country
import com.jarvis.kotlingrammar.basic.data.CountryApp
import com.jarvis.kotlingrammar.basic.data.CountryTest
import com.jarvis.kotlingrammar.basic.data.GrammarBook

/**
 * @author jinxiaodong
 * @description：Kotlin基本语法
 * @date 2020/9/2
 */

fun main(args: Array<String>) {
    val a: Int = 128
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    print(boxedA === anotherBoxedA) // !!!Prints 'false'!!!
}

//fun main() {
//
////    val bookNames= listOf(
////        GrammarBook("123"),
////        GrammarBook("246")
////    ).map(GrammarBook::bookName)
////    println(bookNames)
////
//
////    variableStatement()
////    testMethodMemberRef()
////    testAnonymousfun()
////    testAnonymousfun2()
////    testFor()
//
//
//
//}


/**
 * 1 类型声明
 * Java： String a = "I am Java";
 * Kotlin: val a :String = "I am Kotlin"
 *
 * why: 可以使得代码的可读性更好。同时，这也有利于使用一些良好的语法特性，比如省略类型声明（kotlin支持类型推导）
 *
 * 1.1 增强的类型推导
 * 编译器可以在不显示声明类型的情况下，自动推导出它所需要的类型
 * */
fun variableStatement() {
    val a: String = "I am Kotlin"
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
 * 1.2 声明函数返回值类型
 *
 *  fun 关键字声明函数，与声明变量一样，返回类型信息放在函数名后面，
 *
 *    fun sum(x: Int, y: Int): Int {
 *          return x+y
 *    }
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
fun funStatement3(n: Int): Int = if (n == 0) 1 else n * funStatement3(
    n - 1
)

/**
 * 2 val 和 var 的使用规则
 *
 *      如果 var 代表 varible（变量） val 可看成 value（值）的缩写，但是val更合适解释成varible+final （因为val语法反编译后转化的
 *      Java代码实用final来实现这个特性的）。
 *
 *      val的含义：
 *          引用不可变。val声明的变量是只读变量，它的引用不可更改，但并不代表其引用对象也不可变。看Book例子。
 *
 *          尽可能采用val、不可变对象及纯函数来设计程序。ps：纯函数就是没有副作用的函数，具备应用透明性，第10章中学习
 *          Kotlin支持一开始不定义val变量的取值，随后再进行赋值，但是只能被赋值一次。
 *
 *          思考：如何保证引用对象的不可变性？ 只读集合中有思路
 *
 *      什么时候使用var：
 *          var有更好的性能，占用更少的内存，针对数据结构，可能再业务中需要存储大量的数据时，采用var是更合适的方案。而且代码会简单，易于理解。
 *
 *
 */

fun textVal(): Unit {
    val x = intArrayOf(1, 2, 3)
//    x = intArrayOf(2,3.4) //Val cannot be reassigned
    x[0] = 4
    println(x[0])
    //因为引用不可变，所以x不能指向另一个数组，但是我们可以修改x指向的数组中的值
}

fun textVal2(): Unit {
    val book =
        GrammarBook("Thinking in Kotlin")//val 声明，book 引用不可变，但是可一修改book对象。
    book.bookName = "Thinking in Java"
    book.printBookName()
}


/**
 * 3 高阶函数和lambda：
 *      3.1 Kotlin 支持部分函数式特性，函数式语言一个典型特性就是-函数是头等公民，我们可以：
 *          像类一样再顶层定义一个函数，
 *          也可以再函数内部定义一个局部函数，
 *          可以将函数像普通变量一样传递给另一个函数，也可以有其他函数返回
 *
 *      3.2 什么是高阶函数：
 *          接受一个或多个过程为参数；或者以一个过程作为返回结果。也可以理解成"以其它函数作为参数或返回值的函数"
 *
 *      3.3 函数的类型：
 *          ❑ 通过->符号来组织参数类型和返回值类型，左边是参数类型，右边是返回值类型；
 *          ❑ 必须用一个括号来包裹参数类型；
 *          ❑ 返回值类型即使是Unit，也必须显式声明。
 *
 *          无参 ：()->Unit
 *          多参数，用逗号分隔：(Int,String)->Unit
 *          指定参数名称：（errorCode:Int,errMsg:String）->Unit
 *
 *      3.4 方法和成员引用：
 *          可以通过"::",来实现对于某个类的方法/成员变量进行引用
 *          可以通过"::"，定义一个类的构造方法的引用变量。
 *          countryTest::isBigEuropeanCountry
 *
 *      3.5 匿名函数：
 *          fun(params:String):String{
 *              return "返回参数"
 *          }
 *
 *      3.6 Lambda 语法糖
 *          ❑  fun(test: String)显得比较啰唆，因为编译器会推导类型，所以只需要一个代表变量的test就行了；
 *          ❑ return关键字也可以省略，这里返回的是一个有值的表达式；
 *          ❑ 模仿函数类型的语法，我们可以用->把参数和返回值连接在一起。
 *
 */
fun testMethodMember() {
    val countryApp = CountryApp()
    val countryTest = CountryTest()

    val countries = listOf(Country("1", "2", 3))
    countryApp.filterCountries(countries, countryTest::isBigEuropeanCountry)


    //使用匿名函数
    countryApp.filterCountries(countries, fun(country: Country): Boolean {
        return country.continient == "EU" && country.population > 10000
    })

    //使用lambda表达式
    countryApp.filterCountries(
        countries,
        { country -> country.continient == "EU" && country.population > 10000 })

    //柯里化效果的 调用
    countryApp.filterCountries(countries) { country -> country.continient == "EU" && country.population > 10000 }

}

/**
 *
 * 4.面向表达式编程
 *      什么是表达式？通俗的理解 表达式就是可以返回值的语句
 *
 *      4.1 表达式比语句更安全
 *          避免副作用代码的产生
 *
 *      4.2 Unit类型，让函数调用皆为表达式
 *        Java因为有void关键字，所以存在函数无返回值情况，
 *        Kotlin中 Unit类型也是一个值类型，所以所有函数都存在返回值
 *
 *      4.3 复合表达式：更好的表达力
 *        表达式具备更好的隔离性，更容易进行组合
 *
 *
 *
 * */
