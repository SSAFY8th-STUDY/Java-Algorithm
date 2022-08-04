package p9012;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Stack<Boolean> stack;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p9012/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            stack = new Stack<>();

            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '(') {
                    stack.push(true);
                } else if (stack.isEmpty()) {
                    stack.push(true);
                    break;
                } else {
                    stack.pop();
                }
            }
            sb.append(stack.isEmpty() ? "YES\n" : "NO\n");
        }
        System.out.println(sb);
    }
}
