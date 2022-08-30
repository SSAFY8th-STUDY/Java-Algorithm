package S1227;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1227. 미로2 - D4
 * 
 * @author hoseong
 * @category 델타, BFS
 */
public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\S1227\\input_1227.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = 100; //미로의 사이즈
		
		//하 우 상 좌
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			br.readLine(); //TestCase 번호 - 버리는 값
			
			char[][] map = new char[N][N];
			for(int i=0; i<100; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			//시작점을 판별하여 Queue에 삽입
			Queue<int[]> queue = new LinkedList<>();
			loop: for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '2') {
						queue.offer(new int[] {i, j});
						break loop;
					}
				}
			}
			
			boolean isFind = false;
			while(!queue.isEmpty()) { //더이상 갈 곳이 없을때 까지
				int[] posi = queue.poll(); //탐색할 좌표
				
				int R = posi[0];
				int C = posi[1];
				
				//4방 탐색
				for(int i=0; i<4; i++) {
					int r = R+dr[i];
					int c = C+dc[i];
					
					if(r<0 || r>=N || c<0 || c>=N)
						continue;
					
					if(map[r][c] == '0') { //길일 경우 큐에 삽입 후 지나온 곳 표시
						queue.offer(new int[] {r, c});
						map[r][c] = '1';
						
					} else if(map[r][c] == '3') { //도착지일 경우 큐를 비우고 while 종료
						isFind = true;
						queue.clear();
					}
				}
			}
			
			System.out.println("#" + test_case + " " + (isFind ? 1 : 0));
		}
	}
}