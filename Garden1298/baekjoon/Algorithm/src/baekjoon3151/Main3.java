package baekjoon3151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//N명의 학생 (1 ≤ N ≤ 10000)
		int students[] = new int[N];
		long answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++)
		{
			students[i]= Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(students);
		
		

	}

}
