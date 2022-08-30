package baekjoon1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<Integer> adjList[];
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//정점의 개수 N(1 ≤ N ≤ 1,000)
		M = Integer.parseInt(st.nextToken());//간선의 개수 M(1 ≤ M ≤ 10,000)
		int V = Integer.parseInt(st.nextToken());//탐색을 시작할 정점의 번호 V
		
		adjList = new ArrayList[N+1];
		visited = new boolean[N+1];//방문 관리 배열
		for(int i = 0; i<adjList.length; i++)
		{
			adjList[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		

		for(int i = 0; i<adjList.length; i++)
		{
			Collections.sort(adjList[i]);			
		}

		dfs(V);
		System.out.println();
		bfs(V);
	}

	private static void dfs(int cur) {
		visited[cur] = true;
		System.out.print(cur+" ");
		
		for(int i : adjList[cur])
		{
			if(!visited[i])
			{
				dfs(i);
			}
		}
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[adjList.length];
		
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty())
		{
			int cur = queue.poll();
			System.out.print(cur + " ");
			
			for(int i : adjList[cur])
			{
				if(!visited[i])
				{
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		System.out.println();
	}
}
