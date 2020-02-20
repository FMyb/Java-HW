package search;

import static search.BinarySearch.*;

/**
 * @author Yaroslav Ilin
 */

public class BinarySearchSpan {
//    Pre: Input.length > 0
    public static void main(String[] args) {
        int findVariable = Integer.parseInt(args[0]);
        int[] searchArray = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            searchArray[i - 1] = Integer.parseInt(args[i]);
        }
//        Pre: left = -1 < findVariable < right = Arr.length && Arr[left] >= findVariable > Arr[right]
        int left = searchRecursionLeft(searchArray, findVariable, -1, searchArray.length);
//        Post: left = findVariable, the left of the entry
//        Pre: left = -1 < findVariable < right = Arr.length && Arr[left] > findVariable > Arr[right]
        int length = searchRecursionRight(searchArray, findVariable, -1, searchArray.length) - left + 1;
//        Post: length = the right of the entry - the left of the entry + 1
        System.out.print(left);
        System.out.print(' ');
        System.out.println(length);
    }

    public static int searchCycleRight(int[] searchArray, int findVariable, int left, int right) {
//        Pre : left <= findIndex < right && Arr[left] >= findVariable > right
        while (left + 1 < right) {
//            Post: left + 1 < right && left <= findIndex < right && Arr[left] >= findVariable > right
//            Pre: left + 1 < right && left <= findIndex < right && Arr[left] >= findVariable > right
            int middle = (left + right) / 2;
//            Post: left + 1 < right && left <= findIndex < right && left < middle < right && Arr[left] >= findVariable > Arr[right]
//            Pre: left + 1 < right && left <= findIndex < right && left < middle < right && Arr[left] >= findVariable > Arr[right]
            if (searchArray[middle] < findVariable) {
//               Post: left + 1 < right && left <= findIndex < right && left < middle < right
//                 && Arr[left] >= findVariable > Arr[middle] > Arr[right]
//               Pre: left + 1 < right && left <= findIndex < right && left < middle < right
//               && Arr[left] >= findVariable > Arr[middle] > Arr[right]
                right = middle;
//                Post: left < new Right < right && Arr[left] >= findVariable > Arr[newRight]
            } else {
//                Post: left + 1 < right && left <= findIndex < right && left < middle < right
//                  && Arr[left] >= Arr[middle] >= findVariable > Arr[right]
//                Pre: left + 1 < right && left <= findIndex < right && left < middle < right
//                  && Arr[left] >= Arr[middle] >= findVariable > Arr[right]
                left = middle;
//                Post: left < new Left < right && Arr[newLeft] >= findVariable > Arr[right]
            }
//            Post : newLeft < newRight && Arr[newLeft] >= findVariable > Arr[newRight] && |newLeft - newRight| < |left - right|
        }
//        Pre: left + 1 == right && Arr[left] = findVariable > Arr[right]
        return left;
//        Post: Ret == findInd && Arr[Ret] == findVariable
    }

    public static int searchRecursionRight(int[] searchArray, int findVariable, int left, int right) {
//        Pre: left < right && Arr[left] >= findVariable > Arr[right]
        if (left + 1 == right) {
//            Post: left + 1 == rihgt && Arr[left] == findVariable > Arr[right]
            return left;
        }
//        Post: left + 1 < right && Arr[left] >= findVariable > Arr[right]
//        Pre: left + 1 < right && Arr[left] >= findVariable > Arr[right]
        int middle = (left + right) / 2;
//        Post: left + 1 < right && left < middle < right && Arr[left] >= findVariable > Arr[right]
//        Pre: left + 1 < right && left < middle < right && Arr[left] >= findVariable > Arr[right]
        if (searchArray[middle] < findVariable) {
//            Post: left + 1 < right && left < middle < right && Arr[left] >= findVariable > Arr[middle] > Arr[right]
//            Pre: left < newRight = middle < right && Arr[left] >= findVariable > Arr[newRight] == Arr[middle] > Arr[right]
//              && |newRight - newLeft| < |right - left|
            return searchRecursionRight(searchArray, findVariable, left, middle);
//            Post: Ret == findInd && Arr[Ret] == findVariable
        } else {
//            Post: left + 1 < right && left < middle < right && Arr[left] >= Arr[middle] >= findVariable > Arr[right]
//            Pre: left < newLeft = middle < right && Arr[newLeft] == Arr[middle] >= findVariable > Arr[right]
//              && |newLeft - newRight| < |right - left|
            return searchRecursionRight(searchArray, findVariable, middle, right);
//            Post: Ret == findInd && Arr[Ret] == findVariable
        }
//        Post: Ret == findInd && Arr[Ret] == findVariable
    }
}
