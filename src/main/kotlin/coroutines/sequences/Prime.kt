package coroutines.sequences.prime

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

val primes: Sequence<BigInteger> = sequence {
    val primes = mutableListOf<BigInteger>()
    var current = 2.toBigInteger()

    fun isPrime(num : BigInteger, primes : MutableList<BigInteger>) : Boolean {
        for (p in primes) {
            if (num % p == 0.toBigInteger()) {
                return false
            }
        }
        return true
    }

    while (true) {
        if (isPrime(current, primes)) {
            yield(current)
            primes.add(current)
        }
        current += 1.toBigInteger()
    }
}

class PrimesTest {
    @Test
    fun `should calculate the first 10 prime numbers`() {
        val primes = primes.take(10).toList()
        val expected = listOf(
            BigInteger("2"),
            BigInteger("3"),
            BigInteger("5"),
            BigInteger("7"),
            BigInteger("11"),
            BigInteger("13"),
            BigInteger("17"),
            BigInteger("19"),
            BigInteger("23"),
            BigInteger("29"),
        )
        assertEquals(expected, primes)
    }

    @Test(timeout = 1000)
    fun `should calculate 1000'th prime number`() {
        val prime = primes.drop(1000).first()
        assertEquals(BigInteger("7927"), prime)
    }
}
