package com.company;
import java.util.*;
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    private Node root;
    private Node head;
    public void printThreeLevels()
    {
        printThreeLevels(root);
    }
    private void printThreeLevels(Node x)//очень банальная печать трех уровней дерева
    {
        int distance = 32;
        printSpace(distance);
        StdOut.println(x.key);
        distance = distance/2;
        printSpace(distance);
        StdOut.print(x.left.key);
        printSpace(distance);
        StdOut.println(x.right.key);
        distance = distance/4;
        printSpace(distance);
        StdOut.print(x.left.left.key);
        printSpace(distance);
        StdOut.print(x.left.right.key);
        printSpace(distance);
        StdOut.print(x.right.left.key);
        printSpace(distance);
        StdOut.print(x.right.right.key);
    }
    private void printSpace(int distance)
    {
        for (int i = 0; i < distance; i++) {
            StdOut.print(" ");
        }
    }
    public void BSTToMinHeap()
    {
        root = BSTToMinHeap(root);
    }
    /*
    Алгоритм:
    1. Конвертируем бинарное дерево поиска в отсортированный односвязный список
    1.1. Рекурсивно обрабатываем правое поддерево
    (так как значения будут добавляться в список слева, то сначала нужно обрабатывать правое поддерево,
    так как оно содержит большие ключи, которые окажутся в списке справа и список будет отсортирован по возрастанию)
    1.2. сохраняем значение старой головы слева от корня текущего поддерева
    1.3. если список не пустой, то нужно обнулить узел слева от головы
    1.4. устанавливаем голову(как значение корня текущего поддерева)
    1.5. рекурсивно обрабатываем левое поддерево
    2. Конвертируем отсортированный односвязный список в двоичную кучу
    2.1. Устанаввливем голову списка в корень кучи
    2.2. Обновляем голову
    2.3. добавляем корень в очередь как родителя следующих узлов
    2.4.Пока список не закончится
    2.4.1 сохраняем родителя последующих узлов и удаляем его из очереди
    2.4.2 левым потомком родителя устанавливаем текущую голова
    2.4.3 обновляем голову(уходим вправо по списку, так как список отсортирован по возрастанию
                           каждая следующая голова будет больше предыдущей, поэтому каждый
                           родитель будет меньше своего потомка, что и дает нам двоичную кучу с минимумом в корне)
    2.4.4. добавляем левого потомка в очередь, так как он будет первым среди родителей последующих потомков
    2.4.5 если список не закончился, аналогично добавляем в кучу следующий элемент как правого потомка текущего родителя
     */
    private Node BSTToMinHeap(Node root)
    {
        Node head = null;
        head = BSTToSortedLL(root, head);
        root = null;
        root = SortedLLToMinHeap(root, head);
        return root;
    }
    private Node BSTToSortedLL(Node root, Node head)
    {
        if(root == null)
            return head;
        head = BSTToSortedLL(root.right, head);
        root.right = head;
        if (head != null)
            (head).left = null;
        head = root;
        head = BSTToSortedLL(root.left, head);
        return head;
    }
    private Node SortedLLToMinHeap(Node root, Node head)
    {
        if (head == null)
            return null;
        Queue<Node> q = new LinkedList<>();//вспомогательная очередь, чтобы сохранять родителей текущих узлов,
                                           //после выполнения она останется пустой, поэтому наш алгоритм все равно преобразует на месте
        root = head;
        head = head.right;
        root.right = null;
        q.add(root);
        while (head != null)
        {
            Node parent = q.peek();
            q.remove();
            Node leftChild = head;
            head = head.right;
            leftChild.right = null;
            q.add(leftChild);
            assert parent != null;
            parent.left = leftChild;

            if (head != null)
            {
                Node rightChild = head;
                head = head.right;
                rightChild.right = null;
                q.add(rightChild);
                parent.right = rightChild;
            }
        }
        return root;
    }


    public void MakeNotSearchBT()
    {
        min(root).key = max(root).key;//меняем ключ минимума на ключ максимума
        //очевидно, что после такой операции дерево перестанет быть деревом поиска
    }
    public boolean IsBinarySearchTree()
    {
        return IsBinarySearchTree(root);
    }
    /*
    Алгоритм проверки:
    1. Проверяем являются ли все ключи левого поддерева меньше ключа корня
    2. Проверяем являются ли все ключи правого поддерева больше ключа корня
    3. Рекурсивно проверяем являются ли левое и правое поддерево деревьями поиска
    Это работает из-за того, что у бинарного дерева поиска левое и правое поддерево всегда тоже
    являются бинарными деревьями поиска
         */
    private boolean IsBinarySearchTree(Node root) {
        if (root == null) return true;//случай, когда мы дошли до конца дерева(то есть проверили листья и дерево закончилось)
        if (IsSubtreeLesser(root.left, root.key) &&
                IsSubtreeGreater(root.right, root.key) &&
                IsBinarySearchTree(root.left) &&
                IsBinarySearchTree(root.right))
            return true;
        else return false;
    }
    //все ключи поддерева будут меньше ключа корня, если максимум этого поддерева будет меньше ключа корня
    private boolean IsSubtreeLesser(Node x, Key key)
    {
        if (x == null) return true;
        int cmp = key.compareTo(max(x).key);
        if (cmp > -1)
            return true;
        else return false;
    }
    //все ключи поддерева будут больше ключа корня, если минимум этого поддерева будет больше ключа корня
    private boolean IsSubtreeGreater(Node x, Key key)
    {
        if (x == null) return true;
        int cmp = key.compareTo(min(x).key);
        if (cmp < 0)
            return true;
        else return false;
    }
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) return 0;
        return n.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null; //miss
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return get(x.right, key);
        else if (cmp < 0)
            return get(x.left, key);
        else return x.val; // hit
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key inf(Key key) {
        Node x = inf(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node inf(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return inf(x.left, key);
        Node t = inf(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key sup(Key key) {
        Node x = sup(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node sup(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return sup(x.right, key);
        Node t = sup(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key,x.right);
        else return size(x.left);
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}