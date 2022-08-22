import java.io.*;
import java.util.*;

public class 인구이동_16234 {
	static int N, L, R;
	static int A[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int v[][];
	
	
	public static boolean bfs(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x,y});
		v[x][y] = 1;
		int sum = 0, cnt = 0;
		Queue<int[]> check = new ArrayDeque<>();
		
		while(!queue.isEmpty()) {
			int[] here = queue.poll();
			sum += A[here[0]][here[1]];
			cnt++;
			check.offer(here);
			
			for(int d = 0; d < 4; d++) {
				int nx = here[0] + dx[d];
				int ny = here[1] + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || v[nx][ny] == 1) continue; 
				int diff = Math.abs(A[nx][ny] - A[here[0]][here[1]]);
				if(diff < L || diff > R) continue;
				queue.offer(new int[] {nx,ny});
				v[nx][ny] = 1;
			}
		}
		if(cnt == 1) return false;
		
		int people = (int) (sum / cnt);
		while(!check.isEmpty()) {
			int here[] = check.poll();
			int cx = here[0];
			int cy = here[1];
			A[cx][cy] = people;
		}
		return true;
	}
	
	public static void bfsAll() {
		int com = -1;
		int cnt = 0;
		while(com != 0) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					v[i][j] = 0;
				}
			}
			com = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(v[i][j] == 0) {
						if(bfs(i,j)) com++;
					}
				}
			}
			cnt++;
		}
		System.out.println(cnt-1);
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		v = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfsAll();
		
		
	}

}
