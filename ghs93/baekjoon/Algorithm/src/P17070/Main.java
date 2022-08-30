package P17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 17070. 파이프 옮기기 1 - 골드 5
 * 
 * @author hoseong
 * @category DP, 그래프
 */
public class Main {
	static int N;
	static int[][] home;
	
	static int[][][] drr = new int[][][] {{{0}, {1, -1, 1}}, {{1}, {1, -1, 1}}, {{0}, {1}, {1, -1, 1}}};
	static int[][][] dcc = new int[][][] {{{1}, {0, 1, 0}}, {{0}, {0, 1, 0}}, {{1}, {0}, {0, 1, 0}}};
	static int[][] ndd = new int[][] {{1, 3}, {2, 3}, {1, 2, 3}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		home = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(home[N-1][N-1] == 1)
			System.out.println(0);
		else
			System.out.println(move());
	}

	private static int move() {
		int count = 0;
		PriorityQueue<Integer[]> q = new PriorityQueue<>((a, b) -> {return a[0] - b[0];});
		q.offer(new Integer[] {0, 1, 1});
		boolean isMove = true;
		int r = 0;
		int c = 0;
		
		while(!q.isEmpty()) {
			Integer[] p = q.poll();
			if(p[0] == N-1 && p[1] == N-1) {
				count++;
				continue;
			}
			
			int d = p[2] - 1;
			for(int i=0, iSize = drr[d].length; i<iSize; i++) {
				isMove = true;
				r = p[0];
				c = p[1];
				for(int j=0, jSize = drr[d][i].length; j<jSize; j++) {
					r += drr[d][i][j];
					c += dcc[d][i][j];
					
					if(r>=N || c>=N || home[r][c] != 0) {
						isMove = false;
						break;
					}
				}
				
				if(isMove && !q.contains(new Integer[] {r, c, ndd[d][i]}))
					q.offer(new Integer[] {r, c, ndd[d][i]});
			}
		}
		
		return count;
	}
}