import javax.swing.*;
import java.util.Scanner;
public class Field extends JFrame {
    public static int stlb;
    public static int str;
    public Field() { //введение конструктора класса

            Scanner sc = new Scanner(System.in);

            System.out.println("Введите желаемое количество строк");
            str = sc.nextInt();
            System.out.println("Введите желаемое количество столбцов");
            stlb = sc.nextInt();
            System.out.println("Введите координату по X левого верхнего угла минимального прямоугольника \n" +
                    "ограничивающего фигуру (координатная ось X направлена вправо)");
            int x = sc.nextInt();
            System.out.println("Введите координату по Y левого верхнего угла минимального прямоугольника \n" +
                    "ограничивающего фигуру (координатная ось Y направлена вниз)");
            int y = sc.nextInt();

            System.out.println("Введите путь к файлу с описанием фигуры : ");
            sc = new Scanner(System.in);
            String file = sc.nextLine();

            Readfail Fail = new Readfail();
            Fail.ReadArray(file);

            char[][] newArray = new char[str][stlb];
            char[][] FutureArray = new char[str][stlb];// создание двумерного массива

            while (true) {
                System.out.println("Нажмите e , чтобы произошла эволюция");
                System.out.println("Нажмите q , чтобы завершить игру");
                sc = new Scanner(System.in);
                String line = sc.nextLine();
                switch (line) {
                    case "e":
                        if (isArrayEmpty(FutureArray)) { //первый запуск - пустой массив

                            // Массив не заполнен, заполняем значениями '0'
                            for (int i = 0; i < str; i++) {
                                for (int j = 0; j < stlb; j++) {
                                    FutureArray[i][j] = '0';
                                }
                            }

                            for (int i = 0; i < str; i++) {
                                for (int j = 0; j < stlb; j++) {
                                    newArray[i][j] = '0';
                                }
                            }

                            System.out.println("Массивы были заполнены значениями '0'.");

                            //комбинирование двух массивов : из файла и newArray
                            for (int i = 0; i < (Fail.charArray.length); i++) {
                                for (int j = 0; j < (Fail.charArray[i].length); j++) {
                                    FutureArray[i + x][j + y] = Fail.charArray[i][j];

                                }
                            }
                            System.out.println("Комбинация двух массивов ");
                            Cell.drawCells(FutureArray);
                            break;

                        }

                        for (int i = 0; i < str; i++) { // два массива становятся одинаковыми
                            for (int j = 0; j < stlb; j++) {
                                newArray[i][j] = FutureArray[i][j];
                            }
                        }


                        CheckNeig(newArray, FutureArray); // подсчет соседей
                        Cell.drawCells(FutureArray);  // прорисовка

                        if (CheckGame(newArray, FutureArray)) break; // проверка на конец игры

                        break;
                    case "q":
                        System.out.println("Игра окончена");
                        break;
                }
                if (line.equals("q")) break;
            }
    }
    public boolean isArrayEmpty(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void CheckNeig(char[][] array, char[][] Farray) {
        // проверка соседей для каждой клетки
        for (int i = 0; i < str; i++) {
            for (int j = 0; j < stlb; j++) {
                int count = 0;

                // проверяем всех соседей клетки
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        // игнорируем саму клетку
                        if (x == 0 && y == 0) continue;

                        // учитываем краевые условия
                        int neighborX = (i + x + stlb) % stlb;
                        int neighborY = (j + y + str) % str;

                        // проверяем соседей на наличие живых клеток
                        if (array[neighborX][neighborY] == '1') count++;
                    }
                }
                if (count == 3) Farray[i][j] = '1';
                else if (count != 2) Farray[i][j] = '0';
            }
        }
    }
    public static boolean CheckGame(char[][] array, char[][] Farray) {
        int kol1 = 0;
        int kol2 = 0;

        for (int i = 0; i < str; i++) { //проверка на статику
            for (int j = 0; j < stlb; j++) {
                if (Farray[i][j] != array[i][j]) kol1+=1;
            }
        }
        if (kol1==0) {
            System.out.println("GAME OVER !!!");
            System.exit(0);
            return true;
        }

        for (int i = 0; i < str; i++) { // проверка есть ли живые клетки
            for (int j = 0; j < stlb; j++) {
                if (Farray[i][j] != '0') kol2+=1;
            }
        }
        if (kol2==0){
            System.out.println("GAME OVER !!!");
            System.exit(0);
            return true;
        }
        else return false;
    }
}
