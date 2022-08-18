package practice.p10974;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int R = 2;
    static boolean[] isSelected;
    static int[] output;
    static int[] numArr;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        isSelected = new boolean[N];
        output = new int[N];

        int[] idxArr = new int[N];
        for (int i = 0; i < R; i++) {
            idxArr[i] = 1;
        }
        Arrays.sort(idxArr);

        for (int i = 0; i < N; i++) {
            numArr[i] = i + 1;
        }

        Arrays.sort(numArr);

//        do {
//            for (int i = 0; i < N; i++) {
//                System.out.print(numArr[i] + " ");
//            }
//            System.out.println();
//        } while (permutation4(numArr));

//
//        do {
//            for (int i = 0; i < N; i++) {
//                if (idxArr[i] == 1) {
//                    System.out.print(numArr[i] + " ");
//                }
//            }
//            System.out.println();
//        } while (permutation4(idxArr));
//
//

        powerset(0);

    }

    public static void permutation(int cnt) {
        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                output[cnt] = numArr[i];
                permutation(cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    //중복순열
    public static void permutation2(int cnt) {
        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            output[cnt] = numArr[i];
            permutation2(cnt + 1);
        }
    }

    //비트마스킹
    public static void permutation3(int cnt, int flag) {

        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((flag & 1 << i) == 0) {
                output[cnt] = numArr[i];
                permutation3(cnt + 1, flag | 1 << i);
            }
        }
    }

    //Next Permutation
    public static boolean permutation4(int[] numbers) {

        int N = numbers.length;

        //1. 꼭대기 찾기
        int i = N - 1;
        while (i > 0 && numbers[i - 1] >= numbers[i]) i--;

        if (i == 0) {
            return false;
        }

        //2. 꼭대기 앞자리에 들어갈 값 찾기
        int j = N - 1;
        while (numbers[i - 1] >= numbers[j]) j--;
        swap(numbers, i - 1, j);

        //3. i - 1 과 j의 위치값 교환
        int k = N - 1;
        while (i < k) {
            swap(numbers, i++, k--);
        }
        return true;
    }

    public static void swap (int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }


    //조합
    public static void combination(int idx, int start) {

        if (idx == R) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            output[idx] = numArr[i];
            combination(idx + 1, i + 1);
        }
    }

    //중복 조합
    public static void combination2(int idx, int start) {

        if (idx == R) {
            for (int i = 0; i < N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            output[idx] = numArr[i];
            combination2(idx + 1, start);
        }

    }

    public static void powerset(int cnt) {

        if (cnt == N) {
            for (int i = 0; i < N; i++) {
                if (isSelected[i] == true) {
                    System.out.print(numArr[i] + " ");
                }
            }
            System.out.println();
            return;
        }

         isSelected[cnt] = true;
         powerset(cnt + 1);

         isSelected[cnt] = false;
         powerset(cnt + 1);
    }
}
