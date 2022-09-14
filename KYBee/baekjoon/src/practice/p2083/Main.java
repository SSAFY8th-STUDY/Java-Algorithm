package practice.p2083;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p2083/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            if (name.equals("#")) break;

            int age = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (age > 17 || weight >= 80) System.out.println(name + " Senior");
            else System.out.println(name + " Junior");
        }
    }
}
