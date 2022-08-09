package baekjoon12847;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //월세를 내기 바로 전 날 (1 ≤ n ≤ 100,000)
		int M = Integer.parseInt(st.nextToken()); //일과 일을 할 수 있는 날 (0 ≤ m ≤ n) 
		long answer = 0;
		
		int days[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			days[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<M; i++)
		{
			answer += days[i];
		}
		
		long current = answer;

		for(int i = 0; i<N-M; i++)
		{
			current = current - days[i] + days[i+M];//범위의 왼쪽값을 빼고 오른쪽값을 더해준다
			answer = Math.max(answer, current);
		}		
		System.out.println(answer);
	}
}
