package com.jarvis.kotlingrammar.arithmetic.swordoffer

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2/26/21
 */

/**
 * 1. 赋值运算函数
 * 思路：
 * • 将返回值类型声明为该类型的引用
 * • 把传入的参数类型声明为常量引用
 * • 释放实例自身已有的内存
 * • 判断传入的参数和当前的实例是不是同一个实例
 * 代码实现：略
 * */


/**
 * 2. 单例设计模式
 * 题目描述：设计一个类，只能生成该类的一个实例。
 * 思路：非线程安全与线程安全
 *
 *  Java 实现：
 *
 *  一 、 饿汉式
 *  class SingletonDemo {
 *      private static SingletonDemo instance = new SingletonDemo();
 *
 *      private SingletonDemo() {}
 *
 *      public static SingletonDemo getInstance() {
 *          return instance;
 *      }
 *  }
 *
 * 二、懒汉式
 *  public class SingletonDemo {
 *      private static SingletonDemo instance;
 *      public static SingletonDemo getInstance() {
 *          if (instance == null) {
 *              instance = new SingletonDemo();
 *          }
 *          return instance;
 *      }
 *      private SingletonDemo() {}
 * }
 *
 * 双重校验：
 *  public class SingletonDemo {
 *      private static SingletonDemo instance;
 *      public static  SingletonDemo getInstance() {
 *      if (instance == null) {
 *          synchronized(SingletonDemo.class){
 *              if(instance == null) {
 *                  instance = new SingletonDemo();
 *              }
 *          }
 *      }
 *          return instance;
 *    }
 *      private SingletonDemo() {}
 * }
 *
 * 三、静态内部类
 *
 *     public class SingletonDemo {
 *          private static class SingletonDemoHolder {
 *              private static SingletonDemo instance = new SingletonDemo();
 *          }
 *          private SingletonDemo() {}
 *          private static SingletonDemo getInstance() {
 *                return SingletonDemoHolder.instance;
 *          }
 *     }
 *
 * */

//饿汉式
object SingletonDemoKt

//懒汉式
class SingletonDemoKt2 private constructor() {

    companion object {
        private var instance: SingletonDemoKt2? = null
            get() {
                if (field == null) {
                    field = SingletonDemoKt2()
                }
                return field
            }

        //使用@Synchronized 注解，声明方法为同步方法
        @Synchronized
        fun get(): SingletonDemoKt2 {
            return instance!!
        }
    }
}

//懒汉式2 双重校验
class SingletonDemoKt3 private constructor() {
    companion object {
        val instance: SingletonDemoKt3 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemoKt3()
        }
    }
}

//静态内部类
class SingletonDemoKt4 private constructor() {

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = SingletonDemoKt4
    }
}


/**
 *
 * 3. 二维数组中查找目标值
 * 题目描述：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列
 * 都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一
 * 个整数，判断数组中是否含有该整数。
 * 思路：从右上角或左下角开始找，逐行排除，或者用二分法查找
 * 代码实现：
 * • 解法一：双指针，时间复杂度：O(mn)，空间复杂度：O(1)
 * • 解法二：二分法，时间复杂度：O(log mn)，空间复杂度：O(1)
 * */
fun findInt1(array: Array<IntArray>?, target: Int): Boolean {
    if (array == null || array.isEmpty()) {
        return false
    }
    var row = 0
    var column = array[row].size - 1
    while (row < array.size && column >= 0) {
        when {
            array[row][column] == target -> return true

            array[row][column] > target -> column--

            else -> row++
        }
    }
    return false
}

fun findInt2(array: Array<IntArray>?, target: Int): Boolean {
    if (array == null || array.isEmpty()) {
        return false
    }
    var left = 0
    var right = array.size * array[0].size - 1
    val col = array[0].size

    while (left < right) {
        var mid = (left + right) / 2
        var value = array[mid / col][mid % col]
        when {
            value == target -> return true
            value > target -> right = mid - 1
            else -> left = mid + 1
        }

    }


    return false
}
