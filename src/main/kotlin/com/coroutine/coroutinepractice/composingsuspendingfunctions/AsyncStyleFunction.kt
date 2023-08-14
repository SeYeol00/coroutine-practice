package com.coroutine.coroutinepractice.composingsuspendingfunctions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Async-style functions
 * 이렇게 쓰지 말라고 코틀린에서 예시로 든 것
 * We can define async-style functions
 * xxxAsync functions are not suspending functions
 * They can be used from anywhere
 * Using this style with Kotlin coroutines is strongly DISCOURAGED
 * This problem does not happen with structured concurrency
 * 익셉션이 발생 했을 때 돌이킬 수 없는 상황이 일어남
 * **/


fun main(){
    val time = measureTimeMillis {
        // 일반 함수인 것임
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}
fun somethingUsefulOneAsync() = GlobalScope.async { doSomethingUsefulOne4() }
fun somethingUsefulTwoAsync() = GlobalScope.async { doSomethingUsefulTwo4() }

suspend fun doSomethingUsefulTwo4(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here
    return 29
}

suspend fun doSomethingUsefulOne4(): Int {
    println("doSomethingUsefulOne")
    delay(1000L) // pretend we are doing something useful here, too
    return 13
}