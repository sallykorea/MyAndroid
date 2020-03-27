package com.gura.step20hellokotlin

import java.io.File

/*
    [Creating DTOs (POJOs/POCOs)]
 */
//MemberDto 클래스 정의하기
//var 변할 가능성이 있는 변수, val 변할 가능성이 없는 변수(like Final)
//val로 변수를 지정하면 내부적으로 setter 메소드를 만들지 않는다. 따라서 변수의 값을 변경하려고 하면 오류가남
data class MemberDto(var num:Int, var name:String, var addr:String)

/*
    [인자의 Default 값 지정하기 | Default values for function parameters]
 */
fun foo(num:Int=0, name:String="이름"){
    println("num:$num, name:$name")
}



fun main(){
    //MemberDto에 한명의 회원정보를 저장
    var mem1: MemberDto =MemberDto(1, "김구라", "노량진")
    //내부적으로 getter 메소드가 사용됨
    var num=mem1.num;
    var name=mem1.name;
    var addr=mem1.addr;
    println("MemberDto의 값 변경전 - num:$num, name:$name, addr:$addr")
    //내부적으로 setter 메소드가 사용됨
    mem1.num=2
    mem1.name="이정호"
    mem1.addr="독산동"
    println("MemberDto의 값 변경후 - num:${mem1.num}, name:${mem1.name}, addr:${mem1.addr}")

    println()
    println("-----인자의 default 값 지정하기-------")
    foo() //인자의 default값을 지정해 놓으면 인자를 전달하지 않을 수 있다.
    foo(2) // 인자의 default값을 지정해 놓으면 인자를 원하는 만큼만 전달할 수 있다.
    foo(name="해골") //java에서는 인자를 순서대로 전달했어야하는데, kotlin에서는 어떤 인자에 어떤 값을 전달하는지 명시해주면 순서에 상관없이 인자를 전달할 수 있다.
    foo(name = "원숭이", num = 3)

    /*
        [Filtering a list]
     */
    println()
    println("-----Filtering a list-------")
    //숫자 배열
    var nums= listOf(-20, -10, 0, 10, 20)
    //item 중에서 0보다 큰 값만 추려서 새로운 배열 얻어내기
    var result=nums.filter { x -> x > 0 } //x 대신에 다른 변수명을 쓸 수도 있다.
    println("it 예약어 미사용 : ${result.toString()}")
    var result2=nums.filter { it > 0 } //it은 배열안에 있는 각각의 값들을 칭하는 예약어이다.
    println("it 예약어 사용 : ${result2.toString()}")

    /*
        [Checking element presence in a collection.]

    if ("john@example.com" in emailsList) { ... } //emailsList 안에 john@example.com 이 이메일이 있는지 확인
    if ("jane@example.com" !in emailsList) { ... } //emailsList 안에 john@example.com 이 이메일이 없는지 확인

     */

    /*
        [Instance Checks]
     */
    println()
    println("-----Instance Checks-------")
    var whoAmI:Any=mem1 //whoAmI 변수의 값을 아무거나 바꾸면서 체크해보세요
    when(whoAmI){
        is String -> println("String")
        is MemberDto -> println("MemberDto")
        else -> println("무슨 type 인지 모르겠다.")
    }

    /*
        [Using ranges]
     */
    println()
    println("-----Using ranges : .. 예시-----")
    for (i in 1..10) {  // closed range: includes 10
        print("$i, ")
    }

    println()
    println("-----Using ranges : nutil 예시-----")
    var nums2= listOf(-20, -10, 0, 10, 20)
    for (i in 1 until nums2.size) {  // half-open range: does not include 10
        println("$i 번방의 ${nums2[i]}")
    }
    /*
    for (x in 2..10 step 2) { ... } //2씩 증가
    for (x in 10 downTo 1) { ... } //감소
    if (x in 1..10) { ... }*/

    println()
    /*
        [Read-only list, map]
        - 변경 불가능(immutable)한 map이나 list를 사용하는 것이 더 빠르다.
        - 변경 가능(mutable)
     */
    val info= mapOf("num" to 1, "name" to "김구라", "isMan" to false) //<String, Any> 제너릭 타입도 추론(inference) 가능하다.
    val myNum=info["num"]
    val myName=info["name"]
    val myAddr=info["isMan"]
    info.get("num") //get()메소드로도 value 값을 갖고 올 수 있다.
    println("-----Read-only map-----")
    println("num : $myNum, name: $myName, addr:$myAddr")

    /*
        [casting]
        - as 연산자를 이용해서 casting 한다.
        - Any type으로 casting 하기 위해서는 :Any? 로 null 값이 담길 경우를 대비해주어야한다.
     */
    val myNum2:Int=info["num"] as Int
    val myName2:String=info["name"] as String
    val myAddr2:Boolean=info["isMan"] as Boolean

    println()
    /*
        [If not null shorthand]
        - files 변수에 ?를 안 붙여 주면 null 값을 허용하지 않는게 되버려서 NullPointerException 발생한다.
        - ?를 붙이면 null 값을 허용하는게 되므로, files 변수의 값이 null일 경우 null이 출력된다.
     */
    val files = File("Test").listFiles()
    println("-----If not null shorthand------")
    println(files?.size) //
    /*
        [If not null and else shorthand : Elvis operator]
        - null인 경우 default 값을 주고 싶을 때 사용
        - 엘비스 연산자는 우항으로 return이나 throw도 넣을 수 있습니다.
        - files2 가 null 일 경우, default 값으로 "empty" 문자열을 넣어준다.
     */
    val files2 = File("Test").listFiles()
    println("-----If not null and else shorthand------")
    println(files2?.size ?: "empty")


    /*
        [Executing a statement if null]
     */
    //values에서 "email"값을 참조해서 email 변수의 값으로 넣으려는데 null인 경우, exception을 던진다.
    val values = mapOf("email" to "hi@naver.com")
    val email = values["email"] ?: throw IllegalStateException("Email is missing!")

    println()
    /*
        ['if' expression]
     */
    fun foo(param: Int) {
        val result3 = if (param == 1) {
            println("------'if' expression-----")
            println("one")
        } else if (param == 2) {
            println("------'if' expression-----")
            println("two")
        } else {
            println("------'if' expression-----")
            println("three")
        }
    }
    foo(2) //파라미터를 변경하면서 테스트 해보세요

}