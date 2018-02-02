package de.kotlincook.sophiasearch

fun String.subString(from: Int) = if (from <= length) substring(from) else ""

fun String.subString(from: Int, to: Int) = if (from <= to && to <= length) substring(from, to) else ""

fun String.norm() = trim().toLowerCase()