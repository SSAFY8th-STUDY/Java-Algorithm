package baekjoon1644;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<Integer> primeNum;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		if(N==1)
		{
			System.out.println("0");
			return;
		}
		
		primeNum = new ArrayList<Integer>();
		
		for(int i = 0; i <= N; i++)
		{
			primeFunc(i);
		}
		
//		for(int i = 0; i<primeNum.size();i++)
//		{
//			System.out.print(primeNum.get(i)+" ");
//		}
//		System.out.println();
		
		
		int left = 0, right = 0;
		int sum = primeNum.get(0);
		int answer = 0;
		
		while(left<=right&&right<primeNum.size())
		{
			if(left!=right)
			{
				sum = sum + primeNum.get(right);				
			}
			
			//System.out.println("현재 값 : "+ sum);
			//System.out.println("left : "+ left +" right : "+ right);
			
			if(sum == N)
			{
				//System.out.println("<<<<찾았습니다>>>>");
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
	
	public static void primeFunc(int num)
	{
		if(num<2) return;
		
		if(num == 2)
		{
			primeNum.add(num);
			return;
		}
		
		for(int i = 2; i <= Math.sqrt(num); i++)
		{
			if(num % i == 0) return;
		}
		
		primeNum.add(num);
		return;
	}

}
