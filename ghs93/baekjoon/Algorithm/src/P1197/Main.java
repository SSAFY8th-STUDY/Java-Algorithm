package P1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1197. 최소 스패닝 트리 - 골드 4
 * 
 * @author hoseong
 * @category MST
 */
public class Main {
	/**
	 * Prime 알고리즘 사용
	 * 인접리스트 사용
	 */
	static int V, E;
	static ArrayList<int[]>[] header; //각 정점에 인접한 정점들을 표현하기 위한 리스트 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		header = new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			header[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			//무향 그래프이기 때문에 from->to, to->from 둘 다 연결해 줘야한다
			//[연결된 정점, 가중치]
			header[from].add(new int[] {to, weight});
			header[to].add(new int[] {from, weight});
		}
		
		System.out.println(getPrime());
	}

	private static int getPrime() {
		//가중치를 기준으로 pq를 정렬한다.
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		boolean[] visited = new boolean[V+1]; //정점 방문처리
		
		//임의의 정점에서 시작
		q.addAll(header[1]);
		visited[1] = true;
		
		int result = 0;
		while(!q.isEmpty()) {
			int[] node = q.poll();
			
			if(!visited[node[0]]) {
				visited[node[0]] = true;
				result += node[1];
				
				if(header[node[0]] != null)
					q.addAll(header[node[0]]);
			}
		}
		
		return result;
	}
}
