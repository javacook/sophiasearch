package de.kotlincook.sophiasearch


fun main(args: Array<String>) {
    val elems = FileUtils.loadResourceLines("/streets.txt").map { line -> NormalizedString(line.trim()) }.toList()
    val complete = SophiSearch(elems).complete("Auf dem back")
    println(complete.toList())
}