package baekjoon7511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int parent[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase<=t; testCase++)
		{
			//System.out.println("Scenario "+testCase+":");
			sb.append("Scenario "+testCase+":" + "\n");
			
			int userCnt = Integer.parseInt(br.readLine());//유저의 수 1 ≤ n ≤ 10의6승
			int friendRelCnt = Integer.parseInt(br.readLine());//친구 관계의 수 1 ≤ k ≤ 10의 5승
			
			parent = new int[userCnt+1];
			
			for(int i = 1; i<=userCnt; i++)
			{
				parent[i] = i;
			}
			
			for(int i = 0; i<friendRelCnt; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				int friend1 = Integer.parseInt(st.nextToken());
				int friend2 = Integer.parseInt(st.nextToken());
				
				union(friend1, friend2);
			}
			
			int friendCheckCnt = Integer.parseInt(br.readLine());
			
			for(int i = 0; i<friendCheckCnt; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				int friend1 = Integer.parseInt(st.nextToken());
				int friend2 = Integer.parseInt(st.nextToken());
				
				if(find(friend1)==find(friend2))
				{
					//System.out.println(1);
					sb.append(1 + "\n");
				}
				else
				{
					//System.out.println(0);
					sb.append(0 + "\n");
				}
			}
			
			//System.out.println();
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void union(int from, int to)
	{
		int fromParent = find(from);
		int toParent = find(to);
		
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
		if(target==parent[target])
		{
			return target;
		}
		else
		{
			return parent[target] = find(parent[target]);
		}
	}

}
