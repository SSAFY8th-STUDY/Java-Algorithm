import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 꿀아르바이트_12847 {
	static int n, m;
	static int[] t;

	public static void main(String[] args) throws IOException, FileNotFoundException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		t = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 1;
		long money = 0;
		long max = Long.MIN_VALUE;
		for(int i = 1; i<= n; i++) {
			money += t[i];
			
			if( i >= m ) {
				//System.out.println(start + " " + i);
				max = Math.max(max, money);
				money -= t[start];
				start++;
			}
		}
		
		System.out.println(max);
		
	}

}
