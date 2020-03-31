package com.gura.step20hellokotlin
/*
    [listOf]
    - 수정불가한 고정배열(immutable List)
    - 배열에 값이 들어가면 해당 data는 수정할 수 없다. 그러나 배열 안에 있는 객체의 필드 수정은 가능하다.
 */

//data 클래스 정의하기(DTO)
data class Member(var num:Int, var name:String)

fun main(){
    //수정 불가능한 List
    var nums:List<Int> = listOf(10, 20, 30, 40, 50) // '='대입 연산자 사용시 주의할점! 'List<Int>=listOf()' 경우 띄어쓰기를 안하면 '비교 연산자'로 인식 된다.
    var names = listOf("kim", "lee", "park") //type이 추론도므로 List<String>으로 명시하지 않아도 된다.

    //data class로 객체를 생성하고
    var mem1=Member(1, "김구라")
    var mem2=Member(2, "해골")
    var mem3=Member(3, "원숭이")
    //배열의 참조값을 담아 놓기
    var members= listOf(mem1,mem2,mem3)

    //배열 형태로 값을 가져 올 수도 있고
    var a=members[0].num //1
    var b=members[0].name //"김구라"

    //메소드를 사용하듯이 값을 가져올 수도 있다.
    var c= members.get(0).num //1
    var d= members.get(0).name //"김구라"

    //배열 자체의 데이터는 수정 불가하다.(immutable List 이기 때문에)
    //members[0]=Member(4,"주뎅이")

    //배열 안에 있는 객체의 필드 수정은 가능하다.(var 로 객체의 필드가 선언되어 있다면)
    members[0].num=999
}