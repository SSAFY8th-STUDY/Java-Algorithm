package baekjoon1647;

import java.awt.GradientPaint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>
	{
		int from;
		int to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
	}

	static int parent[];
	static ArrayList<Edge> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int houseCnt = Integer.parseInt(st.nextToken());//N은 2이상 100,000이하인 정수
		int roadCnt = Integer.parseInt(st.nextToken());//M은 1이상 1,000,000이하인 정수
		
		int totalValue = 0;
		int maxEdge = 0;
		
		parent = new int[houseCnt+1];
		for(int i = 1;i<=houseCnt; i++)
		{
			parent[i] = i;
		}
		
		graph = new ArrayList<>();
		for(int i = 0; i<roadCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.add(new Edge(from, to, weight));
		}

		Collections.sort(graph);
		
		for(int i = 0; i < graph.size(); i++)
		{
			Edge edge = graph.get(i);
			
			if(find(edge.from)!=find(edge.to))
			{
				totalValue+=edge.weight;
				union(edge.from, edge.to);
				
				maxEdge = Math.max(maxEdge, edge.weight);
			}
		}
		
		System.out.println(totalValue-maxEdge);
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
