package P1854;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1854. k번째 최단경로 찾기 - 플래티넘 4
 * @author hoseong
 * @category 데이크스트라
 */
public class Main {
	static class Vertex implements Comparable<Vertex>{
		int to, weight;

		public Vertex(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M, K;
	static ArrayList<Vertex>[] cities;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시 수
		M = Integer.parseInt(st.nextToken()); // 도로 수
		K = Integer.parseInt(st.nextToken()); // k번째 경로
		
		cities = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			cities[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());;
			int to = Integer.parseInt(st.nextToken());;
			int weight = Integer.parseInt(st.nextToken());;
			
			cities[from].add(new Vertex(to, weight));
		}
		
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer>[] D = dijkstra();
		for (int i=1; i<=N; i++) {
			PriorityQueue<Integer> d = D[i];
			
			if(d.size() < K)
				sb.append(-1).append('\n');
			else
				sb.append(d.poll()).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static PriorityQueue<Integer>[] dijkstra() {
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		PriorityQueue<Integer>[] kList = new PriorityQueue[N+1];
		
		for (int i = 0; i <= N; i++) {
			kList[i] = new PriorityQueue<>((a, b) -> b-a);
		}
		
		q.add(new Vertex(1, 0));
		kList[1].add(0);
		while(!q.isEmpty()) {
			Vertex vt = q.poll();
			
			for(Vertex v : cities[vt.to]) {
				int cost = vt.weight + v.weight;
				
				if(kList[v.to].size() < K) {
					kList[v.to].add(cost);
					q.add(new Vertex(v.to, cost));
					
				} else if(kList[v.to].peek() > cost){
					kList[v.to].poll();
					kList[v.to].add(cost);
					q.add(new Vertex(v.to, cost));
				}
			}
		}
		
		return kList;
	}
}