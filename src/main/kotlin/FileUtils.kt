package de.kotlincook.sophiasearch

import java.io.File
import java.net.URL
import java.nio.charset.Charset

object FileUtils {

    @Throws(IllegalArgumentException::class)
    fun loadResource(resourceName: String, charSet: Charset = Charsets.UTF_8): String {
        val resource: URL? = javaClass::class.java.getResource(resourceName)
        return resource?.readText(charSet) ?:
            throw IllegalArgumentException("Resource '${resourceName}' not found")
    }

    @Throws(IllegalArgumentException::class)
    fun loadResourceLines(resourceName: String, charSet: Charset = Charsets.UTF_8): Sequence<String> {
        return loadResource(resourceName, charSet).splitToSequence(System.lineSeparator())
    }

    fun loadFileLines(fileName: String, charSet: Charset = Charsets.UTF_8): Sequence<String> {
        return File(fileName).bufferedReader(charSet).lineSequence()
    }

    fun saveFile(content: String, fileName: String, charSet: Charset = Charsets.UTF_8) {
        File(fileName).bufferedWriter(charSet).use { it.write(content) }
    }

    fun saveFileLines(lines: Sequence<String>, fileName: String, charSet: Charset = Charsets.UTF_8) {
        File(fileName).bufferedWriter(charSet).use { out ->
            lines.forEach {
                out.write(it)
                out.newLine()
            }
        }
    }
}

