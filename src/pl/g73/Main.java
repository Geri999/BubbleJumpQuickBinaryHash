package pl.g73;

import pl.g73.searchservice.BinarySearchGP;
import pl.g73.searchservice.HashSearchGP;
import pl.g73.searchservice.JumpSearchGP;
import pl.g73.searchservice.LinearSearchGP;
import pl.g73.services.LoadService;
import pl.g73.services.TimeServices;
import pl.g73.sortservice.BubbleSortGP;
import pl.g73.sortservice.PBDataBase;
import pl.g73.sortservice.QuickSortGP;

import java.io.IOException;

public class Main {

    public static long linearSearchTime;

    public static void main(String[] args) throws IOException {

        String[] directoryDataArray = LoadService.loadDirectoryDataArray();
        String[] findDataArray = LoadService.loadFindDataArray();

        taskLinearSearching(directoryDataArray, findDataArray);
        taskBubbleSort_JumpSearch(directoryDataArray, findDataArray);
        taskQuickSort_BinarySearch(directoryDataArray, findDataArray);
        taskHashTables(directoryDataArray, findDataArray);
    }

    // ---- 1 Linear search ---------------------------------------------------------------------------------------------
    private static void taskLinearSearching(String[] directoryDataArray, String[] findDataArray) {
        long startTime;
        int counter;
        System.out.println("Start searching (linear search)...");
        startTime = System.currentTimeMillis();

        counter = LinearSearchGP.linearSearch(directoryDataArray, findDataArray);

        linearSearchTime = System.currentTimeMillis() - startTime;
        System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, TimeServices.timeConverter(linearSearchTime));
    }

    // ----- 2 Bubble sort + Jump search ---------------------------------------------------------------------------------
    private static void taskBubbleSort_JumpSearch(String[] directoryDataArray, String[] findDataArray) {
        long startTime;
        int counter;
        String[] directoryDataArrayClone = directoryDataArray.clone();

        System.out.println("\nStart searching (bubble sort + jump search)...");

        // ------------ BUBBLE SORT
        startTime = System.currentTimeMillis();
        String[] sortedArray = BubbleSortGP.bubbleSort(directoryDataArrayClone);
        long bubbleSortTime = System.currentTimeMillis() - startTime;

        if (sortedArray != null) {

            // --------- SEARCH JUMP
            startTime = System.currentTimeMillis();
            counter = JumpSearchGP.jumpSearch(sortedArray, findDataArray);
            long jumpSearchTime = System.currentTimeMillis() - startTime;

            System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter,
                    TimeServices.timeConverter(bubbleSortTime + jumpSearchTime));
            System.out.printf("Sorting time: " + TimeServices.timeConverter(bubbleSortTime) + "\n");
            System.out.printf("Searching time: " + TimeServices.timeConverter(jumpSearchTime) + "\n");
        } else {

            // --------- SEARCH LINEAR
            startTime = System.currentTimeMillis();
            counter = LinearSearchGP.linearSearch(directoryDataArray, findDataArray);
            linearSearchTime = System.currentTimeMillis() - startTime;

            System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, TimeServices.timeConverter(bubbleSortTime + linearSearchTime));
            System.out.printf("Sorting time: %s - STOPPED, moved to linear search\n", TimeServices.timeConverter(bubbleSortTime));
            System.out.printf("Searching time: %s", TimeServices.timeConverter(linearSearchTime));
        }
    }

    // ----- 3 Quick sort + Binary search--------------------------------------------------------------------------------------------
    private static void taskQuickSort_BinarySearch(String[] directoryDataArray, String[] findDataArray) {
        long startTime;
        int counter;
        System.out.println("\n\nStart searching (quick sort + binary search)...");
        String[] directoryDataArrayClone;

        //-------- Quick sort
        startTime = System.currentTimeMillis();
        directoryDataArrayClone = directoryDataArray.clone();

        String[] sortedArrayByQSort = QuickSortGP.quickSort(directoryDataArray, 0, directoryDataArrayClone.length - 1);

        long quickSortTime = System.currentTimeMillis() - startTime;

        //-------- Binary search
        startTime = System.currentTimeMillis();
        counter = BinarySearchGP.binarySearchOneArrayInSecond(sortedArrayByQSort, findDataArray);
        long binarySearchTime = System.currentTimeMillis() - startTime;

        System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, TimeServices.timeConverter(quickSortTime + binarySearchTime));
        System.out.printf("Sorting time: " + TimeServices.timeConverter(quickSortTime) + "\n");
        System.out.printf("Searching time: " + TimeServices.timeConverter(binarySearchTime) + "\n");
    }

    // ----- 4 HashTables--------------------------------------------------------------------------------------------
    private static void taskHashTables(String[] directoryDataArray, String[] findDataArray) throws IOException {
        System.out.println("\nStart searching (hash table)...");

        long startTime;
        int counter;

        // ------------------------creating
        startTime = System.currentTimeMillis();
        PBDataBase pbDataBase = new PBDataBase(1200000);
        LoadService.loadDirectoryDataArrayHashMap2(pbDataBase);
        long hashingTime = System.currentTimeMillis() - startTime;

        // ------------------------searching
        startTime = System.currentTimeMillis();
        counter = HashSearchGP.hashSearchGP(pbDataBase, findDataArray);
        long hashingSearchingTime = System.currentTimeMillis() - startTime;

        System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, TimeServices.timeConverter(hashingTime + hashingSearchingTime));
        System.out.printf("Creating time: " + TimeServices.timeConverter(hashingTime) + "\n");
        System.out.printf("Searching time: " + TimeServices.timeConverter(hashingSearchingTime) + "\n");
    }
}
