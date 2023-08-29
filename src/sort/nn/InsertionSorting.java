package sort.nn;

import java.util.Arrays;

public class InsertionSorting {
    public static void main(String[] args) {
        int[] testArr = new int[]{23, 56, 9, 103, 77};
        insertionSort(testArr);
        System.out.println(Arrays.toString(testArr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;

            while (j > 0 && arr[j-1] > temp){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
