package de.kotlincook.sophiasearch

import kotlin.system.measureTimeMillis

class NormedString(val str: String) : Comparable<NormedString> {
    val strNormed = str.norm()
    override fun compareTo(other: NormedString): Int {
        return strNormed.compareTo(other.strNormed)
    }
}

class WrappedNormedString(val normedString: NormedString,
                          val crumbIndexResult: CrumbIndexResult) : Comparable<WrappedNormedString>  {

    override fun compareTo(other: WrappedNormedString): Int {
        val firstLevel = crumbIndexResult.compareTo(other.crumbIndexResult)
        return if (firstLevel != 0) firstLevel else normedString.strNormed.compareTo(other.normedString.strNormed)
    }
}

class SophiSearch(val navSet: Collection<NormedString>) : Completable {

    override fun complete(input: String): Collection<String> {
            val normed = input.norm()
            var result: List<String> = ArrayList()
            println(measureTimeMillis {
                result = navSet
                    .map { WrappedNormedString(it, it.strNormed.crumbIndexOf(normed)) }
                    .filter { it.crumbIndexResult is CrumbIndexResult.Dist }
                    .sortedBy { it }
                    .map { it.normedString.str }
                    .toList()
            })
            return result
    }
}
