package com.gura.step20hellokotlin

import java.lang.IndexOutOfBoundsException

data class Member2(var num:Int, var name:String)

fun main(){
    // immutable list
    var nums:List<Int> = listOf(10, 20, 30)

    //mutable list
    var nums2:MutableList<Int> = mutableListOf(10, 20, 30)
    nums2.add(40)
    nums2.add(50)
    var names= mutableListOf("kim", "lee", "park")
    names.add("jung")
    names.add("an")
    //index에 의한 item 삭제
    nums2.removeAt(0)
    names.removeAt(0)
    //item 값에 의한 삭제
    nums2.remove(20)
    names.remove("lee")

    //존재하지 않는 아이템 삭제 (false 리턴)
    var result1=names.remove("aaa")
    //존재하지 않는 인덱스 삭제(IndexOutOfBoundException 발생)
    try {
        var result2=nums2.removeAt(1000)
    }catch (e:IndexOutOfBoundsException){
        e.printStackTrace()
    }


    //mutableList 생성
    var members:MutableList<Member2> = mutableListOf()
    //Member2 객체 생성해서 배열에 저장
    members.add(Member2(1, "김구라"))
    members.add(Member2(2, "해골"))
    members.add(Member2(3, "원숭이"))

    for (item in members){
        println("번호 : ${item.num}, 이름 : ${item.name}")
    }

}