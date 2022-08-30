package baekjoon3151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//N명의 학생 (1 ≤ N ≤ 10000)
		int students[] = new int[N];
		long answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			students[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(students);
		//System.out.println(Arrays.toString(students));
		
		// i : 고정할 인덱스
		for(int i = 0; i<students.length-2; i++)
		{
			if(students[i]>0) break; //0이 될 수 없으므로 반복문 나가기
			
			// 투 포인터 left, right
			int left = i + 1;
			int right = N - 1;
			
			while(left<right)
			{
				//합 : 고정된 포인터의 값 + 왼쪽 포인터의 값 + 오른쪽 포인터의 값
				int sum = students[i] + students[left] + students[right];
				
				if(sum == 0)//합이 0이면
				{
					//System.out.println("값을 찾았습니다==> current : "+ i +"left :"+left+" right :"+right);
					//같은 숫자가 나올 때 처리 => 같은 숫자의 개수를 세서 답에 더하기
					if(students[left] == students[right])
					{
						int sameNumCnt = right - left + 1;
						answer += sameNumCnt * (sameNumCnt - 1) / 2;//같은 숫자의 수에서 두개를 뽑는 경우 구하기(N(N-1)/R)
						break;
					}
					
					int leftCount = 1;//왼쪽 값과 중복되는 값의 개수를 찾는 변수
					int rightCount = 1;//오른쪽 값과 중복되는 값의 개수를 찾는 변수
					
					//다음 수가 현재 수와 같으면 leftCount에 1을 더한다
					while (left + leftCount < right && students[left + leftCount] == students[left]) 
					{
						leftCount++;
					}
					//다음 수가 현재 수와 같으면 rightCount에 1을 뺀다
					while (right - rightCount > left && students[right - rightCount] == students[right]) 
					{
						rightCount++;
					}
					
					answer += leftCount * rightCount;//경우의 수 구해서 더하기
					left += leftCount;//왼쪽 인덱스 중복되는 값의 개수만큼 이동
					right -= rightCount - 1;//오른쪽 인덱스 중복되는 값의 개수만큼 이동
				}
				else if(sum <= 0)//합이 0보다 작으면
				{
					left++;
				}				
				else if(sum > 0)//합이 0보다 크면
				{
					right--;
				}
			}
		}
		System.out.println(answer);
	}

}
