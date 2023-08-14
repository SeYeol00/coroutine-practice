package com.coroutine.coroutinepractice.composingsuspendingfunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/**
 * Concurrent using async
 * what if there are no dependencies between invocations
 * we want to get the answer faster, by doing both concurrently?
 * This is twice as fast, because the two coroutines execute concurrently
 * Note that concurrency with coroutines is always explicit
 * **/
fun main() = runBlocking<Unit>{
    val time = measureTimeMillis {
        // async 키워드
        // 두 연산이 동시에 돌아감
        val one = async { doSomethingUsefulOne2() }
        val oneAwait = one.await() // 이러면 one이 끝날 때 까지 아래가 안 돌아감
        val two = async { doSomethingUsefulTwo2() }
        //                      async를 달았으면 await를 걸어줘서 기다려야 한다.
        println("The answer is ${oneAwait + two.await()}")
    }
    println("Completed in $time ms")
}
suspend fun doSomethingUsefulTwo2(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here
    return 29
}

suspend fun doSomethingUsefulOne2(): Int {
    println("doSomethingUsefulOne")
    delay(1000L) // pretend we are doing something useful here, too
    return 13
}