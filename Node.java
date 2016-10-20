public class Node {
	private int key;
	private Node[] pointerList;

	public Node (double levelCap) {
		int lcap = (int) levelCap;
		pointerList = new Node[lcap];	}

	public Node (int key, double levelCap) {
		int lcap = (int) levelCap;
		this.key = key;
		pointerList = new Node[lcap];
	}

	public int getKey(){
		return key;
	}

	public Node[] getList() {
		return pointerList;
	}

	public void setValue(int k) {
		key = k;
	}

	public int getMaxUsedLevel() {
		return pointerList.length-1;
	}

}