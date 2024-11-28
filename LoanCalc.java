// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;   // Number of iterations 

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java LoanCalc <loan> <rate> <periods>");
            return;
        }

        // Parse input
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]) / 100 / 12; // Monthly interest rate
        int n = Integer.parseInt(args[2]);

        System.out.println("Loan = " + loan + ", interest rate = " + rate * 12 * 100 + "%, periods = " + n);

        // Brute Force Solver
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("Number of iterations: " + iterationCounter);

        // Bisection Solver
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("Number of iterations: " + iterationCounter);
    }

    // Computes the ending balance of a loan
    private static double endBalance(double loan, double monthlyRate, int n, double payment) {
        for (int i = 0; i < n; i++) {
            loan = loan * (1 + monthlyRate) - payment;
        }
        return loan;
    }

    // Brute Force Solver with dynamic increment
    public static double bruteForceSolver(double loan, double monthlyRate, int n, double epsilon) {
        iterationCounter = 0;
        double g = loan / n; // Initial guess
        double increment = epsilon * 100; // Start with a larger increment

        while (Math.abs(endBalance(loan, monthlyRate, n, g)) > epsilon) {
            g += increment;
            iterationCounter++;

            // Reduce increment as we approach the solution
            if (iterationCounter % 1000 == 0) {
                increment = Math.max(epsilon, increment / 10);
            }
        }
        return g;
    }

    // Bisection Solver
    public static double bisectionSolver(double loan, double monthlyRate, int n, double epsilon) {
        iterationCounter = 0;
        double low = 0;
        double high = loan;
        double mid = 0;

        while (Math.abs(high - low) > epsilon) {
            mid = (low + high) / 2;
            double balance = endBalance(loan, monthlyRate, n, mid);

            if (balance > 0) {
                low = mid; // Payment too low
            } else {
                high = mid; // Payment too high
            }

            iterationCounter++;
        }

        return (low + high) / 2;
    }
}
