import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

//2022.08.01
//2034. 광부게임
//project.ssafy.com/Java/2차원배열/Lv5

public class 광부게임_2034 {
	static char[][] map;
	static char[] menu;
	
	static int x = -1, y = -1; //현재 광부 위치 
	static int cnt = 0;
	public static void main(String[] args) throws IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for(int i = 0; i<N;i++) {
			map[i] = br.readLine().toCharArray();
			if(x == -1 && y == -1) {
				for(int j = 0; j<N; j++) {
					if(map[i][j] == '0') {
							x = i;
							y = j;
					}
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		menu = new char[M];
		int bomb_x = x, bomb_y = y;
		for(int i = 0; i<M; i++) {
			menu[i] = br.readLine().charAt(0);
			bomb_x = x;
			bomb_y = y;
			switch(menu[i]) {
			case 'U' : 
				bomb_x += -1;
				bombing(bomb_x, bomb_y);
				if(map[bomb_x][bomb_y] == '0') x = bomb_x;
				break;
			case 'D' :
				bomb_x += 1;
				bombing(bomb_x, bomb_y);
				if(map[bomb_x][bomb_y] == '0') x = bomb_x;
				break;
			case 'R' :
				bomb_y += 1;
				bombing(bomb_x, bomb_y);
				if(map[bomb_x][bomb_y] == '0') y = bomb_y;
				break;
			case 'L' : 
				bomb_y += -1;
				bombing(bomb_x, bomb_y);
				if(map[bomb_x][bomb_y] == '0') y = bomb_y;
				break;
			case 'X' :
				dynamite(bomb_x, bomb_y);
				break;
			}
		}
		System.out.printf("광부 위치 : (%d,%d)\n", x, y);
		System.out.println("부순 암석 개수 : "+cnt);
	}
	
	static void bombing(int bomb_x, int bomb_y) {
		if(map[bomb_x][bomb_y] - 1 > '0') map[bomb_x][bomb_y] = (char) (map[bomb_x][bomb_y] - 1);
		else if (map[bomb_x][bomb_y] - 1 == '0') {
			map[bomb_x][bomb_y] = '0';
			cnt++;
		}
	}
	// (0,0) (0,1) (0,2)
	// (1,0) (1,1) (1,2)
	// (2,0) (2,1) (2,2)
	
	static void dynamite(int bomb_x, int bomb_y) {
		int []dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
		int []dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
		
		for(int i = 0; i < 8; i++) {
			int nx = bomb_x + dx[i];
			int ny = bomb_y + dy[i];
			
			if(map[nx][ny] != '0') {
				cnt++;
				map[nx][ny] = '0';
			}
		}
	}
}
