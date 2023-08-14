package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.*


/**
 * Run non-cancellable bloc
 * in the rare case
 * when you need to suspend in a cancelled coroutine
 * **/
fun main() = runBlocking{
    val job = launch{
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }finally {// 리소스 해제 위치
            withContext(NonCancellable){
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // 조금 딜레이
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit")
}