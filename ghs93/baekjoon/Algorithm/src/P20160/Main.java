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
		
		long[] YD = new long[10]; //야쿠르트 아줌마 이동거리
		int[] MD = new int[10]; //나의 이동거리
		Arrays.fill(YD, Integer.MAX_VALUE);
		Arrays.fill(MD, Integer.MAX_VALUE);
		
		adjList = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//무향그래프 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new int[] {to, weight});
			adjList[to].add(new int[] {from, weight});
		}
		
		//방문 순서
		st = new StringTokenizer(br.readLine());
		int[] visitList = new int[10];
		for (int i = 0; i < 10; i++) {
			visitList[i] = Integer.parseInt(st.nextToken());
		}
		
		//내 출발 지점
		int mStart = Integer.parseInt(br.readLine());
		
		//야쿠르트 아줌마의 시작점을 기준으로 갈 수 있는 모든 곳의 최단거리 중
		//다음 방문할 곳의 거리를 YD에 저장
		//이후 다음 방문할 곳을 기준으로 갈 수 있는 모든 곳의 최단거리를 구하며
		//YD완성 시키기
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
		
		//나의 출발점에서 갈 수 있는 최단거리
		MD = dijkstra(mStart);
		
		//내가 야쿠르트 아줌마보다 빨리 도착하는 지점의 정점번호
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			int cur = visitList[i];
			
			if(YD[i] != Integer.MAX_VALUE && MD[cur] <= YD[i])
				min = Math.min(min, cur);
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	//다익스트라를 통한 최단경로
	private static int[] dijkstra(int start) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] D = new int[V+1]; //임의의 정점을 기준으로한 모든 정점들과의 최단거리
		Arrays.fill(D, Integer.MAX_VALUE);
		
		//시작 정점의 최단거리는 0으로 초기화
		q.add(new int[] {start, 0});
		D[start] = 0;
		
		while(!q.isEmpty()) {
			int[] vt = q.poll();
			
			//지금까지의 최단거리 보다 vt[0]로 가는 거리가 클 경우 반복문을 넘어간다.
			if(vt[1] > D[vt[0]]) continue;
			
			//해당 정점과 연결된 모든 정점
			for(int[] v : adjList[vt[0]]) {
				//해당 정점까지 누적된 거리와 앞으로 갈 거리의 합
				int cost = D[vt[0]] + v[1];
				
				//위에서 구한 cost가 지금까지 누적된 거리보다 작을 경우 최단거리 재설정
				if(D[v[0]] > cost) {
					D[v[0]] = cost;
					q.add(new int[] {v[0], cost});
				}
			}
		}
		
		return D;
	}
}
