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

import java.util.LinkedList;

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
public class DequeueSort {

	// This class should not be instantiated.
	private DequeueSort() {
	}

	/**
	 * Rearranges the array in ascending order, using the natural order.
	 *
	 * @param a the array to be sorted
	 */
	public static void dequeSort1(Comparable[] a) {
		LinkedList<Markable> ll = new LinkedList<>();
		for (int i = 0; i < a.length; i++) {
			ll.add(new Markable((Integer) a[i]));
		}

		boolean hasSwapped = true;
		while (true) {
			if (!hasSwapped) {
				for (int i = 0; i < a.length; i++) {
					a[i] = ll.get(i).getValue();
				}
				return;
			}
			hasSwapped = false;
			final Markable first0 = ll.peekFirst();
			final Markable second0 = peekSecond(ll);
			if (first0.compareTo(second0) > 0) {
				swapFirstTwo(ll);
				hasSwapped = true;
			}
			final Markable peek = ll.peek();
			peek.setMarked(true);
			moveTopToBottom(ll);
			while (true) {
				final Markable first = ll.peek();
				final Markable second = peekSecond(ll);
				if (!second.isMarked()) {
					if (first.compareTo(second) > 0) {
						swapFirstTwo(ll);
						hasSwapped=true;
					}
					moveTopToBottom(ll);
				} else {
					moveTopToBottom(ll);
					second.setMarked(false);
					break;
				}
			}
		}
	}
	public static void dequeSort2(Comparable[] a) {
		LinkedList<Markable> ll = new LinkedList<>();
		for (int i = 0; i < a.length; i++) {
			ll.add(new Markable((Integer) a[i]));
		}

		boolean hasSwapped = true;
		Markable first;
		Markable second;
		while (true) {
			if (!hasSwapped) {
				for (int i = 0; i < a.length; i++) {
					a[i] = ll.get(i).getValue();
				}
				return;
			}
			hasSwapped = false;
			first = ll.peekFirst();
			second = peekSecond(ll);
			if (first.compareTo(second) > 0) {
				swapFirstTwo(ll);
				hasSwapped = true;
			}
			ll.peek().setMarked(true);
			do {
				first = ll.peek();
				second = peekSecond(ll);
				if (!second.isMarked() && first.compareTo(second) > 0) {
					swapFirstTwo(ll);
					hasSwapped = true;
				}
				moveTopToBottom(ll);
				first = ll.peek();
			} while (!first.isMarked());
			first.setMarked(false);
		}
	}

	private static void swapFirstTwo(LinkedList list) {
		final Object first = list.removeFirst();
		final Object second = list.removeFirst();
		list.addFirst(first);
		list.addFirst(second);
	}

	private static void moveTopToBottom(LinkedList list) {
		final Object first = list.removeFirst();
		list.addLast(first);
	}

	private static Markable peekSecond(LinkedList<Markable> list) {
		final Markable first = list.removeFirst();
		final Markable second = list.peek();
		list.addFirst(first);
		return second;
	}

	static class Markable implements Comparable<Markable> {
		boolean marked;

		Integer value;

		public Markable(Integer value) {
			this.marked = false;
			this.value = value;
		}

		@Override
		public int compareTo(Markable o) {
			return this.value.compareTo(o.getValue());
		}

		public Integer getValue() {
			return value;
		}

		public boolean isMarked() {
			return marked;
		}

		public void setMarked(boolean marked) {
			this.marked = marked;
		}
	}

	/**
	 * Reads in a sequence of strings from standard input; Shellsorts them;
	 * and prints them to standard output in ascending order.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		final Integer[] integers = SortCompare.generateRandomArray(10);
		DequeueSort.dequeSort2(integers);
		final boolean sorted = Shell.isSorted(integers);
		StdOut.println(sorted);
	}

}