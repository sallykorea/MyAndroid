package test.kotline01

fun main(){
    var num=1001 //여기 숫자를 바꿔가며 테스트 해보세요.

    //if문 식
    var result=if(num%2==0){
        "짝수입니다."
    }else{
        "홀수입니다."
    }

    //if문 식 줄여서 쓰기
    var result2=if (num%2==0) "짝수입니다." else "홀수 입니다."

    println("$num 은 $result")
    println("$num 은 $result2")

    //when문 식
    var jumsu=85 //여기 숫자를 바꿔가며 테스트 해보세요.
    var result3=when{
        jumsu >= 90 -> "A 입니다."
        jumsu >= 80 -> "B 입니다."
        jumsu >= 70 -> "C 입니다."
        jumsu >= 60 -> "D 입니다."
        else -> "F 입니다."
    }

    println("$jumsu 를 학점으로 환산하면 $result3")

    println(calc2(75))

}

/*

fun calc(jumsu:Int):String{
  return "xxx"
}

을 줄여서 작성하면
*/
fun calc(jumsu:Int) = "xxx" //줄여서 작성하면 return type을 작성하지 않아도 알아서 inferre (추론)한다.

//함수를 호출하면서 정수를 전달하면 when 구문을 수행한 결과를 return 해준다.
fun calc2(jumsu: Int) = when{
    jumsu >= 90 -> "A 입니다."
    jumsu >= 80 -> "B 입니다."
    jumsu >= 70 -> "C 입니다."
    jumsu >= 60 -> "D 입니다."
    else -> "F 입니다."
}