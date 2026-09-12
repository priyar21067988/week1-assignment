import java.util.Random;

/**
 * TeamBmiCalculator
 * -------------------
 * Generates height and weight data for a wellness-check team,
 * calculates each person's BMI, classifies their health status,
 * and prints a formatted wellness report table.
 *
 * Concepts covered: parallel arrays, arithmetic operations,
 * conditional logic, formatted tabular output, random number
 * generation, checked exceptions.
 */
public class TeamBmiCalculator {

    static final int TEAM_SIZE = 10;
    static final double MINIMUM_HEIGHT_METERS = 1.50;
    static final double MAXIMUM_HEIGHT_METERS = 2.00;
    static final double MINIMUM_WEIGHT_KG = 45.0;
    static final double MAXIMUM_WEIGHT_KG = 120.0;

    // Custom CHECKED exception - heights and weights are parallel
    // arrays, so a length mismatch is a business-rule violation
    // callers are required to handle.
    static class MismatchedTeamDataException extends Exception {
        public MismatchedTeamDataException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        double[] heights = generateRandomHeights(random, TEAM_SIZE);
        double[] weights = generateRandomWeights(random, TEAM_SIZE);

        try {
            printWellnessReport(heights, weights);
        } catch (MismatchedTeamDataException exception) {
            System.out.println("Cannot generate report: " + exception.getMessage());
        }
    }

    // =========================================================
    // Generates random height/weight values for a fast live demo,
    // each in its own single-purpose method.
    // =========================================================
    static double[] generateRandomHeights(Random random, int teamSize) {
        double[] heights = new double[teamSize];
        for (int index = 0; index < teamSize; index++) {
            double randomHeight = MINIMUM_HEIGHT_METERS
                    + random.nextDouble() * (MAXIMUM_HEIGHT_METERS - MINIMUM_HEIGHT_METERS);
            heights[index] = roundToTwoDecimals(randomHeight);
        }
        return heights;
    }

    static double[] generateRandomWeights(Random random, int teamSize) {
        double[] weights = new double[teamSize];
        for (int index = 0; index < teamSize; index++) {
            double randomWeight = MINIMUM_WEIGHT_KG
                    + random.nextDouble() * (MAXIMUM_WEIGHT_KG - MINIMUM_WEIGHT_KG);
            weights[index] = roundToTwoDecimals(randomWeight);
        }
        return weights;
    }

    static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // =========================================================
    // Calculates BMI for one person.
    // BMI = weight / (height * height)
    // =========================================================
    static double calculateBmi(double heightMeters, double weightKg) {
        return weightKg / (heightMeters * heightMeters);
    }

    // =========================================================
    // Classifies a BMI value into its health status category.
    // Suggested method signature per the task.
    // =========================================================
    static String getBmiStatus(double bmi) {
        final double UNDERWEIGHT_MAX = 18.5;
        final double NORMAL_MAX = 24.9;
        final double OVERWEIGHT_MAX = 29.9;

        if (bmi < UNDERWEIGHT_MAX) {
            return "Underweight";
        } else if (bmi <= NORMAL_MAX) {
            return "Normal";
        } else if (bmi <= OVERWEIGHT_MAX) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // =========================================================
    // Builds and prints the full wellness report table using the
    // two parallel arrays. Declares a CHECKED exception so callers
    // must handle mismatched array lengths.
    // =========================================================
    static void printWellnessReport(double[] heights, double[] weights) throws MismatchedTeamDataException {
        if (heights.length != weights.length) {
            throw new MismatchedTeamDataException("heights and weights arrays must be the same length ("
                    + heights.length + " vs " + weights.length + ").");
        }

        System.out.println("Person   | Height (m) | Weight (kg) | BMI   | Status");
        System.out.println("---------------------------------------------------------");

        for (int index = 0; index < heights.length; index++) {
            double bmi = calculateBmi(heights[index], weights[index]);
            String status = getBmiStatus(bmi);

            System.out.printf("Person %-2d| %-10.2f | %-11.2f | %-5.2f | %s%n",
                    (index + 1), heights[index], weights[index], bmi, status);
        }
    }
}
