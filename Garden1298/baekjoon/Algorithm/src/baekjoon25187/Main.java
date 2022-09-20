package baekjoon25187;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int parent[];
	static int waterInfo[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int tankCnt = Integer.parseInt(st.nextToken());//물탱크의 수	
		int pipeCnt = Integer.parseInt(st.nextToken());//파이프의 수
		int visitCnt = Integer.parseInt(st.nextToken());//방문할 횟수
		
		parent = new int[tankCnt+1];
		waterInfo = new int[tankCnt+1];
		
		for(int i = 1; i<=tankCnt; i++)
		{
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=tankCnt; i++)
		{
			int input = Integer.parseInt(st.nextToken());
			if(input == 1)
			{
				waterInfo[i] = 1;				
			}
			else
			{
				waterInfo[i] = -1;
			}
		}
		
		for(int i = 0; i<pipeCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int tank1 = Integer.parseInt(st.nextToken());
			int tank2 = Integer.parseInt(st.nextToken());
			
			union(tank1, tank2);
		}

		for(int i = 0; i<visitCnt; i++)
		{
			int tank2visit = Integer.parseInt(br.readLine());
			int result = waterInfo[parent[tank2visit]];
			
			if(result > 0)
			{
				sb.append(1+"\n");
			}
			else
			{
				sb.append(0+"\n");
			}
		}
		
		System.out.println(sb);
	}

	public static void union(int from, int to)
	{
		int fromParent = find(from);
		int toParent = find(to);
		
		if(fromParent == toParent) return;//이미 처리한 값이면 나가기
		
		if(fromParent < toParent)
		{
			parent[toParent] = fromParent;
			waterInfo[fromParent] += waterInfo[toParent];
		}
		else
		{
			parent[fromParent] = toParent;
			waterInfo[toParent] += waterInfo[fromParent];
		}
		System.out.println("parents : " + Arrays.toString(parent));
		System.out.println("waterInfo : " + Arrays.toString(waterInfo));
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
