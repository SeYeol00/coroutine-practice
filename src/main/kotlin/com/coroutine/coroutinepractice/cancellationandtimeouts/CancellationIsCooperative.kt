package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.*
import java.lang.Exception

/**
 * Coroutine cancellation is cooperative
 * A coroutine code has to 협조적 to be 취소하기 위해
 * suspending functions are 취소 가능함
 * **/
fun main() = runBlocking{
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        // 코틀린은 캔슬 때 익셉션을 던짐 -> JobCancellationException
        try {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {// 단지 CPU를 낭비하는 computation loop
                // 1초에 두 번 메세지 출력
                if (System.currentTimeMillis() >= nextPrintTime) {
                    // suspend function이 있어야 캔슬 가능
                    // 하나라도 실행하지 않으면 캔슬 불가
//                    delay(1L)
                    yield() // suspend 캔슬
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }catch (ex: Exception){
            println("Exception [$ex]")
        }
    }
    delay(1300L) // 조금 딜레이
    println("main: I'm tired of waiting")
    job.cancelAndJoin()
    println("main: Now I can quit.")

}