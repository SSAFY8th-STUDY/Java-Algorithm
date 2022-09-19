package baekjoon17532;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int parent[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());//2 ≤ N ≤ 300,000
		
		parent = new int[N+1];
		for(int i = 1; i<=N; i++)
		{
			parent[i] = i;
		}
		
		
		for(int i = 0; i<N-2; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int bridge1 = Integer.parseInt(st.nextToken());//1, 2, ..., N
			int bridge2 = Integer.parseInt(st.nextToken());//1, 2, ..., N
			
			union(bridge1, bridge2);
		}
		
		for(int i = 1; i<=N-1; i++)
		{
			for(int j = i+1; j<=N; j++)
			{
				int bridge1 = i;
				int bridge2 = j;
				
				//System.out.println(bridge1 +" " + bridge2);
				//System.out.println("find : " + find(bridge1) +" " + find(bridge2));
				
				if(find(bridge1)!=find(bridge2))
				{
					System.out.println(bridge1+" "+bridge2);
					return;
				}
			}
		}
	}

	public static void union(int from, int to)
	{
		int fromParent = find(from);//from의 부모 찾기
		int toParent = find(to);//to의 부모 찾기
		
		//더 큰 부모의 값에 작은 값 할당
		if(fromParent < toParent)
		{
			parent[toParent] = fromParent;
		}
		else
		{
			parent[fromParent] = toParent;
		}
	}
	
	//최상단 노드(부모) 찾기
	public static int find(int target)
	{
		//부모가 자신이면 자기 자신 리턴
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
