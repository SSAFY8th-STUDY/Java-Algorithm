package p1644;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean[] isPrime;
    static int[] prime;
    static int answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p1644/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        getPrime();

        for (int i = 2; i <= N; i++) {
            if (isPrime[i] == true) M++;
        }

        prime = new int[M + 1];
        int cnt = 1;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i] == true) {
                prime[cnt] = i + prime[cnt - 1];
                cnt++;
            }
        }

        int start = 0, end = 1;
        while (start <= M && end <= M && start < end) {
            int current = prime[end] - prime[start];

            if (current == N) {
                answer++;
            }
            if (current >= N) {
                start++;
            } else {
                end++;
            }
        }

        System.out.println(answer);
    }

    public static void getPrime() {
        for (int i = 2; i <= N; i++) {
            if (isPrime[i] == true) {
                int current = i + i;
                while (current <= N) {
                    isPrime[current] = false;
                    current += i;
                }
            }
        }
    }
}
