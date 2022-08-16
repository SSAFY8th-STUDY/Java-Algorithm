package practice.p2231;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int sum = i;
            StringBuilder sb = new StringBuilder();
            sb.append(i);

            for (int j = 0; j < sb.length(); j++) {
                sum += sb.charAt(j) - '0';
            }

            if (sum == N) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
