package baekjoon17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int A = Integer.parseInt(br.readLine());
		Stack<Integer> input = new Stack<>();
		Stack<Integer> tmp = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<A; i++)
		{
			input.add(Integer.parseInt(st.nextToken()));
		}
		
		sb.append(" 1-");
		tmp.add(input.pop());
		
		for(int i = 1; i < A; i++)
		{
			int current = input.pop();
			
			while(!tmp.isEmpty()&&current>=tmp.peek())
			{
				tmp.pop();
			}
			
			if(tmp.isEmpty())
			{
				sb.append(" 1-");
				tmp.add(current);
			}
			else
			{
				sb.append(" "+tmp.peek());
				tmp.add(tmp.peek());
				tmp.add(current);
			}
		}
		
		System.out.println(sb.reverse().toString());
	}
}
