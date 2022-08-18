package hw.p1992;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] picture;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p1992/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        picture = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                picture[i][j] = line.charAt(j) - '0';
            }
        }

        encode(0, 0, N);
        System.out.println(sb);
    }

    public static void encode(int r, int c, int size) {

        if (getTotal(r, c, size)) {
            sb.append(picture[r][c]);
            return;
        }

        int newSize = size / 2;
        sb.append("(");
        encode(r, c, newSize);
        encode(r, c + newSize, newSize);
        encode(r + newSize, c, newSize);
        encode(r + newSize, c + newSize, newSize);
        sb.append(")");
    }

    public static boolean getTotal(int r, int c, int size) {
        int value = picture[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (value != picture[i][j]) return false;
            }
        }
        return true;
    }
}
