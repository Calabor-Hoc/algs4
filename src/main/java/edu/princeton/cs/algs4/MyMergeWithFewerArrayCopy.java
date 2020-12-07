/******************************************************************************
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                https://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Merge < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

/**
 * The {@code Merge} class provides static methods for sorting an
 * array using a top-down, recursive version of <em>mergesort</em>.
 * <p>
 * This implementation takes &Theta;(<em>n</em> log <em>n</em>) time
 * to sort any array of length <em>n</em> (assuming comparisons
 * take constant time). It makes between
 * ~ &frac12; <em>n</em> log<sub>2</sub> <em>n</em> and
 * ~ 1 <em>n</em> log<sub>2</sub> <em>n</em> compares.
 * <p>
 * This sorting algorithm is stable.
 * It uses &Theta;(<em>n</em>) extra memory (not including the input array).
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/22mergesort">Section 2.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * For an optimized version, see {@link MergeX}.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class MyMergeWithFewerArrayCopy {

	// This class should not be instantiated.
	private MyMergeWithFewerArrayCopy() {
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		int left = lo;
		int right = mid + 1;
		for (int i = lo; i <= hi; i++) {
			if (left > mid) {
				a[i] = aux[right++];
				continue;
			}
			if (right > hi) {
				a[i] = aux[left++];
				continue;
			}
			if (less(aux[right], aux[left])) {
				a[i] = aux[right];
				right++;
			} else {
				a[i] = aux[left];
				left++;
			}
		}
	}

	private static void innerMergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		innerMergeSort(aux, a, lo, mid);
		innerMergeSort(aux, a, mid + 1, hi);
//		if (!less(aux[mid], aux[mid + 1])) {
			merge(a, aux, lo, mid, hi);
//		}
	}

	public static void sort(Comparable[] a) {
		final int N = a.length;
		final Comparable[] aux = new Comparable[N];
		System.arraycopy(a, 0, aux, 0, N);
		innerMergeSort(a, aux, 0, N - 1);
	}


	/***************************************************************************
	 *  Helper sorting function.
	 ***************************************************************************/

	// is v < w ?
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	/**
	 * Reads in a sequence of strings from standard input; mergesorts them;
	 * and prints them to standard output in ascending order.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) throws InterruptedException {
		double totalTime = 0;
		int loop = 0;
		double averageTime = 0;
		while (true) {
			loop++;
			final Integer[] input = SortCompare.generateRandomArray(1000000);
//			Quick3way.sort(input);
			final Stopwatch stopwatch = new Stopwatch();
			MyMergeWithFewerArrayCopy.sort(input);
			//			Merge.sort(input);
			final boolean sorted = Shell.isSorted(input);
			StdOut.println(sorted);
			final double elapsedTime = stopwatch.elapsedTime();
			totalTime += elapsedTime;
			StdOut.println("time:" + stopwatch.elapsedTime());
			averageTime = totalTime / loop;
			StdOut.println(String.format("average time: %.3f", averageTime));
			Thread.sleep(100l);
		}
	}
}
