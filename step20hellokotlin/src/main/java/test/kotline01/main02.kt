package test.kotline01

import android.content.Intent

fun main(){
    //java 클래스의 클래스 type
    val  a:Class<MemberDto> = MemberDto::class.java
    val b=MemberDto::class.java
    //사용 예시 : activity 이동할 때
    /*val intent=Intent(this.SubActivity::class.java)
    StartActivity(intent)*/
}