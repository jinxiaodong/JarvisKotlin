package com.jarvis.kotlingrammar.headfirst

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/9/14
 */

var quit = false
fun main() {
//    game1()

    val options = arrayOf("Rock", "Paper", "Scissors")
    while (!quit) {
        printChoiceResult(getUserChoice(options), getGameChoice(options))
    }
}


fun getUserChoice(array: Array<String>): String {
    var userChoice = ""
    var isValidChoice = false
    while (!isValidChoice && !quit) {
        println("Please enter one of thefollowing：")
        for (item in array)
            println("           $item")
        val userInput = readLine()
        if (userInput != null && userInput in array) {
            userChoice = userInput
            isValidChoice = true
        } else if (userInput == "quit") {
            quit = true
        }
        if (!isValidChoice && !quit) {
            println("You must enter a valid choice")
        }
    }

    return userChoice
}

fun getGameChoice(array: Array<String>): String {

    return array[(Math.random() * array.size).toInt()]
}


fun printChoiceResult(gameChoice: String, userChoice: String) {
    if (quit) {
        return
    }
    var result: String
    if (userChoice == gameChoice) {
        result = "Tie"
    } else if ((userChoice == "Rock" && gameChoice == "Scissors")
        || (userChoice == "Paper" && gameChoice == "Rock")
        || (userChoice == "Scissors" && gameChoice == "Paper")
    ) {
        result = "You Win!"
    } else {
        result = "You lose!"
    }

    println("You chose $userChoice. I chose $gameChoice. $result")
}

private fun game1() {
    val wordArray1 = arrayOf("24/7", "multi-tier", "B-to-B", "dynamic", "pervasive")
    val wordArray2 = arrayOf("empowered", "leveraged", "aligned", "targeted")
    val wordArray3 = arrayOf("process", "paradigm", "solution", "portal", "vision")

    val arraySize1 = wordArray1.size
    val arraySize2 = wordArray2.size
    val arraySize3 = wordArray3.size

    val rand1 = (Math.random() * arraySize1).toInt()
    val rand2 = (Math.random() * arraySize2).toInt()
    val rand3 = (Math.random() * arraySize3).toInt()

    val phrase = "${wordArray1[rand1]} ${wordArray2[rand2]} ${wordArray3[rand3]}"

    println(phrase)
}
