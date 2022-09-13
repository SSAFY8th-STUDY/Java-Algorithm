package P14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 14502. 연구소 - 골드 4
 * @author hoseong
 * @category 구현, BFS
 */
public class Main {
	static int N, M, size, map[][], position[][];
	static ArrayList<int[]> blank, virus;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		blank = new ArrayList<>(); //빈칸 위치
		virus = new ArrayList<>(); //바이러스 위치
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				
				if(n == 0)
					blank.add(new int[] {i, j});
				
				if(n == 2)
					virus.add(new int[] {i, j});
			}
		}
		
		size = blank.size();
		position = new int[size][];
		
		comb(0, 0);
		System.out.println(max);
	}

	//조합을 이용하여 벽을 설치할 위치를 탐색
	static void comb(int cnt, int start) {
		if(cnt == 3) {
			max = Math.max(max, goVirus());
			return;
		}
		
		for(int i=start; i<size; i++) {
			position[cnt] = blank.get(i);
			
			comb(cnt+1, i+1);
		}
	}
	
	//바이러스 퍼뜨리기
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int goVirus() {
		//맵 복사
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++)
			temp[i] = map[i].clone();
		
		//벽 설치
		for (int i = 0; i < 3; i++) {
			int[] p = position[i];
			
			temp[p[0]][p[1]] = 1;
		}
		
		//BFS 탐색하며 바이러스 퍼뜨리기
		Queue<int[]> q = new ArrayDeque<>();
		q.addAll(virus);
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int r = v[0] + dir[i][0];
				int c = v[1] + dir[i][1];
				
				if(r>=0 && r<N && c>=0 && c<M && temp[r][c] == 0) {
					temp[r][c] = 2;
					q.add(new int[] {r, c});
				}
			}
		}
		
		return getEmpty(temp);
	}
	
	//빈 공간 개수
	static int getEmpty(int[][] m) {
		int cnt = 0;
		
		for(int[] r : m) 
			for(int c : r)
				if(c == 0)
					cnt++;
		
		return cnt;
	}
}
