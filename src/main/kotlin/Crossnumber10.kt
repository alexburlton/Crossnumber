import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    println(Crossnumber10.AD22())
}

object Crossnumber10
{
    fun AD22() {
        val a22s = getAllPossibilities("X9X").filter { (it - 6).isPalindrome() }

        a22s.forEach { a22 ->
            val d22s = getAllPossibilities("XXX").filter { it.firstDigit() == a22.firstDigit() }
                .filter { it.nthDigitOneOf(1, 2, 4)}
                .filter { !it.isPrime() }
                .filter { it.primeFactorise().distinct().size == 1 }

            println("22A: $a22, 22D: $d22s")
        }
    }

    fun D27() {
        val possibles = getAllPossibilities("X1XX8").filter { it.nthDigitOneOf(3, 7, 8) }

        println("13: ${possibles.filter { it % 13 == 0 }}")
        println("23: ${possibles.filter { it % 23 == 0 }}")
    }

    fun A34() {
        val a34s = getAllPossibilities("1X")
        val a21s = getAllPossibilities("XX").filter { it.endsWith(0, 1, 2) }
        val a26s = getAllPossibilities("XX").filter { it.endsWith(0, 1, 2) }

        a34s.forEach { a34 ->
            a21s.forEach { a21 ->
                a26s.forEach { a26 ->
                    if (gcd(a26, a34) == a21
                        && gcd(a21, a34) == a26
                        && gcd(a21, a26) == a34) {
                            println("$a21, $a26, $a34")
                    }
                }
            }
        }
    }

    fun A12() {
        val a15Possibilities = getAllPossibilities("XXX").filter { it.nthDigitOneOf(0, 5, 4) }
            .filter { it.digitSum() == 7 }
            .filter { it.isReversible() }
            .filter { abs(it - it.reversed()) == 198 }

        println(a15Possibilities)
    }


    fun D9D2D1() {
        val d9Possibilities = getAllPossibilities("XXXX").filter { it.isPalindrome() && it.nthDigitOneOf(2, 4, 5, 6, 7) }

        d9Possibilities.forEach { d9 ->
            val d1Possibilities = getAllPossibilities("1X1XXX").filter { it % d9 == 0 }
            d1Possibilities.forEach { d1 ->
                val d2Possibilities = getAllPossibilities("XX9XX9").filter { it.nthDigitOneOf(0, 4, 8, 9) }
                    .filter { it % d1 == 0 }
                    .filter { it.nthDigitOneOf(3, 6, 7, 8, 9) }
                    .filter { it.getDigits()[3].toInt() > d9.getDigits()[2].toInt() }

                d2Possibilities.forEach { d2 ->
                    println("9D: $d9, 1D: $d1, 2D: $d2")
                }
            }
        }
    }

    fun A48() {
        val possibleParts = A37()

        possibleParts.forEach {
            val reversed = it.reversed()

            val sum = it + reversed
            if (sum.digitCount() == 4) {
                println("$it + $reversed = $sum")
            }
        }
    }

    fun A37(): List<Int> {
        val ret = mutableSetOf<Int>()
        val a49Possibles = A49()

        val newA49Possibilities = mutableSetOf<Int>()

        a49Possibles.forEach {
            val reversed = it.reversed()

            val difference = abs(it - reversed)
            if (difference.digitCount() == 3
                && difference.endsWith(4, 5)) {
                ret.add(difference)
                newA49Possibilities.add(it)
            }
        }

        println("A49: $newA49Possibilities")

        return ret.filter { it.isReversible() }.toList()
    }

    fun A49() = getAllPossibilities("X5X").filter { it.isReversible() && it.isDecreasing() }
        .filter { abs(it - it.reversed()) == 594 }
        .filter { it.endsWith(1, 2)}

    /**
     * Down to:
     *
     * 24, 36: GCD 12, LCM 72
     * 28, 42: GCD 14, LCM 84
     */
    fun lcmHcfThing() {
        val possibilities = getAllPossibilities("XX")

        possibilities.forEach { x ->
            possibilities.filter { it > x }.forEach { y ->
                val lcm = lcm(x, y)
                val gcd = gcd(x, y)

                if (lcm.digitCount() == 2
                    && gcd.digitCount() == 2
                    && lcm != x
                    && lcm != y
                    && gcd != x
                    && gcd != y
                    && (x.endsWith(9) || y.endsWith(9) || lcm.endsWith(x.firstDigit()) || lcm.endsWith(y.firstDigit())))
                {
                    println("$x, $y: GCD $gcd, LCM $lcm")
                }
            }
        }
    }

    fun A1(): List<Int> {
        val initialOptions = getAllPossibilities("XXXXX")
        val possibilities = mutableListOf<Int>()

        initialOptions.forEach {
            if (it.isAnagram(it * 9)) {
                possibilities.add(it)
            }
        }

        return possibilities
    }

    fun D6(): List<Int> {
        val ret = mutableListOf<Int>()

        val hypotenuse = 2665

        val possibilities = getAllPossibilities("26XX")
        possibilities.forEach {
            val remainder = hypotenuse.pow(2) - it.pow(2)
            if (remainder.isSquare())
            {
                if (it.getDigits()[1] != "0"
                    && it.getDigits()[3] != "0"
                    && it.getDigits()[3].toInt() <= 5)
                {
                    ret.add(it)
                }
            }
        }

        return ret
    }

    fun polynomialPossibilities(): List<Int> {
        val d50 = getAllPossibilities("XX")
        val a11 = getAllPossibilities("XXX")

        for (b in d50) {
            for (c in a11) {
                if (c.getDigits().first() == "2" || c.getDigits().first() == "7") {
                    continue
                }

                //b (50D) runs into 52A, which has all digits the same. So can't end with 0.
                if (b.getDigits()[1] == "0") {
                    continue
                }

                //Polynomial has to have valid roots
                val discriminant = b.pow(2) - (4*c)
                if (discriminant < 0 || !discriminant.isSquare()) {
                    continue
                }

                val rootOneD = (b.toDouble() + sqrt(discriminant.toDouble())) / 2
                val rootTwoD = (b.toDouble() - sqrt(discriminant.toDouble())) / 2

                if (!rootOneD.isInt() || !rootTwoD.isInt()) {
                    continue
                }

                val rootOne = rootOneD.toInt()
                val rootTwo = rootTwoD.toInt()
                if (rootOne.digitCount() != 2 || rootTwo.digitCount() != 2) {
                    continue
                }

                //7D and 8D come off from 3A, which is all ones or all twos
                if (rootOne.getDigits().first().toInt() != 2 || rootTwo.getDigits().first().toInt() != 2) {
                    continue
                }

                //11A overlaps with 7D and 8D
                val cDigits = c.getDigits()
                if (cDigits[1] == rootOne.getDigits()[1] && cDigits[2] == rootTwo.getDigits()[1]) {
                    println("x^2 - ${b}x + $c: ${rootOne}, $rootTwo")
                } else if (cDigits[1] == rootTwo.getDigits()[1] && cDigits[2] == rootOne.getDigits()[1]) {
                    println("x^2 - ${b}x + $c: ${rootOne}, $rootTwo")
                }


            }
        }

        return listOf()
    }
}