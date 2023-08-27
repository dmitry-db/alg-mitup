package sort.nn;

public class SortingBySelect {
    public static void main(String[] args) {
        int[] arr = new int[]{29, 72, 98, 13, 87, 66, 52, 51, 36};
        sortingBySelect(arr);
    }

    public static void sortingBySelect(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {  // количество проходов будет равняться количеству элементов - 1
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {  //количество итераций в цикле n - i - 1, где i - индекс прохода
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}
