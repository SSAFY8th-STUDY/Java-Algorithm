package practice.p25304;

import java.util.*;
import java.io.*;

public class Main {
    static int X, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        X = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            X -= Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken());
        }

        if (X == 0) System.out.println("Yes");
        else System.out.println("No");
    }
}
