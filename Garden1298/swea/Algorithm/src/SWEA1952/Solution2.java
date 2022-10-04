package SWEA1952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2 {
	
	static class Node
	{
		int cnt;
		int sum;
		public Node(int cnt, int sum) {
			super();
			this.cnt = cnt;
			this.sum = sum;
		}
		
	}

	static int T,feeDay, feeMonth, fee3Month, feeYear, min;
	static int[] plan = new int[12];
	static Queue<Node> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i<=T; i++)
		{
			//입력 => 변수 및 자료 구조
			StringTokenizer st = new StringTokenizer(br.readLine());
			feeDay = Integer.parseInt(st.nextToken());
			feeMonth = Integer.parseInt(st.nextToken());
			fee3Month = Integer.parseInt(st.nextToken());
			feeYear = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 12; j++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			//완탐 bfs
			//최초 시점의 Node를 하나 queue에 담고 시작
			min = feeYear;
			queue.offer(new Node(0, 0));
			bfs();
			System.out.println("#" + i + " " + min);
		}

	}

	private static void bfs() {
		while(!queue.isEmpty())
		{
			//꺼내어서 다음 단계를 따진다.
			Node node = queue.poll();
			int cnt = node.cnt;
			int sum = node.sum;
			
			//기저 조건
			if( cnt >= 12)
			{
				min = Math.min(min, sum);//12월에 도착한 노드는 min을 따지고 더 계속 방문 X
				continue;
			}
			
			//계속 따져간다
			//이용하지 않는 월에 대한 처리
			if(plan[cnt]==0)
			{
				queue.offer(new Node(cnt+1, sum));
			}
			else
			{
				// cnt + 1번째 달에 대해
				// 1. 일 계산
				queue.offer(new Node(cnt+1, sum+(plan[cnt]*feeDay)));	
				// 2. 월 계산
				queue.offer(new Node(cnt+1, sum+feeMonth));
				// 3. 3개월 계산
				queue.offer(new Node(cnt+3, sum+fee3Month));
			}
		}
	}

	//재귀 호출방식 => 파라미터로 어떤 값을 
	private static void dfs(int cnt, int sum) {
		//기저 조건
		if( cnt >= 12)
		{
			min = Math.min(min, sum);
			return;
		}
		
		//계속 따져간다
		//이용하지 않는 월에 대한 처리
		if(plan[cnt]==0)
		{
			dfs(cnt+1, sum);
		}
		else
		{
			// cnt + 1번째 달에 대해
			// 1. 일 계산
			int cost = plan[cnt]*feeDay;
			if(sum + cost < min) dfs(cnt+1, sum+cost);				
			// 2. 월 계산
			if(sum + feeMonth < min) dfs(cnt+1, sum+feeMonth);
			// 3. 3개월 계산
			if(sum+fee3Month < min) dfs(cnt+3, sum+fee3Month);
		}
		
	}

}
