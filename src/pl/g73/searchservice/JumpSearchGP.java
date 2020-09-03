package pl.g73.searchservice;

public class JumpSearchGP {
    public static int jumpSearch(String[] sortedArray, String[] find500Array) {

        int jump = (int) Math.sqrt(sortedArray.length);
        int counter = 0;

        for (String s : find500Array) {
            if (searchJumpBlock(sortedArray, s, jump) >= 0) {
                counter++;
            }
        }
        return counter;
    }

    private static int searchJumpBlock(String[] sortedArray, String target, int jump) {
        int right = 0;

        if (sortedArray[0].equals(target)) return 0;

        while (right < sortedArray.length - 1) {
            right = Math.min(sortedArray.length - 1, right + jump);
            if ((sortedArray[right].compareTo(target)) >= 0) break;
        }
        if (right == sortedArray.length - 1 && sortedArray[sortedArray.length - 1].compareTo(target) < 0) return -1;

        return searchInBlockLinear(sortedArray, target, right, jump);
    }

    private static int searchInBlockLinear(String[] sortedArray, String target, int right, int jump) {

        for (int i = right; i > right - jump; i--) {
            if (sortedArray[i].equals(target)) return i;
        }
        return -1;
    }
}