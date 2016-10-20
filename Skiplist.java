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
		header = new Node(levelCap);
		maxLevel = 0;
	}

	public boolean search (int key) {
		Node x = header;
		for ( int i = maxLevel; i >= 0; i--){
			while ( x.getList()[i] != null && x.getList()[i].getKey() < key ){
				x = x.getList()[i];
			}
		}
		x = x.getList()[0];
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
			while ( x.getList()[i] != null && x.getList()[i].getKey() < key) {
				System.out.println("while");
				x = x.getList()[i];
				update.getList()[i] = x;
			}
		}
		System.out.println(x);
		x = x.getList()[0];
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
				x.getList()[i] = update.getList()[i];
				update.getList()[i] = x;
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
		Node update = new Node(levelCap);
		Node x = header;
		for (int i = maxLevel; i >= 0; i--) {
			while (x.getList()[i] != null && x.getList()[i].getKey() < key) {
				x = x.getList()[i];
				update.getList()[i] = x;
			}
		}
		x = x.getList()[0];
		if (x != null && x.getKey() == key) {
			for (int i = 1; i < Math.min(x.getMaxUsedLevel(), maxLevel); i++) {
				update.getList()[i].getList()[i] = x.getList()[i];
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
		Node b = a.getList()[level];
		while ( b != null ) {
			if (b.getMaxUsedLevel() > level) {
				a.getList()[level+1] = b;
				a = b;
			}
			b = b.getList()[level];
		}
		a.getList()[level+1] = null;
		maxLevel = level+1;
	}

	public double lCap(double size) {
		levelCap = Math.log(size)/Math.log(1/p); //log_(1/p) (size)
		return levelCap;
	}

}