package P21610;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 21610. 마법사 상어와 비바라기 - 골드 5
 * @author hoseong
 * @category 구현
 */
public class Main {
	static int N, M, map[][], ds[][];
	static ArrayList<Cloud> cloud;
	static int[][] del = {{}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	
	/**
	 * 구름의 좌표와 비교를 위한 클래스
	 */
	static class Cloud {
		int r, c;

		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object obj) {
			Cloud cl = (Cloud) obj;
			return r == cl.r && c == cl.c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		ds = new int[M][2];
		cloud = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			ds[i][0] = Integer.parseInt(st.nextToken());
			ds[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//초기 구름위치
		cloud.add(new Cloud(N, 1));
		cloud.add(new Cloud(N, 2));
		cloud.add(new Cloud(N-1, 1));
		cloud.add(new Cloud(N-1, 2));
		
		//구름이동 및 비바라기
		for (int i = 0; i < M; i++) {
			moveCloud(i);
		}
		
		//물의 합
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}

	/**
	 * 구름이동
	 */
	private static void moveCloud(int d) {
		int dir = ds[d][0]; //방향
		int dist = ds[d][1] % N; //이동횟수
		
		for (int i = 0, size = cloud.size(); i < size; i++) {
			Cloud cl = cloud.get(i);
			int r = cl.r;
			int c = cl.c;
			
			r += del[dir][0] * dist;
			c += del[dir][1] * dist;
			
			r = r<=0 ? N+r : (r>N ? r-N : r);
			c = c<=0 ? N+c : (c>N ? c-N : c);

			cl.r = r;
			cl.c = c;
		}
		
		rainDrop();
	}
	
	/**
	 * 비 내리기 및 대각선 방향 물복사
	 */
	private static void rainDrop() {
		//비 내리기
		for (int i = 0, size = cloud.size(); i < size; i++) {
			int r = cloud.get(i).r;
			int c = cloud.get(i).c;

			map[r][c]++;
		}

		//물 복사
		for (int i = 0, size = cloud.size(); i < size; i++) {
			int r = cloud.get(i).r;
			int c = cloud.get(i).c;
			
			int count = 0;
			for (int j = 1; j <= 4; j++) {
				int dr = r + del[j*2][0];
				int dc = c + del[j*2][1];
				
				if(dr > 0 && dr <= N && dc > 0 && dc <= N && map[dr][dc] > 0) {
					count++;
				}
			}
			
			map[r][c] += count;
		}
		
		makeCloud();
	}
	
	//새로운 구름생성
	private static void makeCloud() {
		ArrayList<Cloud> m = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j] > 1) {
					Cloud cl = new Cloud(i, j);
					
					if(!cloud.contains(cl)) {
						map[i][j] -= 2;
						m.add(cl);
					}
				}
			}
		}
		
		cloud = m;
	}
}