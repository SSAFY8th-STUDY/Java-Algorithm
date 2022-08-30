package baekjoon3151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//N명의 학생 (1 ≤ N ≤ 10000)
		int students[] = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			students[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(students);
		
		//System.out.println(Arrays.toString(students));
		
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i<students.length-1;i++)
		{
			int a[] = Arrays.copyOfRange(students, 0, i);
			//System.out.println(Arrays.toString(a));
			int b[] = Arrays.copyOfRange(students, i+1, students.length);
			//System.out.println(Arrays.toString(b));
			
			int newStudent[] = new int[students.length-1];
			System.arraycopy(a, 0, newStudent, 0, a.length);
			System.arraycopy(b, 0, newStudent, a.length, b.length);
			//System.out.println(Arrays.toString(newStudent));	
			
			//System.out.println("<==============배열 재설정=======================>");
			
			int left = 0, right = newStudent.length-1;
			int search = students[i]*-1;//찾아야 할 숫자
			while(left < right)
			{				
				int currentOutput = newStudent[left]+newStudent[right];
				//System.out.println(i+"번째 인덱스 찾아야 할 값 :"+search);
				//System.out.println(i+"번째 인덱스 계산 결과 :"+currentOutput);
				
				if(currentOutput == search)
				{
					//System.out.println("값을 찾았습니다==> current : "+ i +"left :"+left+" right :"+right);
					
					int arr[] = new int[3];
					int first = i;
					int second = i<left?left+1:left;
					int third = i<right?right+1:right;

					if(first!=second && first!=third && second != third)
					{
						arr[0] = first;
						arr[1] = second;
						arr[2] = third;
						
						Arrays.sort(arr);
						set.add(Arrays.toString(arr));						
					}
									
					left+=1;
				}
				else if(currentOutput>search)
				{
					right-=1;
					//System.out.println("right을 왼쪽으로==> 현재 left :" + left + "현재 right :" + right);
				}
				else
				{
					left+=1;
					//System.out.println("left을 오른쪽으로==> 현재 left :" + left + "현재 right :" + right);
				}	
				
			}
			//System.out.println("현재 누적 정답 : "+set.size());
			//System.out.println("<=====================================>");
		}
		
		//System.out.println("최대팀의 수는?"+set.size());
		
//		String print[] = set.toArray(new String[0]);
//		
//		for(int i = 0; i<set.size();i++)
//		{
//			System.out.println(print[i]);
//		}
		System.out.println(set.size());
	}
}
