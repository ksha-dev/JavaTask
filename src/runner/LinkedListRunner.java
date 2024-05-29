package runner;

import java.util.LinkedList;

public class LinkedListRunner {

	public static void main(String[] args) {
		LinkedList<Integer> numbers = new LinkedList<Integer>();

		numbers.add(1);
		numbers.add(23);
		numbers.addFirst(2);
		numbers.addLast(10);
		numbers.add(2, 234);

		System.out.println(numbers.getFirst());
		System.out.println(numbers.getLast());

		System.out.println(numbers.get(2));

		numbers.push(23);
		numbers.offer(2345);

		numbers.peek();

		System.out.println(numbers);
	}

}
