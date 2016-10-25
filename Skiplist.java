/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

public class Skiplist {
	private Node header;
	private int maxLevel;
	private double probability;
	private int levelCap;
	private double size;
	public int pointerused;

	public Skiplist (double p) {
		size = 0;
		probability = p;
		levelCap = 10;
		header = new Node();
		header.addToList(null);
		maxLevel = 0;
	}

	public boolean search (int key) {
		Node x = header;
		pointerused = 0;
		for ( int i = maxLevel; i >= 0; i--){
			while ( x.getNext(i) != null && x.getNext(i).getKey() < key ){
				pointerused++;
				x = x.getNext(i);
			}
		}
		x = x.getNext(0);
		pointerused++;
		if ( x != null && x.getKey() == key) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean insert (int key) {
		int i;
		Node[] update = new Node[levelCap];
		Node x = header;
		for (i = maxLevel; i >= 0; i--) {
			while ( x.getNext(i) != null && x.getNext(i).getKey() < key) {
				x = x.getNext(i);
			}
			update[i] = x;
		}
		x = x.getNext(0);
		if ( x != null && x.getKey() == key) {
			x.setValue(key);
			return false;
		}
		else {
			x = new Node(key, randomLevel()); 
			int min = Math.min(x.getMaxUsedLevel(), maxLevel);
			for (i = 0; i <= min; i++) { 
				x.setNext(i, update[i].getNext(i));
				update[i].setNext(i, x);
			}
			size++;
			if ( Math.floor(lCap()) > maxLevel ) {
				increaseMaximumLevelOfList();
			}
			return true;
		}
	}

	public boolean delete (int key) {
		int i;
		Node[] update = new Node[levelCap];
		Node x = header;
		for (i = maxLevel; i >= 0; i--) {
			while (x.getNext(i) != null && x.getNext(i).getKey() < key) {
				x = x.getNext(i);
			}
			update[i] = x;
		}
		x = x.getNext(0);
		if (x != null && x.getKey() == key) {
			int min = Math.min(x.getMaxUsedLevel(), maxLevel);
			for (i = 0; i <= min; i++) {
				update[i].setNext(i, x.getNext(i));
			}
			x = null;
			size--;
			if ( Math.floor(lCap()) < maxLevel) {
				maxLevel--;
			}
			return true;
		}
		return false;
	}

	public int randomLevel () {
		int level = 1;
		double cap = Math.ceil(lCap());
		while (Math.random() < probability && level < cap){
			level++;
		}
		return level;
	}

	public void increaseMaximumLevelOfList( ) {
		int level = maxLevel;
		Node a = header;
		Node b = a.getNext(level);
		while ( b != null ) {
			if (b.getMaxUsedLevel() > level) {
				a.setNext(level+1, b);
				a = b;
			}
			b = b.getNext(level);
		}
		a.setNext(level+1, null);
		maxLevel = level+1;
	}

	public double lCap() {
		return Math.log(size)/Math.log(1/probability); //log_(1/p) (size)
	}

/************************************************
*	Print Methods								*
************************************************/

	public void printHeaderPointer() {
		Node x = header;
		for (int i = maxLevel; i >= 0 ; i--) {
			if(x.getNext(i) != null) {
				System.out.println(x.getNext(i).getKey());
			}
			else {
				System.out.println(x.getNext(i));
			}
		}
	}

	public void printListAtLevel(int i) {
		Node x = header;
		while (x.getNext(i) != null) {
			System.out.println(x.getNext(i).getKey());
			x = x.getNext(i);
		}
	}

}