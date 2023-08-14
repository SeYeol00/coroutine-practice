package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.*


/**
 * Making Computation code cancellable
 * 방법 1 : 주기적으로 정지 명령을 발동하기
 * 방법 2 : 취소 상태를 명시적으로 확인(isActive)
 * 이 방법은 익셉션을 던지지 않음
 */
fun main() = runBlocking{
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        println("isActive = $isActive")
        while (isActive) // cancellation computation loop
        {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
        println("isActive = $isActive")
    }
    delay(1300L) // 조금 딜레이
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // job 캔슬 및 완료까지 기다림
    println("main: Now I can quit.")
}