package com.company;

public class BSTtoMinHeapClient {

    public static void main(String[] args) {
        BST<Time_,Integer> bst = new BST<>();
        for (int i = 0; i < 1023; i++) {
            bst.put(new Time_(),i); //заполняем дерево поиска
        }
        bst.BSTToMinHeap();//конвертируем дерево поиска в двочную кучу с минимумом в корне
        bst.printThreeLevels();//печатаем три верхних уровня кучи, чтобы убедиться в правильности
    }
}
