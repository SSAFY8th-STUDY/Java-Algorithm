package p20056;

import java.util.*;
import java.io.*;

public class Main {
    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static int N, M, K;
    static Queue<Fireball>[][] map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p20056/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Queue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new Fireball(r, c, m, s, d));
        }


        for (int i = 0; i < K; i++) {
            Queue<Fireball> q = new ArrayDeque<>();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!map[r][c].isEmpty()) {
                        while (!map[r][c].isEmpty()) {
                            q.add(map[r][c].poll());
                        }
                    }
                }
            }

            while (!q.isEmpty()) {
                Fireball current = q.poll();
                int newR = (current.s * N + current.r + current.s * dr[current.d]) % N;
                int newC = (current.s * N + current.c + current.s * dc[current.d]) % N;

                current.r = newR;
                current.c = newC;

                map[current.r][current.c].add(current);
            }

            // map 안의 내용 비교
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c].size() > 1) {
                        // 내용을 다 바꿔줌
                        int size = map[r][c].size();

                        int totalM = 0;
                        int totalS = 0;

                        boolean dir = true;
                        boolean isEven = map[r][c].peek().d % 2 == 0;

                        while (!map[r][c].isEmpty()){
                            Fireball cur = map[r][c].poll();
                            totalM += cur.m;
                            totalS += cur.s;
                            if (dir && isEven != (cur.d % 2 == 0)) dir = false;
                        }

                        totalM /= 5;
                        totalS /= size;

                        if (totalM == 0) continue;
                        else {
                            int cnt = 0;
                            if (!dir) cnt = 1;

                            for (int k = 0; k < 4; k++) {
                                map[r][c].add(new Fireball(r, c, totalM, totalS, cnt));
                                cnt += 2;
                            }
                        }
                    }
                }
            }
        }

        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j].isEmpty()) {
                    while(!map[i][j].isEmpty()) {
                        total += map[i][j].poll().m;
                    }
                }
            }
        }

        System.out.println(total);
    }
}

class Fireball {
    int r, c, m, s, d;

    Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }

    @Override
    public String toString() {
        return r + " " + c + " : " + m;
    }
}