package P3151;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 3151. 합이 0 - 골드 5
 * 
 * @author hoseong
 * @category 브루트포스 알고리즘, 정렬, 이분 탐색, 두 포인터
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] students = new int[N];
		int standard = 0;
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());

			if (students[i] < 0)
				standard++;
		}

		Arrays.sort(students);
		int count = 0;

		for (int i = 0; i < standard; i++) {
			int sp = standard;
			int ep = N - 1;
			int min = students[i];

			for (int j = 0; j < N - standard - 1; j++) {
				int max = students[sp] + students[ep];
				if (max + min > 0)
					ep--;
				else if (max + min < 0)
					sp++;
				else {
					count++;
					if (students[sp] == students[sp + 1]) {
						sp++;
					} else if (students[ep] == students[ep - 1]) {
						ep++;
					} else {
						sp++;
					}
				}
			}
		}

		for (int i = standard; i < N; i++) {
			int sp = standard - 1;
			int ep = 0;
			int min = students[i];

			for (int j = 0; j < standard; j++) {
				int max = students[sp] + students[ep];
				if (max + min < 0)
					ep++;
				else if (max + min > 0)
					sp--;
				else {
					count++;
					if (students[sp] == students[sp + 1]) {
						sp--;
					} else if (students[ep] == students[ep - 1]) {
						ep++;
					} else {
						sp--;
					}
				}
			}
		}

		System.out.println(count);
	}
}