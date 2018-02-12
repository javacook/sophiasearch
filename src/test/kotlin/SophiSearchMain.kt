package de.kotlincook.sophiasearch


fun main(args: Array<String>) {
    val elems = FileUtils.loadResourceLines("/streets.txt").toList()
    val complete = SophiaSearch(elems).complete("Auf dem back")
    println(complete.toList())
}