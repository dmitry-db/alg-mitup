package sort.nn;

import java.util.Arrays;

public class BubbleSorting {

    public static void main(String[] args) {
        int[] testArray = new int[]{3, 10, 1, 8, 2};

        bubbleSort(testArray);
        System.out.println(Arrays.toString(testArray));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  //число проходов будет равняться количеству элементов массива - 1
            for (int j = 0; j < arr.length - i - 1; j++) { //проходим по всем элементам массива до элемента с индексом (arr.length - i - 1) т.к. дальнейшие элементы массива отсортированы
               if (arr[j] > arr[j+1]) {
                   int temp = arr[j];
                   arr[j] = arr[j+1];
                   arr[j+1] = temp;
               }
            }
        }
    }
}
