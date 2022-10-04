package baekjoon20160;

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
		int weight;
		
		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
		
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", cost=" + weight + "]";
		}
	}
	

	static ArrayList<Node>[] graph;
	static Node[] yogurtTime;
	static int myDistance[], yogurtDistance[], route[];
	static int INF = Integer.MAX_VALUE, minNode = INF;
	static int vertexCnt, edgeCnt;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		vertexCnt = Integer.parseInt(st.nextToken());//정점의 개수 V(1 ≤ V ≤ 10,000)
		edgeCnt = Integer.parseInt(st.nextToken());//도로의 개수 E(0 ≤ E ≤ 100,000)
		
		graph = new ArrayList[vertexCnt+1];
		for(int i = 1; i<= vertexCnt; i++)
		{
			graph[i] = new ArrayList<>();
		}
		
		//그래프 정보 입력 받기
		for(int i = 0; i<edgeCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, weight));
			graph[to].add(new Node(from, weight));
		}
		
		yogurtTime = new Node[11]; //요거트 배달 시간
		yogurtDistance = new int[11]; //요거트 최소 거리
		route = new int[11];
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		route[0] = start;
		int next = -1;
		yogurtTime[0] = new Node(start, 0);
		for(int i = 1; i<yogurtDistance.length; i++)
		{
			next = Integer.parseInt(st.nextToken());
			route[i] = next;
			
			yogurtDistance = dijkstra(start);
			yogurtTime[i] = new Node(next, yogurtDistance[next] + yogurtTime[i-1].weight);
			start = next;
			
			//System.out.println(Arrays.toString(yogurtDistance));
			//System.out.println(yogurtTime[i].toString());
		}
		
		int myStart = Integer.parseInt(br.readLine());
		myDistance = dijkstra(myStart);
		
		for(int i = 0; i<yogurtTime.length; i++)
		{
			if(myDistance[route[i]]<yogurtDistance[i])
			{
				minNode = Math.min(minNode, route[i]);
			}
		}
		
		System.out.println(minNode);
	}

	private static int[] dijkstra(int start) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		int distance[] = new int[vertexCnt+1];
		Arrays.fill(distance, INF);
		
		distance[0] = 0;
		
		while(!pq.isEmpty())
		{
			Node node = pq.poll();
			
			int vertex = node.vertex;
			int weight = node.weight;
			
			if(distance[vertex] < weight) continue;
			
			for(int i = 0; i<graph[vertex].size(); i++)
			{
				Node nextNode = graph[vertex].get(i);
				
				int nextVertex = nextNode.vertex;
				int nextWeight = nextNode.weight + weight;
				
				if(distance[nextVertex] < nextWeight) continue;
				
				distance[nextVertex] = nextWeight;
				pq.add(new Node(nextVertex, nextWeight));
			}
		}
		
		return distance;
	}

}
