package p14891;

import java.util.*;
import java.io.*;

public class Main {

    static Topni[] topnis = new Topni[4];
    static int K;
    static int answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p14891/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            int[] status = new int[8];
            String line = br.readLine();

            for (int j = 0; j < 8; j++) {
                status[j] = line.charAt(j) - '0';
            }

            topnis[i] = new Topni(6, 2, status);
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            reorder(no, dir);
        }

        int score = 1;
        for (int i = 0; i < 4; i++) {
            answer += topnis[i].status[(topnis[i].left + 2) % 8] * score;
            score *= 2;
        }

        System.out.println(answer);
    }

    public static void reorder(int no, int dir) {
        Topni current = topnis[no];
        int newDir = dir;
        int[] spinStatus = new int[4];
        spinStatus[no] = dir;

        for (int i = no - 1; i >= 0; i--) {
            Topni left = topnis[i];
            if (current.status[current.left] != left.status[left.right]) {
                newDir *= -1;
                spinStatus[i] = newDir;
                current = left;
            } else break;
        }

        current = topnis[no];
        newDir = dir;

        for (int i = no + 1; i < 4; i++) {
            Topni right = topnis[i];
            if (current.status[current.right] != right.status[right.left]) {
                newDir *= -1;
                spinStatus[i] = newDir;
                current = right;
            } else break;
        }

        for (int i = 0; i < 4; i++) {
            if (spinStatus[i] != 0) {
                topnis[i].spin(spinStatus[i]);
            }
        }
    }
}

class Topni {
    int left, right;
    int[] status;

    Topni(int left, int right, int[] status) {
        this.left = left;
        this.right = right;
        this.status = status;
    }

    public void spin(int direction) {
        if (direction == -1) {
            this.left = (this.left + 1) % 8;
            this.right = (this.right + 1) % 8;
        } else if (direction == 1) {
            this.left = (this.left + 7) % 8;
            this.right = (this.right + 7) % 8;
        }
    }
}