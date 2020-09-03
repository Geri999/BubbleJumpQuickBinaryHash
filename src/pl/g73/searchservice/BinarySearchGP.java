package pl.g73.searchservice;

public class BinarySearchGP {

    public static int binarySearchOneArrayInSecond(String[] sortedArray, String[] findDataArray) {
        int counter = 0;
        for (int i = 0; i < findDataArray.length; i++) {
            if (binarySearchMyOwn(sortedArray, findDataArray[i]) >= 0)
                counter++;
        }
        return counter;
    }

    private static int binarySearchMyOwn(String[] sortedArray, String key) {
        int left = 0;
        int right = sortedArray.length - 1;

        while (left <= right) {
            int middle = (left + right) >>> 1;
            if (key.compareTo(sortedArray[middle]) > 0) {
                left = middle + 1;
            }
            if (key.compareTo(sortedArray[middle]) < 0) {
                right = middle - 1;
            }
            if (key.compareTo(sortedArray[middle]) == 0) return middle;
        }
        return -1;
    }
}
