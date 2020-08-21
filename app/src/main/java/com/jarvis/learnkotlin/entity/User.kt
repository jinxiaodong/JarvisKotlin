package com.jarvis.learnkotlin.entity

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 *
 *
 * @JvmField 注解让编译器生成一个公开的成员变量
 */
data class User constructor(var username: String?, var password: String?, var code: String?) {
    constructor() : this(null, null, null)

}