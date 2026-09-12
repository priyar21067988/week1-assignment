import java.util.Scanner;

/**
 * CustomerNameReverser
 * ----------------------
 * A customer identity verification exercise: reverses a customer's
 * name for internal testing purposes while leaving the original
 * name unchanged.
 *
 * Concepts covered: String traversal, character array manipulation,
 * string reconstruction, checked exceptions.
 */
public class CustomerNameReverser {

    // Custom CHECKED exception - a blank name is not valid input
    // for identity verification, so callers must handle it.
    static class BlankCustomerNameException extends Exception {
        public BlankCustomerNameException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String rawCustomerName = scanner.nextLine();

        try {
            String customerName = validateCustomerName(rawCustomerName);
            String reversedName = reverseCustomerName(customerName);

            System.out.println("Original Name: " + customerName);
            System.out.println("Reversed Name: " + reversedName);
        } catch (BlankCustomerNameException exception) {
            System.out.println("Invalid name: " + exception.getMessage());
        }

        scanner.close();
    }

    // Declares a CHECKED exception so the caller is required to
    // handle a blank customer name before it reaches reversal logic.
    static String validateCustomerName(String candidateName) throws BlankCustomerNameException {
        if (candidateName.trim().isEmpty()) {
            throw new BlankCustomerNameException("customer name cannot be blank.");
        }
        return candidateName.trim();
    }

    // =========================================================
    // Reverses the given customer name using character array
    // manipulation, without modifying the original String
    // (Strings are immutable, so customerName itself is untouched).
    // Suggested method signature per the task.
    // =========================================================
    static String reverseCustomerName(String customerName) {
        char[] originalCharacters = customerName.toCharArray();
        char[] reversedCharacters = new char[originalCharacters.length];

        for (int index = 0; index < originalCharacters.length; index++) {
            reversedCharacters[index] = originalCharacters[originalCharacters.length - 1 - index];
        }

        return new String(reversedCharacters);
    }
}
