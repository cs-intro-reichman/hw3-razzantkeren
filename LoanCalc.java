// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;   // Number of iterations 
	static final int MAX_ITERATIONS = 1_000_000;
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
		private static double endBalance(double loan, double rate, int n, double payment) {
			double total = loan;
			double monthlyRate = rate / 100; // Convert percentage to a fraction
			for (int i = 0; i < n; i++) {
				total = total * (1 + monthlyRate) - payment; // Apply interest, then subtract payment
				if (total < 0) break; // Stop early if loan is fully paid
			}
			return total;
		}
		

    // Brute Force Solver with dynamic increment
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double g = loan / n; // Start with an initial guess
		double step = epsilon * 100; // Begin with a larger step
	
		while (Math.abs(endBalance(loan, rate, n, g)) > epsilon) {
			double balance = endBalance(loan, rate, n, g);
			if (balance > 0) {
				g += step; // Increase payment if balance is positive
			} else {
				g -= step; // Decrease payment if balance is negative
			}
			iterationCounter++;
	
			if (iterationCounter > MAX_ITERATIONS) {
				System.out.println("Brute force solver exceeded maximum iterations.");
				break; // Exit if iteration limit is reached
			}
		}
		return g;
	}
	

    // Bisection Solver
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double low = 0;         // Minimum payment guess
		double high = loan;     // Maximum payment guess
		double mid = 0;         // Midpoint between low and high
	
		while (Math.abs(high - low) > epsilon) {
			mid = (low + high) / 2;
			double balance = endBalance(loan, rate, n, mid);
	
			if (balance > 0) {
				low = mid; // Payment too low, increase it
			} else {
				high = mid; // Payment too high, decrease it
			}
			iterationCounter++;
	
			if (iterationCounter > MAX_ITERATIONS) {
				System.out.println("Bisection solver exceeded maximum iterations.");
				break; // Exit if iteration limit is reached
			}
		}
	
		return (low + high) / 2;
	}
}
