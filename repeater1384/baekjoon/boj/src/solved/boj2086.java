package solved;
import java.util.Scanner;

public class boj2086 {

	static int MOD = 1_000_000_000;

	public static void main(String[] args) {
		// Fk = Fk+1 - Fk-1
		// Fk^2 = Fk(Fk+1 - Fk-1)
		// Fk^2 = -Fk-1Fk + FkFk+1
		// 시그마 씌우고 정리하면 k:1~n Fk^2 => FnFn+1
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long B = sc.nextLong();

		long temp = get_fibo_sum(B) - get_fibo_sum(A - 1);
		System.out.println(temp > 0 ? temp : temp + MOD);
		sc.close();
	}

	static long get_fibo_sum(long n) {
		// n번째 피보나치 수까지 의 합 return
		long[][] matrix = { { 1, 1 }, { 1, 0 } };
		long[][] result = { { 1, 0 }, { 0, 1 } };
		n += 2;
		while (n > 0) {
			if (n % 2 == 1)
				result = mul(result, matrix);
			matrix = mul(matrix, matrix);
			n /= 2;
		}
		return result[0][1] - 1;
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