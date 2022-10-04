package SWEA1952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {

	static int T,feeDay, feeMonth, fee3Month, feeYear, min;
	static int[] plan = new int[12];
	
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
			for (int j = 0; j < 12; j++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			//완탐 dfs
			//1년치 제외한 3가지 사용료 방법을 모두 따지는 방식 진행 각각 min 갱신
			//dfs 끝나고 나면 min에 가능한 가장 작은 값이 들어가 있음 => 마지막으로 1년치 비용과 비교 최소값
			//min <= 1년치 비용을 넣고 시작 (마지막 비교 X)
			min = feeYear;
			dfs(0,0);
			System.out.println("#" + i + " " + min);
		}

	}

	//재귀 호출방식 => 파라미터로 어떤 값을 
	private static void dfs(int cnt, int sum) {
		//기저 조건
		if( cnt >= 12)
		{
			min = Math.min(min, sum);
			return;
		}
		
		//계속 따져간다
		//이용하지 않는 월에 대한 처리
		if(plan[cnt]==0)
		{
			dfs(cnt+1, sum);
		}
		else
		{
			// cnt + 1번째 달에 대해
			// 1. 일 계산
			int cost = plan[cnt]*feeDay;
			if(sum + cost < min) dfs(cnt+1, sum+cost);				
			// 2. 월 계산
			if(sum + feeMonth < min) dfs(cnt+1, sum+feeMonth);
			// 3. 3개월 계산
			if(sum+fee3Month < min) dfs(cnt+3, sum+fee3Month);
		}
		
	}

}
