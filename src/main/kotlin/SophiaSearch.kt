package de.kotlincook.sophiasearch

/**
 * A wrapper around a string in order to compare its normed
 */
class NormelizedString(val str: String) : Comparable<NormelizedString> {
    val normalized = str.normalize()
    override fun compareTo(other: NormelizedString): Int {
        return normalized.compareTo(other.normalized)
    }
}

class SophiSearch(val navSet: Collection<NormelizedString>) : Completable {

    override fun complete(input: String): Collection<String> {

        // TODO: Please fill in here your code

        return navSet.map { it.str }
    }
}
