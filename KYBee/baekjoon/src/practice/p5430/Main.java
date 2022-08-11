package practice.p5430;

import java.util.*;
import java.io.*;

public class Main {

    static int TC;
    static int N = 0;
    static LinkedList<Integer> list;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p5430/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {

            String command = br.readLine();
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            int[] tempList = new int[N];
            for (int i = 0 ; i < N; i++) {
                tempList[i] = Integer.parseInt(st.nextToken());
            }

            boolean reverse = false;

            list = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                list.add(tempList[i]);
            }

            boolean isErr = false;
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == 'R') {
                    reverse = !reverse;
                } else if (list.size() < 1) {
                    sb.append("error\n");
                    isErr = true;
                    break;
                } else {
                    if (reverse) {
                        list.pollLast();
                    } else {
                        list.pollFirst();
                    }
                }
            }

            if (isErr){
                continue;
            }

            sb.append("[");
            if (reverse) {
                while (list.size() > 1) {
                    sb.append(list.pollLast()).append(",");
                }
            } else {
                while (list.size() > 1) {
                    sb.append(list.pollFirst()).append(",");
                }
            }

            if (list.size() >= 1) {
                sb.append(list.pollLast());
            }
            sb.append("]\n");
        }

        System.out.println(sb);
    }
}