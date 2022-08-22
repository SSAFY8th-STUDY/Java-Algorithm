package P7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 7576. 토마토 - 골드 5
 * 
 * @author hoseong
 * @category BFS
 */
public class Main {
	static int N, M;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] box;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		boolean isAlready = true; //이미 모든 토마토가 익었는지
		box = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int t = Integer.parseInt(st.nextToken());
				box[i][j] = t;

				if (t == 0)
					isAlready = false;
				else if (t == 1)
					q.offer(new int[] { i, j });
			}
		}

		if (isAlready) { // 모든 토마토가 익어있는 경우
			System.out.println(0);

		} else {
			int cnt = bfs();

			//안익은 토마토가 있는가
			for (int[] tomato : box) {
				for (int t : tomato) {
					if (t == 0) {
						System.out.println(-1);
						return;
					}
				}
			}

			System.out.println(cnt);
		}
	}

	private static int bfs() {
		int count = 0;
		
		while (!q.isEmpty()) {
			//가장 최근에 익은 토마토
			Queue<int[]> temp = new LinkedList<>();
			temp.addAll(q);
			q.clear();
			
			boolean isChange = false; //앞으로 익을 토마토가 있는가
			while (!temp.isEmpty()) {
				int[] t = temp.poll();

				for (int i = 0; i < 4; i++) {
					int r = t[0] + dr[i];
					int c = t[1] + dc[i];

					if (r >= 0 && r < N && c >= 0 && c < M && box[r][c] == 0) {
						box[r][c] = 1;
						q.offer(new int[] { r, c });
						isChange = true;
					}
				}
			}

			if (isChange)
				count++;
		}

		return count;
	}
}
