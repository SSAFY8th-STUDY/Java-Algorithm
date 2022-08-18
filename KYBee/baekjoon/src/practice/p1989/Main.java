//TODO -> 아직 못품

package practice.p1989;

import java.util.*;
import java.io.*;

public class Main {

    static int N, S;
    static int[] numList;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p1989/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());

        numList = new int[N];

        for (int i = 0 ; i < N ; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println();
    }



}
