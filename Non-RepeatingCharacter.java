import java.util.Scanner;

/**
 * FirstNonRepeatingCharacterFinder
 * ----------------------------------
 * Finds the first character in a string that appears exactly once,
 * scanning left to right, using array-based frequency counting.
 *
 * Concepts covered: character frequency counting, loops, array-based
 * counting, early-exit scanning, checked exceptions.
 */
public class FirstNonRepeatingCharacterFinder {

    // ASCII has 128 standard code points - large enough to count
    // every character frequency without a HashMap.
    static final int ASCII_TABLE_SIZE = 128;

    // Custom CHECKED exception - "no non-repeating character exists"
    // is a valid outcome of this search, not a bug, so the caller
    // is required to handle it explicitly rather than getting a
    // meaningless default char back.
    static class NoNonRepeatingCharacterException extends Exception {
        public NoNonRepeatingCharacterException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String inputText = scanner.nextLine();

        try {
            char firstUniqueCharacter = findFirstNonRepeatingChar(inputText);
            System.out.println("First Non-Repeating Character: '" + firstUniqueCharacter + "'");
        } catch (NoNonRepeatingCharacterException exception) {
            System.out.println(exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Counts how many times each character appears in the text,
    // using its ASCII code as the array index.
    // =========================================================
    static int[] countCharacterFrequencies(String text) {
        int[] characterFrequencies = new int[ASCII_TABLE_SIZE];

        for (int index = 0; index < text.length(); index++) {
            char currentCharacter = text.charAt(index);
            characterFrequencies[currentCharacter]++;
        }
        return characterFrequencies;
    }

    // =========================================================
    // Scans the text left to right (early-exit) and returns the
    // first character whose frequency is exactly 1. Declares a
    // CHECKED exception for the case where no such character exists.
    // Suggested method signature per the task.
    // =========================================================
    static char findFirstNonRepeatingChar(String text) throws NoNonRepeatingCharacterException {
        int[] characterFrequencies = countCharacterFrequencies(text);

        for (int index = 0; index < text.length(); index++) {
            char currentCharacter = text.charAt(index);
            if (characterFrequencies[currentCharacter] == 1) {
                return currentCharacter; // early exit on first match
            }
        }

        throw new NoNonRepeatingCharacterException("No Non-Repeating Character Found");
    }
}
