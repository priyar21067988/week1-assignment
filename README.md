# week1-assignment
Java Practice Programs — Week 1 Assignment

This repository contains five standalone Java programs, each solving an independent problem while following consistent best practices: methods instead of main-only logic, meaningful variable/method names, and explicit handling of both checked and unchecked exceptions.

Programs
1. RockPaperScissorsGame.java

Scenario: A mini arcade module that plays 5 rounds of Rock-Paper-Scissors between the player and the computer, then prints a round-by-round scoreboard and final statistics.

Key method: String playRound(String playerMove, String computerMove)
Concepts: random number generation, conditional logic, loops, arrays for the round table, formatted/tabular output, percentage calculation
Exception handling: InvalidMoveException (checked) for unrecognized move names, validated in a retry loop

Sample output:

Round 1 — Player: Rock, Computer: Scissors -> Player Wins
Round 2 — Player: Paper, Computer: Paper -> Draw
Final Summary (after 5 rounds): Wins: 2 | Losses: 2 | Draws: 1 | Win % = 40.0%
2. PalindromeChecker.java

Scenario: A QA toolkit that verifies palindrome-detection logic using three independent approaches, which must all agree on the result.

Key methods: isPalindromeIterative(String text), isPalindromeRecursive(String text), isPalindromeArrayReversal(String text)
Concepts: loops, recursion, array manipulation, string comparison
Exception handling: EmptyTextException (checked) for blank input

Sample output:

Input: "madam"
Iterative: Palindrome | Recursive: Palindrome | Array Reversal: Palindrome
3. TeamBmiCalculator.java

Scenario: A corporate wellness report that calculates BMI and health status for a team of 10 employees using randomly generated height/weight data.

Key methods: String getBmiStatus(double bmi), void printWellnessReport(double[] heights, double[] weights)
Concepts: parallel arrays, arithmetic operations, conditional logic, formatted tabular output
Exception handling: MismatchedTeamDataException (checked) for unequal-length height/weight arrays

Sample output:

Person 1 | Height: 1.75 | Weight: 70.00 | BMI: 22.86 | Status: Normal
Person 2 | Height: 1.60 | Weight: 90.00 | BMI: 35.16 | Status: Obese
4. FirstNonRepeatingCharacterFinder.java

Scenario: A "Unique Letter Hunt" mini-game that finds the first character in a string that appears exactly once.

Key method: char findFirstNonRepeatingChar(String text)
Concepts: character frequency counting, loops, array-based counting (ASCII index), early-exit scanning
Exception handling: NoNonRepeatingCharacterException (checked) when every character repeats

Sample output:

Input: "swiss"   -> First Non-Repeating Character: 'w'
Input: "aabbcc"  -> No Non-Repeating Character Found
5. CustomerNameReverser.java

Scenario: A banking identity-verification exercise that reverses a customer's name for internal testing without modifying the original.

Key method: String reverseCustomerName(String customerName)
Concepts: String traversal, character array manipulation, string reconstruction
Exception handling: BlankCustomerNameException (checked) for blank names

Sample output:

Original Name: Sunil
Reversed Name: linuS
Common design patterns used across all programs
One method, one job — input reading, validation, core logic, and output printing are always separate methods; nothing but method calls lives in main().
Checked exceptions are used for expected "bad input" outcomes (blank text, mismatched array lengths, invalid moves) that the caller is required to handle.
Unchecked exceptions (e.g. NumberFormatException) are still caught where user input is parsed, even though the compiler doesn't require it.
Naming conventions — camelCase for variables/methods, PascalCase for classes, verb-first method names (readValidPlayerMove, calculateBmi, printWellnessReport).
How to compile and run

Each file is self-contained. From the project directory:

bash
javac RockPaperScissorsGame.java
java RockPaperScissorsGame

Repeat with the relevant filename for each program (PalindromeChecker, TeamBmiCalculator, FirstNonRepeatingCharacterFinder, CustomerNameReverser).
