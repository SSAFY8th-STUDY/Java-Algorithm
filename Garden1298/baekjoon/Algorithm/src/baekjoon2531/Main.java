package baekjoon2531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int dishNum = Integer.parseInt(st.nextToken());//회전 초밥 벨트에 놓인 접시의 수 (2 ≤ N ≤ 30,000)
		int sushiNum = Integer.parseInt(st.nextToken());//초밥의 가짓수 (d 2 ≤ d ≤ 3,000)
		int dishCont = Integer.parseInt(st.nextToken());//연속해서 먹는 접시의 수 (k  2 ≤ k ≤ 3,000 (k ≤ N))
		int coupon = Integer.parseInt(st.nextToken());//쿠폰 번호 (c 1 ≤ c ≤ d)
		int couponCnt = 0;

		int[] sushis = new int[dishNum+dishCont-1];
		
		//[7, 9, 7, 30, 2, 7, 9, 25, 0, 0, 0]
		for(int i = 0; i<dishNum;i++)
		{
			sushis[i] = Integer.parseInt(br.readLine());
		}
		//System.out.println(Arrays.toString(sushis));
		
		//[7, 9, 7, 30, 2, 7, 9, 25, 7, 9, 7]
		for(int i = 0; i<dishCont-1; i++)
		{
			sushis[dishNum+i] = sushis[i];
		}
		//System.out.println(Arrays.toString(sushis));
		
		int[] firstarr = Arrays.copyOfRange(sushis, 0, dishCont);
		//System.out.println(Arrays.toString(firstarr));
		int firstDuplicate = checkDuplicate(firstarr);
		int answer = dishCont-firstDuplicate;
		
		for(int i = 0; i<dishCont; i++)
		{
			if(sushis[i]==coupon)
			{
				couponCnt++;
			}
		}
		
		if(couponCnt==0)
		{
			answer++;
		}
		
		
		//System.out.println("첫번째 범위에 쿠폰 접시가 있나요?"+couponCnt);
		//System.out.println("현재 최댓값은?"+answer);
		
		for(int i = 1; i<sushis.length-dishCont+1; i++)
		{
			//System.out.println("현재 인덱스 범위는?"+(i)+"~"+(i+dishCont));
			
			int[] arr = Arrays.copyOfRange(sushis, i, i+dishCont);
			//System.out.println(Arrays.toString(arr));

			int duplicate = checkDuplicate(arr);
			
			if(sushis[i-1]==coupon)
			{
				couponCnt--;
			}
			if(sushis[i+dishCont-1]==coupon)
			{
				couponCnt++;
			}
			
			//System.out.println((i)+"번째 범위에 쿠폰 접시가 있나요?"+couponCnt);
			
			int current = dishCont-duplicate;			
			
			if(couponCnt==0)
			{
				current++;
			}
			
			//System.out.println((i)+"번째 범위의 값은?"+current);
			answer = Math.max(answer, current);
			//System.out.println((i)+"번째 범위 이후의 최댓값은?"+answer);
		}
		System.out.print(answer);		
	}
	
	public static int checkDuplicate(int arr[])
	{
		Set<Integer> set = new HashSet<>();
		int duplicate = 0;
		
		for(int i : arr)
		{
			   if(set.contains(i))
			   {
				   duplicate++;
			   }
			   else
			   {
				   set.add(i);
			   }
		}
		
		//System.out.println(duplicate+"개가 중복되었습니다");
		return duplicate;
	}

}
