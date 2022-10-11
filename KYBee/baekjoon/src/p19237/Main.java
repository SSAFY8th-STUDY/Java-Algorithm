package p19237;

import java.util.*;
import java.io.*;

public class Main {
    //0 위 1 아래 2 왼쪽 3 오른쪽
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int N, M, K;
    static int time = 0;

    static Smell[][] smell;
    static int[][] map;
    static Queue<Shark> sharks;
    static Map<Integer, int[]>[] direction;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p19237/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        smell = new Smell[N][N];
        map = new int[N][N];
        sharks = new ArrayDeque();
        direction = new HashMap[M + 1];

        for (int i = 1; i <= M; i++) direction[i] = new HashMap<>();

        PriorityQueue<Shark> pq = new PriorityQueue<>((a, b) -> {
            return a.no - b.no;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    pq.add(new Shark(i, j, 0, map[i][j]));
                    smell[i][j] = new Smell(i, j, K, map[i][j]);
                }
            }
        }

        //상어의 방향
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            Shark shark = pq.poll();
            shark.dir = Integer.parseInt(st.nextToken()) - 1;
            sharks.add(shark);
        }

        //위 아래 왼쪽 오른쪽
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                int one = Integer.parseInt(st.nextToken()) - 1;
                int two = Integer.parseInt(st.nextToken()) - 1;
                int three = Integer.parseInt(st.nextToken()) - 1;
                int four = Integer.parseInt(st.nextToken()) - 1;

                direction[i].put(j, new int[] {one, two, three, four});
            }
        }

        while (time <= 1000 && sharks.size() > 1) {
            moveShark();
            spreadSmell();
            time++;
        }
        System.out.println(time > 1000 ? -1 : time);
    }

    public static void moveShark() {
        Queue<Shark> newSharks = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        while (!sharks.isEmpty()) {
            Shark curShark = sharks.poll();
            int curR = curShark.x;
            int curC = curShark.y;
            int curNo = curShark.no;
            int curDir = curShark.dir;

            int emptyCnt = 0; int smellCnt = 0;
            boolean[] empty = new boolean[4];
            boolean[] mySmell = new boolean[4];

            for (int i = 0; i < 4; i++) {
                int newR = curR + dr[i];
                int newC = curC + dc[i];

                if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                    if (smell[newR][newC] != null && smell[newR][newC].no == curNo) {
                        mySmell[i] = true; smellCnt++;
                    } else if (smell[newR][newC] == null) {
                        empty[i] = true; emptyCnt++;
                    }
                }
            }

            int[] dirOrder = direction[curNo].get(curDir);

            if (emptyCnt >= 1) {
                for (int d : dirOrder) {
                    if (empty[d]) {
                        int newR = curR + dr[d];
                        int newC = curC + dc[d];
                        int newDir = d;

                        if (!visited[newR][newC]) {
                            visited[newR][newC] = true;
                            curShark.x = newR;
                            curShark.y = newC;
                            curShark.dir = newDir;
                            newSharks.add(curShark);
                        }
                        break;
                    }
                }
            } else {
                for (int d : dirOrder) {
                    if (mySmell[d]) {
                        int newR = curR + dr[d];
                        int newC = curC + dc[d];
                        int newDir = d;

                        visited[newR][newC] = true;
                        curShark.x = newR;
                        curShark.y = newC;
                        curShark.dir = newDir;
                        newSharks.add(curShark);
                        break;
                    }
                }
            }
        }

        sharks = newSharks;
    }

    public static void spreadSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smell[i][j] != null) {
                    if (smell[i][j].still()) {
                        smell[i][j] = null;
                    }
                }
            }
        }

        for (int i = 0, size = sharks.size(); i < size; i++) {
            Shark curShark = sharks.poll();
            int r = curShark.x;
            int c = curShark.y;

            if (smell[r][c] == null) {
                smell[r][c] = new Smell(r, c, K, curShark.no);
            } else {
                smell[r][c].r = K;
            }

            sharks.add(curShark);
        }
    }
}

class Shark {
    int x, y, dir, no;

    Shark(int x, int y, int dir, int no) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.no = no;
    }
}

class Smell {
    int x, y, r, no;

    Smell(int x, int y, int r, int no) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.no = no;
    }

    public boolean still() {
        this.r--;
        return this.r <= 0;
    }
}