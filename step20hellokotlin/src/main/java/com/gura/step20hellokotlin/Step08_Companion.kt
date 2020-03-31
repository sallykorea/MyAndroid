package com.gura.step20hellokotlin

class MyUtil{
    //클래스명에 . 찍어서 사용할 수 있는 자원 만들기(Java의 static 클래스 같이)
    //동반 객체 정의 하기
    companion object{
        val color="Red"
        fun write(){
            println(color+"필기를 해요!")
        }
    }
}

class MyDao private constructor(){ //외부에서 객체를 생성하지 못 하도록 private 예약어 사용
    companion object{
        private var dao:MyDao?=null
        fun getInstance():MyDao?{
            if (dao==null){
                dao=MyDao()
            }
            return dao
        }
    }

    fun insert(){
        println("MyDao insert!")
    }
    fun update(){
        println("MyDao update!")
    }
}

//Single ton으로 사용할 수있는 Dao
class YourDao private constructor(){
    companion object{
        private val dao=YourDao()
        fun getInstance():YourDao{
            return dao
        }
    }

    fun insert(){
        println("YourDao insert!")
    }
    fun update(){
        println("YourDao update!")
    }
}

fun main(){
    MyUtil.write() // static 메소드 호출
    var a=MyUtil.color // static 필드 참조
    println("a:"+a)

    MyDao.getInstance()?.insert()
    MyDao.getInstance()?.update()

    YourDao.getInstance().insert()
    YourDao.getInstance().update()
}
