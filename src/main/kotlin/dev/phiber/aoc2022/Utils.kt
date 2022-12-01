@file:Suppress("unused")
package dev.phiber.aoc2022


fun readAllLinesUntilDoubleEmpty() : List<String> =
    generateSequence(::readln)
        .windowed(2)
        .takeWhile { (first, second) -> first.isNotEmpty() || second.isNotEmpty() }
        .map { (first, _) -> first }
        .toList()

fun <T> Iterable<T>.splitOn(predicate: (T) -> Boolean) : List<List<T>> {
    val results: MutableList<MutableList<T>> = mutableListOf(mutableListOf())
    for (item in this) {
        if (predicate(item)) {
            if (results.last().isNotEmpty()) {
                results.add(mutableListOf())
            }
        } else {
            results.last().add(item)
        }
    }
    return if (results.last().isEmpty()) {
        results.subList(0, results.size - 1)
    } else {
        results
    }
}

fun <T : Comparable<T>> List<T>.maxIndex() : Int? {
    var max: T? = firstOrNull()
    var maxIndex: Int? = null
    for ((index, item) in this.withIndex()) {
        if (max!! < item) {
            max = item
            maxIndex = index
        }
    }
    return maxIndex
}
