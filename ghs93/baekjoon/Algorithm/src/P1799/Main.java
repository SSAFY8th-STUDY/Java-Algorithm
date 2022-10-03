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
	static int N, map[][], max;
	static ArrayList<int[]> whiteLocation, blackLocation;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		whiteLocation = new ArrayList<>(); //흰색 체스판 영역
		blackLocation = new ArrayList<>(); //검은색 체스판 영역
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				
				if(n==1) { //비숍을 놓을 수 있는 영역
					if((i+j) % 2 == 0) { //흰색 영역
						whiteLocation.add(new int[] {i, j});
						
					} else { //검은색 영역
						blackLocation.add(new int[] {i, j});
					}
				}
			}
		}
		
		max = Integer.MIN_VALUE;
		subset(0, 0, whiteLocation.size(), whiteLocation);
		int wResult = max;

		max = Integer.MIN_VALUE;
		subset(0, 0, blackLocation.size(), blackLocation);
		int bResult = max;
		
		System.out.println(wResult + bResult);
	}
	
	//부분집합을 이용하여 비숍을 놓았을 때와 안놓았을 때 가능한 경우의 수 구하기
	static void subset(int cnt, int idx, int size, ArrayList<int[]> location) {
		if(idx == size) {
			max = Math.max(max, cnt);
			return;
		}
		
		int[] m = location.get(idx);
		
		// 비숍을 놓았을 경우 바로 겹치는 부분이 있나 검사
		map[m[0]][m[1]] = 1;
		if(check(location)) { // 겹치는 부분이 없을 경우 다음 부분집합 진행
			subset(cnt+1, idx+1, size, location);
		}
		
		map[m[0]][m[1]] = 0;
		subset(cnt, idx+1, size, location);
		
		return;
	}
	
	static int[][] dir = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static boolean check(ArrayList<int[]> location) {
		for (int i = 0, size = location.size(); i < size; i++) {
			int[] m = location.get(i);
			
			if(map[m[0]][m[1]] == 1) {
				for (int j = 0; j < 4; j++) {
					int r = m[0];
					int c = m[1];
					
					while(true) {
						r += dir[j][0];
						c += dir[j][1];
						
						if(r < 0 || r >= N || c < 0 || c >= N)
							break;
						
						if(map[r][c] == 1)
							return false;
					}
				}
			}
		}
		
		return true;
	}
}