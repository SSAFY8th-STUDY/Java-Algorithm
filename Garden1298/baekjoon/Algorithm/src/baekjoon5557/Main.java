package baekjoon5557;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

	static int N;// 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100)
	static int nums[];//0 이상 9 이하의 정수 N개
	static boolean isVisited[];
	static long memo[][];
	static int answer;
	static long count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		isVisited = new boolean [N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			nums[i] = Integer.parseInt(st.nextToken());
		}//입력받기

		answer = nums[N-1];
		
		memo = new long[101][21];
		for(int i = 0; i<101; i++)
		{
			Arrays.fill(memo[i], -1);
		}
		
		comb(1, nums[0]);//첫째수는 무조건 더해야하니까 다음 수 부터 계산한다
		
		System.out.println(count);
	}

	static long comb(int index, int sum)
	{
		if(sum<0||sum>20)//왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다
		{
			return 0;
		}

		if(index == N - 1)//마지막 인덱스일때
		{			
			if(sum == answer)//합이 마지막값과 같을때
			{
				memo[index][sum] = 1;
				count++;
			}
			else
			{
				memo[index][sum] = 0;
			}
			return memo[index][sum];
		}
		
		if(memo[index][sum] != -1)
		{
			count+=memo[index][sum];
			return memo[index][sum];
		}
		
		long memoization = 0;
		
		isVisited[index] = true;
		memoization += comb(index+1,sum+nums[index]);
		isVisited[index] = false;
		memoization += comb(index+1,sum-nums[index]);
		
		
		return memo[index][sum] = memoization;
	}
}
