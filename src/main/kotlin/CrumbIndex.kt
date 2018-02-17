package de.kotlincook.sophiasearch

import de.kotlincook.sophiasearch.CrumbIndexResult.*

sealed class CrumbIndexResult : Comparable<CrumbIndexResult> {
    object None : CrumbIndexResult()
    data class Dist(val value: Int) : CrumbIndexResult()

    override operator fun compareTo(other: CrumbIndexResult): Int {
        // TODO: Please fill in your code here...
        return 0
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
    // TODO: Please fill in your code here...
    return None
}