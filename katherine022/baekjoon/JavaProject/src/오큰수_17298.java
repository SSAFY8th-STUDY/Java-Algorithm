import java.util.*;
import java.io.*;

public class 오큰수_17298 {
	static int N;
	static int a[];
	static Stack<Integer> stack = new Stack<>();
	public static void main(String[] args) throws IOException, FileNotFoundException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		a = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer[] = new int[N+1];
		for(int i = N-1; i >= 0; i--) {
			if(stack.isEmpty()) answer[i] = -1;
			else {
				while(!stack.isEmpty()) {
					if(a[i] >= stack.peek()) stack.pop();
					else break;
				}
				if(stack.isEmpty()) answer[i] = -1;
				else answer[i] = stack.peek();
			}
			stack.add(a[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<N; i++) {
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
		br.close();
	}

}
