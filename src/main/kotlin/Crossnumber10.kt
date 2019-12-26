import kotlin.math.sqrt

fun main() {
    Crossnumber10.lcmHcfThing()
}

object Crossnumber10
{
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
                    && gcd != y)
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