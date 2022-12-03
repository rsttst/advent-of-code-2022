package dev.phiber.aoc2022.day3

import dev.phiber.aoc2022.readAllLinesUntilEmpty


fun itemPriority(item: Char) : Int = if (item < 'a') {
    (item - 'A') + 27
} else {
    (item - 'a') + 1
}

fun main() {
    val lines: List<String> = readAllLinesUntilEmpty()

    val doubleCompartmentItems: List<Char> = lines
        .map { line -> line.substring(0, line.length / 2) to line.substring(line.length / 2) }
        .map { (compartment1, compartment2) ->
            val compartment2Set: Set<Char> = compartment2.toSet()
            compartment1.first { item -> item in compartment2Set }
        }
    val task1PrioritySum = doubleCompartmentItems.sumOf(::itemPriority)

    val teamBadgeItems: List<Char> = lines
        .windowed(size = 3, step = 3)
        .map { (rucksack1, rucksack2, rucksack3) ->
            val rucksack2Set: Set<Char> = rucksack2.toSet()
            val rucksack3Set: Set<Char> = rucksack3.toSet()
            rucksack1.first { item -> item in rucksack2Set && item in rucksack3Set }
        }
    val task2PrioritySum = teamBadgeItems.sumOf(::itemPriority)

    println("Task 1: $task1PrioritySum")
    println("Task 2: $task2PrioritySum")
}
