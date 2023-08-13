package com.coroutine.coroutinepractice

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    /**
     * First Coroutine
     *
     * 코루틴은 경량 스레드
     * launch 함수는 코루틴 빌더
     * GlobalScope
     * GlobalScope.launch{} == thread{}
     * **/
    // 코루틴을 호출하는 클래스
    GlobalScope.launch { // 백그라운드에서 새 코루틴을 호출한다.
        delay(1000L)// 1초의 논블로킹 딜레이
        println("World") // 딜레이 이후 출력
    }
    println("Hello, ")// 메인 스레드는 코루틴이 딜레이 됐을 때도 돌아간다.
    Thread.sleep(2000L)// 메인 스레트를 2초동안 블록해서 jvm이 살아있도록 한다.

    /**
     * Second Coroutine
     * runBlocking(코루틴 빌더)
     *  -> runBlocking을 invoking하는 메인스레드는 블록한다.
     *     runBlocking 안에 있는 코루틴이 완료될 때 까지
     * **/

    GlobalScope.launch { // 백그라운드에서 새 코루틴을 호출한다.
        delay(1000L)// 1초의 논블로킹 딜레이
        println("World") // 딜레이 이후 출력
    }
    println("Hello, ")// 메인 스레드는 즉각 실행
    runBlocking { // 하지만 이 표현식이 메인스레드를 블록한다.
        delay(2000L )// ... 2초 동안 jVM이 살아 있도록 하는 와중
    } // Thread.sleep(2000L)와 같은 것

    /**
     * Third Coroutine
     * 메인 함수를 런블로킹으로 감싸는 것이 관용적
     * 이렇게 전체 코드가 다 실행되기 전까지는 메인 스레드가 런블로킹 된다.
     * **/

    runBlocking {
        GlobalScope.launch { // 백그라운드에서 새 코루틴을 호출한다.
            delay(1000L)// 1초의 논블로킹 딜레이
            println("World") // 딜레이 이후 출력
        }
        println("Hello, ")// 메인 스레드는 즉각 실행
        delay(2000L)
    }


    /**
     * fourth Coroutine
     * Delay 함수는 좋은 방법이 아니다.
     * 명시적으로 job을 만들어서 기다리는 것
     * **/
    runBlocking {
        val job = GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello, ")
        job.join() // 자식 코루틴이 끝나기 전까지 기다린다.
        // -> await()의 기초
    }


    /**
     * Structured Concurrency
     * GlobalScope를 사용할 때 우리는 탑 레벨의 코루틴을 만든다.
     * 이건 메모리를 잡아먹는다.
     * GlobalScope, job 형식으로 사용하면 계속 코루틴을 생성해주어야 한다.
     * GlobalScope를 사용하지 않고 구조적 동시성을 사용하면 메모리가 절약된다.
     *
     * 탑레벨의 코루틴을 만들지 말고 현재 코루틴의 child 코루틴을 만들어서 사용한다.
     * -> Structured Concurrency
     * **/
    runBlocking {
        launch { // 새 코루틴을 runBlocking 범위 내에서 실행, this를 붙인 거다.
            delay(1000L)
            println("World!")
        }
        println("Hello, ")
    }


    /**
     * Suspend Function, Extract function refactoring
     * delay는 suspend function으로 이 함수를 이용하기 위해서는 suspend 키워드를 써야한다.
     * 코드를 호출하는 입장에서는 doWorld()를 호출하면 delay
     * **/
    runBlocking {
        launch {
            doWorld() // suspend 함수
            println("Hello, ")
        }
    }


    /**
     * Coroutines Are light-weight
     * 코루틴은 경량 스레드다 보니 많은 양을 생성 가능하다.
     * 코루틴이 스레드보다는 구조적으로 가볍다.
     * **/
    runBlocking {
        repeat(100_000){// 많은 양의 코루틴 반복
            launch {
                delay(1000L)
                print(".")
            }
        }
    }

    /**
     * Global coroutines are like daemon threads
     * GlobalScope에서 launch한 유효한 코루틴은 프로세스를 지속할 수 없다.
     * 그런 코루틴들은 데몬 스레드와 같다.
     * **/
    runBlocking {
        GlobalScope.launch{
            repeat(1000){
                    i -> println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // 딜레이 뒤에 종료
    }


    /**
     * Global coroutines are like daemon threads
     * suspend <-> resume 체험
     * 두 개의 구조적 컨커런시
     * 두 개의 코루틴이 각각 5번 실행
     * 두 코루틴은 다 같은 main 스레드에서 실행
     * **/

    runBlocking {
        launch {
            repeat(5){
                    i -> println("Coroutine A, $i")
            }
        }
        launch {
            repeat(5){
                    i -> println("Coroutine B, $i")
            }
        }
        println("Coroutine Outer")
    }
}
suspend fun doWorld() {
    delay(1000L)
    println("World!")

}