package P7511;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 7511. 소셜 네트워킹 어플리케이션 - 골드 5
 * @author hoseong
 * @category 분리 집합
 */
public class Main {
	static int N, K, M;
	static int[] friends;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder(); //StringBuilder 안쓰면 시간초과 남
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("Scenario ").append(tc).append(":\n");
			
			N = Integer.parseInt(br.readLine()); //유저 수
			K = Integer.parseInt(br.readLine()); //친구 관계 수
			make();
			
			//친구 만들기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			M = Integer.parseInt(br.readLine()); //친구 검사 수
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = find(Integer.parseInt(st.nextToken()));
				int b = find(Integer.parseInt(st.nextToken()));
				
				sb.append(a==b ? 1 : 0).append('\n');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void make() {
		friends = new int[N];
		for (int i = 0; i < N; i++) {
			friends[i] = i;
		}
	}
	
	static int find(int a) {
		if(friends[a] == a) return a;
		
		return friends[a] = find(friends[a]);
	}
	
	static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return;
		
		friends[br] = ar;
	}
}