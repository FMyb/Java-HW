package search;

/**
 * @author Yaroslav Ilin
 */

public class BinarySearch {
    public static void main(String[] args) {
        int findVariable = Integer.parseInt(args[0]);
        int[] searchArray = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            searchArray[i - 1] = Integer.parseInt(args[i]);
        }
        int ans = searchRecursion(searchArray, findVariable, -1, searchArray.length);
        System.out.println(ans == -1 ? searchArray.length : ans);
    }

    public static int searchCycle(int[] searchArray, int findVariable, int left, int right) {
        while (left + 1 < right) {
            int middle = (left + right) / 2;
            if (searchArray[middle] <= findVariable) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return right;
    }

    public static int searchRecursion(int[] searchArray, int findVariable, int left, int right) {
        if (left + 1 == right) {
            return right;
        }
        int middle = (left + right) / 2;
        if (searchArray[middle] <= findVariable) {
            return searchRecursion(searchArray, findVariable, left, middle);
        } else {
            return searchRecursion(searchArray, findVariable, middle, right);
        }
    }
}
