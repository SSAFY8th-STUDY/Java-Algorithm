package baekjoon1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static boolean isVisited[][];
	static int N, M;
	static int direction[][] = {{-1, 0},{1, 0},{0, -1},{0, 1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int testCase = 0; testCase < tc; testCase++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken()); //가로길이 M(1 ≤ M ≤ 50)
			N = Integer.parseInt(st.nextToken()); //세로길이 N(1 ≤ N ≤ 50)
			int K = Integer.parseInt(st.nextToken());
			int answer = 0;
			
			map = new int[M][N];
			isVisited = new boolean[M][N];
			
			for(int i = 0; i<K; i++)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			
			for(int i = 0; i<M; i++)
			{
				for(int j = 0; j<N; j++)
				{
					if(map[i][j]==1 && !isVisited[i][j])
					{
						dfs(i, j);
						//System.out.println("현재 좌표:"+i+", "+j + "정답에 1을 더했습니다");
						answer += 1;
					}
				}
			}
			System.out.println(answer);
		}
	}

	private static void dfs(int a, int b) {
		
		isVisited[a][b] = true;
		//System.out.println("방문체크: "+a+", "+b);
		
		for(int i = 0; i<direction.length; i++)
		{
			int A = a + direction[i][0];
			int B = b + direction[i][1];
			//System.out.println("현재 방향: "+i+" / 다음 좌표: "+A+", "+B);
			
			if(A<0 || A>=M || B<0 || B>=N ) continue;
			
			if(map[A][B]==1 && !isVisited[A][B])
			{
				dfs(A, B);			
			}
		}
		
	}
}
