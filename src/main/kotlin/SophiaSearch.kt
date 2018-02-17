package de.kotlincook.sophiasearch

/**
 * @param allElements set of elements where to search in
 */
class SophiaSearch(val allElements: Collection<String>) : Completable {

    /**
     * Returns the search result of <code>input</code>, e.g. a collection of
     * words that match <code>input</code>. The result is ordered
     * by the similarity to <code>input</code> at the 1st level and
     * alphabetically at the 2nd level.
     * @see CrumbIndexResult
     */
    override fun complete(input: String): Collection<String> {
        // TODO: Please fill in your code here...
        return allElements.map { it.toString() }
    }
}
