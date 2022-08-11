package P2667;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		System.setIn(new FileInputStream("src\\P2667\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str.charAt(j) + "");
			}
		}
		
		int danji = 0; //단지 수
		Stack<int[]> stack = new Stack<>(); //단지를 파악하기 위한 이전 집 좌표
		ArrayList<Integer> houses = new ArrayList<>(); //단지에 속하는 집 수
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				//집을 만나면 단지 찾기 시작
				if(map[i][j] == 1) {
					danji++; //단지 수 증가
					map[i][j] = -1; //체크 된 집 -1로 변경
					stack.push(new int[] {i, j}); //체크한 집 스택에 저장
					
					int count = 1; //집 수
					while(stack.size() > 0) { //체크한 집이 더 이상 없을 때 까지
						int[] position = stack.pop(); //가장 최근에 체크한 집
						int r = position[0]; //최근에 체크한 집의 행
						int c = position[1]; //최근에 체크한 집의 열
						
						//델타를 이용하면 집 주변에 다른 집 탐색
						for(int k=0; k<4; k++) {
							int rk = r + dr[k];
							int ck = c + dc[k];
							
							//주변에 집이 있으면 스택에 저장 후 체크 표시
							if(rk >= 0 && rk < n && ck >= 0 && ck < n && map[rk][ck] == 1) {
								stack.push(new int[] {rk, ck});
								map[rk][ck] = -1;
								count++;
							}
						}
					}
					
					houses.add(count);
				}
			}
		}
		
		Collections.sort(houses);
		
		System.out.println(danji);
		for(int h : houses) {
			System.out.println(h);
		}
	}
}