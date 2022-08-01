import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] matrix;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		matrix = new int[N + 2][N + 2];

		for (int i = 1; i <= N; i++) {
			String temp = sc.next();
			for (int j = 1; j <= N; j++) {
				matrix[i][j] = temp.charAt(j - 1) - '0';
			}
		}

		ArrayList<Integer> answer = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (matrix[i][j] == 1) {
					answer.add(bfs(j, i, 0));
				}
			}
		}
		Collections.sort(answer);
		System.out.println(answer.size());
		for (int a : answer)
			System.out.println(a);
		
		sc.close();
	}

	static int bfs(int sx, int sy, int size) {
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { sx, sy });
		matrix[sy][sx] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			size++;
			int cx = cur[0];
			int cy = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (matrix[ny][nx] == 1) {
					queue.add(new int[] { nx, ny });
					matrix[ny][nx] = 0;
				}
			}
		}

		return size;
	}

}