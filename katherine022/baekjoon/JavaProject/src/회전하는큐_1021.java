import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회전하는큐_1021 {
	static Deque<Integer> deque = new ArrayDeque<>();
	static Queue<Integer> queue = new LinkedList<>();
	static int num = 0;
	static int answer = 0;
	static int m1, m2;
	public static void main(String[] args) throws IOException, FileNotFoundException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 1; i<=N; i++) {
			deque.add(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< M; i++) {
			
			queue.add(Integer.parseInt(st.nextToken()));
		}
		
		while(!queue.isEmpty()) {
			num = queue.poll();
			
			m1 = 0;
			m2 = 0;
			Deque<Integer> dq1 = new ArrayDeque<>(deque);
			Deque<Integer> dq2 = new ArrayDeque<>(deque);
			
			dq1 = getMethod2(dq1, 0);
			dq2 = getMethod3(dq2, 0);
			
			if(m1 < m2) {
				deque = dq1;
				answer += m1;
			} else {
				deque = dq2;
				answer += m2;
			}
			deque.poll();
		}
		
		System.out.println(answer);
		
	}
	
	public static Deque<Integer> getMethod2(Deque<Integer>dq, int cnt) {
		if(dq.peek() == num) {
			m1 = cnt;
			return dq;
		}
		
		int front = dq.poll();
		dq.add(front);
		dq = getMethod2(dq, cnt+1);
		return dq;
	}
	
	public static  Deque<Integer> getMethod3(Deque<Integer>dq, int cnt) {
		if(dq.peek() == num) {
			m2 = cnt;
			return dq;
		}
		
		int back = dq.pollLast();
		dq.addFirst(back);
		dq = getMethod3(dq, cnt+1);
		return dq;
	}
}
