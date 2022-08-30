package baekjoon17170;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer;
	static int map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i = 1; i<=N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++)
			{
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 2, 1);
		System.out.println(answer);
	}
	
	private static void dfs(int a, int b, int pipe) {
		
		if(a == N && b == N)//도착했다면 정답에 1을 더하고 재귀 나가기
		{
			answer += 1;
			return;
		}
		
		switch (pipe) {
		case 1: //1 : 좌우 파이프
			if(b+1 <= N && map[a][b+1] != 1)//오른쪽 1칸 이동
			{
				dfs(a, b+1, 1);
			}
			if(a+1 <= N && b+1 <= N && map[a+1][b] != 1 && map[a][b+1] != 1 && map[a+1][b+1] != 1)//45도 회전
			{
				dfs(a+1, b+1, 3);
			}
			break;
		case 2: //2 : 상하 파이프
			if(a+1 <= N && map[a+1][b] != 1)//아래쪽 1칸 이동
			{
				dfs(a+1, b, 2);
			}
			if(a+1 <= N && b+1 <= N && map[a+1][b] != 1 && map[a][b+1] != 1 && map[a+1][b+1] != 1)//45도 회전
			{
				dfs(a+1, b+1, 3);
			}
			break;
		case 3: //3 : 대각선 파이프
			if(b+1 <= N && map[a][b+1] != 1)//오른쪽 1칸 이동
			{
				dfs(a, b+1, 1);
			}
			if(a+1 <= N && map[a+1][b] != 1)//아래쪽 1칸 이동
			{
				dfs(a+1, b, 2);
			}
			if(a+1 <= N && b+1 <= N && map[a+1][b] != 1 && map[a][b+1] != 1 && map[a+1][b+1] != 1)//45도 회전
			{
				dfs(a+1, b+1, 3);
			}
			break;
		default:
			break;
		}
	}

}
