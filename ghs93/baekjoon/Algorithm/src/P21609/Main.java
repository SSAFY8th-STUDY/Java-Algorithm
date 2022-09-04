package P21609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 21609. 상어 중학교 - 골드 4
 * @author hoseong
 * @category 구현, BFS, DFS
 */
public class Main {
	static int N, M, map[][];
	static int[][] del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visited, rVisited; //방문처리, 무지개 방문처리
	static ArrayList<int[]> blocks = new ArrayList<>(); //가장 큰 블록그룹 리스트

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		do {
			blocks.clear();
			findBlock();
			
			if(blocks.size() > 1) {
				score += Math.pow(blocks.size(), 2);
			}
		} while(blocks.size() > 1);
		
		System.out.println(score);
	}

	/**
	 * 가장 큰 그룹 찾기
	 */
	private static void findBlock() {
		visited = new boolean[N][N]; //방문처리 초기화
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int block = map[i][j]; //찾을 블록 값
				
				if(visited[i][j] || block <= 0) //이미 방문했거나 특수 블록 또는 빈칸일 경우
					continue;
				
				ArrayList<int[]> bls = bfs(block, i, j); //bfs 진행하며 탐색한 블록들 반환
				if(bls.size() < 2) //그룹형성이 안 된 경우
					continue;
				
				if(bls.size() > blocks.size()) { //새로 찾은 그룹이 더 클 경우 리스트 변경
					blocks = bls;
					
				} else if (bls.size() == blocks.size()) { //그룹 크기가 같은 경우
					int curR = getRainbowCnt(blocks);
					int comR = getRainbowCnt(bls);
					
					if(comR > curR) { //무지개 블록이 더 많은걸로 변경
						blocks = bls;
						
					} else if(curR == comR) { //무지개 블록이 같다면 행, 열이 더 큰 그룹으로 변경
						int[] current = blocks.get(0);
						int[] compare = bls.get(0);
						
						if(compare[0] >= current[0]) {
							if(compare[0] == current[0]) {
								if(compare[1] > current[1])
									blocks = bls;						
							} else {
								blocks = bls;
							}
						}
					}
				}
			}
		}

		if(blocks.size() > 1)
			removeBlock();
	}
	
	/**
	 * 그룹의 무지개블록 개수 반환
	 * @param bl 그룹
	 * @return 무지개 블록 개수
	 */
	private static int getRainbowCnt(ArrayList<int[]> bl) {
		int cnt = 0;
		for(int[] b : bl) {
			if(map[b[0]][b[1]] == 0)
				cnt++;
		}
		
		return cnt;
	}
	
	/**
	 * 블록을 기준으로 그룹을 형성할 수 있는 블록을 BFS로 탐색 
	 * @param b 찾을 블록
	 * @param i 기준 행
	 * @param j 기준 열
	 * @return 블록 그룹 리스트
	 */
	private static ArrayList<int[]> bfs(int b, int i, int j) {
		ArrayList<int[]> bls = new ArrayList<>(); //해당 블록의 그룹 리스트
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i, j});
		
		rVisited = new boolean[N][N]; //무지개블록 방문처리 초기화
		visited[i][j] = true; //기준점 방문 처리
		
		while(!q.isEmpty()) {
			int[] position = q.poll();
			bls.add(position);
			
			for(int d=0; d<4; d++) {
				int r = position[0] + del[d][0];
				int c = position[1] + del[d][1];
				
				if(r>=0 && r<N && c>=0 && c<N && (!visited[r][c] && !rVisited[r][c]) 
						&& (map[r][c] == b || map[r][c] == 0)) {
					if(map[r][c] == 0)
						rVisited[r][c] = true;
					else
						visited[r][c] = true;
					
					q.add(new int[] {r, c});
				}
			}
		}
		
		return bls;
	}
	
	/**
	 * 블록그룹 지우기, 중력 및 회전 적용
	 */
	private static void removeBlock() {
		//블록 그룹 지우기
		for(int[] b : blocks) {
			map[b[0]][b[1]] = -2;
		}
		
		//중력적용
		dropBlock();
		
		//반시계 방향 90도 회전
		int[][] temp = new int[N][N];
		for(int j=N-1; j>=0; j--) {
			for(int i=0; i<N; i++) {
				temp[N-j-1][i] = map[i][j];
			}
		}
		map = temp;
		
		//중력적용
		dropBlock();
	}
	
	/**
	 * 중력적용하여 블록 옮기기
	 */
	private static void dropBlock() {
		for (int i = N-2; i >= 0; i--) { //가장 밑에서 두번째 줄부터
			for (int j = N-1; j >= 0; j--) {
				if(map[i][j] == -1) //검은블록일 경우 중력영향 x
					continue;
				
				int r = i;
				int c = j;
				
				while(true) {
					r += del[2][0]; //아래방향
					
					if(r<N && map[r][c] == -2) { //범위안에 있으며 빈칸일 경우 건너뛰기
						continue;
						
					} else { //바닥이거나 다른 블록을 만났을 경우 멈추기
						r -= del[2][0];
						
						if(r != i) { //한 칸도 못 움직였을 경우 제외
							map[r][c] = map[i][j];
							map[i][j] = -2;
						}
						
						break;
					}
				}
			}
		}
	}
}
