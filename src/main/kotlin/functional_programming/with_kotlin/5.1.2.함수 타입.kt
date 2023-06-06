package functional_programming.with_kotlin

/**
 * 함수 타입: 함수처럼 쓰일 수 있는 값들을 표시하는 타입
 *  1. 괄호로 둘러싸인 파라미터 타입 목록은 함숫값에 전달될 데이터의 종류와 수를 정의한다.
 *  2. 반환 타입은 함수 타입의 함숫값을 호출하면 돌려 받게 되는 값의 타입을 정의한다.
 *  e.g. (Int, Int) -> Boolean 이라는 타입은 인자로 정수 한 쌍 받아서 결과로 Boolean 값을 계산하는 함수
 */

fun main() {
    val consume = StringConsumer { s -> println(s) }
    consume.accept("Hello")

    /**
     * inner: (Int) -> Int = { i -> i + n }
     * outer: (Int) -> { n -> { inner } }
     */
    val shifter: (Int) -> (Int) -> Int = { n -> { i -> i + n } }
    val inc = shifter(1)
    val dec = shifter(-1)

    println("inc: ${inc(10)}")
    println("dec: ${dec(10)}")
}

// 인자를 받지 않는 함수는 함수 타입의 파라미터 목록에 빈 괄호 사용
fun measureTime(action: () -> Unit): Long {
    val start = System.nanoTime()
    action()
    return System.nanoTime() - start
}

// 널이 될 수 있는 타입으로 함수를 받고 싶을 때는 함수 타입 전체를 괄호로 둘러싼 다음 물음표를 붙인다.
fun measureTime1(action: (() -> Unit)?): Long {
    val start = System.nanoTime()

    action?.invoke()

    return System.nanoTime() - start
}

// 파라미터 타입을 둘러싼 괄호는 필수
val inc: (Int) -> Int = { n -> n + 1 }
// val dec: Int -> Int = { n -> n - 1 } <- error

/**
 * Java vs Kotlin
 * Java는 단일 추상 메서드(Single Abstract Method, SAM) 인터페이스를 문맥에 따라 적절히 함수 타입처럼 취급한다.
 *      -> 람다식이나 메서드 참조로 SAM 인터페이스를 인스턴스화 할 수 있다.
 * Kotlin은 항상 (P1, ..., Pn) -> R 형태의 함수 타입에 속하기 때문에 임의의 SAM 인터페이스로 암시젹 변환이 불가능하다.
 *
 * Kotlin 1.4부터는 코틀린 인터페이스 앞에 fun을 붙이면 코틀린 인터페이스를 SAM 인터페이스로 취급한다.
 */
fun interface StringConsumer {
    fun accept(s: String)
}
