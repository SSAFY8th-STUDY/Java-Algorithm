import java.io.*;
import java.util.*;

public class 회전초밥_2531 {
	static int N, d, k, c;
	static int sushi[];
	static int num[];
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[N+1];
		num = new int[d+1];
		
		for(int i = 0; i < N; i++ ) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int start = 0;
		int max = Integer.MIN_VALUE;
		int cnt = 0;
		int i = 0;
		while(start < N) {
			int menu = sushi[i];
			
			if(num[menu] == 0) {
				cnt++;
				num[menu]++;
			} else {
				num[menu]++;
			}
			
			//System.out.println(i + " " + menu + " " + cnt+ " " + Arrays.toString(num));
			
			if(Math.abs(start-i) >= k-1) {
				if(num[c] == 0) max = Math.max(max, cnt+1);
				else max = Math.max(max, cnt);
				
				int s_menu = sushi[start];
				if(num[s_menu] == 1) {
					cnt--;
					num[s_menu]--;
				} else {
					num[s_menu]--;
				}
				
				start++;
			}
			i = (++i)%N;
		}
		
		System.out.println(max);
	}

}
