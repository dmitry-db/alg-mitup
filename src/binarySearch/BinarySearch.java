package binarySearch;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] sortedArray = {1, 3, 5, 6, 7, 8, 10};
        int value = 8;
        System.out.println(binarySearch.binarySearch(sortedArray, value));
        System.out.println(binarySearch.recursionBinarySearch(sortedArray, value, 0, sortedArray.length));
    }

    public int binarySearch(int[] sortedArray, int value) {
        int left = 0;
        int right = sortedArray.length;

        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (sortedArray[middle] == value) {
                return middle;
            }

            if (value > sortedArray[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }

    public int recursionBinarySearch(int[] sortedArray, int value, int left, int right) {   //можно передавать subArr
        int middle = left + (right - left) / 2;

        if (right < left) {
            return -1;
        }

        if (sortedArray[middle] == value) {
            return middle;
        }

        if (sortedArray[middle] < value) {
            return recursionBinarySearch(sortedArray, value, middle + 1, right);    //хвостовую рекурсию стоит убирать
        } else {
            return recursionBinarySearch(sortedArray, value, left, middle - 1);
        }
    }
}
