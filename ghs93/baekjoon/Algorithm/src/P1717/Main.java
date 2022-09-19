package P1717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1717. 집합의 표현 - 골드 4
 * @author hoseong
 * @category 분리 집합
 */
public class Main {
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //집합 수
		M = Integer.parseInt(st.nextToken()); //연산 수
		parents = new int[N+1];
		
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken()); //0-union, 1-find
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(c == 0) { //union
				union(a, b);
				
			} else { //find
				if(find(a) == find(b))
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return;
		
		parents[br] = ar;
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
}