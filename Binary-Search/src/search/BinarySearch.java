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
        }
        // Post: left + 1 = find Variable = right &&  Arr[left] > find Variable >= Arr[right]
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
