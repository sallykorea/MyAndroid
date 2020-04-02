package test.kotline01

class Friend{
    //property
    var num:Int=0
    var name:String?=null
        //setter 메소드 정의
        set(value) { //value 값이 인자(argument)로 전달된다.
            field=value+"님" //여기에서 field는 name을 가리킨다.
        }
    var phone:String?=null
        get() { //여기에서 field는 phone을 가리킨다.
            return field ?: "전화번호 없음" //?:(엘비스 연산자) - 값이 null이면 오른쪽에 있는 값을 return 한다.
        }

    fun showInfo(){
        println("num:$num | name:$name | phone:$phone")
    }

}

fun main(){
    val f1=Friend()
    println("-----before add info-------")
    f1.showInfo()

    println()
    println("-----after add info-------")
    f1.num=1
    f1.name="김구라"
    f1.phone="010-1111-2222"
    f1.showInfo()


}