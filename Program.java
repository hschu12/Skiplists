public class Program {
	
	public static void main(String[] args){
		Skiplist list = new Skiplist(0.5);
		list.insert(2);
		list.insert(2);
		list.insert(4);
		list.insert(5);
		list.insert(2);
		list.search(10);
		list.search(2);
	}

}