package solved.스터디3주차;
import java.util.Arrays;
import java.util.Scanner;

public class 스터디3주차_합이0 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		long answer = 0;
		for (int k = 0; k < n - 2; k++) {
			if (arr[k] > 0)
				break;

			int i = k + 1, j = n - 1;
			while (i < j) {
				int i_cnt = 1;
				int j_cnt = 1;
				int sum = arr[i] + arr[j] + arr[k];
				if (sum == 0) {
					if (arr[i] == arr[j]) {
						int cnt = j - i + 1;
						answer += cnt * (cnt - 1) / 2;
						break;
					}
					while (i + i_cnt < j && arr[i + i_cnt] == arr[i])
						i_cnt++;

					while (i < j - j_cnt && arr[j - j_cnt] == arr[j])
						j_cnt++;
					answer += i_cnt * j_cnt;
					i += i_cnt - 1;
					j -= j_cnt - 1;
				}

				if (sum <= 0)
					i++;
				else
					j--;
			}
		}
		System.out.println(answer);
		sc.close();
	}

}