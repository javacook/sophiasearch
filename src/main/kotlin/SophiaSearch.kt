package de.kotlincook.sophiasearch

import kotlin.system.measureTimeMillis

class NormalizedString(override val original: String) : Wrapper<String, String> {
    override val wrapped = original.normalized()
    override fun compareTo(other: Wrapper<String, String>): Int {
        return wrapped.compareTo(other.wrapped)
    }
}

class WrappedNormedString(override val original: String, override val wrapped: CrumbIndexResult) :
        Wrapper<String, CrumbIndexResult>  {
    override fun compareTo(other: Wrapper<String, CrumbIndexResult>): Int {
        val firstLevel = wrapped.compareTo(other.wrapped)
        return if (firstLevel != 0) firstLevel else original.compareTo(other.original)
    }
}

interface Wrapper<T, S> : Comparable<Wrapper<T,S>> {
    val original: T
    val wrapped: S
}

class SophiSearch<T>(val elements: Collection<Wrapper<String, T>>) : Completable {

    /**
     * Returns the search result of <code>input</code>, e.g. a collection of
     * words that match <code>input</code>. The result is ordered
     * by the similarity with <code>input</code> at the 1st level and
     * alphabetical at the 2nd level.
     * @see CrumbIndexResult
     */
    override fun complete(input: String): Collection<String> {
            val normed = input.normalized()
            var result: List<String> = ArrayList()
            println(measureTimeMillis {
                result = elements
                    .map { WrappedNormedString(it.original, it.wrapped.toString().crumbIndexOf(normed)) }
                    .filter { it.wrapped is CrumbIndexResult.Dist }
                    .sortedBy { it }
                    .map { it.original }
                    .toList()
            })
            return result
    }
}
