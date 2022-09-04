package P17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 17144. 미세먼지 안녕! - 골드 4
 * @author hoseong
 * @category 구현
 */
public class Main {
	static int R, C, T;
	static int[][] room;
	static int[][] air;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		room = new int[R][C];
		air = new int[2][]; //공기청정기 위치
		int a=0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int r = Integer.parseInt(st.nextToken());
				room[i][j] = r;
				
				if(r == -1) {
					air[a++] = new int[] {i, j};
				}
			}
		}
		
		for(int i=0; i<T; i++) {
			spreadDust(); //먼지 퍼뜨리기
			rotateAir(); //공기청정기 돌리기
		}
		
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(room[i][j] > 0)
					cnt += room[i][j];
			}
		}
		
		System.out.println(cnt);
	}

	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	private static void spreadDust() {
		//퍼진 먼지들의 정보
		int[][] temp = new int[R][C];
		temp[air[0][0]][air[0][1]] = -1;
		temp[air[1][0]][air[1][1]] = -1;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(room[i][j] > 0) { //먼지를 찾았을 경우
					ArrayList<int[]> dust = new ArrayList<>();
					for(int d=0; d<4; d++) { //퍼질 수 있는 위치 탐색
						int r = i + dr[d];
						int c = j + dc[d];
						
						if(r >= 0 && r < R && c >= 0 && c < C && temp[r][c] != -1) {
							dust.add(new int[] {r, c});
						}
					}
					
					int spread = room[i][j] / 5; //퍼질 양
					for(int[] d : dust) { //퍼뜨리기
						temp[d[0]][d[1]] += spread;
					}
					temp[i][j] += room[i][j] - (spread * dust.size()); //퍼뜨리고 남은 먼지
				}
			}
		}
		
		room = temp;
	}
	
	private static void rotateAir() {
		int[] top = air[0].clone(); //위쪽 공기청정기
		int[] bottom = air[1].clone(); //아래쪽 공기청정기
		int td = 0; //델타 진행 인덱스 - 상, 우, 하, 좌
		while(true) {
			int r = top[0] + dr[td];
			int c = top[1] + dc[td];
			
			if(r == air[0][0] && c == air[0][1]) { //다음 위치가 공기청정기 위치면
				room[top[0]][top[1]] = 0; //현재 위치 0처리 
				room[r][c] = -1; //공기청정기 표시
				break;
			}
			
			if(r >= 0 && r <= air[0][0] && c >= 0 && c < C) {
				//다음 값 땡겨오기
				room[top[0]][top[1]] = room[r][c];
				
				//현재 위치 이동
				top[0] = r;
				top[1] = c;
				
			} else {
				td = (td+1) % 4;
			}
		}
		
		td = 2; //델타 진행 인덱스 - 하, 우, 상, 좌
		while(true) {
			int r = bottom[0] + dr[td];
			int c = bottom[1] + dc[td];
			
			if(r == air[1][0] && c == air[1][1]) {
				room[bottom[0]][bottom[1]] = 0;
				room[r][c] = -1;
				break;
			}
			
			if(r >= air[1][0] && r < R && c >= 0 && c < C) {
				room[bottom[0]][bottom[1]] = room[r][c];
				bottom[0] = r;
				bottom[1] = c;
				
			} else {
				td = (td+3) % 4;
			}
		}
	}
}