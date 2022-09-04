package p21610;

import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    static int[] copyR = {-1, -1, 1, 1};
    static int[] copyC = {-1, 1, 1, -1};

    static int N, M, dir, S;
    static int[][] map;
    static Queue<Groom> grooms = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p21610/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        grooms.add(new Groom(N - 1, 0));
        grooms.add(new Groom(N - 1, 1));
        grooms.add(new Groom(N - 2, 0));
        grooms.add(new Groom(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            dir = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            boolean[][] visited = new boolean[N][N];
            int[][] temp = new int[N][N];

            //1. 구름이 하나씩 나오고 물의 양이 1만큼 && 2. 구름이 모두 사라짐
            while (!grooms.isEmpty()) {
                Groom g = grooms.poll();

                int newR = (S * N + g.r + S * dr[dir]) % N;
                int newC = (S * N + g.c + S * dc[dir]) % N;

                visited[newR][newC] = true;
                map[newR][newC] += 1;
            }

            //3. 물 복사 마법 시전
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (visited[r][c]) {
                        int total = 0;
                        for (int k = 0; k < 4; k++) {
                            int newR = r + copyR[k];
                            int newC = c + copyC[k];

                            if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                                if (map[newR][newC] > 0) total++;
                            }
                        }
                        temp[r][c] = total;
                    }
                }
            }

            //4. 다 더하기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] += temp[r][c];
                }
            }

            //5. 새로운 구름 만들기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] >= 2 && !visited[r][c]) {
                        grooms.add(new Groom(r, c));
                        map[r][c] -= 2;
                    }
                }
            }
        }

        int total = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                total += map[r][c];
            }
        }

        System.out.println(total);
    }
}

class Groom {
    int r, c;

    Groom(int r, int c) {
        this.r = r;
        this.c = c;
    }
}