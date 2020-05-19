package com.company.samurai.lesson2;

public class Exceptions {

    public static void main(String[] args) throws InterruptedException {
        String[][] array1 = {{"1", "2", "3"}, {"1", "2", "3", "4"}};
        String[][] array2 = {{"1", "2", "3", "4"}, {"1", "error", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] array3 = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        String[][][] array = {array1, array2, array3};

        for (int i = 0; i < array.length; i++) {
            System.out.println("Проверка для массива № " + (i+1));
            try {
                System.out.println("Сумма элементов массива = " + arrayCheck(array[i]));
            } catch (MyArraySizeException | MyArrayDataException e) {
                e.printStackTrace();
                Thread.sleep(100);
            }
        }
    }

    public static int arrayCheck(String[][] array) throws MyArraySizeException {
        int row = array.length;
        int col = array[0].length;
        int sum = 0;

        if (col != 4 || row != 4)
            throw new MyArraySizeException("Введите корректный массив (4х4)!");

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (RuntimeException e) {
                    throw new MyArrayDataException("Не удалось конвертировать ячейку [" + i + "],[" + j + "] в тип Integer.");
                }
            }

        return sum;
    }
}
