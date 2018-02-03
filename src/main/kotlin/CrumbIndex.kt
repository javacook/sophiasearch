package de.kotlincook.sophiasearch

import de.kotlincook.sophiasearch.CrumbIndexResult.*

sealed class CrumbIndexResult : Comparable<CrumbIndexResult> {
    object None : CrumbIndexResult()
    data class Dist(val value: Int) : CrumbIndexResult()

    override operator fun compareTo(other: CrumbIndexResult): Int {
        // TODO: Please fill in here your code...
        return 0
    }
}


fun String.crumbIndexOf(crumbs: String): CrumbIndexResult {
    // TODO: Please fill in here your code...
    return None
}