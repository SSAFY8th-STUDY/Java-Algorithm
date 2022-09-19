package P25187;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 25187. 고인물이 싫어요 - 골드 4
 * @author hoseong
 * @category 분리 집합, 그래프 탐색
 */
public class Main {
	static int N, M, Q, tank[], parents[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //물탱크 수
		M = Integer.parseInt(st.nextToken()); //파이프 수
		Q = Integer.parseInt(st.nextToken()); //방문 횟수
		tank = new int[N+1]; //물탱크 상태
		parents = new int[N+1]; //물탱크 집합
		
		//물탱크 및 물탱크 집합 초기화
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int mul = Integer.parseInt(st.nextToken());
			tank[i] = mul == 0 ? -1 : mul;
			parents[i] = i;
		}
		
		//물탱크 연결
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		//방문한 물탱크
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int m = tank[find(Integer.parseInt(br.readLine()))];
			
			sb.append(m > 0 ? 1 : 0).append('\n');
		}
		
		System.out.println(sb);
	}

	static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return;
		
		//집합의 루트 위치의 탱크에서 물 상태를 본다
		int at = tank[ar];
		int bt = tank[br];
		int mul = at + bt; //청정수와 고인물 혼합
		
		tank[ar] = mul; //루트가 될 탱크에 혼합 된 물을 넣는다
		parents[br] = ar; //탱크 연결
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
}
