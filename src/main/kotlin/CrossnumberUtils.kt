import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun Int.getDigits() = toString().toCharArray().map { it.toString() }
fun Int.digitCount() = getDigits().size

fun Int.isAnagram(other: Int) = getDigits().sorted() == other.getDigits().sorted()

fun Int.pow(pow: Int) = toDouble().pow(pow).toInt()

fun Int.getDigitsPaddedWithZeros(length: Int): List<String> {
    val str = toString().padStart(length, '0')
    return str.toCharArray().map { it.toString() }
}

fun Int.primeFactorise(): List<Int>
{
    val factors = mutableListOf<Int>()

    val primes = getPrimesUpTo(this)
    var remaining = this
    primes.forEach {
        while (remaining % it == 0) {
            remaining /= it
            factors.add(it)
        }
    }

    return factors.toList()
}

/**
 * Use the Euclidean algorithm to compute GCD / HCF
 */
fun gcd(x: Int, y: Int): Int
{
    if (x == y) return x

    var max = maxOf(x, y)
    var min = minOf(x, y)

    while (max % min != 0) {
        val newMin = max % min
        max = min
        min = newMin
    }

    return min
}

fun lcm(x: Int, y: Int): Int
{
    val xFactors = x.primeFactorise()
    val yFactors = y.primeFactorise()

    val allPrimesPresent = xFactors.union(yFactors)

    var lcm = 1
    allPrimesPresent.forEach { prime ->
        val maxCount = maxOf(xFactors.count { it == prime }, yFactors.count { it == prime })

        lcm *= prime.pow(maxCount)
    }

    return lcm
}

fun Int.isSquare(): Boolean
{
    if (this <= 0) {
        return false
    }

    val root = sqrt(this.toDouble())
    val int = root.roundToInt()
    return int.pow(2) == this
}

fun Double.isInt() = roundToInt().toDouble() == this

fun getPrimesUpTo(limit: Int): List<Int> = (2..limit).toList().filter { it.isPrime() }

fun Int.isPrime(): Boolean {
    if (this == 2) return true
    if (this % 2 == 0) return false

    val range = 3..(this/2) step 2
    return range.all { this % it != 0 }
}



/**
 * Takes a template - e.g. 5XX6X - and returns a list of all possibilities
 */
fun getAllPossibilities(template: String): List<Int> {
    val possibilities = mutableListOf<Int>()

    val numberOfReplacements = template.count { it == 'X' }

    for (i in 0 until 10.pow(numberOfReplacements)) {
        val digits = i.getDigitsPaddedWithZeros(numberOfReplacements)

        var substituted = template
        digits.forEach { substituted = substituted.replaceFirst("X", it) }

        if (!substituted.startsWith("0")) {
            possibilities.add(substituted.toInt())
        }
    }

    return possibilities
}
