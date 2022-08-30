import java.io.*;
import java.util.*;

public class 토마토_7576 {
	static int M,N;
	static int box[][];
	static int visited[][];
	static int bef[] = new int[3]; //0 : 안익은 토마토, 인덱스 1 : 익은 토마토, 인덱스 2 : 토마토 없음
	static Queue<int[]> tomato = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				switch(box[i][j]) {
				case -1 :
					bef[2]++;
					break;
				case 1:
					bef[1]++;
					tomato.offer(new int[] {i,j});
					visited[i][j] = 1;
					break;
				}
			}
		}
		//for(int[] t : tomato) System.out.println(Arrays.toString(t));
		int answer = 0;
		if(bef[1] + bef[2] == (M*N)) answer = 0;
		else {
			answer = bfs();
			
			if(bef[0] + bef[1] + bef[2] != (M*N)) answer = -1;
		}
		sb.append(answer);
		System.out.println(sb.toString());
	}
	
	public static int bfs() {
		int dx[] = {0, 0, 1, -1};
		int dy[] = {1, -1, 0, 0};
		
		int level = 0;
		Queue<int[]> q = new ArrayDeque<>(tomato);
		
		while(!q.isEmpty()) {
			int qSize = q.size();
			
			for(int s = 0; s < qSize; s++) {
				int[] here = q.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = here[0] + dx[d];
					int ny = here[1] + dy[d];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if( visited[nx][ny] == 0 && box[nx][ny] == 0 ) {
						bef[0]++;
						box[nx][ny] = 1;
						visited[nx][ny] = 1; 
						q.offer(new int[] {nx, ny});
					}
					
				}
			}
			level++;
		}
		
		return level - 1;
	}
}
