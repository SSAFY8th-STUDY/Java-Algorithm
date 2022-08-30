import java.io.*;
import java.util.*;

public class 유기농배추_1012 {
	static int T,N,M,K;
	static int farm[][] = new int[51][51];
	static int visited[][] = new int[51][51];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
			farm = new int[N+1][M+1];
			visited = new int[N+1][M+1];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				farm[x][y] = 1;
			}
			//for(int[] f : farm) System.out.println(Arrays.toString(f));
			int answer = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(visited[i][j] == 0 && farm[i][j] == 1) {
						dfs(i,j);
						answer++;
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static void dfs(int x, int y) {
		
		visited[x][y] = 1;
		int dx[] = { -1, 1, 0, 0};
		int dy[] = { 0, 0, 1, -1 };
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			
			if(farm[nx][ny] == 1 && visited[nx][ny] == 0) dfs(nx, ny);
		}
		
	}
}
