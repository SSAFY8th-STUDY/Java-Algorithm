package P5557;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 5557. 1학년 - 골드 5
 * @author hoseong
 * @category DP
 */
public class Main {
	static int N, last;
	static ArrayList<Integer> arr;
	static long dp[][]; //메모이제이션

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		dp = new long[N-1][21];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N-1; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			arr.add(n);
			Arrays.fill(dp[i], -1);
		}
		
		last = Integer.parseInt(st.nextToken());
		cal(0, 0);
		System.out.println(dp[0][0]);
	}

	static long cal(int sum, int idx) {
		if(idx == N-1) { //끝까지 계산한 경우
			return sum == last ? 1 : 0; //값이 맞으면 경우의 수 +1
		}
		
		if(dp[idx][sum] != -1) //이미 했던 경우의 수 일 경우 저장된 값 리턴
			return dp[idx][sum];
		
		int s = sum + arr.get(idx); //더할 경우
		int d = sum - arr.get(idx); //뺄 경우
		long cnt = 0; //경우의 수
		
		if(s < 21) {
			cnt += cal(s, idx + 1);
		}
		
		if(idx != 0 && d > -1) {
			cnt += cal(d, idx + 1);
		}
		
		return dp[idx][sum] = cnt;
	}
}
