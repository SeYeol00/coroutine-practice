package com.coroutine.coroutinepractice.composingsuspendingfunctions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Lazily started async
 * Optionally, async can be made lazy by setting its start parameter
 * its result is required by await, or if its Job's start function is invoked
 * if we just call await in println without first calling start
 * **/
fun main() = runBlocking<Unit>{
    val time = measureTimeMillis {
        // async 키워드
        // 두 연산이 동시에 돌아감
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne3() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo3() }
        // some computation, start를 안 걸어주면 시작 안 한다.
        one.start() // start the first one
        two.start() // start the second one

        // start를 하지 않으면 순차적으로 실행한다. 애초에 async가 걸리지 않은 것
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}
suspend fun doSomethingUsefulTwo3(): Int {
    println("doSomethingUsefulTwo")
    delay(1000L) // pretend we are doing something useful here
    return 29
}

suspend fun doSomethingUsefulOne3(): Int {
    println("doSomethingUsefulOne")
    delay(1000L) // pretend we are doing something useful here, too
    return 13
}