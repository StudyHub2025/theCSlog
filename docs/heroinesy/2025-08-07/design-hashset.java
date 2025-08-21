class MyHashSet {
    private int numBuckets;
    private LinkedList<Integer>[] buckets;

    public MyHashSet() {
        this.numBuckets = 769;
        this.buckets = new LinkedList[numBuckets];

        for (int i = 0; i < numBuckets; i++){
            buckets[i] = new LinkedList<>();
        }
    }

    private int getHashValue(int key) {
        return key % numBuckets;
    }

    public void add(int key) {
        int index = getHashValue(key);
        if (!buckets[index].contains(key)) {
            buckets[index].add(key);
        }
    }
    
    public void remove(int key) {
        int index = getHashValue(key);
        buckets[index].removeFirstOccurrence(key);
    }
    
    public boolean contains(int key) {
        int index = getHashValue(key);
        return buckets[index].contains(key);
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */