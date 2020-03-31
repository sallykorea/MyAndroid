package com.gura.step20hellokotlin

//인터페이스
/*
    [인터페이스]
    - 콜론 ( : )을 사용해서 구현한다.
    - 메소드를 오버라이드 한다.
 */
interface Remocon{
    fun up()
    fun down()
}

class TvRemocon:Remocon{
    override fun up() {
        println("tv 채널을 올려요")
    }

    override fun down() {
        println("tv 채널을 내려요")
    }

}

//Weapon 클래스 상속 받고 Weapon 인터페이스를 구현한 클래스
class MultiClass:Weapon(), Remocon{
    override fun attack() {
        println("아무거나 공격해요")
    }

    override fun up() {
        println("파워를 올려요")
    }

    override fun down() {
        println("파워를 내려요")
    }

}

fun main(){

    println("----TvRemocon----")
    var r1=TvRemocon()
    r1.up()
    r1.down()

    println("----Anonymous inner type----")
    var r2=object:Remocon{
        override fun up() {
            println("볼륨을 올려요")
        }

        override fun down() {
            println("볼륨을 내려요")
        }

    }

    r2.up()
    r2.down()

    println("----MultiClass----")
    var r3=MultiClass()
    r3.move()
    r3.attack()
    r3.up()
    r3.down()

    //다형성 : MultiClass 객체의 참조 값을 다양한 type 변수에 담기
    var m1:MultiClass= MultiClass()
    var m2:Weapon= MultiClass()
    var m3:Remocon= MultiClass()

    //부모 type 참조값을 자식 type 변수에 casting 해서 대입해야한다.
    var m6:MultiClass=m2 as MultiClass
    var m7:MultiClass=m3 as MultiClass
}
