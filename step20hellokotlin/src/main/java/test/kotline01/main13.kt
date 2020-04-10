package test.kotline01

/*
    Q. var 과 val 의 차이점
    A. 1. var : 수정 가능한 변수 선언
        2. val : 수정 불가능한 변수 선언
 */
/*
    Q. var num:Int = 999 이 한줄의 코드가 수행되면 벌어지는 일은?
    A. int type인 변수 num에 999를 대입한다.
         **대문자 Int!!!
 */
/*
    Q.
    fun main(){
        fun a(){
            println("대경아 놀자")
        }

        a()
    }
    이 한줄의 코드가 수행되면 벌어지는 일은?

    A. a()함수가 호출되면서 콘솔창에 대경아 놀자 가 찍힌다.
 */
/*
    Q.var a="김구라"
    println("내이름 $a")
    이 한줄의 코드가 수행된 결과는?

    A. 내이름은 김구라 가 콘솔창에 출력된다.
 */
/*
    Q.
    var names= listOf("AAA", "BBB", "CCC")
    println(names[0])
    이 한줄의 코드가 수행된 결과는?

    A. 콘솔창에 AAA가 출력된다.
 */
/*
    Q.
    for (a in 0..9){
        println(a)
    }
    이 한줄의 코드가 수행된 결과는?

    A. 콘솔창에 0 부터 9 까지 다 출력된다.
 */
/*
    for (a in 0..9){ ..은 range
        println(a)
    }
    는
    listOf(0,1,2,3,4,5,6,7,8,9)
    와 비슷한 코드이다.
 */
/*
    for (a in 0 until 9){
        println(a)
    }
    nutil은 9 이전의 숫자까지 찍힌다.
 */
/*
    fun test(num:Int=0){  기본값지정. 기본값을 설정하지 않으면 값을 전달 안 하는 경우 오류남
        println(num)
    }
    1. 전달하는 경우 : test(10) 콘솔창에 10이 찍힌다.
    2. 전달 안 하는 경우 : test() 콘솔창에 설정해 놓은 기본값이 0이 찍힌다.
 */
/*
    a 라는 이름의 변수를 만들고 정수 100을 대입해 보세요.
    단, a는 수정 가능한 변수여야 합니다.
    var a:Int =100
 */
/*
    b 라는 이름의 변수를 만들고 정수 100을 대입해 보세요.
    단, b는 수정 불가능한 변수여야 합니다.
    val b:String = "안녕"
 */
/*
    gura 라는 이름의 함수를 만들어 보세요.
    단, 해당 함수를 호출하면 콘솔창에 "hello"라는 문자열이 출력되어야 합니다.
    fun gura(){
        println("hello")
    }
 */
/*
    콘솔창에 5부터 10까지 순서대로 출력하는 for 문을 구성해보세요.
    for (num in 5..10){
        println(num)
    }
 */
/*
    전달하는 실수의 두 곱을 리턴해주는 mul 이라는 이름의 함수를 만들어보세요.
    fun mul(a:Double , b:Double):Double{
        return a*b
    }

    println(mul(1.2, 1.2))

    ** 실수는  Double!!!
    ** 정수는 Int!!!
 */
/*
    Q.
    var mem = mapOf("num" to 10, "name" to "원숭이")
    println(mem["name"])
    수행결과는?

    A.콘솔창에 원숭이가 출력된다.

 */
/*
    Q.
    var nums:List<가> = listOf(100.1, 200.1, 300.1)
    가 를 완성하세요

    A. Double

    Q.
    var nums:List<나> = listOf(100.1, true, "kim)
    나 를 완성하세요

    A. Any

    **제너릭 타입을 적을 수 있어야한다. 중요!! TYPE은 대문자로 시작함
 */
/*
    제너릭 작성방법 2가지
    var nums:List<Any> = listOf(100.1, true, "kim)
    var nums = listOf<Any>(100.1, true, "kim)
 */
/*
    Phone 이라는 클래스를 정의해보세요
    class Phone(){

    }

 */
/*
    Phone 이라는 클래스를 정의해보세요
    생성자의 인자로 수정 가능한 문자열(전화기 이름)을 전달 받도록 생성자를 정의해 보세요.
    class Phone(var name:String){

    }

 */
/*
    Phone 이라는 클래스를 정의해보세요
    생성자의 인자로 수정 가능한 문자열(전화기 이름)을 전달 받도록 생성자를 정의해 보세요.
    call 이라는 이름의 함수를 클래스 안에 정의해 보세요.
    class Phone(var name:String){
        fun call(){

        }
    }

 */
/*
    Phone 이라는 클래스를 정의해보세요
    생성자의 인자로 수정 가능한 문자열(전화기 이름)을 전달 받도록 생성자를 정의해 보세요.
    만일 외부에서 call 이라는 이름의 함수를 호출하면 "xxx가 전화를 걸어요" 가 콘솔창에 출력되도록 해보세요
    call 이라는 이름의 함수를 클래스 안에 정의해 보세요.
    class Phone(var name:String){
        fun call(){
            println("$name 가 전화를 걸어요")
        }
    }

 */
/*
    Phone 이라는 클래스를 정의해보세요
    생성자의 인자로 수정 가능한 문자열(전화기 이름)을 전달 받도록 생성자를 정의해 보세요.
    만일 외부에서 call 이라는 이름의 함수를 호출하면 "xxx가 전화를 걸어요" 가 콘솔창에 출력되도록 해보세요
    call 이라는 이름의 함수를 클래스 안에 정의해 보세요.
    Phone 클래스로 객체를 생성해서 phon1이라는 이름의 변수에 담는 코드를 작성해보세요

    var phone1:Phone=Phone("gura")

 */


class Phone(var name:String){
    fun call(){
        println("$name 가 전화를 걸어요")
    }
}

fun main(){
    var phone1:Phone=Phone("gura")
    phone1.call()
}
