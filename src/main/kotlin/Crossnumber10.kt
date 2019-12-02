fun main() {
    val possibilities = Crossnumber10.D6()
    println(possibilities)
}

object Crossnumber10
{
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

        val possibilities = getAllPossibilities("XXXX")
        possibilities.forEach {
            val remainder = hypotenuse.pow(2) - it.pow(2)
            if (remainder > 0 && remainder.isSquare())
            {
                ret.add(it)
            }
        }

        return ret
    }
}