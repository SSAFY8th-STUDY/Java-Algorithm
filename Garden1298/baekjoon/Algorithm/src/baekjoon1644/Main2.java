package baekjoon1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {

	static List<Integer> primeNum;
	//에라토스테네스의 체 활용
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N==1)
		{
			System.out.println("0");
			return;
		}
		
//		primeNum = new ArrayList<Integer>();
//		for(int i = 0; i <= N; i++)
//		{
//			primeFunc(i);
//		}
		
		int left = 0, right = 0;
		int sum = primeNum.get(0);
		int answer = 0;
		
		while(left<=right&&right<primeNum.size())
		{
			if(left!=right)
			{
				sum = sum + primeNum.get(right);				
			}
			
			if(sum == N)
			{
				answer++;
				if(left == primeNum.size()-1) break;
				
				left += 1;
				right = left;
				sum = primeNum.get(left);	
			}
			else if(sum > N)
			{
				if(left == primeNum.size()-1) break;
				
				left += 1;
				right = left;
				sum = primeNum.get(left);					
				
			}
			else
			{
				right += 1;
			}
		}
		
		System.out.println(answer);

	}
	
//	private static void primeFunc(int index) {
//		
//		boolean isPrime[] = new boolean[index+1];
//		Arrays.fill(isPrime, true);
//		
//		isPrime[0] = isPrime[1] = false;
//		
//		for(int i = 2)
//		
//	}

}
