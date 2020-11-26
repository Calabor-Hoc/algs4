/******************************************************************************
 *  Compilation:  javac Insertion.java
 *  Execution:    java Insertion < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                https://algs4.cs.princeton.edu/21elementary/words3.txt
 *
 *  Sorts a sequence of strings from standard input using insertion sort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Insertion < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Insertion < words3.txt
 *  all bad bed bug dad ... yes yet zoo   [ one string per line ]
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

import java.util.Random;

public class SortCompare {
	private static final long seed=23L;
	private static final Random random=new Random(seed);    // pseudo-random number generator
	// This class should not be instantiated.
	private SortCompare() {
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 *
	 * @param a the array to be sorted
	 */
	public static double time(String alg, Comparable[] a) {
		Stopwatch timer = new Stopwatch();
		if (alg.equals("Insertion"))
			Insertion.sort(a);
		if (alg.equals("Selection"))
			Selection.sort(a);
		if (alg.equals("Shell"))
			Shell.sort(a);
		if (alg.equals("Merge"))
			Merge.sort(a);
		if (alg.equals("Quick"))
			Quick.sort(a);
		if (alg.equals("Heap"))
			Heap.sort(a);
		return timer.elapsedTime();
	}

	public static double timeRandomInput(String alg, int N, int T) { // Use alg to sort T random arrays of length N.

		double total = 0.0;

		Double[] a = new Double[N];

		for (int t = 0; t < T; t++) {

			// Perform one experiment (generate and sort an array).
			for (int i = 0; i < N; i++)
				a[i] = StdRandom.uniform();
			total += time(alg, a);

		}
		return total;

	}

	public static Integer[] generateRandomArray(int N) { // Use alg to sort T random arrays of length N.
		Integer[] a = new Integer[N];
		// Perform one experiment (generate and sort an array).
		for (int i = 0; i < N; i++)
			a[i] = random.nextInt(999999);
		return a;
	}

	public static void main(String[] args) {

		String alg1 = args[0];

		String alg2 = args[1];

		int N = Integer.parseInt(args[2]);

		int T = Integer.parseInt(args[3]);
		// total for alg1 double t2 = timeRandomInput(alg2, N, T); // total for alg2 StdOut.printf("For %d random
		double t1 = timeRandomInput(alg1, N, T);
		// Doubles\n %s is", N, alg1); StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);

	}

}
