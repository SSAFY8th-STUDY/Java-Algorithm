package p17608;

import java.io.*;

public class Main {

    static int N;
    static int[] sticks;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p17608/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sticks = new int[N];
        for (int i = 0 ; i < N; i++) {
            sticks[N - i - 1] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        int maxStick = 0;
        for (int i = 0 ; i < N; i++) {
            if (maxStick < sticks[i]) {
                maxStick = sticks[i];
                answer++;
            }
        }

        System.out.println(answer);
    }
}
