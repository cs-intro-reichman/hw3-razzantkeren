// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    // Gets the loan data and computes the periodical payment.
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java LoanCalc <loan> <rate> <periods>");
            System.out.println("Example: java LoanCalc 120000 3.5 60");
            return;
        }

        // Gets the loan data
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Computes the periodical payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
        System.out.println("Number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
        System.out.println("Number of iterations: " + iterationCounter);
    }

    // Computes the ending balance of a loan
    private static double endBalance(double loan, double rate, int n, double payment) {
        double total = loan;
        rate = 1 + rate / 100 / 12; // Convert annual rate to monthly
        for (int i = 0; i < n; i++) {
            total = total * rate - payment;
        }
        return total;
    }

    // Brute force solver
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double g = loan / n; // Initial guess
        while (Math.abs(endBalance(loan, rate, n, g)) > epsilon) {
            g += epsilon; // Increment payment
            iterationCounter++;
        }
        return g;
    }

    // Bisection solver
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double low = 0;
        double high = loan;
        double mid = 0;

        while (Math.abs(high - low) > epsilon) {
            mid = (low + high) / 2;
            double balanceAtMid = endBalance(loan, rate, n, mid);

            if (balanceAtMid > 0) {
                low = mid; // Payment too low
            } else {
                high = mid; // Payment too high
            }

            iterationCounter++;
        }

        return (low + high) / 2;
    }
}
