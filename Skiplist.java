/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

public class Skiplist {
	private Node header;
	private int maxLevel;
	private double p;
	private int levelCap;
	private double size;

	public Skiplist (double p) {
		size = 0;
		this.p = p;
		levelCap = 10;
		header = new Node();
		header.addToList(null);
		maxLevel = 0;
	}

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

	public void printList(int i) {
		Node x = header;
		while (x.getNext(i) != null) {
			System.out.println(x.getNext(i).getKey());
			x = x.getNext(i);
		}
	}

	public boolean search (int key) {
		Node x = header;
		for ( int i = maxLevel; i >= 0; i--){
			while ( x.getNext(i) != null && x.getNext(i).getKey() < key ){
				x = x.getNext(i);
			}
		}
		x = x.getNext(0);
		if ( x != null && x.getKey() == key) {
			System.out.println("Found: " + x.getKey());
			return true;
		}
		else {
			System.out.println(key + " not found");
			return false;
		}
	}

	public boolean insert (int key) {
		System.out.println("inserting " + key);
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
			System.out.println(key + " already exists. Value have been updated");
			return false;
		}
		else {
			x = new Node(key, randomLevel()); //Random kan gå til level cap
			//System.out.println("xused Level: " + x.getMaxUsedLevel() + " and maxlvl: " + maxLevel);
			for (i = 0; i <= Math.min(x.getMaxUsedLevel(), maxLevel); i++) { // Burde kun rette dem som skal.
				x.setNext(i, update[i].getNext(i));
				update[i].setNext(i, x);
			}
			size++;
			if ( Math.floor(lCap()) > maxLevel ) {
				System.out.println("increasing max");
				increaseMaximumLevelOfList();
			}
			return true;
		}
	}

	public boolean delete (int key) {
		System.out.println("Deleting " + key);
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
			for (i = 0; i <= Math.min(x.getMaxUsedLevel(), maxLevel); i++) {
				update[i].setNext(i, x.getNext(i));
			}
			x = null;
			size--;
			if ( Math.floor(lCap()) < maxLevel) {
				System.out.println("decrease");
				maxLevel--;
			}
			return true;
		}
		return false;
	}

	public int randomLevel () {
		int level = 1;
		double cap = Math.ceil(lCap());
		while (Math.random() < p && level < cap){
			level++;
		}
		//System.out.println("Random = " + level);
		return level;
	}

	public void increaseMaximumLevelOfList( ) {
		int level = maxLevel;
		Node a = header;
		Node b = a.getNext(level);
		while ( b != null ) {
			//System.out.println("MaxusedLevel: " + b.getMaxUsedLevel());
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
		return Math.log(size)/Math.log(1/p); //log_(1/p) (size)
	}

}