package hw.p1759;

import java.util.*;
import java.io.*;

public class Main_BJ_1759_암호만들기_김영빈 {
    static int N, R;
    static char[] word;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p1759/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        word = new char[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N; i++) word[i] = st.nextToken().charAt(0);

        Arrays.sort(word);
        goEncode(0, 0, "", 0, 0);
    }

    public static void goEncode(int cnt, int start,  String result, int jaum, int moum) {
        if (cnt == R) {
            if (jaum >= 2 && moum >= 1) System.out.println(result);
            return;
        }

        for (int i = start; i < N; i++) {
            if (word[i] == 'a' || word[i] == 'e' || word[i] == 'i' || word[i] == 'o' || word[i] == 'u')
                goEncode(cnt + 1 , i + 1, result + word[i], jaum, moum + 1);
            else goEncode(cnt + 1 , i + 1, result + word[i], jaum + 1, moum);
        }
    }
}