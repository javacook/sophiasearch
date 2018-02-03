package de.kotlincook.sophiasearch

/**
 * A wrapper around a string in order to compare its normalized form
 */
class NormalizedString(val str: String) : Comparable<NormalizedString> {
    val value = str.normalize()
    override fun compareTo(other: NormalizedString): Int {
        return value.compareTo(other.value)
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
        // TODO: Please fill in here your code...
        return navSet.map { it.str }
    }
}
