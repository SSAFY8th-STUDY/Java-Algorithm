package P1753;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1753. 최단경로 - 골드 4
 * 
 * @author hoseong
 * @category 데이크스트라
 */
public class Main {
	static int V, E;
	static ArrayList<int[]>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		int start = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new int[] {to, weight});
		}
		
		int[] d = dijkstra(start);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1, size = d.length; i < size; i++) {
			sb.append(d[i] == Integer.MAX_VALUE ? "INF" : d[i]).append('\n');
		}
		
		System.out.println(sb);
	}

	private static int[] dijkstra(int start) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] d = new int[V+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		
		q.add(new int[] {start, 0});
		d[start] = 0;
		
		while(!q.isEmpty()) {
			int[] vt = q.poll();
			
			if(vt[1] > d[vt[0]]) continue;
			
			for(int[] v : adjList[vt[0]]) {
				int cost = d[vt[0]] + v[1];
				
				if(d[v[0]] > cost) {
					d[v[0]] = cost;
					q.add(new int[] {v[0], cost});
				}
			}
		}
		
		return d;
	}

}
