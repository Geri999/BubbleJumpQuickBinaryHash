package pl.g73.searchservice;

public class LinearSearchGP {

    public static int linearSearch(String[] directoryDataArray, String[] findDataArray) {
        int counter = 0;
        for (int i = 0; i < findDataArray.length; i++) {
            for (int j = 0; j < directoryDataArray.length; j++) {
                if (findDataArray[i].equals(directoryDataArray[j])) {
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }
}
