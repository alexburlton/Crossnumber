import io.kotlintest.matchers.collections.shouldBeOneOf
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.collections.shouldNotBeOneOf
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.matchers.numerics.shouldBeLessThan
import io.kotlintest.shouldBe
import org.junit.Test
import kotlin.math.abs

private const val a1 = 10890
private const val a3 = 222222222
private const val a10 = 28
private const val a11 = 675
private const val a12 = 1089
private const val a13 = 258
private const val a14 = 9754320
private const val a15 = 412
private const val a18 = 4119
private const val a21 = 10
private const val a22 = 107
private const val a23 = 967896301
private const val a24 = 42
private const val a25 = 198
private const val a26 = 10
private const val a27 = 891871651431211L
private const val a32 = 14
private const val a33 = 720
private const val a34 = 10
private const val a35 = 745634123
private const val a37 = 594
private const val a40 = 84
private const val a41 = 5559
private const val a43 = 891
private const val a45 = 2618169
private const val a47 = 495
private const val a48 = 1089
private const val a49 = 852
private const val a51 = 49
private const val a52 = 222222222
private const val a53 = 10890

private val acrossClues: List<Number> = listOf(a1, a3, a10, a11, a12, a13, a14, a15, a18, a21, a22, a23, a24, a25, a26, a27, a32, a33, a34,
    a35, a37, a40, a41, a43, a45, a47, a48, a49, a51, a52, a53)

private const val d1 = 111100
private const val d2 = 999900
private const val d4 = 225247
private const val d5 = 28701814
private const val d6 = 2664
private const val d7 = 27
private const val d8 = 25
private const val d9 = 5555
private const val d13 = 23
private const val d16 = 110110
private const val d17 = 20101
private const val d19 = 1993
private const val d20 = 9681
private const val d22 = 121
private const val d24 = 494449
private const val d27 = 81788
private const val d28 = 8765
private const val d29 = 7235
private const val d30 = 10452442
private const val d31 = 214
private const val d36 = 196992
private const val d38 = 999180
private const val d39 = 999900
private const val d42 = 4119
private const val d44 = 1222
private const val d46 = 15
private const val d49 = 82
private const val d50 = 52

class Crossnumber10Test
{
    @Test
    fun `1 across`()
    {
        a1.digitCount() shouldBe 5
        (a1 * 9).isAnagram(a1) shouldBe true
    }

    @Test
    fun `2 across`()
    {
        a3.digitCount() shouldBe 9
        a3.getDigits().distinct().size shouldBe 1
    }

    @Test
    fun `10 across`()
    {
        a10.digitCount() shouldBe 2
        a10.shouldNotBeOneOf(a32, a40)
    }

    @Test
    fun `11 across`()
    {
        a11.digitCount() shouldBe 3
        a11 % d8 shouldBe 0
    }

    @Test
    fun `12 across`()
    {
        a12.digitCount() shouldBe 4
        a12 shouldBe a25 + a43
    }

    @Test
    fun `13 across`()
    {
        a13.digitCount() shouldBe 3
        a13.isReversible() shouldBe true
        a13.reversed() shouldBe a49
    }

    @Test
    fun `14 across`()
    {
        a14.digitCount() shouldBe 7
        val digits = a14.getDigits()
        digits.forEachIndexed { ix, digitStr ->
            if (ix > 0)
            {
                val prevDigit = digits[ix - 1].toInt()
                val digit = digitStr.toInt()

                digit.shouldBeOneOf(prevDigit - 1, prevDigit - 2)
            }
        }
    }

    @Test
    fun `15 across`()
    {
        a15.digitCount() shouldBe 3
        a15.isReversible() shouldBe true
        a15.reversed() shouldBe d31
    }

    @Test
    fun `18 across`()
    {
        a18.digitCount() shouldBe 4
        (a18 - 5).isPalindrome() shouldBe true
    }

    @Test
    fun `21 across`()
    {
        a21.digitCount() shouldBe 2
        a21 shouldBe gcd(a26, a34)
    }

    @Test
    fun `22 across`()
    {
        a22.digitCount() shouldBe 3
        (a22 - 6).isPalindrome() shouldBe true
    }

    @Test
    fun `23 across`()
    {
        a23.digitCount() shouldBe 9

        val digits = a23.getDigits()
        digits.forEachIndexed { ix, digitStr ->
            if (ix > 0)
            {
                val prevDigit = digits[ix - 1].toInt()
                val digit = digitStr.toInt()

                digit.shouldBeOneOf(prevDigit + 1, prevDigit - 3)
            }
        }
    }

    @Test
    fun `24 across`()
    {
        a24.digitCount() shouldBe 2
        a24.shouldNotBeOneOf(a32, a40)
    }

    @Test
    fun `25 across`()
    {
        a25.digitCount() shouldBe 3
        a25 shouldBe abs(d31 - a15)
    }

    @Test
    fun `26 across`()
    {
        a26.digitCount() shouldBe 2
        a26 shouldBe gcd(a21, a34)
    }

    @Test
    fun `27 across`()
    {
        a27.digitCount() shouldBe 15

        val digits = a27.getDigits()
        val middleDigits = digits.subList(1, digits.size - 1)
        println(middleDigits)

        //Index is actually ix - 1
        middleDigits.forEachIndexed { ix, digitStr ->
            val prevDigit = digits[ix].toInt()
            val nextDigit = digits[ix + 2].toInt()
            val digit = digitStr.toInt()

            digit.shouldBeOneOf(abs(prevDigit - nextDigit), prevDigit + nextDigit)
        }
    }

    @Test
    fun `32 across`()
    {
        a32.digitCount() shouldBe 2
        a32 shouldBe gcd(a10, a24)
    }

    @Test
    fun `33 across`()
    {
        a33.digitCount() shouldBe 3
        (a33 - 3).isPalindrome() shouldBe true
    }

    @Test
    fun `34 across`()
    {
        a34.digitCount() shouldBe 2
        a34 shouldBe gcd(a21, a26)
    }

    @Test
    fun `35 across`()
    {
        a35.digitCount() shouldBe 9

        val digits = a35.getDigits()
        digits.forEachIndexed { ix, digitStr ->
            if (ix > 0)
            {
                val prevDigit = digits[ix - 1].toInt()
                val digit = digitStr.toInt()

                digit.shouldBeOneOf(prevDigit + 1, prevDigit - 3)
            }
        }
    }

    @Test
    fun `37 across`()
    {
        a37.digitCount() shouldBe 3
        a37 shouldBe abs(a49 - a13)
    }

    @Test
    fun `40 across`()
    {
        a40.digitCount() shouldBe 2
        a40 shouldBe lcm(a10, a24)
    }

    @Test
    fun `41 across`()
    {
        a41.digitCount() shouldBe 4
        (a41 - 4).isPalindrome() shouldBe true
    }

    @Test
    fun `43 across`()
    {
        a43.digitCount() shouldBe 3
        a43.isReversible() shouldBe true
        a43.reversed() shouldBe a25
    }

    @Test
    fun `45 across`()
    {
        a45.digitCount() shouldBe 7
        (a45 - 7).isPalindrome() shouldBe true
    }

    @Test
    fun `47 across`()
    {
        a47.digitCount() shouldBe 3
        a47.isReversible() shouldBe true
        a47.reversed() shouldBe a37
    }

    @Test
    fun `48 across`()
    {
        a48.digitCount() shouldBe 4
        a48 shouldBe a47 + a37
    }

    @Test
    fun `49 across`()
    {
        a49.digitCount() shouldBe 3
        a49.isDecreasing() shouldBe true
    }

    @Test
    fun `51 across`()
    {
        a51.digitCount() shouldBe 2
        a51 shouldBe a23.digitSum()
    }

    @Test
    fun `52 across`()
    {
        a52.digitCount() shouldBe 9
        a52.getDigits().distinct().size shouldBe 1
    }

    @Test
    fun `53 across`()
    {
        a53.digitCount() shouldBe 5
        a53 shouldBe a1
    }

    @Test
    fun `Number of across clues`()
    {
        acrossClues.size shouldBe 31
        println(acrossClues.sumByDouble { it.toDouble() }.toLong() )
    }


    /**
     * Down Clues
     */
    @Test
    fun `1 down`()
    {
        d1.digitCount() shouldBe 6
        d1 % d9 shouldBe 0
    }

    @Test
    fun `2 down`()
    {
        d2.digitCount() shouldBe 6
        d2 % d1 shouldBe 0
    }

    @Test
    fun `4 down`()
    {
        d4.digitCount() shouldBe 6

        val a33digits = a33.getDigits().distinct()
        a33digits.any { a33Digit -> d4.getDigits().count { it == a33Digit} > 1 } shouldBe true
    }

    @Test
    fun `5 down`()
    {
        d5.digitCount() shouldBe 8

        (d5 + a23 + d30).shouldBeGreaterThan(1000000000)
    }

    @Test
    fun `6 down`()
    {
        d6.digitCount() shouldBe 4
        (2665.pow(2) - d6.pow(2)).isSquare() shouldBe true
    }

    @Test
    fun `7 down`()
    {
        d7.digitCount() shouldBe 2
        d7.pow(2) - (d50 * d7) + a11 shouldBe 0
    }

    @Test
    fun `8 down`()
    {
        d8.digitCount() shouldBe 2
        d8.pow(2) - (d50 * d8) + a11 shouldBe 0
    }

    @Test
    fun `9 down`()
    {
        d9.digitCount() shouldBe 4
        d9.isPalindrome() shouldBe true
    }

    @Test
    fun `13 down`()
    {
        d13.digitCount() shouldBe 2
        d13.isPrime() shouldBe true
    }

    @Test
    fun `16 down`()
    {
        d16.digitCount() shouldBe 6

        val digits = d16.getDigits()
        val middleDigits = digits.subList(1, digits.size - 1)

        //Index is actually ix - 1
        middleDigits.forEachIndexed { ix, digitStr ->
            val prevDigit = digits[ix].toInt()
            val nextDigit = digits[ix + 2].toInt()
            val digit = digitStr.toInt()

            digit.shouldBeOneOf(abs(prevDigit - nextDigit), prevDigit + nextDigit)
        }
    }

    @Test
    fun `17 down`()
    {
        d17.digitCount() shouldBe 5
        d17.digitSum() shouldBe d17.firstDigit().pow(2)
    }

    @Test
    fun `19 down`()
    {
        d19.digitCount() shouldBe 4
        (d19 - 2).isPalindrome() shouldBe true
    }

    @Test
    fun `20 down`()
    {
        d20.digitCount() shouldBe 4
        (d20 + a33).shouldBeGreaterThan(10000)
    }

    @Test
    fun `22 down`()
    {
        d22.digitCount() shouldBe 3
        d22.isPrime() shouldBe false
        d22.primeFactorise().distinct().size shouldBe 1
    }

    @Test
    fun `24 down`()
    {
        d24.digitCount() shouldBe 6
        d24.getDigits().distinct().size shouldBe 2
    }

    @Test
    fun `27 down`()
    {
        d27.digitCount() shouldBe 5
        d27 % d13 shouldBe 0
    }

    @Test
    fun `28 down`()
    {
        d28.digitCount() shouldBe 4

        val digits = d28.getDigits()
        digits.forEachIndexed { ix, digitStr ->
            if (ix > 0)
            {
                val prevDigit = digits[ix - 1].toInt()
                val digit = digitStr.toInt()

                digit shouldBe prevDigit - 1
            }
        }
    }

    @Test
    fun `29 down`()
    {
        d29.digitCount() shouldBe 4
        (d29 - 8).isPalindrome() shouldBe true
    }

    @Test
    fun `30 down`()
    {
        d30.digitCount() shouldBe 8
        d30 % a22.lastDigit() shouldBe 0
    }

    @Test
    fun `31 down`()
    {
        d31.digitCount() shouldBe 3
        d31.digitSum() shouldBe 7
    }

    @Test
    fun `36 down`()
    {
        d36.digitCount() shouldBe 6
        d36.shouldBeLessThan(d4)
        d36 % 9 shouldBe 0
    }

    @Test
    fun `38 down`()
    {
        d38.digitCount() shouldBe 6
        d38 shouldBe d39 - a33
    }

    @Test
    fun `39 down`()
    {
        d39.digitCount() shouldBe 6
        d39 shouldBe d2
    }

    @Test
    fun `42 down`()
    {
        d42.digitCount() shouldBe 4
        d42 shouldBe a18
    }

    @Test
    fun `44 down`()
    {
        d44.digitCount() shouldBe 4
        (d44 - 1).isPalindrome() shouldBe true
    }

    @Test
    fun `46 down`()
    {
        d46.digitCount() shouldBe 2
        a14 % d46 shouldBe 0
    }

    @Test
    fun `49 down`()
    {
        d49.digitCount() shouldBe 2

        a45.getDigits().shouldContain(d49.getDigits().first())
    }

    @Test
    fun `50 down`()
    {
        d50.digitCount() shouldBe 2
        d50.shouldBeGreaterThan(d8)
    }
}