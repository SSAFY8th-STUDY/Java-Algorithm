package P1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1647. 도시 분할 계획 - 골드 4
 * 
 * @author hoseong
 * @category MST
 */
public class Main {
	/**
	 * Prime 알고리즘 사용
	 * 인접 리스트 사용
	 * 
	 * 최소신장 트리를 구한 다음
	 * 그 트리에서 가중치가 가장 큰 값을 끊어서 2개의 마을을 만든다.
	 */
	
	//노드를 표현하기 위한 클래스
	static class Node implements Comparable<Node>{
		int v, weight;
		
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	static int N, M;
	static ArrayList<Node>[] header; //각 정점에 인접한 정점들을 표현하기 위한 리스트 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		header = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			header[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			//무향 그래프이기 때문에 from->to, to->from 둘 다 연결해 줘야한다
			header[from].add(new Node(to, weight));
			header[to].add(new Node(from, weight));
		}
		
		System.out.println(getPrime());
	}
	
	//Prime 알고리즘 사용
	private static int getPrime() {
		ArrayList<Node> start = header[1]; //임의의 시작 정점
		PriorityQueue<Node> q = new PriorityQueue<>(); //간선의 가중치를 정렬하기 위한 힙
		boolean[] visited = new boolean[N+1]; //정점 방문여부
		
		q.addAll(start); //해당 정점에서 방문할 수 있는 모든 정점을 가중치를 기준으로 정렬
		visited[1] = true; //해당 정점 방문처리
		
		int result = 0, max = Integer.MIN_VALUE;
		while(!q.isEmpty()) { //가중치가 적은 곳 순서대로 방문
			Node n = q.poll();
			
			if(!visited[n.v]) { //방문한 적이 없는 정점일 경우
				visited[n.v] = true; //방문처리
				
				result += n.weight; //가중치 누적
				max = Math.max(max, n.weight); //누적된 가중치 중 가장 큰 값
				
				if(header[n.v] != null) //방문한 정점에서 이어서 방문 가능한 곳이 있으면 pq에 추가
					q.addAll(header[n.v]);
			}
		}
		
		//총 누적된 가중치에서 가장 큰 가중치를 빼면 두 마을의 유지비의 최소값
		return result - max;
	}
}
