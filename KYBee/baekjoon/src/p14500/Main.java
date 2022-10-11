package p14500;

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static long[][] graph;
    static long[][] sumGraph;
    static long answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p14500/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new long[N + 1][M + 1];
        sumGraph = new long[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                sumGraph[i][j] = graph[i][j] + sumGraph[i - 1][j] + sumGraph[i][j - 1] - sumGraph[i - 1][j - 1];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                firstBlock(i, j);
                secondBlock(i, j);
                thirdBlock(i, j);
                fourthBlock(i, j);
                fifthBlock(i, j);
            }
        }

        System.out.println(answer);
    }

    public static void firstBlock(int r, int c) {
        int[] width = new int[] { 3, 0 };
        int[] height = new int[] { 0, 3 };

        for (int i = 0; i < 2; i++) {
            int newR = r + height[i];
            int newC = c + width[i];

            if (1 <= newR && newR <= N && 1 <= newC && newC <= M) {
                long total = sumGraph[newR][newC] - sumGraph[r - 1][newC] - sumGraph[newR][c - 1] + sumGraph[r - 1][c - 1];
                answer = Math.max(answer, total);
            }
        }
    }

    public static void secondBlock(int r, int c) {
        int[] width = new int[] { 1 };
        int[] height = new int[] { 1 };

        for (int i = 0; i < 1; i++) {
            int newR = r + height[i];
            int newC = c + width[i];

            if (1 <= newR && newR <= N && 1 <= newC && newC <= M) {
                long total = sumGraph[newR][newC] - sumGraph[r - 1][newC] - sumGraph[newR][c - 1] + sumGraph[r - 1][c - 1];
                answer = Math.max(answer, total);
            }
        }
    }

    public static void thirdBlock(int r, int c) {
        int[] width = new int[] { 1, 2 };
        int[] height = new int[] { 2, 1 };

        for (int i = 0; i < 2; i++) {
            int newR = r + height[i];
            int newC = c + width[i];

            if (1 <= newR && newR <= N && 1 <= newC && newC <= M) {
                long total = sumGraph[newR][newC] - sumGraph[r - 1][newC] - sumGraph[newR][c - 1] + sumGraph[r - 1][c - 1];
                long total1; long total2; long total3; long total4;

                if (i == 0) {
                    total1 = total - graph[r][newC] - graph[r + 1][newC];
                    total2 = total - graph[newR - 1][c] - graph[newR][c];
                    total3 = total - graph[r][c] - graph[r + 1][c];
                    total4 = total - graph[newR - 1][newC] - graph[newR][newC];
                } else {
                    total1 = total - graph[r][newC] - graph[r][newC - 1];
                    total2 = total - graph[r][c] - graph[r][c + 1];
                    total3 = total - graph[newR][newC] - graph[newR][newC - 1];
                    total4 = total - graph[newR][c] - graph[newR][c + 1];
                }

                answer = Math.max(answer, total1);
                answer = Math.max(answer, total2);
                answer = Math.max(answer, total3);
                answer = Math.max(answer, total4);
            }
        }
    }


    public static void fourthBlock(int r, int c) {
        int[] width = new int[] { 1, 2 };
        int[] height = new int[] { 2, 1 };

        for (int i = 0; i < 2; i++) {
            int newR = r + height[i];
            int newC = c + width[i];

            if (1 <= newR && newR <= N && 1 <= newC && newC <= M) {
                long total = sumGraph[newR][newC] - sumGraph[r - 1][newC] - sumGraph[newR][c - 1] + sumGraph[r - 1][c - 1];
                long total1; long total2;

                total1 = total - graph[newR][c] - graph[r][newC];
                total2 = total - graph[r][c] - graph[newR][newC];

                answer = Math.max(answer, total1);
                answer = Math.max(answer, total2);
            }
        }
    }

    public static void fifthBlock(int r, int c) {
        int[] width = new int[] { 1, 2 };
        int[] height = new int[] { 2, 1 };

        for (int i = 0; i < 2; i++) {
            int newR = r + height[i];
            int newC = c + width[i];

            if (1 <= newR && newR <= N && 1 <= newC && newC <= M) {
                long total = sumGraph[newR][newC] - sumGraph[r - 1][newC] - sumGraph[newR][c - 1] + sumGraph[r - 1][c - 1];
                long total1; long total2;

                if (i == 0) {
                    total1 = total - graph[r][newC] - graph[newR][newC];
                    total2 = total - graph[r][c] - graph[newR][c];
                } else {
                    total1 = total - graph[r][newC] - graph[r][c];
                    total2 = total - graph[newR][newC] - graph[newR][c];
                }

                answer = Math.max(answer, total1);
                answer = Math.max(answer, total2);
            }
        }
    }
}