package P2206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2206. 벽 부수고 이동하기 - 골드 4
 * 
 * @author hoseong
 * @category BFS
 */
public class Main {
	static int N,M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			String r = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = r.charAt(j) - '0';
			}
		}

		
	}

}
