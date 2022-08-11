package solved.스터디3주차;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스터디3주차_소가길을건너간이유5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		boolean[] arr = new boolean[N + 1];
		Arrays.fill(arr, true);

		while (B-- > 0)
			arr[Integer.parseInt(br.readLine())] = false;
		int normal = 0;
		for (int i = 0; i < K; i++)
			if (arr[i])
				normal++;
		int answer = K;
		for (int i = K; i <= N; i++) {
			normal = normal + (arr[i] ? 1 : 0) + (arr[i - K] ? -1 : 0);
			answer = Math.min(answer, K-normal);
		}
		System.out.println(answer);
	}

}