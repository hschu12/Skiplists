import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String[] args) {
		int i;
		int size = 0;
		int pointerusedRange = 40;
		int[] pointersUsedForSearch = new int[pointerusedRange];


		/***********************************************************
		* Test of Average Search Complexity						   *
		* In-Order (Ascending) input. 							   *
		***********************************************************/

		System.out.println("Average - In-Order");

		ArrayList<Integer> inOrderAverageList = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			inOrderAverageList.add(i);
		}

		Skiplist inOrderAverageSkip;

		for (int j = 1; j <= 5; j++ ) {
			System.out.println();
			System.out.println("Run: " + j);

			while( size < 950 ) {
				size = size + 50;
				inOrderAverageSkip = new Skiplist(0.5, true);
				double acumulatedPointers = 0;
				System.out.print("Size of n = " + size);

				for (i = 0; i < size ; i++) {
					inOrderAverageSkip.insert(inOrderAverageList.get(i));
				}

				for (i = 0; i < 10000; i++) {
					int x = (int) (Math.random() * size);
					inOrderAverageSkip.search(x);
					acumulatedPointers = acumulatedPointers + inOrderAverageSkip.pointerused;
				}
				//System.out.println(acumulatedPointers);
				
				double averagePointersUsed = acumulatedPointers / 10000;
			
				System.out.println("\t Average: " + averagePointersUsed);
			}
			size = 0;
		}
		
		/***********************************************************
		* Reverse-Order (Descending) input. 					   *
		***********************************************************/

		System.out.println("Average - Descend-Order");

		ArrayList<Integer> descendAverageOrderList = new ArrayList<Integer>();
		for( i = 1000; i >= 1; i--) {
			descendAverageOrderList.add(i);
		}
		
		Skiplist descendAverageOrderSkip;

		for (int j = 1; j <= 5; j++ ) {
			System.out.println();
			System.out.println("Run: " + j);

			while( size < 950 ) {
				size = size + 50;
				descendAverageOrderSkip = new Skiplist(0.5, true);
				double acumulatedPointers = 0;
				System.out.print("Size of n = " + size);

				for (i = 0; i < 1000; i++) {
					descendAverageOrderSkip.insert(descendAverageOrderList.get(i));
				}

				for (i = 0; i < 10000; i++) {
					int x = (int) (Math.random() * size);
					descendAverageOrderSkip.search(x);
					acumulatedPointers = acumulatedPointers + descendAverageOrderSkip.pointerused;
				}
				//System.out.println(acumulatedPointers);
				
				double averagePointersUsed = acumulatedPointers / 10000;
			
				System.out.println("\t Average: " + averagePointersUsed);
			}
			size = 0;
		}

		/***********************************************************
		* Permuted input. Check number of pointers used per search *
		***********************************************************/

		System.out.println("Average - Permute-Order");

		ArrayList<Integer> permutedAverage = new ArrayList<Integer>();
		for (i = 0; i < 1000; i++) {
			permutedAverage.add(i);
		}
		Collections.shuffle(permutedAverage);

		Skiplist permutedAverageSkip = new Skiplist(0.5, true);

		for (int j = 1; j <= 5; j++ ) {
			System.out.println();
			System.out.println("Run: " + j);

			while( size < 950 ) {
				size = size + 50;
				permutedAverageSkip = new Skiplist(0.5, true);
				double acumulatedPointers = 0;
				System.out.print("Size of n = " + size);

				for (i = 0; i < 1000; i++) {
					permutedAverageSkip.insert(permutedAverage.get(i));
				}

				for (i = 0; i < 10000; i++) {
					int x = (int) (Math.random() * size);
					permutedAverageSkip.search(x);
					acumulatedPointers = acumulatedPointers + permutedAverageSkip.pointerused;
				}
				//System.out.println(acumulatedPointers);
				
				double averagePointersUsed = acumulatedPointers / 10000;
			
				System.out.println("\t Average: " + averagePointersUsed);
			}
			size = 0;
		}

		/***********************************************************
		* Test of effect of p 									   *
		* In-Order (Ascending) input. 							   *
		***********************************************************/

		System.out.println("Effect of p - In-Order");

		ArrayList<Integer> inOrderAverageListPeffect = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			inOrderAverageListPeffect.add(i);
		}

		Skiplist inOrderAveragePEffectSkip;

		double probability = 0.1;
		
		while (probability <= 0.5) {
			System.out.println("Probability = " + probability);
			for (int j = 1; j <= 5; j++ ) {
				System.out.println();
				System.out.println("Run: " + j);

				while( size < 950 ) {
					size = size + 50;
					inOrderAveragePEffectSkip = new Skiplist(probability, true);
					double acumulatedPointers = 0;
					System.out.print("Size of n = " + size);

					for (i = 0; i < size ; i++) {
						inOrderAveragePEffectSkip.insert(inOrderAverageListPeffect.get(i));
					}

					for (i = 0; i < 10000; i++) {
						int x = (int) (Math.random() * size);
						inOrderAveragePEffectSkip.search(x);
						acumulatedPointers = acumulatedPointers + inOrderAveragePEffectSkip.pointerused;
					}
					//System.out.println(acumulatedPointers);
					
					double averagePointersUsed = acumulatedPointers / 10000;
				
					System.out.println("\t Average: " + averagePointersUsed);
				}
				size = 0;
			}
			probability = probability+0.1;
		}

		/***********************************************************
		* Reverse-Order (Descending) input. 					   *
		***********************************************************/

		System.out.println("Effect of p - Descending-Order");

		ArrayList<Integer> descendOrderAverageListPEffect = new ArrayList<Integer>();
		for( i = 1000; i >= 1; i--) {
			descendOrderAverageListPEffect.add(i);
		}


		Skiplist descendOrderAveragePEffectSkip;

		probability = 0.1;
		
		while (probability <= 0.5) {
			System.out.println("Probability = " + probability);
			for (int j = 1; j <= 5; j++ ) {
				System.out.println();
				System.out.println("Run: " + j);

				while( size < 950 ) {
					size = size + 50;
					descendOrderAveragePEffectSkip = new Skiplist(probability, true);
					double acumulatedPointers = 0;
					System.out.print("Size of n = " + size);

					for (i = 0; i < size ; i++) {
						descendOrderAveragePEffectSkip.insert(descendOrderAverageListPEffect.get(i));
					}

					for (i = 0; i < 10000; i++) {
						int x = (int) (Math.random() * size);
						descendOrderAveragePEffectSkip.search(x);
						acumulatedPointers = acumulatedPointers + descendOrderAveragePEffectSkip.pointerused;
					}
					//System.out.println(acumulatedPointers);
					
					double averagePointersUsed = acumulatedPointers / 10000;
				
					System.out.println("\t Average: " + averagePointersUsed);
				}
				size = 0;
			}
			probability = probability+0.1;
		}

		/***********************************************************
		* Permuted input. Check number of pointers used per search *
		***********************************************************/

		System.out.println("Effect of p - Descending-Order");

		ArrayList<Integer> permuteOrderAverageListPEffect = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			permuteOrderAverageListPEffect.add(i);
		}

		Collections.shuffle(permuteOrderAverageListPEffect);

		Skiplist permuteOrderAveragePEffectSkip;

		probability = 0.1;
		
		while (probability <= 0.5) {
			System.out.println("Probability = " + probability);
			for (int j = 1; j <= 5; j++ ) {
				System.out.println();
				System.out.println("Run: " + j);

				while( size < 950 ) {
					size = size + 50;
					permuteOrderAveragePEffectSkip = new Skiplist(probability, true);
					double acumulatedPointers = 0;
					System.out.print("Size of n = " + size);

					for (i = 0; i < size ; i++) {
						permuteOrderAveragePEffectSkip.insert(permuteOrderAverageListPEffect.get(i));
					}

					for (i = 0; i < 10000; i++) {
						int x = (int) (Math.random() * size);
						permuteOrderAveragePEffectSkip.search(x);
						acumulatedPointers = acumulatedPointers + permuteOrderAveragePEffectSkip.pointerused;
					}
					//System.out.println(acumulatedPointers);
					
					double averagePointersUsed = acumulatedPointers / 10000;
				
					System.out.println("\t Average: " + averagePointersUsed);
				}
				size = 0;
			}
			probability = probability+0.1;
		}

		/***********************************************************
		* Test of Variation in search complexity				   *
		* In-Order (Ascending) input. 							   *
		***********************************************************/

		System.out.println("Ascending order list");

		ArrayList<Integer> inOrderList = new ArrayList<Integer>();
		for( i = 1; i <= 1000; i++) {
			inOrderList.add(i);
		}

		Skiplist inOrderSkip = new Skiplist(0.5, true);

		for (i = 0; i < 1000; i++) {
			inOrderSkip.insert(inOrderList.get(i));
		}

		for ( int j = 1; j <= 5; j++) {

			System.out.println("Run: " + j);
			System.out.println();

			for ( i = 0; i < pointerusedRange; i++ ) {
				pointersUsedForSearch[i] = 0;
			}

			for (i = 0; i < 10000; i++) {
				int x = (int) (Math.random() * 1000);
				inOrderSkip.search(x);
				pointersUsedForSearch[inOrderSkip.pointerused]++;
			}
			for ( i = 0; i < pointerusedRange; i++ ) {
				//System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
				System.out.println(pointersUsedForSearch[i]);
			}
		}

		/***********************************************************
		* Reverse-Order (Descending) input. 					   *
		***********************************************************/
		
		System.out.println();
		System.out.println("Descending order list");

		ArrayList<Integer> descendOrderList = new ArrayList<Integer>();
		for( i = 1000; i >= 1; i--) {
			descendOrderList.add(i);
		}
		
		Skiplist descendOrderSkip = new Skiplist(0.5, true);

		for (i = 0; i < 1000; i++) {
			descendOrderSkip.insert(descendOrderList.get(i));
		}

		for (int j = 1; j<=5 ; j++) {

			System.out.println("Run: " + j);
			System.out.println();

			for ( i = 0; i < pointerusedRange; i++ ) {
				pointersUsedForSearch[i] = 0;
			}

			for (i = 0; i < 10000; i++) {
				int x = (int) (Math.random() * 1000);
				descendOrderSkip.search(x);
				pointersUsedForSearch[descendOrderSkip.pointerused]++;
			}
			for ( i = 0; i < pointerusedRange; i++ ) {
				//System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
				System.out.println(pointersUsedForSearch[i]);
			}	
		}

		/***********************************************************
		* Permuted input. Check number of pointers used per search *
		***********************************************************/

		System.out.println();
		System.out.println("Permuted list");

		ArrayList<Integer> permuted = new ArrayList<Integer>();
		for (i = 0; i < 1000; i++) {
			permuted.add(i);
		}
		Collections.shuffle(permuted);

		Skiplist permutedSkip = new Skiplist(0.5, true);

		for (i = 0; i < 1000; i++) {
			permutedSkip.insert(permuted.get(i));
		}

		for (int j = 1; j <= 5 ; j++) {
			for ( i = 0; i < pointerusedRange; i++ ) {
				pointersUsedForSearch[i] = 0;
			}

			for (i = 0; i < 10000; i++) {
				int x = (int) (Math.random() * 1000);
				permutedSkip.search(x);
				pointersUsedForSearch[permutedSkip.pointerused]++;
			}
			for ( i = 0; i < pointerusedRange; i++ ) {
				//System.out.println("There are " + pointersUsedForSearch[i] + " that uses " + i + " pointer(s)");
				System.out.println(pointersUsedForSearch[i]);
			}
		}	
			
	}
}