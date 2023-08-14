package com.coroutine.coroutinepractice.composingsuspendingfunctions

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Cancellation propagated coroutines hierarchy
 * Note how both the first async and the awaiting prarent ate cancelled on failure od one of the children(namely, two):
 * 안에 있는 코루틴이 exception이 일어나면 모든 코루틴이 캔슬된다.
 *
 * **/


fun main() = runBlocking<Unit>{
    try {
        failedConcurrentSum()
    }catch (ex: ArithmeticException){
        println("Computation failed with ArithmeticException")
    }
}

suspend fun failedConcurrentSum():Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // 아주 길게 계산
            42
        }finally {
            println("First child was cancelled")
        }
    }
    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }
    one.await() + two.await()
}