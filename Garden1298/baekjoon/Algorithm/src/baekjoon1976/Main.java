package baekjoon1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int parent[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int cityCnt = Integer.parseInt(br.readLine());
		int travelCnt = Integer.parseInt(br.readLine());
		
		parent = new int[cityCnt+1];
		for(int i = 1; i<=cityCnt; i++)
		{
			parent[i] = i;
		}
		
		//i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미한다
		for(int i = 1; i<=cityCnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j<=cityCnt; j++)
			{
				int connect = Integer.parseInt(st.nextToken());
				
				if(connect==1)
				{
					union(i, j);
				}
			}	
		}
				
		st = new StringTokenizer(br.readLine());
		
		int value = find(Integer.parseInt(st.nextToken()));
		
		for(int i = 1; i<travelCnt; i++)
		{
			if(value != find(Integer.parseInt(st.nextToken())))
			{
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
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
