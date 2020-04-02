package test.kotline01

/*
    [Map]
 */
fun main(){
    val map1= mutableMapOf<String, String>()
    map1.put("house", "집")
    map1.put("phone", "전화기")
    map1.put("suger", "설탕")

    //반복문을 이용해서 map에 있는 정보를 일괄적으로 빼내기
    for ((key, value) in map1){ //key와 value 값을 일괄적으로 얻어내기
        println("map1 ${key}:${value}")
    }

    println("--------")

    for (key in map1.keys){ //key의 배열을 얻어내서
        println("map1 ${key}:${map1.get(key)}") //key값으로 value값을 얻어내기
    }

}