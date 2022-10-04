package baekjoon10775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int parent[];

	public static void main(String[] args) throws NumberFormatException, IOException {
	
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int gate = Integer.parseInt(br.readLine());
			int airplane = Integer.parseInt(br.readLine());
			int answer = 0;
			
			parent = new int[gate+1];
			for(int i = 1; i <= gate; i++)
			{
				parent[i]=i;
			}
			
			for(int i = 0; i<airplane; i++)
			{
				int wantGate = Integer.parseInt(br.readLine());
				int canGate= find(wantGate);
				
				if(canGate == 0)//도킹 불가능
				{
					break;
				}
				
				answer++;
				
				union(canGate-1, canGate);
			}
			
			System.out.println(answer);
			
	}
	
	public static int find(int node)
	{
		if(node == parent[node])
		{
			return node;
		}
		
		return parent[node] = find(parent[node]);
	}
	
	public static void union(int from, int to)
	{
		from = find(from);
		to = find(to);
		
		if(from<to)
		{
			parent[to] = parent[from];
		}
		else
		{
			parent[from] = parent[to];
		}
	}
}
