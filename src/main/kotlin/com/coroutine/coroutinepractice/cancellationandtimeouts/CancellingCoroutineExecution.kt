package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Cancelling coroutine execution
 * 돌아가는 코루틴은 취소할 수 있는 job
 * **/
fun main() = runBlocking{
    val job = launch {// 반환된 코루틴 객체
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 조금 딜레이
    println("main: I'm tired of waiting!")
    job.cancel() // 메인 스레드에서 job 취소
    job.join() // job이 끝날 때까지 기다리기
    println("main: Now I can quit")
}