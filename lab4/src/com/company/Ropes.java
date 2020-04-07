package com.company;
/*
Задача: Даны n веревок разной длины, нам нужно соединить
эти веревки в одну веревку. Стоимость соединения двух веревок равна сумме их длин.
Нам нужно соединить веревки с минимальными затратами:
требуется вывести последовательность таких соединений и совокупную стоимость.
Чтобы получить минимальные затраты для соединения веревок всегда нужно выбирать
две веревки с наименьшими длинами.
Алгоритм решения:
1. Длины поместим в очередь с приоритетом(минимум в приоритете)
2. Пока не соединим все веревки
2.1 Удаляем наименьшую длину и помещаем ее в переменную
2.2 Если в очереди еще есть элементы удаляем минимум еще раз и сохраняем его значение,
    иначе обнуляем это значение
2.3 К общей стоимости соединений прибавляем две минимальные длины
2.4 Если очередь не пуста, то вставляем в нее длину, равную сумме двух минимальных длин
 */
public class Ropes {

    public static void main(String[] args) {
	MinPQueue<Integer> minPQueue = new MinPQueue<>(100);
	Integer[] len = {12,3,6,23,1,7};
	int cost = 0;
	for (int i = 0;i<len.length;i++)
	    minPQueue.insert(len[i]);
	while (!minPQueue.isEmpty()) {
        Integer minlen1 = minPQueue.delMin();
        Integer minlen2;
        if (!minPQueue.isEmpty())
            minlen2 = minPQueue.delMin();
        else minlen2 = 0;
        cost += minlen1 + minlen2;
        if (!minPQueue.isEmpty())
            minPQueue.insert(minlen1+minlen2);
        StdOut.println("United: " + minlen1 + " + " + minlen2);
    }
	StdOut.println("Total cost: "+cost);


    }
}

