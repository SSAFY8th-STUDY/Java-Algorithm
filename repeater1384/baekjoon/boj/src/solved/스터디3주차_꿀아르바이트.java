package solved.스터디3주차;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스터디3주차_꿀아르바이트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		long cur_sum = 0;
		for (int i = 0; i < M; i++)
			cur_sum += arr[i];

		long answer = 0;
		for (int i = M; i <= N; i++) {
			answer = Math.max(answer, cur_sum);
			cur_sum = cur_sum + arr[i] - arr[i - M];
		}
		System.out.println(answer);
	}

}