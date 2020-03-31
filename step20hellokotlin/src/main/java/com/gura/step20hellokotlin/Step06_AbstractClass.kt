package com.gura.step20hellokotlin

/*
   [추상 클래스]
    - 구현된 함수와 구현되지 않은 함수가 모두 정의 되어 있다.
    - 단독으로 객체를 생성할 수 없다.
    - 추상 함수를 반드시 override 해주어야한다.
    - 추상 클래스와 추상함수는 default 값이 open이다.
 */
abstract class Weapon{
    fun move(){
        println("이동합니다.")
    }
    //추상 함수(모양만 정의된 함수)
    abstract fun attack()
}

class MyWeapon : Weapon(){
    //추상 함수 오버라이드
    override fun attack() {
       println("지상 공격을 해요")
    }

}

fun main(){
    //단독으로 객체를 생성할 수 없다.
    var w1=MyWeapon()

    w1.move()
    w1.attack()

    println("--------------")
    //Anonymous inner Class(추상 익명 클래스)
    var w2=object : Weapon(){
        override fun attack() {
            println("공중 공격을 해요")
        }

    }
    w2.move()
    w2.attack()
}