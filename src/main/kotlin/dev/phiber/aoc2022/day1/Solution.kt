package dev.phiber.aoc2022.day1

import dev.phiber.aoc2022.readAllLinesUntilDoubleEmpty
import dev.phiber.aoc2022.splitOn


fun main() {
    val lines: List<String> = readAllLinesUntilDoubleEmpty()
    val caloriesPerElve: List<Int> = lines
        .splitOn { it.isEmpty() }
        .map { calorieEntries -> calorieEntries.sumOf { it.toInt() } }
        .sortedDescending()

    println("Task 1: ${caloriesPerElve.first()}")
    println("Task 2: ${caloriesPerElve.take(3).sum()}")
}
