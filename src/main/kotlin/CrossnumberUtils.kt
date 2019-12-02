import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun Int.getDigits() = toString().toCharArray().map { it.toString() }

fun Int.isAnagram(other: Int) = getDigits().sorted() == other.getDigits().sorted()

fun Int.pow(pow: Int) = toDouble().pow(pow).toInt()

fun Int.getDigitsPaddedWithZeros(length: Int): List<String> {
    val str = toString().padStart(length, '0')
    return str.toCharArray().map { it.toString() }
}

fun Int.isSquare(): Boolean
{
    val root = sqrt(this.toDouble())

    val int = root.roundToInt()

    return int.pow(2) == this
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
