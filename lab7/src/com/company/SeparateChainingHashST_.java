package com.company;

public class SeparateChainingHashST_<Key extends Comparable<Key> , Value> {
    private int N;
    private int M;
    private SequentialSearchST_<Key>[] st;

    public SeparateChainingHashST_()
    {
        this(999773);
    }
    public SeparateChainingHashST_(int M)
    {
        this.M = M;
        st = (SequentialSearchST_<Key>[]) new SequentialSearchST_[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST_<Key>();
        }
    }
    private int hash(Key key)
    {
        return(key.hashCode() & 0x7fffffff) % M;
    }
    public Integer get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }
    public void put(Key key, Integer val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (N >= 10*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    }
    public void putforfrequency(Key key, Integer val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (N >= 10*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) {
            N++;
            st[i].put(key, val);
            return;
        }
        st[i].putforfrequency(key, val);
    }
    private void resize(int chains) {
        SeparateChainingHashST_<Key, Value> temp = new SeparateChainingHashST_<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
    }
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (M > 9973 && N <= 2*M) resize(M/2);
    }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
}
