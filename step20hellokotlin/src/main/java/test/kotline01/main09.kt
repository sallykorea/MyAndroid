package test.kotline01

fun main(){
    var str="1000"
    var str2="천"
    //try 식
    var result=try {
        Integer.parseInt(str2) //str 과 str2 번갈아 넣어가며 테스트해보세요
    }catch (ne:NumberFormatException){ //exception이 발생하면 result에 0이 대입된다.
        0
    }

    println(result + 10)

}