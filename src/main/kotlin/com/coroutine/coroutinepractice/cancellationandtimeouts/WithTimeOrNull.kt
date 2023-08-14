package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking{
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // 이 결과를 출력하기 전에 캔슬될 것
    }
    println("Result is $result")
}