package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Closing resources with finally
 * 캔슬 가능한 suspend function은 Cancellation Exception을 던진다.
 * try{...} finally{...}
 * **/
fun main() = runBlocking{
    val job = launch{
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }finally {// 리소스 해제 위치
            println("job: I'm running finally")
        }
    }
    delay(1300L) // 조금 딜레이
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit")
}