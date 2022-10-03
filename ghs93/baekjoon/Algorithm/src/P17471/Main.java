package P17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 17471. 게리맨더링 - 골드 4
 * @author hoseong
 * @category 그래프, 조합론
 */
public class Main {
	static int N, total, min = Integer.MAX_VALUE;
	static int[] population;
	static boolean[] city;
	static ArrayList<Integer>[] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		population = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int p = Integer.parseInt(st.nextToken());
			population[i] = p;
			total += p;
		}
		
		map = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			map[i] = new ArrayList<>();
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				map[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 1; i <= N/2; i++) {
			city = new boolean[N+1];
			comb(1, 0, i);
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	
	public static void comb(int start, int cnt, int end) {
		if(cnt == end) {
			ArrayList<Integer> aGroup = new ArrayList<>();
			ArrayList<Integer> bGroup = new ArrayList<>();
			int aCnt = 0, bCnt = 0;
			
			for (int i = 1; i <= N; i++) {
				if(city[i]) {
					aGroup.add(i);
					aCnt += population[i];
					
				} else {
					bGroup.add(i);
					bCnt += population[i];
				}
			}
			
			if(checkConnect(aGroup) && checkConnect(bGroup)) {
				min = Math.min(min, Math.abs(aCnt-bCnt));
			}
			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			city[i] = true;
			comb(i+1, cnt+1, end);
			
			city[i] = false;
		}
	}
	
	public static boolean checkConnect(ArrayList<Integer> group) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.add(group.get(0));
		visited[group.get(0)] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(group.contains(cur))
				cnt++;
			
			for (Integer ct : map[cur]) {
				if(group.contains(ct) && !visited[ct]) {
					visited[ct] = true;
					q.add(ct);
				}
			}
		}
		
		return group.size() == cnt;
	}
}