import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		int i;

		/***********************************************************
		* In-Order (Ascending) input. 							   *
		***********************************************************/

		/*ArrayList<Integer> inOrderList = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			inOrderList.add(i);
		}

		Skiplist inOrderSkip = new Skiplist(0.5);

		for (i = 0; i < 1000; i++) {
			inOrderSkip.insert(inOrderList.get(i));
		}*/
		/***********************************************************
		* Reverse-Order (Descending) input. 					   *
		***********************************************************/

		/*ArrayList<Integer> reverseOrderList = new ArrayList<Integer>();
		for( i = 1000; i >= 1; i--) {
			reverseOrderList.add(i);
		}
		
		Skiplist reverseOrderSkip = new Skiplist(0.5);

		for (i = 0; i < 1000; i++) {
			reverseOrderSkip.insert(reverseOrderList.get(i));
		}*/
		/***********************************************************
		* Permuted input. Check number of pointers used per search *
		***********************************************************/

		ArrayList<Integer> permuted = new ArrayList<Integer>();
		for (i = 0; i < 1000; i++) {
			permuted.add(i);
		}
		Collections.shuffle(permuted);

		Skiplist permutedSkip = new Skiplist(0.5, true);

		int[] pointersUsedForSearch = new int[30];
		for ( i = 0; i < 30; i++ ) {
			pointersUsedForSearch[i] = 0;
		}

		for (i = 0; i < 1000; i++) {
			permutedSkip.insert(permuted.get(i));
		}

		for (i = 0; i < 10000; i++) {
			int x = (int) (Math.random() * 1000);
			permutedSkip.search(x);
			pointersUsedForSearch[permutedSkip.pointerused]++;
		}
		for ( i = 0; i < 30; i++ ) {
			System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
		}
	}
}