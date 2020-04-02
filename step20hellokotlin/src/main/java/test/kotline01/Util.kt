package test.kotline01

class Util {
    //compaion object (동반 객체) : java에서의 static 같은 것이다.
    companion object{
        val version="1.0"
        @JvmField // java에서 static field로 사용하고 싶을 때
        val author="김구라"
        fun download(){
            println("다운로드 해요!")
        }
        @JvmStatic //java에서 static method로 사용하고 싶을 때
        fun upload(){
            println("업로드 해요!")
        }
    }
}