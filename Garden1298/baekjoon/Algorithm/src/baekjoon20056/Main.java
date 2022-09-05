package baekjoon20056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Point
	{
		int posX;
		int posY;
		
		public Point(int posX, int posY) 
		{
			super();
			this.posX = posX;
			this.posY = posY;
		}
		
		@Override
		public String toString() 
		{
			return "Point [posX=" + posX + ", posY=" + posY + "]";
		}
	}
	
	static int N, M, d, s;
	static int map[][];
	static int direction[][] = {{0,0},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};//1번부터 8번까지 왼쪽부터 시계방향
	static int directionWaterCopy[][] = {{-1,-1},{-1,1},{1,1},{1,-1}};//대각선 방향
	
	static boolean isVisited[][];
	static List<Point> rainClouds = new ArrayList<>();

	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//맵의 가로 세로 크기(2 ≤ N ≤ 50)
		M = Integer.parseInt(st.nextToken());//구름이 이동할 횟수(1 ≤ M ≤ 100)
		
		map = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//처음 구름의 위치
		rainClouds.add(new Point(N, 1));
		rainClouds.add(new Point(N, 2));
		rainClouds.add(new Point(N-1, 1));
		rainClouds.add(new Point(N-1, 2));

		for(int i = 0; i < M; i++)
		{
			isVisited = new boolean[N+1][N+1];

			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());//구름의 이동 방향(1 ≤ d ≤ 8)
			s = Integer.parseInt(st.nextToken());//구름의 이동 범위(1 ≤ s ≤ 50)
			
			moveClouds();//구름 이동
			rain();//비 내리기
			waterCopyMagic();//물복사마법
			makeClouds();//구름 생성
			
			//System.out.println(i+"단계 끝");
			//print();
		}
		
		System.out.println(sumWater());
	}
	
//	private static void print()
//	{
//		System.out.println("<=====맵 출력=====>");
//		for(int i = 1; i <= N; i++)
//		{
//			for(int j = 1; j <= N; j++)
//			{
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
	
	private static int sumWater()
	{
		int sum = 0;
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				sum += map[i][j]; 
			}
		}
		return sum;
	}

	private static void makeClouds() 
	{
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(isVisited[i][j]) continue;
				
				if(map[i][j]>=2)
				{
					rainClouds.add(new Point(i, j));
					map[i][j]-=2;
				}
			}
		}
		//print();
	}

	private static void waterCopyMagic() 
	{
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(isVisited[i][j])//물이 증가한 칸이라면
				{
					int waterBasketCnt = 0;
					for(int dir = 0; dir < directionWaterCopy.length; dir++)
					{
						int nextX = i+directionWaterCopy[dir][0];
						int nextY = j+directionWaterCopy[dir][1];
						
						if(nextX<1 || nextX>N || nextY<1 || nextY>N || map[nextX][nextY]==0) continue;
						
						waterBasketCnt++;
					}
					map[i][j] += waterBasketCnt;
				}
			}
		}
		//print();
	}

	private static void rain() 
	{
		for(int i = 0; i < rainClouds.size(); i++)
		{
			Point cloud = rainClouds.get(i);
			
			map[cloud.posX][cloud.posY] += 1;//해당 칸에 비 내리기
			isVisited[cloud.posX][cloud.posY] = true;
		}
		rainClouds = new ArrayList<>();//구름 사라지기
		
		//print();
	}

	private static void moveClouds() 
	{
		for(int i = 0; i < s; i++)
		{
			for(int j = 0; j < rainClouds.size(); j++)
			{
				Point cloud = rainClouds.get(j);
				
				int nextX = cloud.posX+direction[d][0];
				int nextY = cloud.posY+direction[d][1];
				
				//맵을 넘어갔다면
				if(nextX<1 || nextX>N || nextY<1 || nextY>N)
				{
					nextX = connectMap(nextX);
					nextY = connectMap(nextY);
				}
				
				rainClouds.get(j).posX = nextX;
				rainClouds.get(j).posY = nextY;
			}
		}
	}
	
	static int connectMap(int position)
	{
		if(position == 0)//다음 좌표가 맵의 가장 왼쪽/위쪽 이라면
		{
			position = N;//이어진 왼쪽/위쪽 맵으로 이동
		}
		else if(position == N+1)//다음 좌표가 맵의 가장 오른쪽/아래쪽 이라면
		{
			position = 1;//이어진 오른쪽/아래쪽 맵으로 이동
		}
		
		return position;
	}

}
