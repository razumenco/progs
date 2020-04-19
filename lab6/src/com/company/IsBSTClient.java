package com.company;

public class IsBSTClient {
    //описание методов IsBinarySearchTree() и MakeNotSearchBT() см. в классе BST
    public static void main(String[] args) {
	BST<Time_,Integer> bst = new BST<>();
        for (int i = 0; i < 1023; i++) {
            bst.put(new Time_(),i); //заполняем дерево поиска
        }
        StdOut.println(bst.IsBinarySearchTree());//это дерево поиска, поэтому должно быть значение true
        bst.MakeNotSearchBT();//делаем из него не дерево поиска
        StdOut.println(bst.IsBinarySearchTree());//это не дерево поиска, поэтому должно быть значение false
    }
}
