package P23806;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 백준 23806. 골뱅이 찍기 - 브론즈 3
 * @author hoseong
 * @category 구현
 */
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = N * 5;

		char[][] map = new char[M][M];
		for (char[] cs : map) {
			Arrays.fill(cs, '@');
		}
		
		for (int i = N; i < M-N; i++) {
			for (int j = N; j < M-N; j++) {
				map[i][j] = ' ';
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (char[] cs : map) {
			for (char c : cs) {
				sb.append(c);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

}
