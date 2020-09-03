package pl.g73.sortservice;

public class QuickSortGP {

    public static String[] quickSort(String[] array, int left, int right) {
        if (left < right) {
            int border = partition(array, left, right);
            quickSort(array, left, border - 1);
            quickSort(array, border + 1, right);
        }
        return array;
    }

    private static int partition(String[] array, int left, int right) {
        String pivot = array[right];
        int border = left;

        for (int i = left; i < right; i++) {
            if (array[i].compareTo(pivot) < 0) {
                swap(array, i, border);
                border++;
            }
        }
        swap(array, border, right);
        return border;
    }

    private static void swap(String[] cloneArray, int first, int second) {
        String temp = cloneArray[first];
        cloneArray[first] = cloneArray[second];
        cloneArray[second] = temp;
    }
}
