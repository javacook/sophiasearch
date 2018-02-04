package de.kotlincook.sophiasearch

import kotlin.system.measureTimeMillis

class NormalizedString(val str: String) : Comparable<NormalizedString> {
    val value = str.normalized()
    override fun compareTo(other: NormalizedString): Int {
        return value.compareTo(other.value)
    }
}

class WrappedNormedString(val normalizedString: NormalizedString,
                          val crumbIndexResult: CrumbIndexResult) : Comparable<WrappedNormedString>  {

    override fun compareTo(other: WrappedNormedString): Int {
        val firstLevel = crumbIndexResult.compareTo(other.crumbIndexResult)
        return if (firstLevel != 0) firstLevel else normalizedString.value.compareTo(other.normalizedString.value)
    }
}

class SophiSearch(val navSet: Collection<NormalizedString>) : Completable {

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
                result = navSet
                    .map { WrappedNormedString(it, it.value.crumbIndexOf(normed)) }
                    .filter { it.crumbIndexResult is CrumbIndexResult.Dist }
                    .sortedBy { it }
                    .map { it.normalizedString.str }
                    .toList()
            })
            return result
    }
}
