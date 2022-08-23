package baekjoon16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	
	static class Country
	{
		int posX;
		int posY;
		int value;
		
		public Country(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
	}
	
	static int direction[][] = {{-1, 0},{1, 0},{0, -1},{0, 1}};
	static boolean isVisited[][];
	static int map[][];
	static int N, min, max, answer = 0;
	static boolean isUnion = false;
	
	public static void main(String[] args) throws IOException 
	{
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
		
		while(true)
		{
			isUnion = false;
			isVisited = new boolean[N][N];

			for(int i = 0; i<N; i++)
			{
				for(int j = 0; j<N; j++)
				{
					if(!isVisited[i][j])
					{
						 bfs(new Country(i, j));
					}
				}
			}
			
			if(isUnion) answer++;
			else 
			{
				System.out.println(answer); 
				break;
				
			}
		}
	}

	
	private static void bfs(Country startCountry) 
	{
		Queue<Country> queue = new LinkedList<>();
		List<Country> union = new ArrayList<>();
		queue.offer(startCountry);
		union.add(startCountry);
		
		isVisited[startCountry.posX][startCountry.posY] = true;
		int sum = map[startCountry.posX][startCountry.posY];
		int count = 1;
		
		boolean isCurrentUnion = false;
		
		while(!queue.isEmpty())
		{
			Country currentCountry = queue.poll();
			for(int i = 0; i<direction.length; i++)
			{
				int nextX = currentCountry.posX + direction[i][0];
				int nextY = currentCountry.posY + direction[i][1];
				
				if(nextX<0 || nextX>=N || nextY<0 || nextY>= N || isVisited[nextX][nextY]) continue;
				
				int gap = Math.abs(map[currentCountry.posX][currentCountry.posY] - map[nextX][nextY]);
				
				if(gap>=min&&gap<=max)
				{
					isCurrentUnion = true;
					isVisited[nextX][nextY] = true;
					queue.offer(new Country(nextX, nextY));
					union.add(new Country(nextX, nextY));
					sum+=map[nextX][nextY];
					count++;
				}
			}
		}
		
		if(isCurrentUnion)
		{
			isUnion = true;
			int value = sum/count;
			
			for(int i = 0; i<union.size(); i++)
			{
				int x = union.get(i).posX;
				int y = union.get(i).posY;
				
				map[x][y] = value;
			}
			
			union = new ArrayList<>();
		}
	}
}
