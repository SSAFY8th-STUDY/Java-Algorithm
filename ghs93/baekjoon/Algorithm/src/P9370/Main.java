package P9370;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 9370. 미확인 도착지 - 골드 2
 * 
 * @author hoseong
 * @category 데이크스트라
 */
public class Main {
	static class Vertex implements Comparable<Vertex>{
		int to;
		long  weight;
		
		public Vertex(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static ArrayList<Vertex>[] adjList;
	static int N, M, goals[], G, H;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //교차로 개수
			M = Integer.parseInt(st.nextToken()); //도로 개수
			int t = Integer.parseInt(st.nextToken()); //목적지 후보 개수
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); //예술가들 출발지
			G = Integer.parseInt(st.nextToken()); //지나간 흔적 교차로 g
			H = Integer.parseInt(st.nextToken()); //지나간 흔적 교차로 h
			
			//도로 초기화
			adjList = new ArrayList[N+1];
			for (int i = 0; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				// 각 간선의 가중치를 2배로 해서 짝수로 만들고 g, h를 지나는 부분만 -1을해서 홀수로 만든다
				if((from == G || from == H) && (to == G || to == H)) {
					adjList[from].add(new Vertex(to, weight * 2 - 1));
					adjList[to].add(new Vertex(from, weight * 2 - 1));
					
				} else {
					adjList[from].add(new Vertex(to, weight * 2));
					adjList[to].add(new Vertex(from, weight * 2));
				}
			}
			
			//목적지 리스트 초기화
			ArrayList<Integer> goal = new ArrayList<>();
			goals = new int[t];
			for (int i = 0; i < t; i++) {
				int gl = Integer.parseInt(br.readLine());
				goals[i] = gl;
			}
			
			//다익스트라 알고리즘을 이용하여 최단경로를 구하고 각 목적지에 가는 경로가 홀수면 
			//g, h 구간을 지난 최단거리 이므로 목적지 후보에 추가한다. 
			long[] dist = dijkstra(s);
			for (int i = 0; i < t; i++) {
				if(dist[goals[i]] != Long.MAX_VALUE && dist[goals[i]] % 2 == 1)
					goal.add(goals[i]);
			}
			
			Collections.sort(goal);
			for (Integer gl : goal) {
				sb.append(gl).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	private static long[] dijkstra(int start) {
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		long[] D = new long[N+1];
		Arrays.fill(D, Long.MAX_VALUE);
		
		q.add(new Vertex(start, 0));
		D[start] = 0;
		
		while(!q.isEmpty()) {
			Vertex vt = q.poll();
			
			if(vt.weight > D[vt.to]) continue;
			
			for (Vertex v : adjList[vt.to]) {
				long cost = D[vt.to] + v.weight;
				
				if(D[v.to] > cost) {
					D[v.to] = cost;
					q.add(new Vertex(v.to, cost));
				}
			}
		}
		
		return D;
	}
}
