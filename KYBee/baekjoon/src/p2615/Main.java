package p2615;

import java.util.*;
import java.io.*;

public class Main {

    // 8방을 모두 탐색할 필요는 없음
    public static final int[] dr = {-1, 0, 1, 1};
    public static final int[] dc = {1, 1, 1, 0};

    public static final int MAP_SIZE = 19;
    public static int[][] map = new int[MAP_SIZE + 2][MAP_SIZE + 2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int r = 1; r <= MAP_SIZE; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= MAP_SIZE; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int winner = 0;
        int winR = 0;
        int winC = 0;

        boolean isAble = true;

        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                if (map[r][c] != 0) {
                    int current = map[r][c];

                    // 4방 탐색
                    for (int i = 0; i < 4; i++) {
                        int newR = r + dr[i];
                        int newC = c + dc[i];
                        isAble = true;

                        // 일단 4번 가본다. 가다가 다른 돌이 나오거나 빈 공간이 나오면 탐색을 종료한다.
                        for (int j = 0; j < 4; j++) {
                            if (0 < newR && newR <= MAP_SIZE && 0 <= newC && newC <= MAP_SIZE) {
                                if (map[newR][newC] == current) {
                                    newR += dr[i];
                                    newC += dc[i];
                                } else {
                                    isAble = false;
                                    break;
                                }
                            } else {
                                isAble = false;
                                break;
                            }
                        }

                        if (isAble) {
                            // 여기까지 왔다는 것은 일단 5개의 연속적인 돌을 탐색 완료 했다는 것이다. 따라서 그 돌의 양 옆이 다른 종류의 돌이거나 빈 공간이면 승리한다.
                            // 마지막 돌이 경계인 경우 예외처리를 위해 0과 MAP_SIZE + 1값도 포함
                            if (0 <= newR && newR <= MAP_SIZE + 1 && 0 <= newC && newC <= MAP_SIZE + 1) {
                                if (map[newR][newC] == current) {
                                    isAble = false;
                                }
                            } else {
                                isAble = false;
                            }
                        }

                        if (isAble) {
                            newR = r - dr[i];
                            newC = c - dc[i];

                            if (0 <= newR && newR <= MAP_SIZE + 1 && 0 <= newC && newC <= MAP_SIZE + 1) {
                                if (map[newR][newC] == current) {
                                    isAble = false;
                                } else {
                                    winner = current;
                                    winR = r;
                                    winC = c;
                                    break;
                                }
                            } else {
                                isAble = false;
                            }
                        }
                    }
                }

                if (winner != 0) {
                    break;
                }
            }

            if (winner != 0) {
                break;
            }
        }

        if (winner == 0) {
            System.out.println(0);
        } else {
            System.out.println(winner);
            System.out.printf("%d %d\n", winR, winC);
        }
    }
}