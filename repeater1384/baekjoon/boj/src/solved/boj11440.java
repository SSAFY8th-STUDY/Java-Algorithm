package solved;
import java.util.Scanner;

public class boj11440 {

	static int MOD = 1_000_000_007;

	public static void main(String[] args) {
		// Fk = Fk+1 - Fk-1
		// Fk^2 = Fk(Fk+1 - Fk-1)
		// Fk^2 = -Fk-1Fk + FkFk+1
		// 시그마 씌우고 정리하면 k:1~n Fk^2 => FnFn+1
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long[][] matrix = { { 1, 1 }, { 1, 0 } };
		long[][] result = { { 1, 0 }, { 0, 1 } };

		while (N > 0) {
			if (N % 2 == 1)
				result = mul(result, matrix);
			matrix = mul(matrix, matrix);
			N /= 2;
		}

		System.out.println(result[1][0] * result[0][0] % MOD);

		sc.close();
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