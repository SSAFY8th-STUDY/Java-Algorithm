package P1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 10775. 여행 가자 - 골드 4
 * @author hoseong
 * @category 분리 집합
 */
public class Main {
	static int N, M, cities[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); //도시 수
		M = Integer.parseInt(br.readLine()); //계획에 속한 도시 수
		cities = new int[N+1];
		for (int i = 1; i <= N; i++) {
			cities[i] = i;
		}
		
		//도시 연결
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				int c = Integer.parseInt(st.nextToken());
				if(c != 0) {
					union(i, j);
				}
			}
		}
		
		//여행 계획
		st = new StringTokenizer(br.readLine());
		//첫 번째 도시
		int start = find(Integer.parseInt(st.nextToken()));
		
		//이후 방문해야 할 도시
		for (int i = 1; i < M; i++) {
			int to = find(Integer.parseInt(st.nextToken()));
			
			//도시가 같은 집합이 아니면 방문 불가
			if(start != to) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}

	static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return;
		
		cities[br] = ar;
	}
	
	static int find(int a) {
		if(cities[a] == a) return a;
		
		return cities[a] = find(cities[a]);
	}
}