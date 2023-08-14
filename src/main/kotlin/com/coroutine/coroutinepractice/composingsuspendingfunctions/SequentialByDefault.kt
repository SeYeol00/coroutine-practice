package com.coroutine.coroutinepractice.composingsuspendingfunctions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Sequential by default
 * 코루틴은 기본적으로 다른 일반 코드와 마찬가지로 시퀀셜이다.
 * 비동기를 순차적으로 적기만 하면 알아서 순차적으로 만들어 준다.
 * 비동기로 실행되는 코드를 간단화하기
 * 비동기 콜백을 순차적 코드로 변황
 * suspend 함수를 써서 비동기 코드를 순차적으로 만들어준다.
 * **/
fun main() = runBlocking<Unit>{
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")
}

suspend fun doSomethingUsefulTwo(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here
    return 29
}

suspend fun doSomethingUsefulOne(): Int {
    println("doSomethingUsefulOne")
    delay(1000L) // pretend we are doing something useful here, too
    return 13
}
