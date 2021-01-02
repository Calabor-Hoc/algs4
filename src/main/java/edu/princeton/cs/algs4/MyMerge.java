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
public class MyMerge {

	// This class should not be instantiated.
	private MyMerge() {
	}

	public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		if (hi + 1 - lo >= 0)
			System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
		int k =lo;
		int left = lo;
		int right = mid + 1;
		for (int i = lo; i <= hi; i++) {
			if (left > mid) {
				break;
			} else if (right > hi) {
				a[i] = aux[left++];
			} else if (less(aux[right], aux[left])) {
				a[i] = aux[right++];
			} else {
				a[i] = aux[left++];
			}
		}
	}

	private static void innerMergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		innerMergeSort(a, aux, lo, mid);
		innerMergeSort(a, aux, mid + 1, hi);
		if (!less(a[mid], a[mid + 1])) {
			merge(a, aux, lo, mid, hi);
		}
	}

	public static void sort(Comparable[] a) {
		final int N = a.length;
		final Comparable[] aux = new Comparable[N];
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
			final Integer[] input = SortCompare.generateRandomArray(100000);
			final Stopwatch stopwatch = new Stopwatch();
			MyMerge.sort(input);
//			Merge.sort(input);
			final double elapsedTime = stopwatch.elapsedTime();
			final boolean sorted = Shell.isSorted(input);
			if (!sorted) {
				throw new IllegalStateException("Sort error!");
			}
			totalTime += elapsedTime;
			StdOut.println("time:" + elapsedTime);
			averageTime = totalTime / loop;
			StdOut.println(String.format("average time: %.3f", averageTime));

			Thread.sleep(100l);
		}
	}
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
