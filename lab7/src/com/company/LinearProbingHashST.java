package com.company;

public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 999999;
    private int N;
    private int M;
    private Key[] keys;
    private Value[] vals;
    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }
    public LinearProbingHashST(int capacity) {
        M = capacity;
        N = 0;
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }
    private int hash(Key key)
    {
        return(key.hashCode() & 0x7fffffff) % M;
    }
    public int size() {
        return N;
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        M    = temp.M;
    }
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    public void put(Key key, Value val)
    {
        if (N >= M/2) resize(2*M);
        int i;
        for (i = hash(key);keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(key))
            {
                vals[i] = val;
                return;
            }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key)
    {
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(key))
                return vals[i];
            return null;
    }
    public void delete(Key key)
    {
        if (!contains(key)) return;
        int i = hash(key);
        while(!key.equals(keys[i]))
            i = (i+1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i+1) % M;
        while (keys[i] != null)
        {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo,valToRedo);
            i = (i+1) % M;
        }
        N--;
        if (N > 0 && N <= M/8) resize(M/2);
    }

}
