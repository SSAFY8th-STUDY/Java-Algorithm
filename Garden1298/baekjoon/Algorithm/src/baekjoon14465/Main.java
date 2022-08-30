package baekjoon14465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//신호등의 개수
		int K = Integer.parseInt(st.nextToken());//연속하게 있어야하는 신호등의 개수
		int B = Integer.parseInt(st.nextToken());//고장난 신호등의 개수
		int crossingLight[] = new int[N];//고장난 신호등 번호
		
		for(int i = 0; i<B; i++)
		{
			int number = Integer.parseInt(br.readLine());
			crossingLight[number-1] = 1;//고장난 신호등의 인덱스를 1로 바꾼다.
		}
		
		int min = 0;
		
		for(int i = 0; i<K; i++)
		{
			min += crossingLight[i];
		}

		int current = min;
		
		for(int i = 1; i<N-K+1; i++)
		{
			current = current - crossingLight[i-1] + crossingLight[i+K-1];
			min = Math.min(min, current);
		}
		
		System.out.println(min);
	}

}
