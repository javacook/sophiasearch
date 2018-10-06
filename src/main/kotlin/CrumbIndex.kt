package de.kotlincook.sophiasearch

import de.kotlincook.sophiasearch.CrumbIndexResult.*

sealed class CrumbIndexResult : Comparable<CrumbIndexResult> {
    object None : CrumbIndexResult()
    data class Dist(val value: Int) : CrumbIndexResult()

    override operator fun compareTo(other: CrumbIndexResult): Int {
        return when {
            this is None -> Int.MIN_VALUE
            other is None -> Int.MAX_VALUE
            else -> {
                val thisValue = (this as Dist).value
                val otherValue = (other as Dist).value
                val to = "$thisValue, $otherValue"
                return thisValue - otherValue
            }
        }
    }

    operator fun plus(other: CrumbIndexResult): CrumbIndexResult {
        return when {
            this is None -> None
            other is None -> None
            else -> {
                val thisValue = (this as Dist).value
                val otherValue = (other as Dist).value
                return Dist(thisValue + otherValue)
            }
        }
    }
}

/**
 * This measure sums the gaps between the matching letters of <code>crumbs</code> which are spread over "this".
 * The distance from the beginning to first containing letter should be included, the distance until the end not.
 * In this example 2 + 2 + 3 + 4 = 11.
 * <pre>
 * This is an example  = this
 *   i  i   n    m     = crumbs
 * |2|-2|-3-|--4-|-0|
 * |---- 11 -----|
 * </pre>
 */
fun String.crumbIndexOf(crumbs: String): CrumbIndexResult {
    tailrec fun crumbIndexOf(word: String, crumbs: String, akku: CrumbIndexResult): CrumbIndexResult {
        if (word == "" && crumbs != "") return None;
        if (word == "" || crumbs == "") return akku;
        val idx = word.indexOf(crumbs[0])
        if (idx < 0) return None
        return crumbIndexOf(word.subString(idx + 1), crumbs.subString(1), Dist(idx) + akku);
    }
    return crumbIndexOf(this, crumbs, Dist(0))
}


fun String.crumbIndexOfSimple(crumbs: String): CrumbIndexResult {
    if (crumbs == "") return Dist(0)
    val idx = indexOf(crumbs[0])
    return if (idx < 0) None else Dist(idx) + subString(idx+1).crumbIndexOfSimple(crumbs.subString(1))
}