package SWEA1952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4 {

	static int T,feeDay, feeMonth, fee3Month, feeYear, min;
	static int[] plan = new int[13];// 0 dummy
	static int[] dp = new int[13];// 0 dummy
	
	//dp는 메모이제이션의 한 방법으로 점화식과 같은 신뢰할 수 있는 (검증된) 식으로 단계별로 다음 값을 계산해 가는 방식
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i<=T; i++)
		{
			//입력 => 변수 및 자료 구조
			StringTokenizer st = new StringTokenizer(br.readLine());
			feeDay = Integer.parseInt(st.nextToken());
			feeMonth = Integer.parseInt(st.nextToken());
			fee3Month = Integer.parseInt(st.nextToken());
			feeYear = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 12; j++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			min = feeYear;

			//dp
			dp[0] = 0;
			dp[1] = Math.min(plan[1] * feeDay, feeMonth);//일 계산과 월 계산 중 비용이 적은 것 선택
			dp[2] = Math.min(dp[1] + plan[1]*feeDay, feeMonth);//누적!
			
			//dp[3] 
			for (int j = 3; j <= 12; j++) {
				dp[i] = min(dp[i-1]+plan[i]*feeDay,
	                        dp[i-1] + feeMonth,
	                        dp[i-3]+fee3Month);
			}
			
			//dp[12] <= 1일, 1달, 3달에 대한 최소값 계산
			//dp[12], min 중 최소값 선택
			min = Math.min(min, dp[12]);
			System.out.println("#" + i + " " + min);
		}

	}
	
	static int min(int a, int b, int c)
	{
		return Math.min(a, Math.min(b, c));
	}

}
