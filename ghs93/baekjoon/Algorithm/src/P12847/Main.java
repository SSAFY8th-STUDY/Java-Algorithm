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
		
		int n = Integer.parseInt(st.nextToken()); //월세 전까지의 근무일
		int m = Integer.parseInt(st.nextToken()); //일 할 수 있는 날
		
		long[] pays = new long[n]; //전체 일급
		long pay = 0l; //근무 가능한 일 수의 일급 합
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			pays[i] = Long.parseLong(st.nextToken());
			
			if(i<m)
				pay+=pays[i];
		}
		
		long max = pay;
		for(int i=0; i<n-m; i++) {
			//슬라이딩 윈도우
			pay -= pays[i];
			pay += pays[i+m];
			
			//최대 이익
			if(max < pay)
				max = pay;
		}
		
		System.out.println(max);
	}
}