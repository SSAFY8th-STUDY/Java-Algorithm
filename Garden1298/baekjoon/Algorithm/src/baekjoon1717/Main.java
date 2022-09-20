package baekjoon1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int parent[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int numCnt = Integer.parseInt(st.nextToken());
		int operCnt = Integer.parseInt(st.nextToken());
		
		parent = new int[numCnt+1];
		
		for(int i = 0; i<=numCnt; i++)
		{
			parent[i] = i;
		}
		
		for(int i = 0; i<operCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int operator = Integer.parseInt(st.nextToken());//합집합은 0, 같은 집합에 포함되어 있는지를 확인하는 연산은 1
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			if(operator == 0)
			{
				union(num1, num2);
			}
			else
			{
//				if(parent[num1]==parent[num2])
//				{
//					System.out.println("YES");
//				}
//				else
//				{
//					System.out.println("NO");
//				}
				if(find(num1)==find(num2))
				{
					System.out.println("YES");
				}
				else
				{
					System.out.println("NO");
				}
			}
		}
	}
	
	public static void union(int from, int to)
	{
		int fromParent = find(from);
		int toParent = find(to);
		
		if(fromParent == toParent) return;
		
		if(fromParent < toParent)
		{
			parent[toParent] = fromParent;
		}
		else
		{
			parent[fromParent] = toParent;
		}
	}
	
	public static int find(int target)
	{
		if(target == parent[target])
		{
			return target;
		}
		else
		{
			return parent[target] = find(parent[target]);
		}
	}
}
