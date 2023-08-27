package sort.nlogn;

import java.util.Arrays;

public class MergeSorting {

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 6, 2, 1};

        System.out.println(Arrays.toString(mergeSort(Arrays.copyOfRange(arr, 0, arr.length / 2),
                Arrays.copyOfRange(arr, arr.length / 2, arr.length))));
    }

    public static int[] sort(int[] arr) {
        return mergeSort(Arrays.copyOfRange(arr, 0, arr.length / 2),
                Arrays.copyOfRange(arr, arr.length / 2, arr.length));
    }

    public static int[] mergeSort(int[] leftArr, int[] rightArr) {
        int[] sortLeftArr;
        int[] sortRightArr;
        int[] sortArr = new int[leftArr.length + rightArr.length];  //получившийся отсортированный массив

        if (leftArr.length > 1) {
            sortLeftArr = mergeSort(Arrays.copyOfRange(leftArr, 0, leftArr.length / 2),
                    Arrays.copyOfRange(leftArr, leftArr.length / 2, leftArr.length));
        } else {
            sortLeftArr = leftArr;
        }

        if (rightArr.length > 1) {
            sortRightArr = mergeSort(Arrays.copyOfRange(rightArr, 0, rightArr.length / 2),
                    Arrays.copyOfRange(rightArr, rightArr.length / 2, rightArr.length));
        } else {
            sortRightArr = rightArr;
        }

        int leftPointer = 0;
        int rightPointer = 0;

        for (int i = 0; i < sortArr.length; i++) {      //слияние левых и правых частей
            if (leftPointer >= sortLeftArr.length || rightPointer < sortRightArr.length && sortLeftArr[leftPointer] >= sortRightArr[rightPointer]) {
                sortArr[i] = sortRightArr[rightPointer++];
            } else {
                sortArr[i] = sortLeftArr[leftPointer++];
            }
        }

        return sortArr;
    }
}
