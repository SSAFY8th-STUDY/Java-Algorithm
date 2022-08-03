package baekjoon10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] stack;
	static int size = 0;
	static int isEmpty = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		stack = new int[N];
		
		for(int i = 0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			switch (st.nextToken()) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				push(num);
				break;
			case "pop":
				pop();
				break;
			case "size":
				size();
				break;
			case "empty":
				empty();
				break;
			case "top":
				top();
				break;
			default:
				break;
			}
		}
	}
	
	public static void push(int num)
	{
		stack[size] = num;
		size++;
		isEmpty = 0;
	}
	
	public static void pop()
	{
		if(isEmpty==1)
		{
			System.out.println("-1");
		}
		else
		{
			System.out.println(stack[size-1]);
			stack[size-1] = 0;
			size--;
			if(size == 0)
			{
				isEmpty = 1;
			}
		}
	}
	
	public static void size()
	{
		System.out.println(size);
	}
	
	public static void empty()
	{
		System.out.println(isEmpty);
	}
	
	public static void top()
	{
		if(isEmpty == 1)
		{
			System.out.println("-1");
		}
		else
		{
			System.out.println(stack[size-1]);
		}
	}
}
