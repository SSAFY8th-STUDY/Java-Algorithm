package baekjoon1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>
	{
		int vertex;
		int cost;
		
		public Node(int vertex, int cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", cost=" + cost + "]";
		}
	}
	
	static ArrayList<Node>[] graph;
	static int distance[]; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int vertexCnt = Integer.parseInt(st.nextToken());//정점의 개수 (1 ≤ V ≤ 20,000)
		int edgeCnt = Integer.parseInt(st.nextToken());//간선의 개수 (1 ≤ E ≤ 300,000)
		int start = Integer.parseInt(br.readLine());//시작 정점의 번호 K(1 ≤ K ≤ V)
		
		graph = new ArrayList[vertexCnt+1];
		for(int i = 1; i<=vertexCnt; i++)
		{
			graph[i] = new ArrayList<>();
		}
		
		distance = new int[vertexCnt+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		for(int i = 0; i<edgeCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to,cost));
		}
		
		dijkstra(start);
		
		for(int i = 1; i<= vertexCnt; i++)
		{
			if(distance[i]==Integer.MAX_VALUE)
			{
				System.out.println("INF");
			}
			else
			{
				System.out.println(distance[i]);
			}
		}
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty())
		{
			Node node = pq.poll();
			
			int vertex = node.vertex;
			int cost = node.cost;
			
			if(distance[vertex] < cost) continue;
			
			for(int i = 0; i<graph[vertex].size(); i++)
			{
				Node nextNode = graph[vertex].get(i);
				
				int nextVertex = nextNode.vertex;
				int nextCost = nextNode.cost + cost;
				
				if(distance[nextVertex] < nextCost) continue;
				
				distance[nextVertex] = nextCost;
				pq.add(new Node(nextVertex,nextCost));
			}
		}
		
	}

}
