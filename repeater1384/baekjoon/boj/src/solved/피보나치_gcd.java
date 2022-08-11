package solved;
import java.util.Scanner;

public class 피보나치_gcd {

	static int MOD = 1_000_000_007;

	public static void main(String[] args) {
		// Fn과 Fm의 GCD는 GCD(n,m)번째 피보나치수이다.
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long M = sc.nextLong();
		if (M > N) {
			long temp = N;
			N = M;
			M = temp;
		}

		long pow = gcd(N, M);
		long[][] matrix = { { 1, 1 }, { 1, 0 } };
		// I 로 init
		long[][] result = { { 1, 0 }, { 0, 1 } };

		while (pow > 0) {
			if (pow % 2 == 1)
				result = mul(result, matrix);
			matrix = mul(matrix, matrix);
			pow /= 2;
		}
		System.out.println(result[1][0]);

		sc.close();
	}

	static long gcd(long a, long b) {
		return b > 0 ? gcd(b, a % b) : a;
	}

	static long[][] mul(long[][] m1, long[][] m2) {
		long[][] result = new long[2][2];
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					result[i][j] += (m1[i][k] * m2[k][j]) % MOD;
					result[i][j] %= MOD;
				}
			}
		}

		return result;
	}
}