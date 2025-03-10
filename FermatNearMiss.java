import java.util.Scanner;

/**
 * Title: Fermat Near Miss Finder
 * File: FermatNearMiss.java
 * External Files: None
 * Output Files: None
 * Programmers:  Matthew Kominkiewicz & Seth Gilmore
 * Emails: matthewgkominkiewi@lewisu.edu & sethagilmore@lewisu.edu
 * Course: SP25-CPSC-44000-LT1
 * Submission Date: 2/16/2025
 * Description: This program searches for "near misses" complacent to Fermat's Last Theorem
 *              by checking integer values of x, y, z, and n within user-defined ranges.
 * Resources Used: W3Schools was used to help with proper syntax
 */

public class FermatNearMiss {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for exponent n and validate input
        int n;
        do {
            System.out.print("Enter an exponent n (where 2 <= n <= 12): ");
            n = scanner.nextInt();
            // Ensuring n falls in the allowed range
        } while (n < 2 || n > 12);

        // Prompt user for upper bound k and validate input
        int k;
        do {
            System.out.print("Enter a value k (where k >= 10): ");
            k = scanner.nextInt();
            // Ensuring k meets the minimum requirement
        } while (k < 10);

        // Initialize tracking variables for the smallest relative miss found
        double smallestRelativeMiss = Double.MAX_VALUE;
        int bestX = 0, bestY = 0, bestZ = 0;
        long bestMiss = Long.MAX_VALUE;

        // Iterate through x and y values within range
        for (int x = 10; x <= k; x++) {
            for (int y = x; y <= k; y++) {
                long leftSide = (long) Math.pow(x, n) + (long) Math.pow(y, n);

                int z = (int) Math.pow(leftSide, 1.0 / n); // Approximate z

                long zPower = (long) Math.pow(z, n);
                long zNextPower = (long) Math.pow(z + 1, n);

                long miss1 = Math.abs(leftSide - zPower);
                long miss2 = Math.abs(zNextPower - leftSide);
                long miss;
                int candidateZ;

                if (miss1 < miss2) {
                    miss = miss1;
                    candidateZ = z;
                } else {
                    miss = miss2;
                    candidateZ = z + 1;
                }

                double relativeMiss = (double) miss / leftSide;

// Update only when a smaller relative miss is found
                if (relativeMiss < smallestRelativeMiss) {
                    smallestRelativeMiss = relativeMiss * 100;
                    bestX = x;
                    bestY = y;
                    bestZ = candidateZ;
                    bestMiss = miss;

                    System.out.printf("Near miss found: x=%d, y=%d, z=%d, miss=%d, relative miss=%.8f percent\n",
                            bestX, bestY, bestZ, bestMiss, smallestRelativeMiss);
                }
            }
        }

        // Display final smallest near miss found
        System.out.println("\nSmallest near miss found:");
        System.out.printf("x=%d, y=%d, z=%d, miss=%d, relative miss=%.8f percent\n",
                bestX, bestY, bestZ, bestMiss, smallestRelativeMiss);

        scanner.close();
    }
}
