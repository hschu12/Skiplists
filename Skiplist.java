public class Skiplist {
	private Node header;
	private int maxLevel;
	private double p;
	private double levelCap;
	private double size;

	public Skiplist (double p) {
		size = 0;
		this.p = p;
		levelCap = 1;
		header = new Node();
		header.addToList(null);
		maxLevel = 0;
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
		System.out.println("inserting: " + key);
		Node update = header;
		Node x = header;
		for (int i = maxLevel; i >= 0; i--) {
			System.out.println("for");
			while ( x.getNext(i) != null && x.getNext(i).getKey() < key) {
				System.out.println("while");
				x = x.getNext(i);
				update.setNext(i, x);
			}
		}
		System.out.println(x);
		x = x.getNext(0);
		System.out.println(x);

		if ( x != null && x.getKey() == key) {
			x.setValue(key);
			System.out.println("Key: " + x.getKey()+ " already exists and has been updated");
			return false;
		}
		else {
			x = new Node(key, randomLevel()); 
			System.out.println(x.getKey());
			System.out.println("xmax: " + x.getMaxUsedLevel() + " and maxlvl " +  maxLevel);
			for (int i = 0; i <= Math.min(x.getMaxUsedLevel(), maxLevel); i++) {
				System.out.println("for with i = " + i);
				x.setNext(i, update.getNext(i));
				update.setNext(i, x);
			}
			size++;
			if (Math.floor(lCap(size)) > maxLevel) {
				System.out.println("increasing maxlvl");
				increaseMaximumLevelOfList();
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
			if (Math.floor(lCap(size)) < maxLevel) {
				maxLevel--;
			}
			return true;
		}
		return false;
	}

	public double randomLevel () {
		int level = 1;
		while (Math.random() < p && level < levelCap){
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

	public double lCap(double size) {
		levelCap = Math.log(size)/Math.log(1/p); //log_(1/p) (size)
		return levelCap;
	}

}