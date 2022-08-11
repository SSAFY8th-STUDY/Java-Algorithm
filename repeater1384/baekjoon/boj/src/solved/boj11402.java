package solved;

import java.util.Scanner;

public class boj11402 {

	static int MOD;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long N = sc.nextLong();
		long K = sc.nextLong();
		MOD = sc.nextInt();

		int result = 1;
		while (N > 0 || K > 0) {
			if (N % MOD < K % MOD) {
				result = 0;
				break;
			}
			result = result * nCk(N % MOD, K % MOD) % MOD;
			N /= MOD;
			K /= MOD;
		}
		System.out.println(result);
		sc.close();
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

	public static int nCk(long N, long K) {
		long A = factorial(N);
		long B = factorial(K) * factorial(N - K) % MOD;

		return (int) ((A * fast_pow(B, MOD - 2)) % MOD);
	}

}