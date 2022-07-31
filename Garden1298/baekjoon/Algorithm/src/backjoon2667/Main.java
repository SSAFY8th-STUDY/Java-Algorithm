package backjoon2667;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int[][] map = new int[N+2][N+2];
		int[][] visited = new int[N+2][N+2];
		int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
		int[] danzi = new int[N*N];
		int count =0;
		
		for(int i = 1; i<=N; i++)
		{
			String input = in.next();
			for(int j = 1; j<=N; j++)
			{
				map[i][j] = input.charAt(j-1)-'0';
			}
		}
		
		for(int i = 1; i<=N; i++)
		{
			for(int j = 1; j<=N; j++)
			{				
				if(map[i][j] == 1 && visited[i][j] == 0)
				{
					Stack<int[]> toSearch = new Stack<>();
					int tmp = 0;
					
					toSearch.add(new int[] {i,j});
					visited[i][j] = 1;
					
					while(!toSearch.isEmpty())
					{
						int[] current = toSearch.pop();
						tmp++;
						
						for(int k =0; k<direction.length;k++)
						{
							int x = current[0] + direction[k][0];
							int y = current[1] + direction[k][1];
							
							if(map[x][y]==1 && visited[x][y]==0)
							{
								toSearch.add(new int[] {x,y});
								visited[x][y] = 1;
							}
						}											
					}
					danzi[count++] = tmp;
				}
			}
		}
		
		danzi = Arrays.copyOfRange(danzi, 0, count);
		
		Arrays.sort(danzi);
		
		System.out.println(count);
		for(int i =0; i<count;i++)
		{
			System.out.println(danzi[i]);
		}
	}
}
