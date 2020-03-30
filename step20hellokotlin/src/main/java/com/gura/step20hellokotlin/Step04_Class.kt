package com.gura.step20hellokotlin

//클래스 정의하기
class MyClass

//클래스 정의하기
class YourClass{
    //멤버 함수
     fun printInfo(){
        println("YourClass 의 메소드(함수) 호출됨")
    }
}

//대표(primary) 생성자는 클래스명 우측에 선언하고 생략가능하다.
class OurClass1 constructor(){ //1.대표(primary) 생성자는 클래스명 우측에 선언

}

class OurClass2(){ //2.대표(primary) 생성자 예약어 consturctor 예약어 생략 가능

}

class OurClass3{ //3. 인자로 전달 받을게 없으면 ()도 생략 가능

}

//생성자의 인자로 문자열을 전달 받는 생성자
class Car(name:String){
    //String type을 담을 수 있는 필드 선언
    var name:String
    //초기화 블럭(객체가 생성되는 시점에 무언가 작업할 수 있는 블럭)
    init {
        println("Car 클래스 init")
        //생성자의 인자로 전달 받은 문자열을 필드에 저장
        this.name=name;
    }

    //함수
    fun drive(){
        println("$name 이(가) 달려요")
    }

}

//var 예약어를 생성자의 인자에 선언해 놓으면 알아서 멤버변수를 만들어주고, 전달된 값을 필드에 저장도 해준다.
//var 로 선언하면 getter, setter가 모두 가능
class YourCar(var name: String){
    /*
    var name: String
    init {
        println("Car 클래스 init")
        //생성자의 인자로 전달 받은 문자열을 필드에 저장
        this.name=name;
    }
    */

    fun drive(){
        println("$name 이(가) 달려요")
    }

}

class OurCar(){ //Primary 생성자

    //property(멤버 변수) 정의
    var name:String?=null

    //두번째 생성자 정의 하기
    constructor(name: String):this(){ //primary 생성자를 호출해야한다.
        //property(필드)에 값 대입하기
        this.name=name
    }

    fun drive(){
        println("$name 이(가) 달려요")
    }
}

fun main(){
    //MyClass 로 객체 생성해서 my 라는 변수에 담기
    var my=MyClass()
    //YourClass 로 객체 생성해서 참조값을 your 라는 변수에 담기
    var your=YourClass()
    //printInfo() 함수 호출하기
    your.printInfo()

    println()
    println("------------")
    var c1=Car("samsung")
    println(c1.name) //필드(property) 참조
    c1.drive()

    println()
    println("------------")
    var c2=YourCar("아반떼")
    var info2=c2.name
    info2="프라이드"
    println("info2 : $info2")
    c2.drive()

    println()
    println("-----class OurCar : 생성자-------")
    var c3=OurCar()
    var c4=OurCar("그렌저")

    c3.drive()
    c4.drive()
}