package com.gura.step20hellokotlin

import android.provider.ContactsContract

open class Phone{
    fun call(){
        println("전화를 걸어요")
    }
}

/*
    [Phone 클래스 상속 받기]
    상속은 콜론( : )으로 받는다.
    final로 정의된 class는 다른 클래스에서 상속을 받을 수 없다.(java, kotlin 공통사항)
    Kotlin에서는 class의 default 값이 fianl 이다.
    따라서, 상속을 받기 위해서는 open이라는 예약어를 부모 class 선언해 주어야한다.
 */
open class HandPhone : Phone(){ //생성자가 있으면 호출하는 표현식을 써야한다.
    fun mobileCall(){
        println("이동 중에 전화가 왔어요")
    }

    //함수도 default 는 final 이다.
    //override가 가능하려면 open 이라는 예약어가 필요하다
    open fun takePictur(){
        println("30만 화소의 사진을 찍어요")
    }
}

class SmartPhone : HandPhone(){
    fun doInternet(){
        println("인터넷을 해요")
    }

    //open된 함수를 override 할 수 있다.
    override fun takePictur() {
        //부모 메소드 call
        //super.takePictur()
        println("1000만 화소의 사진을 찍어요")
    }
}

fun main(){
    var p1=Phone()
    var p2=HandPhone()
    var p3=SmartPhone()
    println("----Phone-----")
    p1.call()

    println("----HandPhone-----")
    p2.call()
    p2.mobileCall()
    p2.takePictur()

    println("----SmartPhone-----")
    p3.call()
    p3.mobileCall()
    p3.doInternet()
    p3.takePictur()


}