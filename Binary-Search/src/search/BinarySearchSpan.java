package search;

import static search.BinarySearch.*;

/**
 * @author Yaroslav Ilin
 */

public class BinarySearchSpan {
    public static void main(String[] args) {
        int findVariable = Integer.parseInt(args[0]);
        int[] searchArray = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            searchArray[i - 1] = Integer.parseInt(args[i]);
        }
        int left = searchCycleLeft(searchArray, findVariable, -1, searchArray.length);
        int length = searchCycleRight(searchArray, findVariable, -1, searchArray.length) - left + 1;
        System.out.print(left);
        System.out.print(' ');
        System.out.println(length);
    }

    public static int searchCycleRight(int[] searchArray, int findVariable, int left, int right) {
        while (left + 1 < right) {
            int middle = (left + right) / 2;
            if (searchArray[middle] < findVariable) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return left;
    }

    public static int searchRecursionRight(int[] searchArray, int findVariable, int left, int right) {
        if (left + 1 == right) {
            return left;
        }
        int middle = (left + right) / 2;
        if (searchArray[middle] < findVariable) {
            return searchRecursionRight(searchArray, findVariable, left, middle);
        } else {
            return searchRecursionRight(searchArray, findVariable, middle, right);
        }
    }
}
