package P2034;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SSAFY PROJECT
 * 2034. 데일리실습 3-5. 광부 게임 Lv5
 */
public class Main {

	public static void main(String[] args) throws Exception {
		// 상, 하, 좌, 우, 우상, 좌하, 좌상, 우하
		int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};
		int[] dc = {0, 0, -1, 1, 1, -1, -1, 1};
		
		System.setIn(new FileInputStream("src\\P2034\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 광산의 크기
		int[][] map = new int[N][N]; // 광산
		int[] minerPosition = new int[2]; // 광부 위치
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int j=0;
			while(st.hasMoreTokens()) {
				int m = Integer.parseInt(st.nextToken());
				
				if(m == 0) { // 광부 시작 위치
					minerPosition[0] = i;
					minerPosition[1] = j;
				}
				
				map[i][j++] = m;
			}
		}
		
		int M = Integer.parseInt(br.readLine()); // 광부의 이동 횟수
		char[] moves = new char[M]; // 광부의 움직임
		
		for(int i=0; i<M; i++) {
			moves[i] = br.readLine().charAt(0);
		}
		
		int stone = 0; //부순 암석 수
		for(int i=0; i<M; i++) {
			switch(moves[i]) {
			case 'U': // 위
				int ur = minerPosition[0] + dr[0];
				int uc = minerPosition[1] + dc[0];
				
				stone += move(ur, uc, map, minerPosition);
				break;
				
			case 'D': // 아래
				int d_r = minerPosition[0] + dr[1];
				int d_c = minerPosition[1] + dc[1];
				
				stone += move(d_r, d_c, map, minerPosition);
				break;
				
			case 'L': // 왼쪽
				int lr = minerPosition[0] + dr[2];
				int lc = minerPosition[1] + dc[2];
				
				stone += move(lr, lc, map, minerPosition);
				break;
				
			case 'R': //오른쪽
				int rr = minerPosition[0] + dr[3];
				int rc = minerPosition[1] + dc[3];
				
				stone += move(rr, rc, map, minerPosition);
				break;
				
			case 'X': //다이너마이트
				stone += dynamite(map, minerPosition, dr, dc);
				break;
			}
		}
		
		System.out.printf("광부 위치 : (%d,%d)\n", minerPosition[0], minerPosition[1]);
		System.out.println("부순 암석 개수 : " + stone);
	}
	
	public static int move(int r, int c, int[][] map, int[] miner) {
		if(map[r][c] == 0) { //암석이 없을 경우 광부 위치만 이동
			miner[0] = r;
			miner[1] = c;
			
			return 0;
			
		} else if(map[r][c] == 1) { //암석이 1일 경우 0으로 바꾸고 광부 위치이동 후 1 반환
			map[r][c] = 0;
			miner[0] = r;
			miner[1] = c;
			
			return 1;
			
		} else { //암석 내구도 감소
			map[r][c]--;
			return 0;
		}
	}
	
	public static int dynamite(int[][] map, int[] miner, int[] dr, int[] dc) {
		int n = map[0].length;
		int count = 0;
		
		for(int i=0; i<8; i++) { //광부 주변 8방 탐색
			int r = miner[0] + dr[i];
			int c = miner[1] + dc[i];
			if(r>=0 && r<n && c>=0 && c<n && map[r][c] > 0) { //주변에 암석이 있을 경우 0으로 바꾸고 부순 수 증가
				map[r][c] = 0;
				count++;
			}
		}
		
		return count;
	}
}