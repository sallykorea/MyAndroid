package com.gura.step20hellokotlin

// Kotlin 참고 사이트 : https://kotlinlang.org/docs/reference/basic-syntax.html

/*
    [Function]
 */
//인자로 Int type 2개를 전달 받아서 Int type을 리턴해주는 함수
fun sum(a: Int, b: Int): Int { // 코틀린에서는 다 참조 데이터 타입이기 때문에 Int 로 작성한다.
    return a + b //return 이라는 예약어로 값을 리턴해준다.
}

fun sum2(a: String, b: Int) = a + b
/*
    코틀린에서는 리턴 값이 없는 경우에도 반드시 값을 리턴하는데 이를 의미 없는 값이라고 한다.
    : Unit 을 명시하면 리턴되는 값이 의미가 없다는 것이다.(javascript의 undefined)
    : Unit 은 생략가능하다.
 */
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

/*
    [Conditional expressions]

 */
fun maxOf(a: Int, b: Int): Int { // 정식으로 표현
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int) = if (a > b) a else b //간단하게 표현


/*
    [Nullable values and null checks]
    A reference must be explicitly marked as nullable when null value is possible.

    Return null if str does not hold an integer:
 */
fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

/*
    [Type checks and automatic casts]
 */
fun getStringLength(obj: Any): Int? { //Any : 인자로 어떤 type이던지 받을 수 있다.(Object type과 비슷하다)
    if (obj is String) { //is 연산자로 자동으로 변수의 type 체크를 할 수 있다.
        // `obj` is automatically cast to `String` in this branch (type 체크한 뒤 true 이면 해당 블럭내에서는 자동으로 casting 된다.)
        return obj.length
    }

    // `obj` is still of type `Any` outside of the type-checked branch (type 체크 블럭 밖에서는 casting하지 않은 type으로 돌아온다.)
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` is automatically cast to `String` in this branch
    return obj.length
}


fun getStringLength3(obj: Any): Int? {
    // `obj` is automatically cast to `String` on the right-hand side of `&&`
    if (obj is String && obj.length > 0) { //obj가 Sting type이라면 obj의 길이가 0보다 클 경우 obj의 크기를 리턴한다.
        return obj.length
    }

    return null
}

/*
    [when expression]
    JAVA 의 swich 구문과 비슷하다.
    단, swich구문에서는 swich(숫자) 만 가능하다
 */
fun describe(obj: Any): String =
        when (obj) {
            1          -> "One"
            "Hello"    -> "Greeting"
            is Long    -> "Long"
            !is String -> "Not a string"
            else       -> "Unknown"
        }


/*
    [Creating basic classes and their instances]
 */
abstract class Shape(val sides: List<Double>) {
    val perimeter: Double get() = sides.sum()
    abstract fun calculateArea(): Double
}

interface RectangleProperties {
    val isSquare: Boolean
}

class Rectangle( // Rectangle 클래스는 Shape 클래스를 상속 받았다.
        var height: Double,
        var length: Double
) : Shape(listOf(height, length, height, length)), RectangleProperties {
    override val isSquare: Boolean get() = length == height
    override fun calculateArea(): Double = height * length
}

class Triangle( // Triangle 클래스는 Shape 클래스를 상속 받았다.
        var sideA: Double,
        var sideB: Double,
        var sideC: Double
) : Shape(listOf(sideA, sideB, sideC)) {
    override fun calculateArea(): Double {
        val s = perimeter / 2
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}


fun main() {
    print("sum of 3 and 5 is ")
    println(sum(3, 5))
    println(sum2("1",3))
    printSum(-1, 8)

    //3. 변수
    //3-1. Read-only local variables are defined using the keyword val. They can be assigned a value only once.
    val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment
    //3-2. Variables that can be reassigned use the var keyword
    var x = 5 // `Int` type is inferred
    x += 1
    println("x = $x")

    /*
        $변수명 : 단순히 변수에 들어 있는 값을 출력하고 싶을 때
        ${실행할 내용} : 변수에 들어 있는 값으로 어떤 실행의 결과를 출력하고 싶을때
    */
    var a1 = 1
    // simple name in template:
    val s1 = "a is $a1"
    a1 = 2
    // arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a1"
    println("")
    println("------String templates------")
    println(s2)

    println("")
    println("------Conditional expressions------")
    println("max of 0 and 42 is ${maxOf(0, 42)}")
    println("max of 0 and 42 is ${maxOf2(0, 42)}")

    println("")
    println("-----Nullable values and null checks-------")
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")

    println("")
    println("-----Type checks and automatic casts-------")
    fun printLength(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))

    println("-----Type checks and automatic casts2-------")
    fun printLength2(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }
    printLength2("Incomprehensibilities")
    printLength2(1000)
    printLength2(listOf(Any()))

    println("-----Type checks and automatic casts3-------")
    fun printLength3(obj: Any) {
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, is empty or not a string at all"} ")
    }
    printLength3("Incomprehensibilities")
    printLength3("")
    printLength3(1000)

    /*
        [for loop]
    */
    println("")
    println("------for loop get itme name------")
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) { //items 배열 안에 있는 내용을 item이라는 변수에 담아서 하나씩 출력하기
        println(item)
    }
    println("")
    println("------for loop get itme index------")
    for (index in items.indices) { //items 배열의 index를 index라는 변수에 담아서 하나씩 출력하기
        println("item at $index is ${items[index]}")
    }

    /*
        [while loop]
     */
    println("")
    println("------while loop------")
    val fruites = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < fruites.size) {
        println("item at $index is ${fruites[index]}")
        index++
    }

    println("")
    println("------when expression------")
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    /*
        [Ranges]
        ..은 범위를 의미한다.
        예) 1..5 = 1이상 5이하
     */
    println("")
    println("------Ranges------")
    val x1 = 10
    val y = 9
    if (x1 in 1..y+1) { // x1 변수 안에 있는 값이 1~(y+9) 사이에 있으면 true를 반환 한다.
        println("fits in range")
    }

    println("------for 문에서의 Ranges------")
    for (x in 1..5) {
        print(x)
    }
    println()
    println("------2씩 증가하면서 반복문 돌기 Ranges------")
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    println("------3씩 감소하면서 반복문 돌기 Ranges------")
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
    println()
    println()
    /*
        [Collections]
     */
    //Checking if a collection contains an object using in operator:
    println("------Collections------")
    val items2 = setOf("apple", "banana", "kiwifruit")
    when {
        "orange" in items2 -> println("juicy")
        "apple" in items2 -> println("apple is fine too")
    }
    println()
    //Using lambda expressions to filter and map collections:
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits.filter { it.startsWith("a") } // filter{} 배열의 여러가지 중에서 조건이 true인 것만 골라낸다. | it 은 배열안에 있는 item을 지칭하는 예약어이다.
            .sortedBy { it } //sortedBy{} 정렬 (문자열인 경우 알파벳 순서로 정렬한다.)
            .map { it.toUpperCase() } // map{} item에 대해 작업할 내용 작성한다.
            .forEach { println(it) } // forEach{println(it)} 배열 각각의 내용을 print 한다.

    println()
    println("------Creating basic classes and their instances------")
    val rectangle = Rectangle(5.0, 2.0)
    val triangle = Triangle(3.0, 4.0, 5.0)
    println("Area of rectangle is ${rectangle.calculateArea()}, its perimeter is ${rectangle.perimeter}")
    println("Area of triangle is ${triangle.calculateArea()}, its perimeter is ${triangle.perimeter}")

}





