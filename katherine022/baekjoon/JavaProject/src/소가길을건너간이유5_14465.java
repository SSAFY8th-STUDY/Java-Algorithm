import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 소가길을건너간이유5_14465 {
	static int N, K, B;
	static int trafficLight[];
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		trafficLight = new int[N+1];
		
		for(int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			trafficLight[tmp] = 1;
		}
		
		int start = 1;
		int fixLight = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			fixLight += trafficLight[i];
			
			if(i >= K) {
				System.out.println(start + " " + i + " " + fixLight);
				min = Math.min(min, fixLight);
				fixLight -= trafficLight[start];
				start++;
			}
		}
		System.out.println(min);
	}

}
