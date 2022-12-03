package dev.phiber.aoc2022.day2

import dev.phiber.aoc2022.readAllLinesUntilEmpty


enum class Outcome(val points: Int) {
    LOSS(0), TIE(3), WIN(6);

    companion object {

        fun forChar(char: Char) : Outcome = when (char) {
            'X' -> LOSS
            'Y' -> TIE
            'Z' -> WIN
            else -> throw RuntimeException("Invalid character.")
        }

    }

}

enum class Symbol(
    val points: Int,
    val evaluateOutcome: (opponentSymbol: Symbol) -> Outcome,
    val evaluatePlayerSymbol: (outcome: Outcome) -> Symbol
) {
    ROCK(
        points = 1,
        evaluateOutcome = { opponentSymbol -> when (opponentSymbol) {
            ROCK -> Outcome.TIE
            PAPER -> Outcome.LOSS
            SCISSORS -> Outcome.WIN
        } },
        evaluatePlayerSymbol = { outcome -> when (outcome) {
            Outcome.LOSS -> SCISSORS
            Outcome.TIE -> ROCK
            Outcome.WIN -> PAPER
        } }
    ),
    PAPER(
        points = 2,
        evaluateOutcome = { opponentSymbol -> when (opponentSymbol) {
            ROCK -> Outcome.WIN
            PAPER -> Outcome.TIE
            SCISSORS -> Outcome.LOSS
        } },
        evaluatePlayerSymbol = { outcome -> when (outcome) {
            Outcome.LOSS -> ROCK
            Outcome.TIE -> PAPER
            Outcome.WIN -> SCISSORS
        } }
    ),
    SCISSORS(
        points = 3,
        evaluateOutcome = { opponentSymbol -> when (opponentSymbol) {
            ROCK -> Outcome.LOSS
            PAPER -> Outcome.WIN
            SCISSORS -> Outcome.TIE
        } },
        evaluatePlayerSymbol = { outcome -> when (outcome) {
            Outcome.LOSS -> PAPER
            Outcome.TIE -> SCISSORS
            Outcome.WIN -> ROCK
        } }
    );

    companion object {

        fun forOpponentChar(opponentChar: Char) : Symbol = when (opponentChar) {
            'A' -> ROCK
            'B' -> PAPER
            'C' -> SCISSORS
            else -> throw RuntimeException("Invalid character.")
        }

        fun forPlayerChar(playerChar: Char) : Symbol = when (playerChar) {
            'X' -> ROCK
            'Y' -> PAPER
            'Z' -> SCISSORS
            else -> throw RuntimeException("Invalid character.")
        }

    }

}


fun main() {
    val lines: List<String> = readAllLinesUntilEmpty()

    val characterPairs: List<Pair<Char, Char>> = lines
        .map { line -> line.split(" ", limit=2) }
        .map { (opponentSymbolStr, playerSymbolStr) -> opponentSymbolStr[0] to playerSymbolStr[0] }

    val task1Matches: List<Pair<Symbol, Symbol>> = characterPairs
        .map { (opponentSymbolChar, playerSymbolChar) ->
            Symbol.forOpponentChar(opponentSymbolChar) to Symbol.forPlayerChar(playerSymbolChar)
        }
    val task1Points: Int = task1Matches.sumOf { (opponentSymbol, playerSymbol) ->
        playerSymbol.points + playerSymbol.evaluateOutcome(opponentSymbol).points
    }

    val task2Matches: List<Pair<Symbol, Outcome>> = characterPairs
        .map { (opponentSymbolChar, outcomeChar) ->
            Symbol.forOpponentChar(opponentSymbolChar) to Outcome.forChar(outcomeChar)
        }
    val task2Points: Int = task2Matches.sumOf { (opponentSymbol, outcome) ->
        outcome.points + opponentSymbol.evaluatePlayerSymbol(outcome).points
    }

    println("Task 1: $task1Points")
    println("Task 2: $task2Points")
}
