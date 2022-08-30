package P16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 16234. 인구 이동 - 골드 5
 * 
 * @author hoseong
 * @category BFS, 시뮬레이션
 */
public class Main {
	static int N, L, R;
	static List<Union> uList; //연합된 국가들의 리스트
	static Union[][] land; //땅
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Union { //국가들의 정보
		int r, c;
		int people;

		public Union(int r, int c, int people) {
			this.r = r;
			this.c = c;
			this.people = people;
		}

		@Override
		public boolean equals(Object obj) {
			Union u = (Union) obj;

			return r == u.r && c == u.c && people == u.people;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 땅 크기
		L = Integer.parseInt(st.nextToken()); // 최소 인구 차이
		R = Integer.parseInt(st.nextToken()); // 최대 인구 차이

		land = new Union[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				land[i][j] = new Union(i, j, Integer.parseInt(st.nextToken()));
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		boolean isOpen = false; //국경을 열어도 되는지 여부
		int open = 0; //인구 이동 날짜

		do { //우선 국경을 열어본다.
			int[][] visited = new int[N][N];
			isOpen = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int count = 0, total = 0; //count: 국경을 연 국가 수, total: 총 인구 수
					uList = new ArrayList<>(); //연합된 국가들의 리스트
					Queue<Union> queue = new LinkedList<>(); //탐색을 위한 큐

					if (visited[i][j] == 0) { // 방문한 적이 없는 국가일 경우
						//해당 국가를 리스트와 큐에 넣는다.
						Union f = land[i][j];
						queue.offer(f);
						uList.add(f);
						
						//방문한 국가의 수와 인구를 기록
						count++;
						total += f.people;

						// f국가를 기점으로 연합여부 탐색
						while (!queue.isEmpty()) {
							Union u = queue.poll();
							visited[u.r][u.c] = 1; //방문 처리

							for (int d = 0; d < 4; d++) { //사방탐색
								int r = u.r + dr[d];
								int c = u.c + dc[d];
								if (r >= 0 && r < N && c >= 0 && c < N && visited[r][c] == 0) { //방문한 적이 없는 국가이면
									Union next = land[r][c];
									int diff = Math.abs(u.people - next.people); //인구 비교

									if (diff >= L && diff <= R && !uList.contains(next)) { //인구가 연합 가능하고 연합중인 국가가 아니면
										//방문한 국가의 수와 인구를 기록
										count++;
										total += next.people;
										
										//국경을 열고
										isOpen = true;
										
										//다음나라 방문
										uList.add(next);
										queue.offer(next);
									}
								}
							}
						}

						total /= count > 0 ? count : 1;
						for (int u = 0, size = uList.size(); u < size; u++) {
							uList.get(u).people = total;
						}
					}
				}
			}

			//국경이 열려있으면 오픈일 증가
			if (isOpen)
				open++;

		} while (isOpen);

		return open;
	}
}