package de.kotlincook.sophiasearch

fun String.subString(from: Int) = "" // TODO: Please fill in here your code...

fun String.subString(from: Int, to: Int) = "" // TODO: Please fill in here your code...

/**
 * A wrapper around a string in order to compare its normalized form
 */
class NormalizedString(val str: String) : Comparable<NormalizedString> {
    val value = str.trim().toLowerCase()
    override fun compareTo(other: NormalizedString): Int {
        return value.compareTo(other.value)
    }
    override fun toString() = str
}