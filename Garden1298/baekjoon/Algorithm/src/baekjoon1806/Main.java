package baekjoon1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//10,000 이하의 자연수로 이루어진 길이 N짜리 수열
		int S = Integer.parseInt(st.nextToken());//수열의 합 조건 (0 < S ≤ 100,000,000)
		int nums[] = new int[N];
		int minLength = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			nums[i] = Integer.parseInt(st.nextToken());
			if(nums[i] == S)
			{
				System.out.println("1");
				return;
			}
		}
		
		int sums = nums[0];
		int left = 0, right = 1;
		int length = Integer.MAX_VALUE;
		
		//System.out.println("찾아야 할 값 : "+ S);
		while(left<=right&&right<N)
		{
			
			sums = sums + nums[right];
			//System.out.println("left : "+ left +" right : "+ right);
			//System.out.println("현재 값 : "+ sums);
			
			if(sums >= S)
			{
				length = right - left + 1;
				minLength  = Math.min(minLength, length);
				//System.out.println("<<<<찾았습니다>>>> 길이 : "+ length);
				left += 1;
				right = left + 1;
				sums = nums[left];
			}
			else if(sums < S)
			{
				right += 1;
				//System.out.println("sums < S입니다. 오른쪽: 이동");
			}
		}
		
		if(minLength == Integer.MAX_VALUE)
		{
			System.out.println("0");
		}
		else
		{
			System.out.println(minLength);			
		}

	}

}
