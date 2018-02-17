package de.kotlincook.sophiasearch

import kotlin.system.measureTimeMillis

/**
 * @param allElements set of elements where to search in
 */
class SophiaSearch(arg: Collection<String>) : Completable {

    val elements = arg.map { Pair(it, it.normalized()) }

    /**
     * Returns the search result of <code>input</code>, e.g. a collection of
     * words that match <code>input</code>. The result is ordered
     * by the similarity to <code>input</code> at the 1st level and
     * alphabetically at the 2nd level.
     * @see CrumbIndexResult
     */
    override fun complete(input: String): Collection<String> {
            val normedInput = input.normalized()
            var result: List<String> = ArrayList()
            println(measureTimeMillis {
                result = elements
                    .map { Triple(it.first, it.second, it.second.crumbIndexOf(normedInput)) }
                    .filter { it.third is CrumbIndexResult.Dist }
                    .sortedBy { it.third }
                    .map { it.first }
            })
            return result
    }
}
