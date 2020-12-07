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
public class MyMergeQueue {

	// This class should not be instantiated.
	private MyMergeQueue() {
	}

	public static Queue<Comparable> mergeQueues(Queue<Comparable> queue1, edu.princeton.cs.algs4.Queue<Comparable> queue2) {
		Queue<Comparable> mergedQueue = new Queue<>();

		while (!queue1.isEmpty() && !queue2.isEmpty()) {
			if (queue1.peek().compareTo(queue2.peek()) <= 0) {
				mergedQueue.enqueue(queue1.dequeue());
			} else {
				mergedQueue.enqueue(queue2.dequeue());
			}
		}

		while (!queue1.isEmpty()) {
			mergedQueue.enqueue(queue1.dequeue());
		}
		while (!queue2.isEmpty()) {
			mergedQueue.enqueue(queue2.dequeue());
		}
		return mergedQueue;
	}

	public static void sort(Comparable[] a) {
		final int N = a.length;
		final Queue<Queue<Comparable>> totalQueue = new Queue<>();
		for (Comparable comparable : a) {
			final Queue<Comparable> queue = new Queue<>();
			queue.enqueue(comparable);
			totalQueue.enqueue(queue);
		}
		while (true) {
			final Queue<Comparable> q1 = totalQueue.dequeue();
			if (totalQueue.isEmpty()) {
				totalQueue.enqueue(q1);
				break;
			}else {
				final Queue<Comparable> q2 = totalQueue.dequeue();
				final Queue<Comparable> mergedQueue = mergeQueues(q1, q2);
				totalQueue.enqueue(mergedQueue);
			}
		}
		final Queue<Comparable> q = totalQueue.dequeue();
		int i = 0;
		while (!q.isEmpty()) {
			a[i++] = q.dequeue();
		}
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
			final Stopwatch stopwatch = new Stopwatch();
			MyMergeQueue.sort(input);
			//			Merge.sort(input);
			final boolean sorted = Shell.isSorted(input);
			final double elapsedTime = stopwatch.elapsedTime();
			totalTime += elapsedTime;
			averageTime = totalTime / loop;
			StdOut.println(sorted);
			StdOut.println("time:" + stopwatch.elapsedTime());
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
