package P12847;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 12847. 꿀 아르바이트 - 실버 4
 * @author hoseong
 * @category 누적합, 슬라이딩 윈도우
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] pays = new long[n];
		long pay = 0l;
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			pays[i] = Long.parseLong(st.nextToken());
			
			if(i<m)
				pay+=pays[i];
		}
		
		long max = pay;
		for(int i=0; i<n-m; i++) {
			pay -= pays[i];
			pay += pays[i+m];
			
			if(max < pay)
				max = pay;
		}
		
		System.out.println(max);
	}
}