package com.company;

public class MinPQueue_<Key extends Comparable<Key>>{
    private Key[] pq;
    private int N = 0;
    public MinPQueue_(int maxN)
    {
        pq = (Key[]) new Comparable[maxN+1];

    }
    public boolean isEmpty()
    {
        return N==0;
    }
    public int size()
    {
        return N;
    }
    public Key peekMin()
    {
        return pq[1];
    }
    public void insert(Key v)
    {
        pq[++N] = v;
        swim(N);
    }
    public Key delMin()
    {
        Key min = pq[1];
        exch(1,N--);
        pq[N+1] = null;
        sink(1);
        return min;

    }
    private void swim(int k)
    {
        while (k>1 && !less(k/2, k))
        {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j<N && !less(j, j+1)) j++;
            if (less(k,j)) break;
            exch(k, j);
            k=j;
        }
    }
    protected boolean less(int a, int b)
    {
        return pq[a].compareTo(pq[b]) < 0;
    }
    protected void exch(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    public void printQueue()
    {
        for (int i = 1; i < N+1; i++) {
            StdOut.println(""+i+": "+pq[i]);

        }
    }
}
