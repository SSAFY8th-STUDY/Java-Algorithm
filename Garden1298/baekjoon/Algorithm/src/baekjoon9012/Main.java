package baekjoon9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String answer = "YES";
		
		for(int i = 0; i<N; i++)
		{
			String str = br.readLine();
			Stack<Character> stack = new Stack<>();
			for(int j = 0; j<str.length();j++)
			{
				char c = str.charAt(j);
				
				if(c=='(')//열린 괄호면 스택에 넣는다.
				{
					stack.add(c);					
				}
				else//닫힌 괄호라면
				{
					if(stack.isEmpty())//스택이 비었으면 답이 틀렸음을 표시하고 반복문을 나간다.
					{
						answer = "NO";
						break;
					}
					
					char before = stack.pop();//스택에서 값을 꺼낸다.
				}				
			}
			
			//스택에 값이 남아 있다면 답이 틀렸음을 표시한다.
			if(!stack.isEmpty())
			{
				answer = "NO";
			}
			
			//답 출력
			System.out.println(answer);
			
			//다음 테스트를 위해 답을 맞음으로 표시한다.
			answer = "YES";
		}
		
	}

}
