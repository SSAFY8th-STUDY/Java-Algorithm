package solved.스터디3주차;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 스터디3주차_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + K];

		int[] seusi = new int[D + 1];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		for(int i = N;i<N+K-1;i++) {
			arr[i] = arr[i-N];
		}

		Set<Integer> idxSet = new HashSet<>();
		for (int i = 0; i < K; i++) {
			seusi[arr[i]]++;
			idxSet.add(arr[i]);
		}
		int answer = 0;
		for (int i = K; i < N + K; i++) {
			int cur = seusi[C] > 0 ? 0 : 1;
			for (int idx : idxSet) {
				if (seusi[idx] > 0)
					cur++;
			}

			answer = Math.max(cur, answer);

			seusi[arr[i - K]]--;
			if (seusi[arr[i - K]] == 0)
				idxSet.remove(arr[i - K]);
			seusi[arr[i]]++;
			idxSet.add(arr[i]);
		}
		System.out.println(answer);

	}

}