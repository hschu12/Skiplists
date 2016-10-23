import java.util.ArrayList;

public class Node {
	private int key;
	private ArrayList<Node> pointerList;

	public Node (){
		pointerList = new ArrayList<Node> ();
	}

	public Node (int key, double level) {
		int lcap = (int) level;
		this.key = key;
		pointerList = new ArrayList<Node> ();
		for (int i = 0; i < lcap; i++) {
			pointerList.add(new Node());
		}
	}

	public int getKey(){
		return key;
	}

	public Node getNext(int i) {
		return pointerList.get(i);
	}

	public void setNext(int i, Node x) {
		pointerList.add(i, x);
	}

	public void setValue(int k) {
		key = k;
	}

	public void addToList(Node n) {
		pointerList.add(n);
	}

	public int getMaxUsedLevel() {
		/*int level = 0;
		for ( int i = 1; i < pointerList.size() ; i++ ) {
			if (pointerList.get(i) != null ) {
				level++;
			}
		}
		System.out.println("level: " + level + ", size = " + pointerList.size());
		return level;*/
		return pointerList.size()-1;
	}

}