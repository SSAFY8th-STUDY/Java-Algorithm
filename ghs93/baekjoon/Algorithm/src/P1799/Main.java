package P1799;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 1799. 비숍 - 골드 1
 * @author hoseong
 * @category 백트래킹
 */
public class Main {
	static int N, R, map[][], lc[][];
	static ArrayList<int[]> location;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		location = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				
				if(n == 1)
					location.add(new int[] {i, j});
			}
		}
		
		for(int i=location.size(); i>0; i--) {
			R = i;
			lc = new int[R][2];

			if(comb(0, 0)) {
				System.out.println(i);
				break;
			}
		}
	}

	static boolean comb(int cnt, int start) {
		if(cnt == R) {
			return check();
		}
		
		for(int i=start; i<location.size(); i++) {
			lc[cnt] = location.get(i);
			
			if(comb(cnt+1, i+1))
				return true;
		}
		
		return false;
	}
	
	static int[][] dir = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static boolean check() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}
		
		for (int i = 0; i < R; i++) {
			int[] m = lc[i];
			temp[m[0]][m[1]] = 2;
		}
		
		for (int i = 0; i < R; i++) {
			int[] m = lc[i];
			
			for (int d = 0; d < 4; d++) {
				int r = m[0];
				int c = m[1];
				while(true) {
					r += dir[d][0];
					c += dir[d][1];
					
					if(r>=0 && r<N && c>=0 && c<N) {
						if(temp[r][c] == 2) {
							return false;
						}
						
					} else {
						break;
					}
				}
			}
		}
		
		return true;
	}
}