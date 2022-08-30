package P2206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2206. 벽 부수고 이동하기 - 골드 4
 * 
 * @author hoseong
 * @category BFS
 */
public class Main {
	static int N,M, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited, dVisited; //visited: 부순벽이 없는 상태로 방문 / dVisited: 벽을 부순 상태로 방문
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		dVisited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			String r = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = r.charAt(j) - '0';
			}
		}

		bfs();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void bfs() {
		Queue<Integer[]> queue = new LinkedList<>();
		queue.offer(new Integer[] {0, 0, 1, 0}); //행, 열, 진행 수, 1 통과 수, 이전 행, 이전 열
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Integer[] p = queue.poll();
			if(p[0] == N-1 && p[1] == M-1) { //목적지에 도달했을때
				min = Math.min(min, p[2]);
				continue;
			}
			
			for(int d=0; d<4; d++) {
				int r = p[0] + dr[d];
				int c = p[1] + dc[d];
				if(r>=0 && r<N && c>=0 && c<M) {
					if(map[r][c] == 1 && p[3] < 1 && !dVisited[r][c]) { //가려는 곳이 1이고 1을 지난적이 없다면
						dVisited[r][c] = true;
						queue.add(new Integer[] {r, c, p[2] + 1, 1});
						
					} else if(map[r][c] == 0) {
						if(p[3] == 1 && !dVisited[r][c]) {
							dVisited[r][c] = true;
							queue.add(new Integer[] {r, c, p[2] + 1, p[3]});
						}
						
						if(p[3] == 0 && !visited[r][c]) {
							visited[r][c] = true;
							queue.add(new Integer[] {r, c, p[2] + 1, p[3]});
						}
					}
				}
			}
		}
	}
}
