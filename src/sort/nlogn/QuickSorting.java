package sort.nlogn;

import java.util.Arrays;

public class QuickSorting {

    public static void main(String[] args) {
        int[] testArray = new int[]{75, 26, 15, 67, 85, 54, 31, 49};
        quickSort(testArray, 0, testArray.length - 1);
        System.out.println(Arrays.toString(testArray));
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex+1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1;    //индекс элемента, слева от которого все числа меньше pivot, справа больше

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];      //меняем местами элемент arr[j], который меньше pivot, слева от которого все элементы меньше pivot
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];    //элементы с индексом ind <= i, меньше или равны pivot, элемент i + 1 - первый элемент
        arr[i+1] = arr[end];        // который больше pivot, поэтому поменяем местами элементы arr[i + 1] и pivot
        arr[end] = swapTemp;

        return i+1;
    }
}
