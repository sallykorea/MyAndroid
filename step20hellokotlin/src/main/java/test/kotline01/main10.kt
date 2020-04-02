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

}