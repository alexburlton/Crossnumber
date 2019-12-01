fun main() {
    val possibilities = Crossnumber10.A1()
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
}