package P2615;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//{상,하}, {좌,우}, {우상,좌하}, {좌상, 우하}
		int[][] dr = {{-1, 1}, {0, 0}, {-1, 1}, {-1, 1}};
		int[][] dc = {{0, 0}, {-1, 1}, {1, -1}, {-1, 1}};
		
		System.setIn(new FileInputStream("src\\P2615\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = 19;
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int index=0;
			while(st.hasMoreTokens()) {
				map[i][index++] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1 || map[i][j] == 2) { // 바둑알일 경우
					int dol = map[i][j];
					
					for(int x=0; x<4; x++) { // 델타 진행 - 시작지점 양쪽(상-하, 좌-우, 우상-좌하, 좌상-우하)으로 총 5개인지 판별
						int count = 1;
						
						int r = 0;
						int c = 0;
						boolean rd = false; //좌하 방햐일 경우 판별
						for(int y=0; y<2; y++) {
							r=i;
							c=j;
							
							while(true) {
								r += dr[x][y];
								c += dc[x][y];
								
								if(r>=0 && r<N && c>=0 && c<N && map[r][c] == dol) {
									if(x==2 && y==1) { // 좌하 방햐일 경우
										rd = true;
									} else {
										rd = false;
									}
									count++;
								} else {
									break;
								}
							}
						}
						
						if(count == 5) {
							System.out.println(dol); //돌 출력
							
							if(rd) { // 좌하 방향일 경우 좌측 좌표 출력
								System.out.println(r + " " + (c+2));
								
							} else { // 좌하 방향이 아닐 경우 시작 좌표 출력
								System.out.println((i+1) + " " + (j+1));
							}
							
							return;
						}
					}
				}
			}
		}
		
		System.out.println(0);
	}
}