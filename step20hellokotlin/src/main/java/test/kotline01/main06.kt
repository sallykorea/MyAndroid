package test.kotline01
/*
    [생성자가 있는 enum 클래스]
    - property를 가질 수 있다.
    - 기능을 가질 수 있다.
    - 상수이지만 여러개의 필드 값을 가지는 상수를 정의 할 수 있다.
    - 생성자가 있는 enum 클래스에 바로 상수값을 정의하면 오류가 난다.
 */
enum class Color(val r:Int, val g:Int, val b:Int){
    //생성자가 있는 enum 클래스에 바로 상수값을 정의하면 오류가 난다.
    //RED, GREEN, BLUE
    RED(225,0,0),
    GREEN(0,255,0),
    BLUE(0,0,255); //함수와 구분하기 위해 세미콜론(;)이 필요하다.

    fun toHex():String{
        var result=Integer.toHexString(r)+ //toHexString : 정수를 16진수형태의 문자열로 바꿔준다. 예) 색상을
                Integer.toHexString(g)+
                Integer.toHexString(b);
        return result
    }
}

fun main(){
    //각각의 상수(RED, GREEN, BLUE)는 고유한 값(r,g,b)을 가지고 있다.
    var c1=Color.RED
    var c2=Color.GREEN
    var c3=Color.BLUE

    println("c1:${c1.r} | c2:${c2.g} | c3:${c3.b}")
    println(c1.toHex())
}