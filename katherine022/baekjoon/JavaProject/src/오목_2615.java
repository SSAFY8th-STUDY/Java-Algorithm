import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2022.07.30
//2615. 오목
//https://www.acmicpc.net/problem/2615
//skyna

public class 오목_2615 {
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int [][]map = new int[20][20];
		int dx[] = {-1, 1, -1, 1, 1, -1, 0, 0}; //2 묶음씩 이동 방향 생각  : 왼쪽 위, 위, 왼쪽 아래, 왼쪽
		int dy[] = {-1, 1, 0, 0, -1, 1, -1, 1}; //2 묶음씩 이동 방향 생각  : 왼쪽 위, 위, 왼쪽 아래, 왼쪽
		
		//Input
		for(int i = 1; i<=19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i<= 19; i++) {
			for(int j = 1; j<=19; j++) {
				int cur = map[i][j]; 
				if(cur != 0) {
					for(int d = 0; d<8; d+=2) {
						int cnt = 1; 
						int sX = i, sY = j; //시작 위치 x, y
						for(int k = d; k <= d+1; k ++) {
							int x = i, y = j; // 현재 위치
							for(int l = 1; l <= 19; l++) {
								x += dx[k];
								y += dy[k];
								//탈출 조건 : 범위를 벗어나거나 돌 색깔이 같지 않을 경우
								if(x < 1 || x > 19 || y< 1 || y > 19 || map[x][y] != cur) break;
								cnt++;
								if(k % 2 ==0) { // 상, 좌, 좌상, 좌하에 있을 경우에는 계속해서 시작위치를 갱신해주어야 함.
									sX = x;
									sY = y;
								}
							}
						}
						//Print
						if(cnt == 5) {
							System.out.println(cur);
							System.out.println(sX + " " + sY);
							System.exit(0);
						}
					}
				}
			}
		}
		System.out.println(0);
	}
}
