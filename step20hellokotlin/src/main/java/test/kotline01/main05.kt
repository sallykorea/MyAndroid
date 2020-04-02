package test.kotline01

//열거형(enum) 클래스(상수값을 관리할때 사용한다.)
enum class MyColor{
    RED, BLUE, GREEN
    //In Java
    //public static final int RED=0;
    //public static final int BLUE=1;
    //public static final int GREEN=2;
}

fun main(){
    var c1:MyColor=MyColor.RED
    var c2 = MyColor.BLUE
    var c3 = MyColor.GREEN

    var color=MyColor.BLUE

    when(color){
        c1 -> println("c1 과 같아요")
        c2 -> println("c2 와 같아요")
        c3 -> println("c3 와 같아요")
    }
}
