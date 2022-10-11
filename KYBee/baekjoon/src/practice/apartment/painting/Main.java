package practice.apartment.painting;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] yellow;
    static int[] blue;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/apartment/painting/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        yellow = new int[N + 1];
        blue = new int[N + 1];

        yellow[1] = 1; blue[1] = 1;
        for (int i = 2; i <= N; i++) {
            yellow[i] = yellow[i - 1] + blue[i - 1];
            blue[i] = yellow[i - 1];
        }

        System.out.println(yellow[N] + blue[N]);
    }


}
