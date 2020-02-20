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
//        Pre: left = -1, right = length
//        Inv: left < index of findVariable <= right && Arr[left] > findVariable >= Arr[right]
        int ans = searchRecursion(searchArray, findVariable, -1, searchArray.length);
        // Post: find Variable == Ret && find Variable >= Arr[Ret]
        System.out.println(ans == -1 ? searchArray.length : ans);
    }

    public static int searchCycle(int[] searchArray, int findVariable, int left, int right) {
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
//                Pre: left < index of find Variable <= right && left + 1 < right && left < middle < right
//                  && Arr[left] > find Variable >= Arr[right] && Arr[middle] <= find Variable
                right = middle;
//                Post: Arr[left] > find Variable >= Arr[right] && left < find Variable <= right = middle
            } else {
//                Pre: left < index of find Variable <= right && left + 1 < right && left < middle < right
//                  && Arr[left] > find Variable >= Arr[right] && Arr[middle] > find Variable
                left = middle;
//                Post: Arr[left] > find Variable >= Arr[right] && left = middle < find Variable <= right
            }
//            Post: Arr[left] > find Variable >= Arr[right] && left < find Variable <= right
        }
        // Post: left + 1 = find Variable = right &&  Arr[left] > find Variable >= Arr[right]
        return right;
    }

    public static int searchRecursion(int[] searchArray, int findVariable, int left, int right) {
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
//            Pre: left < index of find Variable <= newRight == middle && Arr[left] > find Variable >= Arr[newRight = middle]
            return searchRecursion(searchArray, findVariable, left, middle);
//            Post: Ret == index of find Variable && Arr[Ret] == find Variable
        } else {
//            Pre: newLeft == middle < index of find Variable <= right && Arr[newLeft == middle] > find Variable >= Arr[right]
            return searchRecursion(searchArray, findVariable, middle, right);
//            Post: Ret == index of find Variable && Arr[Ret] == find Variable
        }
    }
}
