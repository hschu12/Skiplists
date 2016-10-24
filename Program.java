/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/
import java.util.ArrayList;
public class Program {
	
	public static void main(String[] args){
		Skiplist list = new Skiplist(0.5, false);
		list.insert(10);
		list.insert(2);
		list.insert(5);
		list.insert(1);
		list.insert(20);
		list.insert(24);
		list.insert(52);
		list.insert(13);
		list.insert(14);
		
		
		list.search(10);
		list.search(2);
		list.search(5);
		list.search(1);
		/*

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

		list.printListAtLevel(0);
		*/
	}
}