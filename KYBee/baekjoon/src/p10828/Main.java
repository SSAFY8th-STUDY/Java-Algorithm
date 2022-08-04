package p10828;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] stack = new int[10001];
    static int top = -1;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p10828/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            switch (op) {
                case "push" :
                    int number = Integer.parseInt(st.nextToken());
                    stack[++top] = number;
                    break;
                case "pop":
                    sb.append(top == -1 ? -1 : stack[top--]).append("\n");
                    break;
                case "size":
                    sb.append(top + 1).append("\n");
                    break;
                case "empty":
                    sb.append(top == -1 ? 1 : 0).append("\n");
                    break;
                case "top":
                    sb.append(top == -1 ? top : stack[top]).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}
