package p3025;

import java.io.*;
import java.util.*;

public class Main {

    // R 세로
    // C 가로
    // N 화산재 수
    // Map 맵
    static int R, C, N;
    static char[][] map;
    static Stack<int[]>[] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/p3025/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // Map은 경계 체크를 항상 하지 않기 위해서 패딩을 줬습니다.
        map = new char[R + 1][C + 1];
        dp = new Stack[C + 1];

        for (int i = 0; i <= C; i++) {
            dp[i] = new Stack<>();
        }

        // Map의 상태를 입력받습니다.
        for (int r = 1; r <= R; r++) {
            String line = br.readLine();
            for (int c = 1; c <= C; c++) {
                map[r][c] = line.charAt(c - 1);
            }
        }

        N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            // 화산재가 올 때 마다 떨어뜨립니다.
            int dropColumn = Integer.parseInt(br.readLine());
            int[] starting = new int[] {1, dropColumn};

            while (!dp[dropColumn].isEmpty()) {
                int[] current = dp[dropColumn].peek();

                if (map[current[0]][current[1]] != '.') {
                    dp[dropColumn].pop();
                } else {
                    starting = current;
                    break;
                }
            }

            drop(starting[0], starting[1], dropColumn);
        }

        for(int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void drop(int dropR, int dropC, int dropColumn) {

        while (dropR + 1 <= R && map[dropR + 1][dropC] != 'X') {
            if (map[dropR + 1][dropC] == '.') dropR++;
            else if (map[dropR + 1][dropC] == 'O') {
                if (dropC - 1 >= 1 && dropR + 1 <= R && map[dropR][dropC - 1] == '.' && map[dropR + 1][dropC - 1] == '.') {
                    dropC--; dropR++;
                } else if (dropC + 1 <= C && dropR + 1 <= R && map[dropR][dropC + 1] == '.' && map[dropR + 1][dropC + 1] == '.') {
                    dropC++; dropR++;
                } else {
                    break;
                }
            }
            dp[dropColumn].push(new int[] {dropR, dropC});
        }

        map[dropR][dropC] = 'O';
    }
}