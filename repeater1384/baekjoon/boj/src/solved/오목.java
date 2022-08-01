package solved;

import java.util.Scanner;

public class 오목 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 6목 이상 체크를 위한 패딩.
		int[][] matrix = new int[21][21];

		for (int i = 1; i <= 19; i++)
			for (int j = 1; j <= 19; j++)
				matrix[i][j] = sc.nextInt();

		// 가로 체크
		for (int row = 1; row <= 19; row++) {
			for (int col = 1; col <= 15; col++) {
				int temp = matrix[row][col];
				// temp -> 바둑돌의 색
				if (temp == 0)
					continue;

				boolean check = true;
				for (int t = 1; t < 5; t++) {
					if (temp != matrix[row][col + t]) {
						check = false;
						break;
					}
				}
				// 일단 5목은 만들어졌을때, 6목체크
				if (check)
					if (temp == matrix[row][col + 5] || temp == matrix[row][col - 1])
						check = false;

				// 6목 아닌 5목. 승자 정해진 경우.
				// 5목이 두군데서 만들어진 경우는 없다고 했으니까 더 탐색하지 않고 프로그램 종료.
				if (check) {
					System.out.printf("%d\n%d %d", temp, row, col);
					System.exit(0);
				}
			}
		}

		// 세로 체크
		for (int col = 1; col <= 19; col++) {
			for (int row = 1; row <= 15; row++) {
				int temp = matrix[row][col];
				if (temp == 0)
					continue;

				boolean check = true;
				for (int t = 1; t < 5; t++) {
					if (temp != matrix[row + t][col]) {
						check = false;
						break;
					}
				}
				if (check)
					if (temp == matrix[row + 5][col] || temp == matrix[row - 1][col])
						check = false;

				if (check) {
					System.out.printf("%d\n%d %d", temp, row, col);
					System.exit(0);
				}
			}
		}

		// 우하향 대각선 체크
		for (int row = 1; row <= 15; row++) {
			for (int col = 1; col <= 15; col++) {
				int temp = matrix[row][col];
				if (temp == 0)
					continue;

				boolean check = true;
				for (int t = 1; t < 5; t++) {
					if (temp != matrix[row + t][col + t]) {
						check = false;
						break;
					}
				}
				if (check)
					if (temp == matrix[row + 5][col + 5] || temp == matrix[row - 1][col - 1])
						check = false;

				if (check) {
					System.out.printf("%d\n%d %d", temp, row, col);
					System.exit(0);
				}
			}
		}

		// 좌상향 대각선 체크
		for (int row = 5; row <= 19; row++) {
			for (int col = 1; col <= 15; col++) {
				int temp = matrix[row][col];
				if (temp == 0)
					continue;

				boolean check = true;
				for (int t = 1; t < 5; t++) {
					if (temp != matrix[row - t][col + t]) {
						check = false;
						break;
					}
				}
				if (check)
					if (temp == matrix[row - 5][col + 5] || temp == matrix[row + 1][col - 1])
						check = false;

				if (check) {
					System.out.printf("%d\n%d %d", temp, row, col);
					System.exit(0);
				}
			}
		}

		// 비긴 경우
		System.out.println(0);

		sc.close();

	}

}
