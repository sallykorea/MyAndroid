package test.kotline01

//폭과 높이를 생성자의 인자로 전달 받는 Rect 클래스
class Rect(val width:Int, val height:Int){
    //property(field) -
    var isSquare:Boolean=false
        get() {
            return width==height
        }
        //get() = return width==height 간단하게 표현하기

}

fun main(){
    val rect1=Rect(10, 20)
    val rect2=Rect(20, 10)
    val rect3=Rect(20, 20)

    //rect1,2,3 변수에 담겨있는 Rect 객체의 isSquare property의 값을 참조한다.
    //이때 getter 메소드를 사용하는데 위에 정의 되어 있는 getter메소드에 따라 ${}안의 값이 결정된다.
    println("rect1의 정사각형 여부:${rect1.isSquare}")
    println("rect2의 정사각형 여부:${rect2.isSquare}")
    println("rect3의 정사각형 여부:${rect3.isSquare}")

}