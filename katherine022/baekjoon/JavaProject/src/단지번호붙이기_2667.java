//2022.07.31
//2667. 단지번호 붙이기
//https://www.acmicpc.net/problem/2667
//skyna
//Java 8

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 단지번호붙이기_2667 {
	static char[][] map;
	static boolean[][] check;
	static int N;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		check = new boolean[N][N];
		List<Integer> house = new ArrayList<Integer>();
		
		for(int i = 0; i< N ; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j<N; j++) {
				if(isRange(i,j)) continue;
				house.add(dfs(i,j));
			}
		}
		Collections.sort(house); //sort함수를 통해 오름차순 정렬
		System.out.println(house.size());
		for(Integer h : house) {
			System.out.println(h);
		}
	}
	
	public static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N || map[x][y] == '0' || check[x][y] == true;
	}
	
	public static int dfs(int start_x, int start_y) {
		check[start_x][start_y] = true;
		int []dx = {0,0,-1,1}; //상하좌우 방향
		int []dy = {1,-1,0,0}; //상하좌우 방향
		int cnt = 1;
		
		for(int i = 0; i<4; i++) {
			int nx = start_x + dx[i];
			int ny = start_y + dy[i];
			
			if(isRange(nx, ny)) continue; //범위를 넘어설 경우, 값이 0일 경우, 이미 방문한 곳일 경우 continue
			
			cnt += dfs(nx, ny);
		}
		return cnt;
	}

}
