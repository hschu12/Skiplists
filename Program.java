/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

public class Program {
	
	public static void main(String[] args){
		Skiplist list = new Skiplist(0.5);
		list.insert(10);
		list.insert(2);
		list.insert(5);
		list.insert(1);
		list.insert(20);
		list.insert(24);
		list.insert(52);
		list.insert(13);
		list.insert(14);
		list.insert(23);
		list.insert(11);
		list.insert(16);

		System.out.println("Header list");

		list.printHeaderPointer();
		
		System.out.println("wholelist");

		list.printList(0);

		list.search(10);
		list.search(52);
		list.search(23);
		list.search(15);
		

		list.delete(2);

		list.search(2);

		list.delete(20);
		list.delete(52);
		list.delete(23);
		list.delete(14);
		list.delete(1);
		
		System.out.println("Header list");

		list.printHeaderPointer();
		
		System.out.println("wholelist");

		list.printList(0);
		
	}
}