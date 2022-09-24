package P20160;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 20160. 야쿠르트 아줌마 야쿠르트 주세요 - 골드 3
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
		
		long[] YD = new long[10];
		int[] MD = new int[10];
		Arrays.fill(YD, Integer.MAX_VALUE);
		Arrays.fill(MD, Integer.MAX_VALUE);
		
		adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new int[] {to, weight});
			adjList[to].add(new int[] {from, weight});
		}
		
		st = new StringTokenizer(br.readLine());
		int[] visitList = new int[10];
		for (int i = 0; i < 10; i++) {
			visitList[i] = Integer.parseInt(st.nextToken());
		}
		
		int mStart = Integer.parseInt(br.readLine());
		
		YD[0] = 0;
		int yStart = visitList[0], yIdx = 0;
		for (int i = 1; i < 10; i++) {
			int yNext = visitList[i];
			int weight = dijkstra(yStart)[yNext];
			
			if(weight == Integer.MAX_VALUE) continue;
			
			YD[i] = weight + YD[yIdx];
			yStart = yNext;
			yIdx = i;
		}
		
		MD = dijkstra(mStart);
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			int cur = visitList[i];
			
			if(YD[i] != Integer.MAX_VALUE && MD[cur] <= YD[i])
				min = Math.min(min, cur);
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static int[] dijkstra(int start) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] D = new int[V+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		
		q.add(new int[] {start, 0});
		D[start] = 0;
		
		while(!q.isEmpty()) {
			int[] vt = q.poll();
			
			if(vt[1] > D[vt[0]]) continue;
			
			for(int[] v : adjList[vt[0]]) {
				int cost = D[vt[0]] + v[1];
				
				if(D[v[0]] > cost) {
					D[v[0]] = cost;
					q.add(new int[] {v[0], cost});
				}
			}
		}
		
		return D;
	}
}
