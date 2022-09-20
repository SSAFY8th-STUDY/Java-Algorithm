package P17352;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17352. 여러분의 다리가 되어 드리겠습니다! - 골드 5
 * @author hoseong
 * @category 분리 집합
 */
public class Main {
	static int N, parents[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //섬 수
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		//연결된 섬
		for (int i = 0; i < N-2; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		//연결되지 않은 두 다리 찾기 - 집합의 루트
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if(find(i) == i)
				sb.append(i).append(' ');
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
		if(parents[a] == a)
			return a;
		
		return parents[a] = find(parents[a]);
	}
}
