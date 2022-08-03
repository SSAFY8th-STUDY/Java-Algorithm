import java.util.*;
import java.io.*;

public class p2034 {

    public static final int[] dr = {-1, -1, 0, 1, 1,  1,  0, -1};
    public static final int[] dc = { 0,  1, 1, 1, 0, -1, -1, -1};
    public static final Map<Character, Integer> dirToInt = new HashMap<>();

    public static int N;
    public static int[][] map;
    public static int M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("miner.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dirToInt.put('U', 0);
        dirToInt.put('D', 4);
        dirToInt.put('R', 2);
        dirToInt.put('L', 6);

        int r = 0;
        int c = 0;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    r = i;
                    c = j;
                }
            }
        }

        int answer = 0;
        int newR, newC;
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            char op = br.readLine().charAt(0);
            if (op == 'X') {
                if (map[r][c] != 0) {
                    answer++;
                    map[r][c] = 0;
                }
                for (int j = 0; j < 8; j++) {
                    newR = r + dr[j];
                    newC = c + dc[j];

                    if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                        if (map[newR][newC] != 0) {
                            answer++;
                            map[newR][newC] = 0;
                        }
                    }
                }
            } else {
                int key = dirToInt.get(op);
                newR = r + dr[key];
                newC = c + dc[key];

                if (0 <= newR && newR < N && 0 <= newC && newC < N) {
                    if (map[newR][newC] == 0) {
                        r = newR;
                        c = newC;
                    } else if (map[newR][newC] == 1) {
                        r = newR;
                        c = newC;
                        map[newR][newC]--;
                        answer++;
                    } else {
                        map[newR][newC]--;
                    }
                }
            }
        }

        System.out.printf("광부 위치 : (%d,%d)\n", r, c);
        System.out.printf("부순 암석 개수 : %d\n", answer);
    }
}