package dev.phiber.aoc2022.day4

import dev.phiber.aoc2022.readAllLinesUntilEmpty


fun parseRange(rangeStr: String) : IntRange {
    val (rangeStartStr, rangeEndStr) = rangeStr.split("-", limit = 2)
    return rangeStartStr.toInt()..rangeEndStr.toInt()
}

infix fun IntRange.fullyContains(other: IntRange) : Boolean = this.first <= other.first && this.last >= other.last

infix fun IntRange.intersects(other: IntRange) : Boolean = this.first <= other.last && this.last >= other.first

fun main() {
    val lines: List<String> = readAllLinesUntilEmpty()

    val sectionRangePairs: List<Pair<IntRange, IntRange>> = lines
        .map { line -> line.split(",", limit = 2) }
        .map { (sectionRangeStr1, sectionRangeStr2) -> parseRange(sectionRangeStr1) to parseRange(sectionRangeStr2) }

    val sectionFullContainmentCount: Int = sectionRangePairs.count { (sectionRange1, sectionRange2) ->
        sectionRange1 fullyContains sectionRange2 || sectionRange2 fullyContains sectionRange1
    }

    val sectionIntersectionCount: Int = sectionRangePairs.count { (sectionRange1, sectionRange2) ->
        sectionRange1 intersects sectionRange2
    }

    println("Task 1: $sectionFullContainmentCount")
    println("Task 2: $sectionIntersectionCount")
}
