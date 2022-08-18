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
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(students);
		long count = 0;

		for (int std = 0; std < N - 2; std++) {
			if (students[std] > 0)
				break;

			int l = std + 1;
			int r = N - 1;

			while (l < r) {
				int sum = students[std] + students[l] + students[r];

				if (sum == 0) {
					int lSame = 1, rSame = 1;

					if (students[l] == students[r]) {
						int d = r - l + 1;
						count += d * (d - 1) / 2;
						break;

					} else {
						while (l + lSame < r && students[l + lSame] == students[l])
							lSame++;

						while (r - rSame > l && students[r - rSame] == students[r])
							rSame++;

						count += lSame * rSame;
						l += lSame - 1;
						r -= rSame - 1;
					}
				}

				if (sum <= 0)
					l++;
				else
					r--;
			}
		}

		System.out.println(count);
	}
}