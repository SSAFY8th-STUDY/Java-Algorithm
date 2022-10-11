package p17143;

import java.util.*;
import java.io.*;

public class Main_BJ_17143_낚시왕_김영빈 {

    static int R, C, M;
    static int answer;
    static Map<Integer, Map<Integer, Shark>> sharks = new HashMap<>();
    static Queue<Shark> sharkQ = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p17143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            sharks.put(i, new HashMap<>());
            for (int j = 0; j < C; j++) {
                sharks.get(i).put(j, null);
            }
        }

        //r, c, s, d, z
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;

            if (d == 1) d = 2;
            else if (d == 2) d = 1;

            int z = Integer.parseInt(st.nextToken());

            sharkQ.add(new Shark(r, c, s, d, z, R, C));
        }


        for (int k = 0; k < C; k++) {
            // Queue에 있는 상어를 이동함, 같은 좌표는 없앰
            while (!sharkQ.isEmpty()) {
                Shark current = sharkQ.poll();
                int r = current.r;
                int c = current.c;

                if (sharks.get(r).get(c) == null) {
                    sharks.get(r).replace(c, current);
                } else {
                    Shark before = sharks.get(r).get(c);

                    sharks.get(r).replace(c, before.compareTo(current) < 0 ? before : current);
                }
            }

            // 상어를 하나 잡음 && 잡은 상어 없앰
            for (int r = 0; r < R; r++) {
                if (sharks.get(r).get(k) != null) {
                    answer += sharks.get(r).get(k).z;
                    sharks.get(r).replace(k, null);
                    break;
                }
            }

            // 상어 위치를 바꾸고 큐에 넣음
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (sharks.get(r).get(c) != null) {
                        Shark current = sharks.get(r).get(c);

                        sharks.get(r).replace(c, null);
                        // 이동하고 넣기
                        current.move();
                        sharkQ.add(current);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}

class Shark implements Comparable<Shark> {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    int r, c, s, d, z;
    int R, C;

    Shark(int r, int c, int s, int d, int z, int R, int C) {
        this.r = r;
        this.c = c;
        this.d = d;
        this.z = z;
        this.R = R;
        this.C = C;

        if (d % 2 == 0) {
            this.s = (s % (2 * (R - 1)));
        } else {
            this.s = (s % (2 * (C - 1)));
        }
    }

    @Override
    public int compareTo(Shark s) {
        return Integer.compare(s.z, this.z);
    }

    public void move() {
        //이동한다.
        int cnt = this.s; int newR = this.r; int newC = this.c;
        while(cnt-->0) {
            newR += dr[this.d];
            newC += dc[this.d];

            if (!(0 <= newR && newR < R && 0 <= newC && newC < C)) {
                cnt++;
                newR -= dr[this.d];
                newC -= dc[this.d];
                this.d = (this.d + 2) % 4;
            }
        }

        this.r = newR; this.c = newC;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "r=" + r +
                ", c=" + c +
                ", s=" + s +
                ", d=" + d +
                ", z=" + z +
                '}';
    }
}