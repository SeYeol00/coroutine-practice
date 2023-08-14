package com.coroutine.coroutinepractice.cancellationandtimeouts

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


/**
 * Timeout
 * its execution time has exceeded some timeout
 * there is a ready to use withTimeout function
 * CancellationException is considered to be a normal reason for coroutine completion
 * we have used withTimout right inside the main function
 * **/
fun main() = runBlocking{
    withTimeout(1300L){
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
}