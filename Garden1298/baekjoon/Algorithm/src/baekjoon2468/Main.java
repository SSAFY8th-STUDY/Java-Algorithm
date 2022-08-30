package baekjoon2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Land
	{
		int posX;
		int posY;
		public Land(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
	}
	
	static int N;
	static int map[][];
	static int direction[][] = {{-1, 0},{0, 1},{1, 0},{0, -1}};
	static boolean isVisited[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //N은 2 이상 100 이하의 정수
		map = new int[N][N];
		
		int maxHeight = 0;
		int answer = 0;
		
		for(int i = 0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++)
			{
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				maxHeight = Math.max(maxHeight, input);
			}
		}
		
		for(int height = 0; height<maxHeight; height++)
		{
			int count = 0;
			isVisited = new boolean[N][N];
			
			for(int a = 0; a<N; a++)
			{
				for(int b = 0; b<N; b++)
				{
					if(map[a][b] > height && !isVisited[a][b])
					{
						bfs(a, b, height);
						count += 1;
					}
				}
			}
			
			answer = Math.max(answer, count);
		}
		System.out.println(answer);
	}

	private static void bfs(int a, int b, int height) 
	{
		Queue<Land> queue = new LinkedList<Land>();
		isVisited[a][b] = true;
		queue.add(new Land(a, b));
		
		while(!queue.isEmpty()) {
			
			Land currentLand = queue.poll();
			
			for (int i =0; i < direction.length; i++) {
				
				int A = direction[i][0] + currentLand.posX;
				int B = direction[i][1] + currentLand.posY;
				
				if(A < 0 || A >= N || B <0 || B >= N ) continue;

				if( map[A][B] > height  && !isVisited[A][B]) 
				{
					queue.add(new Land(A, B));
					isVisited[A][B] = true;
				}
			}
		}
	}
	
}
