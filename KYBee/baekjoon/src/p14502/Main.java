package p14502;

import java.util.*;
import java.io.*;

public class Main {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int N, M;
    static int E;
    static int[][] map;
    static int maxValue;

    // Virus point
    static List<Point> virusList = new ArrayList<>();
    // Emtpy point
    static List<Point> pointList = new ArrayList<>();
    static int[] result = new int[3];

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p14502/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virusList.add(new Point(i, j));
                } else if (map[i][j] == 0) {
                    pointList.add(new Point(i, j));
                }
            }
        }

        E = pointList.size();
        combination(0, 0);
        System.out.println(maxValue);
    }

    public static void combination(int start, int cnt){
        if (cnt == 3) {
            bfs();
            return;
        }

        for (int i = start, size = pointList.size(); i < size; i++) {
            result[cnt] = i;
            combination(i + 1, cnt + 1);
        }
    }

    public static void bfs() {
        int[][] newMap = new int[N][M];

        for (int i = 0; i < 3; i++) {
            int x = pointList.get(result[i]).x;
            int y = pointList.get(result[i]).y;

            newMap[x][y] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    newMap[i][j] = 1;
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        for (Point virus : virusList) {
            q.add(virus);
            newMap[virus.x][virus.y] = 1;
        }

        while (!q.isEmpty()) {

            Point current = q.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (0 <= newX && newX < N && 0 <= newY && newY < M) {
                    if (map[newX][newY] == 0 && newMap[newX][newY] == 0) {
                        newMap[newX][newY] = 1;
                        q.add(new Point(newX, newY));
                    }
                }
            }
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                total += newMap[i][j];
            }
        }

        maxValue = Math.max(maxValue, N * M - total);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

}