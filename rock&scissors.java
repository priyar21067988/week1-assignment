import java.util.Random;
import java.util.Scanner;

/**
 * RockPaperScissorsGame
 * -----------------------
 * Plays N rounds of Rock-Paper-Scissors between the player and a
 * randomly-moving computer, records every round's result, then
 * prints a round-by-round scoreboard plus a final win/loss/draw
 * summary with win percentage.
 *
 * Concepts covered: random number generation, conditional logic,
 * loops, arrays for the round table, formatted/tabular output,
 * percentage calculation, checked exceptions.
 */
public class RockPaperScissorsGame {

    static final String[] VALID_MOVES = {"Rock", "Paper", "Scissors"};
    static final int TOTAL_ROUNDS = 5;

    // Custom CHECKED exception - an invalid move name is a
    // business-rule violation the caller is required to handle.
    static class InvalidMoveException extends Exception {
        public InvalidMoveException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] playerMoves = new String[TOTAL_ROUNDS];
        String[] computerMoves = new String[TOTAL_ROUNDS];
        String[] roundResults = new String[TOTAL_ROUNDS];

        for (int round = 0; round < TOTAL_ROUNDS; round++) {
            String computerMove = generateComputerMove(random);
            String playerMove = readValidPlayerMove(scanner, round + 1);
            String result = playRound(playerMove, computerMove);

            playerMoves[round] = playerMove;
            computerMoves[round] = computerMove;
            roundResults[round] = result;

            System.out.println("Round " + (round + 1) + " — Player: " + playerMove
                    + ", Computer: " + computerMove + " -> " + result);
        }

        printRoundSummaryTable(playerMoves, computerMoves, roundResults);
        printFinalStatistics(roundResults);

        scanner.close();
    }

    // =========================================================
    // Generates the computer's move randomly from the three
    // valid options.
    // =========================================================
    static String generateComputerMove(Random random) {
        int randomIndex = random.nextInt(VALID_MOVES.length);
        return VALID_MOVES[randomIndex];
    }

    // =========================================================
    // Reads and validates the player's move for one round, retrying
    // until valid. Declares/handles a CHECKED exception for
    // unrecognized move names.
    // =========================================================
    static String readValidPlayerMove(Scanner scanner, int roundNumber) {
        String validatedMove = null;
        boolean isValidMove = false;

        while (!isValidMove) {
            System.out.print("Round " + roundNumber + " - Enter your move (Rock/Paper/Scissors): ");
            String rawMove = scanner.nextLine();
            try {
                validatedMove = validateMove(rawMove);
                isValidMove = true;
            } catch (InvalidMoveException exception) {
                System.out.println("Invalid move: " + exception.getMessage());
            }
        }
        return validatedMove;
    }

    // Declares a CHECKED exception so the caller is forced to handle
    // a move name that isn't Rock, Paper, or Scissors.
    static String validateMove(String candidateMove) throws InvalidMoveException {
        String trimmedMove = candidateMove.trim();
        for (String validMove : VALID_MOVES) {
            if (validMove.equalsIgnoreCase(trimmedMove)) {
                return validMove; // return the properly-cased version
            }
        }
        throw new InvalidMoveException("'" + candidateMove + "' is not Rock, Paper, or Scissors.");
    }

    // =========================================================
    // Determines the winner of one round using standard rules.
    // Suggested method signature per the task.
    // =========================================================
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        boolean playerWins = (playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors"))
                || (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock"))
                || (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"));

        return playerWins ? "Player Wins" : "Computer Wins";
    }

    // =========================================================
    // Prints the round-by-round scoreboard using the recorded arrays.
    // =========================================================
    static void printRoundSummaryTable(String[] playerMoves, String[] computerMoves, String[] roundResults) {
        System.out.println("\nRound | Player Move | Computer Move | Result");
        System.out.println("------------------------------------------------");
        for (int round = 0; round < playerMoves.length; round++) {
            System.out.printf("%-5d | %-11s | %-14s | %s%n",
                    (round + 1), playerMoves[round], computerMoves[round], roundResults[round]);
        }
    }

    // =========================================================
    // Counts wins/losses/draws and prints the final statistics
    // including win percentage.
    // =========================================================
    static void printFinalStatistics(String[] roundResults) {
        int winCount = 0;
        int lossCount = 0;
        int drawCount = 0;

        for (String result : roundResults) {
            if
