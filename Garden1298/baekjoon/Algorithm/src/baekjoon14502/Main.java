package baekjoon14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point
	{
		int posX;
		int posY;
		
		public Point(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
		
		@Override
		public String toString() {
			return "Point [posX=" + posX + ", posY=" + posY + "]";
		}
	}

	static int N,M;//맵의 세로,가로 크기
	static int map[][];
	static int direction[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	
	static Queue<Point> virus = new LinkedList<>();
	static List<Point> empty = new LinkedList<>();
	static Point newWall[] = new Point[3];
	static boolean isVisited[];
	static int answer = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++)
			{
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if(input == 2)//바이러스라면
				{
					virus.add(new Point(i, j));
				}
				if(input == 0)//빈공간이라면
				{
					empty.add(new Point(i, j));
				}
			}
		}

		isVisited = new boolean[empty.size()];
		comb(0,0);
		
		System.out.println(answer);
	}
	
	static void bfs(Point wallPos[])
	{
		boolean isVisitedBfs[][] = new boolean[N][M];
		Queue<Point> newVirus = new LinkedList<>(virus);
		int newMap[][] = deepCopy(map);
		buildWall(wallPos, newMap);
		
		while(!newVirus.isEmpty())
		{
			Point currentVirus = newVirus.poll();
			
			for(int i = 0; i<direction.length; i++)
			{
				int nextX = currentVirus.posX+direction[i][0];
				int nextY = currentVirus.posY+direction[i][1];
				
				if(nextX<0 || nextX>=N || nextY<0 || nextY>=M) continue;//범위를 벗어났다면 컨티뉴
				if(isVisitedBfs[nextX][nextY] || newMap[nextX][nextY] == 1 || newMap[nextX][nextY]==2) continue;//방문했거나 벽이거나 바이러스가 있다면 컨티뉴
				
				isVisitedBfs[nextX][nextY] = true;
				newMap[nextX][nextY] = 2;
				newVirus.offer(new Point(nextX, nextY));
			}
		}
		
		countSafeArea(newMap);
	}

	static void comb(int cnt, int start)
	{
		if(cnt == 3)
		{
			bfs(newWall);
			return;
		}
		
		for(int i = start; i<empty.size();i++)
		{
			if(!isVisited[i])
			{
				isVisited[i] = true;
				newWall[cnt] = empty.get(i);
				comb(cnt+1, i+1);
				isVisited[i] = false;
				newWall[cnt] = null;
			}
		}
	}
	
	static void buildWall(Point[] wallPos, int map[][])
	{
		for(Point p : wallPos)
		{
			map[p.posX][p.posY] = 1;
		}
	}
	
	static void countSafeArea(int map[][])
	{
		int count = 0;
		
		for(int i = 0; i<N; i++)
		{
			for(int j = 0; j<M; j++)
			{
				if(map[i][j]==0)
				{
					count++;
				}
			}
		}
		//print(map);
		answer = Math.max(answer, count);
	}
	
	static int[][] deepCopy(int map[][])
	{
		int newMap[][] = new int[N][M];
		
		for(int i = 0; i<N; i++)
		{
			for(int j = 0; j<M; j++)
			{
				newMap[i][j] = map[i][j];
			}
		}
		
		return newMap;
	}
	
	static void print(int map[][])
	{
		System.out.println("<========맵출력========>");
		for(int i = 0; i<N; i++)
		{
			for(int j = 0; j<M; j++)
			{
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
