import java.util.Scanner;

public class Main {
	static int MOD = 1_000_000_000;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int result;
		int a = 0;
		int b = 1;
		if (n > 0) {
			while (n-- > 0) {
				int hap = (a + b) % MOD;
				a = b;
				b = hap;
			}
			result = a;
		} else {
			while (n++ < 0) {
				int hap = (a - b) % MOD;
				b = hap;
				a = b;
			}
			result = b;
		}

		System.out.printf("%d\n%d", result > 0 ? 1 : result == 0 ? 0 : -1, Math.abs(result));
	}
}