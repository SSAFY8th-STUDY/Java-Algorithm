package P1806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 1806. 부분합 - 골드 4
 * @author hoseong
 * @category 누적합, 투포인터
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //수열
		int S = Integer.parseInt(st.nextToken()); //부분합

		int[] arr = new int[N + 1];
		int sum = 0, sPosition = 0; //sPosition: 누적합 투포인터 끝 부분
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			sum += Integer.parseInt(st.nextToken());
			arr[i] = sum; //누적합

			if (sum < S) //누적합이 처음으로 부분합을 넘을 때까지
				sPosition = i + 1;
		}

		int dist = Integer.MAX_VALUE; //최소 거리
		
		if(sPosition > N) { //누적합이 부분합보다 작을 경우
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i <= N; i++) {
			if (arr[sPosition] - arr[i] >= S) { //누적합이 부분합보다 큰 구간
				int min = sPosition - i; //부분합 갯수

				if (dist > min)
					dist = min;

			} else {
				if (sPosition + 1 <= N) { //누적합 투포인터 끝부분이 끝까지 갈 때까지
					sPosition++;
					i--;
				}
			}
		}

		System.out.println(dist);
	}
}