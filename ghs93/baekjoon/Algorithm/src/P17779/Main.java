package P17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17779. 게리맨더링2 - 골드 4
 * @author hoseong
 * @category 구현
 */
public class Main {
	static int N, map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = Integer.MAX_VALUE;
		
		//x, y, d1, d2의 조건
		//d1, d2 ≥ 1
		//1 ≤ x < x+d1+d2 ≤ N
		//1 ≤ y-d1 < y < y+d2 ≤ N
		for(int x=1; x<=N-2; x++) { //x의 최댓값은 d1, d2가 1일 경우 -> N-2 이하
			for(int y=2; y<= N-1; y++ ) { //y의 최댓값은 d2가 1일 경우 -> N-1 이하
				for(int d1=1; d1<y; d1++) { //d1의 최댓값은 y-d1이 1이상 -> y 미만
					if(x + d1 >= N) //d2가 성립할 수 없음
						break;
					
					for(int d2=1; x+d1+d2<=N && y+d2<=N; d2++) {
						min = Math.min(min, divide(x, y, d1, d2));
					}
				}
			}
		}
		
		System.out.println(min);
	}

	private static int divide(int x, int y, int d1, int d2) {
		int[][] area = new int[N+1][N+1];
		area[x][y] = 5;

		//5번 지역구 설정
		for(int i=1; i<=d1; i++) {
			area[x+i][y-i] = 5;
			area[x+d2+i][y+d2-i] = 5;
		}
		
		for(int j=1; j<=d2; j++) {
			area[x+j][y+j] = 5;
			area[x+d1+j][y-d1+j] = 5;
		}
		
		int[] count = new int[5];
		
		//1번 지역구 설정
		for (int i = 1; i < x+d1; i++) {
			for (int j = 1; j <= y; j++) {
				if(area[i][j] == 5)
					break;
				
				area[i][j] = 1;
			}
		}
		
		//2번 지역구 설정
		for (int i = 1; i <= x+d2; i++) {
			for(int j=N; j>y; j--) {
				if(area[i][j] == 5)
					break;
				
				area[i][j] = 2;
			}
		}
		
		//3번 지역구 설정
		for(int i=x+d1; i<=N; i++) {
			for(int j=1; j<y-d1+d2; j++) {
				if(area[i][j] == 5)
					break;
				
				area[i][j] = 3;
			}
		}
		
		//4번 지역구 설정
		for(int i=x+d2+1; i<= N; i++) {
			for(int j=N; j>=y-d1+d2; j--) {
				if(area[i][j] == 5)
					break;
				
				area[i][j] = 4;
			}
		}
		
		//지역구 별 인구수 구하기
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				int people = map[i][j];
				
				if(area[i][j] == 1) {
					count[0] += people;
					
				} else if(area[i][j] == 2) {
					count[1] += people;
					
				} else if(area[i][j] == 3) {
					count[2] += people;
					
				} else if(area[i][j] == 4) {
					count[3] += people;
					
				} else {
					count[4] += people;
				}
			}
		}

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < 5; i++) {
			min = Math.min(min, count[i]);
			max = Math.max(max, count[i]);
		}
		
		return max-min;
	}
}