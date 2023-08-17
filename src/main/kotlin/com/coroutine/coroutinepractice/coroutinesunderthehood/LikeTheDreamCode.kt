package com.coroutine.coroutinepractice.coroutinesunderthehood

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 이상적인 드림코드
 * suspend를 잘 활용하자
 * 코틀린 내부에는 생성할 때 Continuation이 인수로 들어가는데 -> Continuation Passing Style, CPS Transform -> 함수형 프로그래밍 Callback 패턴과 유사
 * 이 Continuation의 필드 값 중 Label이 있다.
 * Label의 값이 0 1 2로 나누어지고 이 Label의 값에 따라 함수 호출이 달라진다.
 * 각 케이스에 함수 호출에도 Continuation이 함수 인자로 들어가 Label을 변동한다.
 * 마지막에 Continueation의 resume 기능을 통해 다시 메인 코루틴을 호출해서 switch문으로 이동한다.
 * **/
@OptIn(DelicateCoroutinesApi::class)
fun main():Unit{
    GlobalScope.launch {
        val userData = fetchUserData()
        val cacheUserData = cacheUserData(userData)
        updateTextView(cacheUserData)
    }
}

suspend fun fetchUserData() = "user_name"
suspend fun cacheUserData(user:String) = user

fun updateTextView(user: String) = user

