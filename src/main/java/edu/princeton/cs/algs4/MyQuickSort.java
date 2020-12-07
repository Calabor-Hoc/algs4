/******************************************************************************
 *  Compilation:  javac Shell.java
 *  Execution:    java Shell < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/21elementary/tiny.txt
 *                https://algs4.cs.princeton.edu/21elementary/words3.txt
 *
 *  Sorts a sequence of strings from standard input using shellsort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Shell < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Shell < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *
 ******************************************************************************/

package edu.princeton.cs.algs4;

import static edu.princeton.cs.algs4.Shell.exch;
import static edu.princeton.cs.algs4.Shell.less;

/**
 * The {@code Shell} class provides static methods for sorting an
 * array using <em>Shellsort</em> with
 * <a href = "https://oeis.org/A003462"> Knuth's increment sequence</a>
 * (1, 4, 13, 40, ...). In the worst case, this implementation makes
 * &Theta;(<em>n</em><sup>3/2</sup>) compares and exchanges to sort
 * an array of length <em>n</em>.
 * <p>
 * This sorting algorithm is not stable.
 * It uses &Theta;(1) extra memory (not including the input array).
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class MyQuickSort {

	// This class should not be instantiated.
	private MyQuickSort() {
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 *
	 * @param a the array to be sorted
	 */

	public static void quickSort(Comparable[] a) {
		StdRandom.shuffle(a);
		quickSort(a, 0, a.length - 1);
	}

	private static void quickSort(Comparable[] a, int l, int r) {
		final int pivotIndex = pivotArray(a, l, r);
		if ((pivotIndex-1)-l > 0) {
			quickSort(a, l, pivotIndex - 1);
		}
		if (r-(pivotIndex+1) > 0) {
			quickSort(a, pivotIndex+1, r);
		}
	}

	private static int pivotArray(Comparable[] a, int l, int r) {
		if (l == r) {
			return l;
		}
		Comparable pv = a[l];
		int li = l + 1;
		int ri = r;
		while (true) {
			for (; li <= r && less(a[li], pv); ) {
				li++;
			}
			for (; ri >= li && less(pv, a[ri]); ) {
				ri--;
			}
			if (ri > li) {
				exch(a, li, ri);
				li++;
				ri--;
			} else {
				exch(a, l, li - 1);
				return li - 1;
			}
		}

	}

	/**
	 * Reads in a sequence of strings from standard input; Shellsorts them;
	 * and prints them to standard output in ascending order.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			final Integer[] integers =SortCompare.generateRandomArray(10000000);
			final Stopwatch stopwatch = new Stopwatch();
			MyQuickSort.quickSort(integers);
//			Quick.sort(integers);
			final boolean sorted = Shell.isSorted(integers);
			StdOut.println(sorted);
			StdOut.println("time:"+stopwatch.elapsedTime());
			Thread.sleep(1000l);
		}

	}

}