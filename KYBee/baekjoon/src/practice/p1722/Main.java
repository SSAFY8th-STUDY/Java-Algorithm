package practice.p1722;

import java.util.*;

public class Main {

    static int N;
    static int R;
    static int totalCnt;
    static int[] numbers;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        numbers = new int[N];
        isSelected = new boolean[N + 1];

        int op = sc.nextInt();
        if (op == 1) {
            int order = sc.nextInt();
            //System.out.println(getNum(order));
        } else {
            String temp = "";
            for (int i = 0; i < N; i++) {
                int tempVal = sc.nextInt();
                temp += tempVal + " ";
            }
            //perm(0, temp);
        }
    }

}
