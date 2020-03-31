package com.gura.step20hellokotlin

fun main(){
    //Immutable Map : 수정 불가능한 Map
    var map1:Map<String, Any> = mapOf("num" to 1, "name" to "김구라")
    var map2= mapOf("num" to 2, "name" to "해골") //type infferred(type 추론)

    //저장된 값을 참조할때 2가지가 모두 가능
    var a=map1.get("name") //메소드 형으로 참조
    var b=map1["name"] //javascript에서 object를 참조하듯이

    //Mutable Map : 수정 가능한 Map
    var map3:MutableMap<String, Any> = mutableMapOf()
    var map4= mutableMapOf<String, Any>()

    map3.put("num", 3)
    map3.put("name", "원숭이")

    map4["num"]=4
    map4["name"]="주뎅이"
}