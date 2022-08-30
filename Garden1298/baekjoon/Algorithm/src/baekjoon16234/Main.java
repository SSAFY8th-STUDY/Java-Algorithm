package baekjoon16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Country
	{
		int posX;
		int posY;
		int value;
		
		public Country(int posX, int posY, int value) {
			super();
			this.posX = posX;
			this.posY = posY;
			this.value = value;
		}
	}
	
	static int direction[][] = {{-1, 0},{1, 0},{0, -1},{0, 1}};
	static int map[][];
	static int N, min, max, answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//1 ≤ N ≤ 50
		min = Integer.parseInt(st.nextToken());//1 ≤ L ≤ R ≤ 100
		max = Integer.parseInt(st.nextToken());//1 ≤ L ≤ R ≤ 100
		map = new int[N][N];
		
		for(int i = 0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(answer);
		
	}

	private static void bfs() {
		
		boolean union = false;
		List<Country> list = new ArrayList<>();
		boolean isVisited[][] = new boolean[N][N];
		
		do {
			union = false;
			boolean isCurrentUnion = false;
			list = new ArrayList<>();
			isVisited = new boolean[N][N];
			
			for(int i = 0; i<N; i++)
			{
				for(int j = 0; j<N; j++)
				{
					for(int dir = 0; dir<direction.length; dir++)
					{
						isCurrentUnion = false;
						
						int a = i+direction[dir][0];
						int b = j+direction[dir][1];
						
						if(a<0 || a>=N || b<0 || b>= N || isVisited[a][b]) continue;
						
						int gap = Math.abs(map[i][j] - map[a][b]);
						
						if(gap>=min&&gap<=max)
						{
							isVisited[a][b] = true;
							isCurrentUnion = true;
							union = true;
							list.add(new Country(a, b, map[a][b]));
						}
					}
					
					if(isCurrentUnion && !isVisited[i][j]) 
					{
						list.add(new Country(i, j, map[i][j]));						
						isVisited[i][j] = true;
					}
				}
			}
			
			if(union)
			{
				int avg = 0;
				for(int i = 0; i<list.size(); i++)
				{
					avg += list.get(i).value;
				}
				avg/=list.size();
				
				for(int i = 0; i<list.size(); i++)
				{
					map[list.get(i).posX][list.get(i).posY] = avg;
				}		
				
				answer++;
			}
			
		} while (union);
	}
}
