package practice.p15170;

import java.util.*;
import java.io.*;

public class Main {
    static int TC, N, answer;
    static int[] output;
    static int[] people;
    static int[] position;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/practice/p15170/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            output = new int[3];
            people = new int[3];
            position = new int[3];

            answer = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                int pos = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                position[i] = pos;
                people[i] = cnt;
            }

            goToYourPosition(0, 0);
            sb.append("#" + tc + " " + answer + "\n");
        }

        System.out.println(sb);
    }

    public static void goToYourPosition(int cnt, int flag) {
        if (cnt == 3) {
            takeSeat(new boolean[N + 2], 0, 0);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if ((flag & (1 << i)) == 0) {
                output[cnt] = i;
                goToYourPosition(cnt + 1, flag | (1 << i));
            }
        }
    }

    public static void takeSeat(boolean[] occupied, int idx, int dist) {
        if (idx == 3) {
            answer = Math.min(dist, answer);
            return;
        }

        if (answer < dist) return;

        int curPosition, leftPos, rightPos;
        curPosition = leftPos = rightPos = position[output[idx]];
        int waiting = people[output[idx]];

        //init
        while (waiting-- > 1) {
            while (0 < leftPos && occupied[leftPos]) leftPos--;
            while (rightPos < N + 1 && occupied[rightPos]) rightPos++;

            if (leftPos < 1) {
                occupied[rightPos] = true;
                dist += rightPos++ - curPosition + 1;
            } else if (rightPos > N) {
                occupied[leftPos] = true;
                dist += curPosition - leftPos-- + 1;
            } else if (leftPos == rightPos) {
                occupied[leftPos] = true;
                dist += curPosition - leftPos-- + 1;
                rightPos++;
            } else {
                if (curPosition - leftPos <= rightPos - curPosition) {
                    occupied[leftPos] = true;
                    dist += curPosition - leftPos-- + 1;
                } else {
                    occupied[rightPos] = true;
                    dist += rightPos++ - curPosition + 1;
                }
            }
        }

        while (0 < leftPos && occupied[leftPos]) leftPos--;
        while (rightPos < N + 1 && occupied[rightPos]) rightPos++;

        if (leftPos < 1) {
            occupied[rightPos] = true;
            dist += rightPos++ - curPosition + 1;
            takeSeat(occupied.clone(), idx + 1, dist);
        } else if (rightPos > N) {
            occupied[leftPos] = true;
            dist += curPosition - leftPos-- + 1;
            takeSeat(occupied.clone(), idx + 1, dist);
        } else if (curPosition - leftPos < rightPos - curPosition) {
            occupied[leftPos] = true;
            dist += curPosition - leftPos-- + 1;
            takeSeat(occupied.clone(), idx + 1, dist);
        } else if (curPosition - leftPos > rightPos - curPosition) {
            occupied[rightPos] = true;
            dist += rightPos++ - curPosition + 1;
            takeSeat(occupied.clone(), idx + 1, dist);
        } else if (curPosition - leftPos == rightPos - curPosition) {
            occupied[leftPos] = true;
            dist += curPosition - leftPos + 1;
            takeSeat(occupied.clone(), idx + 1, dist);
            occupied[leftPos] = false;
            occupied[rightPos] = true;
            takeSeat(occupied.clone(), idx + 1, dist);
            occupied[rightPos] = false;
        }
    }
}