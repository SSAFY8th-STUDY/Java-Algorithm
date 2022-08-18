package P1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1012. 유기농 배추 - 실버 2
 * 
 * @author hoseong
 * @category BFS, DFS
 */
public class Main {
	static int[] br = {-1, 0, 1, 0};
	static int[] bc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=0; t<TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); //가로길이
			int N = Integer.parseInt(st.nextToken()); //세로길이
			int K = Integer.parseInt(st.nextToken()); //배추 수
			
			int[][] bat = new int[N][M];
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				bat[y][x] = 1;
			}
			
			
		}
	}

}
