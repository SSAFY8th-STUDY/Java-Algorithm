package practice.p16139;

import java.util.*;
import java.io.*;

public class Main {

    static String S;
    static int q;
    static int[][] alphabet;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p16139/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        S = br.readLine();
        q = Integer.parseInt(br.readLine());
        alphabet = new int[26][S.length() + 1];

        for (int i = 1 ; i <= S.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (S.charAt(i - 1) - 'a' == j) {
                    alphabet[j][i]++;
                }
                alphabet[j][i] = alphabet[j][i] + alphabet[j][i - 1];
            }
        }

        for (int i = 0 ; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            char target = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken()) + 1;
            int end = Integer.parseInt(st.nextToken()) + 1;

            sb.append(alphabet[target - 'a'][end] - alphabet[target - 'a'][start - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
