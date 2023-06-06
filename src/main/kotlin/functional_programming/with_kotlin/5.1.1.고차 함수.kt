package functional_programming.with_kotlin

/**
 * 고차 함수: 함수를 인자로 받거나 결과로 반환하는 함수
 */

fun main() {
    println("sum: ${sum(intArrayOf(1, 2, 3))}")
    println("sum1: ${sum1(intArrayOf(1, 2, 3))}")
    println("max: ${max(intArrayOf(1, 2, 3))}")
}

// 람다는 주어진 인덱스에 따라 배열 원소 값을 계산한다.
val squares = IntArray(5) { n -> n * n }

// 어떤 정수 배열의 원소의 합계를 계산하는 함수
fun sum(numbers: IntArray): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) {
        result += numbers[i]
    }

    return result
}

/**
 * sum 함수를 더 일반화해보자.
 * 곱셈이나 최댓값/최솟값처럼 다양한 집계 함수를 사용하게 하려면 어떻게 해야 할까?
 * 함수 자체의 기본적인 루프 로직은 그대로 두고, 중간 값들을 함수의 파라미터로 호출 -> 일반화한 함수를 호출할 때 이 파라미터에 적당한 연산을 제공하면 된다.
 */
fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw java.lang.IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) {
        result = op(result, numbers[i])
    }

    return result
}

/**
 * op를 함수처럼 호출할 수 있다.
 * 여기서 op는 Int 값 한 쌍을 받아 Int를 결과로 내놓는 함수
 */
fun sum1(numbers: IntArray) = aggregate(numbers) { result, op -> result + op }
fun max(numbers: IntArray) = aggregate(numbers) { result, op -> if (op > result) op else result }