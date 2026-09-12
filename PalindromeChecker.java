import java.util.Scanner;

/**
 * PalindromeChecker
 * -------------------
 * Verifies whether a text is a palindrome using three independent
 * approaches - iterative comparison, recursion, and array reversal -
 * and confirms all three agree on the same input.
 *
 * Concepts covered: loops, recursion, array manipulation, string
 * comparison, checked exceptions.
 */
public class PalindromeChecker {

    // Custom CHECKED exception - an empty string has nothing
    // meaningful to check, so callers are required to handle it.
    static class EmptyTextException extends Exception {
        public EmptyTextException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to check: ");
        String inputText = scanner.nextLine();

        try {
            reportAllPalindromeChecks(inputText);
        } catch (EmptyTextException exception) {
            System.out.println("Cannot check: " + exception.getMessage());
        }

        scanner.close();
    }

    // =========================================================
    // Runs all three checks on the same input and prints them
    // together so agreement (or disagreement) is easy to see.
    // =========================================================
    static void reportAllPalindromeChecks(String text) throws EmptyTextException {
        if (text.isEmpty()) {
            throw new EmptyTextException("text cannot be empty.");
        }

        boolean iterativeResult = isPalindromeIterative(text);
        boolean recursiveResult = isPalindromeRecursive(text);
        boolean arrayReversalResult = isPalindromeArrayReversal(text);

        System.out.println("Iterative: " + describeResult(iterativeResult)
                + " | Recursive: " + describeResult(recursiveResult)
                + " | Array Reversal: " + describeResult(arrayReversalResult));
    }

    // Converts a boolean result into the display wording used in output.
    static String describeResult(boolean isPalindrome) {
        return isPalindrome ? "Palindrome" : "Not Palindrome";
    }

    // =========================================================
    // Approach 1: ITERATIVE - compare characters from both ends
    // moving toward the middle.
    // =========================================================
    static boolean isPalindromeIterative(String text) {
        int leftIndex = 0;
        int rightIndex = text.length() - 1;

        while (leftIndex < rightIndex) {
            if (text.charAt(leftIndex) != text.charAt(rightIndex)) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }
        return true;
    }

    // =========================================================
    // Approach 2: RECURSIVE - compare first and last characters,
    // then recurse on the shrinking inner substring.
    // =========================================================
    static boolean isPalindromeRecursive(String text) {
        // Base case: 0 or 1 character left means it's a palindrome.
        if (text.length() <= 1) {
            return true;
        }

        char firstCharacter = text.charAt(0);
        char lastCharacter = text.charAt(text.length() - 1);

        if (firstCharacter != lastCharacter) {
            return false;
        }

        String innerSubstring = text.substring(1, text.length() - 1);
        return isPalindromeRecursive(innerSubstring);
    }

    // =========================================================
    // Approach 3: ARRAY REVERSAL - convert to a char array, reverse
    // it, and compare the reversed array against the original text.
    // =========================================================
    static boolean isPalindromeArrayReversal(String text) {
        char[] originalCharacters = text.toCharArray();
        char[] reversedCharacters = reverseCharacterArray(originalCharacters);
        String reversedText = new String(reversedCharacters);

        return text.equals(reversedText);
    }

    // Single-purpose helper - reverses a char array using a
    // separate result array (does not mutate the input array).
    static char[] reverseCharacterArray(char[] characters) {
        char[] reversed = new char[characters.length];
        for (int index = 0; index < characters.length; index++) {
            reversed[index] = characters[characters.length - 1 - index];
        }
        return reversed;
    }
}
