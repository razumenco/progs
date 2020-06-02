package com.company;

public class WordInGrid {

    /*
    Задание: 9. Дан двумерный массив ("сетка") символов и слово. Найдите все вхождения данного слова в сетке.
    Слово может быть найдено во всех 8 направлениях(вверх, вниз, вправо, влево и по диагоналям) в любой точке.
    Говорят, слово "лежит в направлении", если все символы совпадают в этом направлении (не в зигзагообразной форме).

    Решение: Выделим в сетке все горизонтали, вертикали и диагонали и в каждой их этих строк сетки будем искать подстроку(данное слово)
    с помощью  алгоритма Кнута-Морриса-Пратта. Горизонтали выделим как все одномерные подмассивы сетки, вертикали как
    одномерные подмассивы транспонированной сетки, а диагонали выделим с помощью  особого метода. Он основывается на том, что
    для каждой точки, находящейся на границе сетки соответствуют две диагонали(одна с наклоном в право, одна с наклоном
    в лево). Соответственно мы можем пройти по всем символам границы и для каждой проходить по диагонали пока не закончится
    сетка.
    Для поиска по обратным напрвлениям, будем искать подстроку в перевернутых строках.
     */

    public static void main(String[] args) {
        String word = "add";
        KMPSearchSubstring kmp = new KMPSearchSubstring(word);
        char[][] grid = {{'g','d','d','a','d','d'},
                         {'d','f','d','d','d','k'},
                         {'d','d','a','d','b','d'}};
        int m = grid[0].length, n = grid.length;

        for (int i = 0; i < n; i++) {//ищем слово в строках сетки (по направлению вправо)
            String buff = new String(grid[i]);
            int pos = kmp.search(buff);
            int cutlen = 0;
            while (pos != buff.length()) {
                int hor = i+1, ver = pos+cutlen+1;
                StdOut.println("Слово "+word+" найдено в " + hor + "-ой(-ей) строке, начиная с " + ver + "-ой(-ей) позиции вправо.");
                buff = buff.substring(pos+word.length());
                cutlen += pos+word.length();
                pos = kmp.search(buff);
            }
        }

        for (int i = 0; i < n; i++) {//ищем слово в строках сетки (по направлению влево)
            String buff = new String(grid[i]);
            buff = reverse(buff);//для этого каждую строку переворачиваем
            int pos = kmp.search(buff);
            int cutlen = 0;
            while (pos != buff.length()) {
                int hor = i+1, ver = m-(pos+cutlen);
                StdOut.println("Слово "+word+" найдено в " + hor + "-ой(-ей) строке, начиная с " + ver + "-ой(-ей) позиции влево.");
                buff = buff.substring(pos+word.length());
                cutlen += pos+word.length();
                pos = kmp.search(buff);
            }
        }

        grid = transpose(grid);//транспонируем сетку чтобы делать поиск в столбцах(аналогично строкам)

        for (int i = 0; i < m; i++) {//поиск по столбцам(по направлению вниз)
            String buff = new String(grid[i]);
            int pos = kmp.search(buff);
            int cutlen = 0;
            while(pos != buff.length()) {
                int hor = i+1, ver = pos+cutlen+1;
                StdOut.println("Слово "+word+" найдено в " + hor + "-ом(-ем) столбце, начиная с " + ver + "-ой(-ей) позиции вниз.");
                buff = buff.substring(pos+word.length());
                cutlen += pos+word.length();
                pos = kmp.search(buff);
            }
        }

        for (int i = 0; i < m; i++) {//поиск по стобцам(по направлению вверх)
            String buff = new String(grid[i]);
            buff = reverse(buff);
            int pos = kmp.search(buff);
            int cutlen = 0;
            while(pos != buff.length()) {
                int hor = i+1, ver = n-(pos+cutlen);
                StdOut.println("Слово "+word+" найдено в " + hor + "-ом(-ем) столбце, начиная с " + ver + "-ой(-ей) позиции вверх.");
                buff = buff.substring(pos+word.length());
                cutlen += pos+word.length();
                pos = kmp.search(buff);
            }
        }

        grid = transpose(grid);//второй раз транспонируем сетку, чтобы вернуть ее в исходное состояние

        String[] diags  = new String[m+n];
        for (int i = 0; i < m+n; i++) {
            diags[i] = "";
        }

        int k = 0;
        for (int i = m-1; i > -1; i--) {//выделяем диагонали с наклоном влево
            int j = i, h = 0;
            while (j < m && h < n)
            {
                diags[k] += grid[h][j];
                h++;
                j++;
            }
            k++;
        }
        for (int i = 1; i < n; i++) {
            int j = i, h = 0;
            while (j < n && h < m) {
                diags[k] += grid[j][h];
                h++;
                j++;
            }
            k++;
        }

        for (int i = 0; i < k; i++) {//ищем слово в этих диагоналях
            if(diags[i].length() < word.length())
                continue;
            int pos = kmp.search(diags[i]);
            int cutlen = 0;
            while(pos != diags[i].length()) {
                int x=1,y=1;
                if(i < m)
                    y = m-i;
                else x = i-m+2;
                x += pos+cutlen;
                y += pos+cutlen;
                StdOut.println("Слово "+word+" найдено по диагонали(наклон в лево) начиная с символа на пересечении "+x+"-ой(-ей) строки и "+y+"-ого(-его) столбца");
                diags[i] = diags[i].substring(pos+word.length());
                cutlen += pos+word.length();
                pos = kmp.search(diags[i]);
            }
        }

        k = 0;
        for (int i = 0; i < m+n; i++) {
            diags[i] = "";
        }

        for (int i = 0; i < m; i++ ) {//выделяем диагонали с наклоном вправо
            int j = i, h = 0;
            while (j > -1 && h < n)
            {
                diags[k] += grid[h][j];
                h++;
                j--;
            }
            k++;
        }
        for (int i = 1; i < n; i++) {
            int h = i, j = m-1;
            while (h < n && j > -1) {
                diags[k] += grid[h][j];
                h++;
                j--;
            }
            k++;
        }

        for (int i = 0; i < k; i++) {//ищем слово в этих диагоналях
            if(diags[i].length() < word.length())
                continue;
            int pos = kmp.search(diags[i]),cutlen = 0;
            while(pos != diags[i].length()) {
                int x,y;
                if(i < m) {
                    x = i + 1;
                    y = 1;
                }
                else {
                    x = m;
                    y = i-m+2;
                }
                x -= pos+cutlen;
                y += pos + cutlen;
                StdOut.println("Слово "+word+" найдено по диагонали(наклон в право) начиная с символа на пересечении "+y+"-ой(-ей) строки и "+x+"-ого(-его) столбца");
                diags[i] = diags[i].substring(pos+word.length());
                cutlen += pos+word.length();
                pos = kmp.search(diags[i]);
            }
        }
    }
    private static char[][] transpose(char[][] table) {//функция для транспонирования сетки символов
        char[][] buff = new char[table[0].length][table.length];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                buff[j][i] = table[i][j];
            }
        }
        return buff;
    }
    private static String reverse(String torev)//функция для переворачивания строки
    {
        String buff = "";
        char[] ctorev = torev.toCharArray();
        for (int i = torev.length()-1; i > -1; i--) buff += ctorev[i];
        return buff;
    }
}
