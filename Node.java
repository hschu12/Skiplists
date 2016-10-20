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

	public void addToList(Node n) {
		pointerList.add(n);
	}

	public void setValue(int k) {
		key = k;
	}

	public int getMaxUsedLevel() {
		return pointerList.size()-1;
	}

}