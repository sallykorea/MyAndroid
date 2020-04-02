package test.kotline01

/*
    [List 의 기능]
 */
val nums= listOf<Int>(10, 5, 100, 65, 4 , 9)

fun main(){
    println("first : ${nums.first()}")
    println("last : ${nums.last()}")
    println("max : ${nums.max()}")
    println("min : ${nums.min()}")

    var nums2 = mutableListOf<Int>()
    for (num in 1..10){ //1..10 : 1부터10까지의 배열이라고 생각하면 된다.
        nums2.add(num)
    }

    println(nums2)
    nums2.shuffle() // 배열에 담겨 있는 값들이 무작위로 섞인다.
    println(nums2)
}