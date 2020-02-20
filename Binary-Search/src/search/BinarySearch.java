package search;

/**
 * @author Yaroslav Ilin
 */

public class BinarySearch {
//    Pre: Input.length > 0
    public static void main(String[] args) {
        int findVariable = Integer.parseInt(args[0]);
        int[] searchArray = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            searchArray[i - 1] = Integer.parseInt(args[i]);
        }
//        Pre: left = -1, right = length
//        Inv: left < index of findVariable <= right && Arr[left] > findVariable >= Arr[right] && |new Right - new Left| < |right - left|
        int ans = searchRecursionLeft(searchArray, findVariable, -1, searchArray.length);
        // Post: find Variable == Ret && find Variable >= Arr[Ret], the left of the entry
        System.out.println(ans == -1 ? searchArray.length : ans);
    }

//    Pre: Arr is sort, left < findVariable <= right
//    Post: Ret = Index of find Variable
    public static int searchCycleLeft(int[] searchArray, int findVariable, int left, int right) {
//        Pre: left < index of find Variable <= right && Arr[left] > find Variable >= Arr[right]
        while (left + 1 < right) {
//            Post: left < index of find Variable <= right && left + 1 < right && Arr[left] > find Variable >= Arr[right]
//            Pre: left < index of find Variable <= right && left + 1 < right && Arr[left] > find Variable >= Arr[right]
            int middle = (left + right) / 2;
//            Post: left < index of find Variable <= right && left + 1 < right && left < middle < right
//              && Arr[left] > find Variable >= Arr[right]
//            Pre: left < index of find Variable <= right && left + 1 < right && left < middle < right
//              && Arr[left] > find Variable >= Arr[right]
            if (searchArray[middle] <= findVariable) {
//                Post: left < index of find Variable <= right && left + 1 < right && left < middle < right
//                  && Arr[left] > find Variable >= Arr[right] && Arr[middle] <= find Variable
//                Pre: left < index of find Variable <= right && left + 1 < right && left < middle < right
//                  && Arr[left] > find Variable >= Arr[right] && Arr[middle] <= find Variable
                right = middle;
//                Post: Arr[left] > find Variable >= Arr[newRight] && left < find Variable <= newRight = middle < right
            } else {
//                Post: left < index of find Variable <= right && left + 1 < right && left < middle < right
//                 && Arr[left] > find Variable >= Arr[right] && Arr[middle] > find Variable

//                Pre: left < index of find Variable <= right && left + 1 < right && left < middle < right
//                  && Arr[left] > find Variable >= Arr[right] && Arr[middle] > find Variable
                left = middle;
//                Post: Arr[newLeft] > find Variable >= Arr[right] && newLeft < left = middle < find Variable <= right
            }
//            Post: Arr[Left] > find Variable >= Arr[newRight] && newLeft < find Variable <= newRight, |new Left - new Right| < |left - right|
        }
        // Post: find Variable = right
        return right;
    }

//    Pre: Arr is sort, left < findVariable <= right
//    Post: Ret = Index of find Variable
    public static int searchRecursionLeft(int[] searchArray, int findVariable, int left, int right) {
//        Pre: left < index of find Variable <= right && Arr[left] > find Variable >= Arr[right]
        if (left + 1 == right) {
//            Pre: left + 1 == index of find Variable == right && Arr[left] > find Variable >= Arr[right]
            return right;
        }
//        Post: left + 1 < index of find Variable <= right && Arr[left] > find Variable >= Arr[right]
//        Pre: left + 1 index of find Variable <= right && Arr[left] > find Variable >= Arr[right]
        int middle = (left + right) / 2;
//        Post: left + 1 index of find Variable <= right && left < middle < right && Arr[left] > find Variable >= Arr[right]
        if (searchArray[middle] <= findVariable) {
//            Pre: left < index of find Variable <= newRight == middle < right && Arr[left] > find Variable >= Arr[newRight = middle]
            return searchRecursionLeft(searchArray, findVariable, left, middle);
//            Post: Ret == index of find Variable && Arr[Ret] == find Variable
        } else {
//            Pre: left < newLeft == middle < index of find Variable <= right && Arr[newLeft == middle] > find Variable >= Arr[right]
            return searchRecursionLeft(searchArray, findVariable, middle, right);
//            Post: Ret == index of find Variable && Arr[Ret] == find Variable
        }
    }
}
