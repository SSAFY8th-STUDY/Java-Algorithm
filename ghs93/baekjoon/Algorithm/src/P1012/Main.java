package P1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1012. 유기농 배추 - 실버 2
 * 
 * @author hoseong
 * @category BFS, DFS
 */
public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] position; //배추 위치
	static int N, M;
	static int[][] bat; //밭

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로길이
			N = Integer.parseInt(st.nextToken()); // 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추 수

			bat = new int[N][M];
			int[][] position = new int[K][2];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				bat[y][x] = 1;
				position[i][0] = y;
				position[i][1] = x;
			}

			int count = 0;
			for (int i = 0; i < K; i++) {
				if (bat[position[i][0]][position[i][1]] == 1) {
					count++;
					dfs(position[i]);
				}
			}

			System.out.println(count);
		}
	}

	private static void dfs(int[] cur) {
		bat[cur[0]][cur[1]] = 0; //탐색한 곳 처리

		//4방안에 배추가있다면 그 좌표로 이동
		for (int i = 0; i < 4; i++) {
			int r = cur[0] + dr[i];
			int c = cur[1] + dc[i];

			if (r >= 0 && r < N && c >= 0 && c < M && bat[r][c] == 1) {
				dfs(new int[] { r, c });
			}
		}
	}
}