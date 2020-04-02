package test.kotline01

fun main(){
    //key : Int type, value : String type
    var map1= mutableMapOf<Int, String>()

    for (num in 0..255){
        //num에 해당하는 이진수의 문자
        var bin=Integer.toBinaryString(num)
        //map에 저장하기
        map1.put(num, bin)
    }

    //반복문을 이용해서 map에 있는 정보를 일괄적으로 빼내기
    for ((key, value) in map1){ //key와 value 값을 일괄적으로 얻어내기
        println("map1 ${key}:${value}")
    }

}