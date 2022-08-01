package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj11401 {

	static long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			long N = Integer.parseInt(st.nextToken());
			long K = Integer.parseInt(st.nextToken());

			long A = factorial(N);
			long B = factorial(K) * factorial(N - K) % MOD;

			System.out.println(A * fast_pow(B, MOD - 2) % MOD);
		}
	}

	public static long factorial(long N) {
		long fac = 1;

		while (N > 1) {
			fac = (fac * N) % MOD;
			N--;
		}
		return fac;
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