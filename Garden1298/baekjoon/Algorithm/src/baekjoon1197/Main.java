package baekjoon1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>
	{
		int from;
		int to;
		int cost;
		
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
		}
	}
	
	static int parent[];
	static ArrayList<Edge> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException 
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int vertexCnt = Integer.parseInt(st.nextToken());
		int edgeCnt = Integer.parseInt(st.nextToken());
		int graphMinCost = 0;

		parent = new int[vertexCnt+1];
		for(int i = 1;i<=vertexCnt; i++)
		{
			parent[i] = i;
		}
		
		for(int i = 0; i<edgeCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.add(new Edge(from,to,cost));
		}
		
		Collections.sort(graph);
		
		for(int i = 0; i<graph.size(); i++)
		{
			Edge edge = graph.get(i);
			
			if(find(edge.from)!=find(edge.to))
			{
				graphMinCost += edge.cost;
				union(edge.from,edge.to);
			}
		}
		
		System.out.println(graphMinCost);
	}
	
	public static int find(int node)
	{
		if(node == parent[node])
		{
			return node;
		}
		
		return parent[node] = find(parent[node]);
	}
	
	public static void union(int from, int to)
	{
		from = find(from);
		to = find(to);
		
		if(from<to)
		{
			parent[to] = parent[from];
		}
		else
		{
			parent[from] = parent[to];
		}
		
	}
}
