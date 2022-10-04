package baekjoon3151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//이분 탐색 활용
public class Main3 {
	
	static int students[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//N명의 학생 (1 ≤ N ≤ 10000)
		students = new int[N];
		long answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			students[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(students);
		
		for(int i = 0; i< N-2; i++)
		{
			for(int j = i+1; j<N-1; j++)
			{
				int search = (students[i]+students[j])*-1;
				int lowerResult = lowerBound(j+1, N, search);
				
				if(lowerResult == N || students[lowerResult]!=search)
				{
					continue;
				}
				
				int upperResult = upperBound(j+1, N, search);
				answer += upperResult - lowerResult;
			}
		}
		
		System.out.println(answer);
		
	}
	
	public static int lowerBound(int left, int right, long target)
	{
		while(left<right)
		{
			int mid = (left + right) /2;
			if(target <= students[mid])
			{
				right = mid;
			}
			else
			{
				left = mid +1;
			}
		}
		return left;
	}
	
	public static int upperBound(int left, int right, long target)
	{
		while(left<right)
		{
			int mid = (left + right) /2;
			if(target < students[mid])
			{
				right = mid;
			}
			else
			{
				left = mid +1;
			}
		}
		return left;
	}

}
