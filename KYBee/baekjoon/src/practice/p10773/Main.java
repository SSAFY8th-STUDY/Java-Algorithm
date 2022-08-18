package practice.p10773;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p10773/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int op = Integer.parseInt(br.readLine());
            if (op == 0) {
                stack.pop();
            } else {
                stack.push(op);
            }
        }

        int total = 0;
        while(!stack.isEmpty()) {
            total += stack.pop();
        }

        System.out.println(total);
    }
}