package de.kotlincook.sophiasearch

import kotlin.system.measureTimeMillis


class SophiaSearch(arg: Collection<String>) : Completable {

    val elements = arg.map { it.normalized() }

    /**
     * Returns the search result of <code>input</code>, e.g. a collection of
     * words that match <code>input</code>. The result is ordered
     * by the similarity with <code>input</code> at the 1st level and
     * alphabetical at the 2nd level.
     * @see CrumbIndexResult
     */
    override fun complete(input: String): Collection<String> {
            val normedInput = input.normalized()
            var result: List<String> = ArrayList()
            println(measureTimeMillis {
                result = elements
                    .map { Pair(it, it.crumbIndexOf(normedInput)) }
                    .filter { it.second is CrumbIndexResult.Dist }
                    .sortedBy { it.second }
                    .map { it.first }
            })
            return result
    }
}
