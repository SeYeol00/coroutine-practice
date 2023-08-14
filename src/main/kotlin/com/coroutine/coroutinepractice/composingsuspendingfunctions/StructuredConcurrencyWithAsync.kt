package com.coroutine.coroutinepractice.composingsuspendingfunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/**
 * Structured concurrency with async
 * 코틀린 권장 사항, 이렇게 쓰라고 명시함
 * This way, if throws an exceptions, all the coroutines will be cancelled
 * 중간에 익셉션이 터지면 종료가 된다.
 * **/
fun main() = runBlocking{
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")

}

suspend fun concurrentSum(): Int = coroutineScope{// structured concurrency 형태로 함수 만들기
    val one = async { doSomethingUsefulOne5() }
    val two = async { doSomethingUsefulTwo5() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulTwo5(): Int {
    println("start > doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here
    println("end > doSomethingUsefulTwo")
    return 29
}

suspend fun doSomethingUsefulOne5(): Int {
    println("start > doSomethingUsefulOne")
    delay(1000L) // pretend we are doing something useful here, too
    println("end > doSomethingUsefulOne")
    return 13
}