package solved;

import java.util.Scanner;

public class boj1244 {
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++)
			arr[i] = sc.nextInt();
		
		int M = sc.nextInt();
		while (M-- > 0) {
			int sex = sc.nextInt();
			int num = sc.nextInt();

			if (sex == 1) {
				for (int s = num; s <= N; s += num)
					swap(s);
			} else {
				swap(num);
				int d = 1;
				while (1 <= num - d && num + d <= N && arr[num - d] == arr[num + d]) {
					swap(num - d);
					swap(num + d);
					d++;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (i != 1 && i % 20 == 1)
				System.out.println();
			System.out.print(arr[i] + " ");
		}
		sc.close();
	}

	static void swap(int idx) {
		arr[idx] = arr[idx] == 0 ? 1 : 0;
	}
}
