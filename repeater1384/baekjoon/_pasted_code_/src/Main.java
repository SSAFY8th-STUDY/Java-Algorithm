import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int MOD = 1000000007;
	static int MAX_SIZE = 4000001;
	static int[] fac;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		fac = new int[MAX_SIZE];
		fac[0] = 1;
		for (int i = 1; i < MAX_SIZE; i++) {
			fac[i] = (int) (((long) fac[i - 1] * i) % MOD);
		}

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			long A = fac[N];
			long B = (long) fac[K] * fac[N - K] % MOD;

			System.out.println(A * fast_pow(B, MOD - 2) % MOD);
		}
		br.close();
	}

	public static long fast_pow(long base, long exp) {
		long result = 1;

		while (exp > 0) {
			if (exp % 2 == 1) {
				result *= base;
				result %= MOD;
			}
			base = (base * base) % MOD;
			exp /= 2;
		}

		return result;
	}

}