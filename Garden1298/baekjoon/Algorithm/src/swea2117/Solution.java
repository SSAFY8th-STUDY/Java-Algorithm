package swea2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution
{
	static class Point
	{
    	int posX;
    	int posY;
    	int distance;

		public Point(int r, int c) {
			super();
			this.posX = r;
			this.posY = c;
		}
		
		public void updateDistance(int r, int c) {
			this.distance = Math.abs(this.posX - r) + Math.abs(this.posY - c);
		}

		@Override
		public String toString() {
			return "[r=" + posX + ", c=" + posY + ", d=" + distance + "]";
		}
    }
	
    static int N, M;
    static int [][] map;
    static int [][] direction = {{-1, 0},{1, 0},{0,-1},{0,1}};
    
    static int totalHomeCnt = 0;// 전체 집의 개수
    static int maxHomeCnt = 0;// 최대 집의 개수
    static List<Point> homes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= T; tc++)
	    {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	
        	N = Integer.parseInt(st.nextToken());//도시의 크기 N (5 ≤ N ≤ 20)
        	M = Integer.parseInt(st.nextToken());//하나의 집이 지불할 수 있는 비용 M (1 ≤ M ≤ 10)
        	map = new int[N][N];//도시의 정보
        	maxHomeCnt = 0;
        	totalHomeCnt = 0;
        	
        	//맵 정보 입력 받기
        	for(int i=0; i<N; i++) 
        	{
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) 
        		{
        			map[i][j] = Integer.parseInt(st.nextToken());
        			
        			if(map[i][j]==1)//1이면 집이므로 값을 저장한다.
        			{
        				totalHomeCnt++;
        				homes.add(new Point(i, j));
        			}
        		}
        	}
        	
        	for(int i=0; i<N; i++) 
        	{
        		for(int j=0; j<N; j++) 
        		{
        			bfs(i, j);
        		}
        	}
        	
        	System.out.println("#"+tc+" "+maxHomeCnt);
	    }
	    

	}

	private static void bfs(int startA, int startB) {
		
		Queue<Point> q = new LinkedList<>();
		boolean isVisited[][] = new boolean[N][N];
		
		q.offer(new Point(startA, startB));
		isVisited[startA][startB] = true;
		
		int discoveredHomeCnt = map[startA][startB];//춟발점이 이미 집인 경우 체크, 집이 있다면 1 없다면 0이 들어간다.
		int depth = 1;
		
		while(!q.isEmpty())
		{
			int benefit = discoveredHomeCnt * M;//홈방범 서비스를 제공받는 집들은 각각 M의 비용을 지불할 수 있다
			int cost = depth * depth + (depth-1) * (depth-1);//운영 비용 = K * K + (K - 1) * (K - 1)
			
			//이익이 비용보다 더 많다면
			if(benefit >= cost)
			{
				//집의 개수가 더 많은 걸 찾는다.
				maxHomeCnt = Math.max(maxHomeCnt, discoveredHomeCnt);
			}
			
			//집을 다 찾았다면 더 이상 탐색할 집이 없으므로 반복문 나가기
			if(discoveredHomeCnt == totalHomeCnt) return;
			
			int size = q.size();
			while(size-- > 0)
			{
				Point current = q.poll();
				
				for(int i = 0; i<direction.length; i++)
				{
					int nextA = current.posX+direction[i][0];
					int nextB = current.posY+direction[i][1];
					
					if(nextA<0 || nextA>=N || nextB<0 || nextB >=N || isVisited[nextA][nextB]) continue;
					
					isVisited[nextA][nextB] = true;
					q.add(new Point(nextA,nextB));
					discoveredHomeCnt+=map[nextA][nextB];//집이 아니라면 0 집이라면 1을 더한다.
				}
			}
			//한 depth가 종료되었으므로 depth++
			depth++;
		}
	}

}
