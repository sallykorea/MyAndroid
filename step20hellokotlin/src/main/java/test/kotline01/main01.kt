package test.kotline01

//실행순서가 시작되는 main 함수
fun main(){
    //kotlin에서 java class 사용 가능? 사용가능!
    //이유 : kotline이 컴파일시 자바 코드로 바뀌기 때문에 사용 가능하다. kotline은 여러 언어와 호환이 가능하기 때문에
    //java class를 import 해서 setter 메소드 사용하기
    var mem1=MemberDto()
    mem1.num=1
    mem1.name="김구라"
    mem1.addr="노량진"
    //java class를 import 해서 getter 메소드 사용하기
    var a=mem1.num
    var b=mem1.name
    var c=mem1.addr

    var mem2=MemberDto(2, "해골", "행신동")

}