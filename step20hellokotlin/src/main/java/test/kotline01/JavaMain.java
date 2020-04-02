package test.kotline01;

public class JavaMain {
    public static void main(String[] args) {
        //Kotlin에서 정의한 compaion object (동반 객체)를 java class에서 사용하려면
        //클래스명.Companion.필드 또는 메소드 로 사용해야한다.
        Util.Companion.download();
        //Kotlin class 에서 @JvmField 어노테이션을 붙여주면 java class에서도 static field를 참조하는 것 처럼 사용할 수 있다.
        Util.upload();

        System.out.println("Util version:"+Util.Companion.getVersion());
        //Kotlin class 에서 @JvmStatic 어노테이션을 붙여주면 java class에서도 static method를 참조하는 것 처럼 사용할 수 있다.
        System.out.println("Util author:"+Util.author);
    }
}
