package com.coroutine.coroutinepractice.androidexample

import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis


    suspend fun runDreamCode(){
        val time = measureTimeMillis {
            val one = doSomethingUsefulTwo()
            val two = doSomethingUsefulOne()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }
    suspend fun doSomethingUsefulTwo(): Int {
        println("start > doSomethingUsefulTwo")
        delay(1000L) // pretend we are doing something useful here
        println("end > doSomethingUsefulTwo")
        return 29
    }

    suspend fun doSomethingUsefulOne(): Int {
        println("start > doSomethingUsefulOne")
        delay(1000L) // pretend we are doing something useful here, too
        println("end > doSomethingUsefulOne")
        return 13
    }


