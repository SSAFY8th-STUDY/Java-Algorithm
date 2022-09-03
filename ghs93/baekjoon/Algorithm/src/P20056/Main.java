package P20056;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 20056. 마법사 상어와 파이어볼 - 골드 4
 * @author hoseong
 * @category 구현
 */
public class Main {
	static int N, M, K, map[][];
	static int[][] del = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static ArrayList<FireBall> fireBall = new ArrayList<>();
	
	/**
	 * 파이어볼의 정보와 좌표 비교를 위한 클래스
	 */
	static class FireBall {
		int r, c;
		int m , s, d; //질량, 속력, 방향
		
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		public FireBall(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object obj) {
			FireBall f = (FireBall) obj;

			return r == f.r && c == f.c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			fireBall.add(new FireBall(r, c, m, s, d));
		}
		
		for (int i = 0; i < K; i++) {
			move();
		}
		
		int sum = 0;
		for(FireBall f : fireBall)
			sum += f.m;
		
		System.out.println(sum);
	}

	private static void move() {
		map = new int[N+1][N+1]; //이돟하기 전 매번 map 초기화
		
		//이동
		for (int i = 0, size = fireBall.size(); i < size; i++) {
			int r = fireBall.get(i).r;
			int c = fireBall.get(i).c;
			int dir = fireBall.get(i).d;
			int dist = fireBall.get(i).s % N;
			
			r += del[dir][0] * dist;
			c += del[dir][1] * dist;
			
			r = r<=0 ? N+r : (r>N ? r-N : r);
			c = c<=0 ? N+c : (c>N ? c-N : c);
			
			fireBall.get(i).r = r;
			fireBall.get(i).c = c;
			
			map[r][c]++;
		}
		
		//파이어볼 2개 이상인 곳 검사
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(map[i][j] > 1) {
					FireBall f = new FireBall(i, j); //비교를 위한 해당 좌표의 임시 파이어볼 생성
					int m = 0, s = 0, cnt = 0;
					int[] d = new int[4];
					boolean even = true, odd = true; //짝, 홀
					
					for (int k = fireBall.size()-1; k >= 0; k--) {
						if(fireBall.get(k).equals(f)) { //해당 좌표의 파이어볼
							//합치는 과정..
							FireBall fb = fireBall.remove(k);
							m += fb.m;
							s += fb.s;
							cnt++;
							
							//짝수 검사
							if(fb.d % 2 != 0)
								even = false;
							
							//홀수 검사
							if(fb.d % 2 == 0)
								odd = false;
						}
					}
					
					//파이어볼 나누기
					m = m/5;
					s = s/cnt;
					for (int k = 0; k < 4; k++) {
						d[k] = (even || odd) ? k*2 : k*2 + 1; //홀수 또는 짝수일 경우와 아닐 경우
					}
					
					if(m != 0) { //질량이 0인 파이어볼은 소멸
						for (int k = 0; k < 4; k++) {
							fireBall.add(new FireBall(i, j, m, s, d[k]));
						}
					}
				}
			}
		}
	}
}