package com.company;
/*
Задача: Поиск медианы в потоке данных(реализующих интерфейс Comparable)
Решение:
Я использовал собственный класс Time_ в своем решении
Для решения этой задачи я разбиваю поток на две части
примерно равной длины(она может отличаться на 1 при нечетном количестве элементов)
Те элементы, которые меньше текущей медианы помещаются в левую часть,
которая представляет собой очередь с приоритетом(максимум в приоритете)
Те элементы, которые больше текущей медианы, помещаются в правую часть,
которая представляет собой очередь с приоритетом(минимум в приоритете)
Если в левой части получилось больше чем на один элемент больше чем в правой,
очереди нужно сбалансировать, то есть удалить из левой части максимальный элемент и
вставить его в правую
Если в левой части получилось так, что элементов больше чем в левой,
очереди нужно сбалансировать, то есть удалить из правой части минимальный элемент и
вставить его в левую
После балансировки ммедиану нужно снова пересчитать, она будет равна максимальному элементу из левой части
В случае, если на медианное значение претендуют два элемента(в потоке четное количество элементов), за медиану будет принят меньший из них
 */
public class Median {
    public static void Balance(MaxPQueue<Time_> maxpq, MinPQueue<Time_> minpq, int comp)
    {
        if (comp > 0)
            minpq.insert(maxpq.delMax());
        else maxpq.insert(minpq.delMin());
    }
    public static void main(String[] args) {
	MaxPQueue<Time_> maxpq = new MaxPQueue<>(10000000);
	MinPQueue<Time_> minpq = new MinPQueue<>(10000000);
	Time_ median = new Time_();
	for (int i = 0; i < 10; i++) {
	    Time_ current = new Time_();
	    StdOut.println("Added time: " + current.toString());
	    if (current.compareTo(median) > 0) {
	        minpq.insert(current);
	    }
	    else {
	        maxpq.insert(current);
	    }
        if ((maxpq.size() > minpq.size() + 1) || (minpq.size() > maxpq.size()))
            if (maxpq.size() > minpq.size() + 1)
                Balance(maxpq,minpq,1 );
            else Balance(maxpq,minpq,0);
	    median = maxpq.peekMax();
        StdOut.println("Current median: " + median.toString());
	}
	StdOut.println("Data stream is:");
	maxpq.printQueue();
	minpq.printQueue();
	StdOut.println();
    StdOut.println("Median is: " + median.toString());
    }
}
