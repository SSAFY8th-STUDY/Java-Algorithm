package P2887;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 2887. 행성 터널- 골드 4
 * 
 * @author hoseong
 * @category MST
 */
public class Main {
	static class Planet {
		int x, y, z, idx;

		public Planet(int x, int y, int z, int idx) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
		}
	}
	
	static class Tunnel implements Comparable<Tunnel>{
		int from, to, weight;
	
		public Tunnel(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Tunnel o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int N;
	static Planet[] planet;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		planet = new Planet[N];
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			planet[i] = new Planet(x, y, z, i);
			parents[i] = i;
		}
		
		PriorityQueue<Tunnel> pq = new PriorityQueue<>();
		
		//x기준 정렬 후 pq에 삽입
		Arrays.sort(planet, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return o1.x - o2.x;
			}
		});
		for (int i = 0; i < N-1; i++) {
			pq.add(new Tunnel(planet[i].idx, planet[i+1].idx, planet[i+1].x - planet[i].x));
		}
		
		//y기준 정렬 후 pq에 삽입
		Arrays.sort(planet, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return o1.y - o2.y;
			}
		});
		for (int i = 0; i < N-1; i++) {
			pq.add(new Tunnel(planet[i].idx, planet[i+1].idx, planet[i+1].y - planet[i].y));
		}
		
		//z기준 정렬 후 pq에 삽입
		Arrays.sort(planet, new Comparator<Planet>() {
			@Override
			public int compare(Planet o1, Planet o2) {
				return o1.z - o2.z;
			}
		});
		for (int i = 0; i < N-1; i++) {
			pq.add(new Tunnel(planet[i].idx, planet[i+1].idx, planet[i+1].z - planet[i].z));
		}
		
		//연결된 간선의 수가 N-1개일 때 까지 pq에서 꺼내며 union한다.
		int result = 0, cnt = 0;
		while(!pq.isEmpty()) {
			Tunnel tn = pq.poll();
			
			if(union(tn.to, tn.from)) {
				cnt++;
				result += tn.weight;
			}
			
			if(cnt == N-1) break;
		}
		
		System.out.println(result);
	}
	
	static boolean union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return false;
		
		parents[br] = ar;
		return true;
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
}