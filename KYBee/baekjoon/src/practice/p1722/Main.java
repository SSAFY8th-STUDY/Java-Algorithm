package practice.p1722;

import java.util.*;

public class Main {

    static int N;
    static int[] isSelected;
    static long[] facto;
    static int[] output;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        isSelected = new int[N + 1];
        facto = new long[N + 1];
        output = new int[N + 1];

        Arrays.fill(facto, 1);
        for (int i = 1; i <= N; i++) {
            facto[i] = facto[i - 1] * i;
        }

        int op = sc.nextInt();
        if (op == 1) {
            long order = sc.nextLong();
            for (int i = 1; i <= N; i++) {
                // 자리수 찾기
                for (int j = 1; j <= N; j++) {
                    // 어떤 숫자가 들어갈지 찾기
                    if (isSelected[j] != 0) {
                        continue;
                    } else if (facto[N - i] < order) {
                        order -= facto[N - i];
                    } else {
                        isSelected[j] = 1;
                        output[i] = j;
                        break;
                    }
                }
            }
            for (int i = 1; i <= N; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= N; i++) {
                output[i] = sc.nextInt();
                isSelected[i] = i - 1;
            }
            long result = 0;

            for (int i = 1; i <= N; i++) {
                int current = output[i];
                result += facto[N - i] * isSelected[current];
                for (int j = current; j <= N; j++) {
                    isSelected[j]--;
                }
            }
            System.out.println(result + 1);
        }
    }
}