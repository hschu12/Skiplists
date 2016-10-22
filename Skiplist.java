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
		System.out.println("Header address: " + header);
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
		for (i = 0; i <= maxLevel; i++) {
			update[i] = new Node();
			for (int j = 0; j <= maxLevel; j++) {
				update[i].setNext(j, null);
			}
		}
		update[0] = header;
		Node x = header;
		for (i = maxLevel; i >= 0; i--) {
			while ( x.getNext(i) != null && x.getNext(i).getKey() < key) {
				x = x.getNext(i);
				update[i] = x;
			}
		}
		System.out.println("before: " + x);
		x = x.getNext(0);
		System.out.println("after: " + x);
		if ( x != null && x.getKey() == key) {
			x.setValue(key);
			System.out.println(key + " already exists. Value have been updated");
			return false;
		}
		else {
			x = new Node(key, randomLevel());
			for (i = 0; i <= Math.min(x.getMaxUsedLevel(), maxLevel); i++) {
				x.setNext(i, update[i].getNext(i));
				update[i].setNext(i, x);
			}
			size++;
			if ( Math.floor(lCap()) > maxLevel ) {
				System.out.println("increase max");
				//increaseMaximumLevelOfList();
			}
			return true;
		}
	}

	public boolean delete (int key) {
		Node update = header;
		Node x = header;
		for (int i = maxLevel; i >= 0; i--) {
			while (x.getNext(i) != null && x.getNext(i).getKey() < key) {
				x = x.getNext(i);
				update.setNext(i, x);
			}
		}
		x = x.getNext(0);
		if (x != null && x.getKey() == key) {
			for (int i = 1; i < Math.min(x.getMaxUsedLevel(), maxLevel); i++) {
				//update.getNext(i).getNext(i) = x.getNext(i);
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
		double cap = lCap();
		while (Math.random() < p && level < cap){
			level++;
		}
		System.out.println("RLvl set level to: " + level);
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
		return Math.log(size)/Math.log(1/p); //log_(1/p) (size)
	}

}