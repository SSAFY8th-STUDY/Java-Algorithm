package P14465;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 14465. 소가 길을 건너간 이유 5 - 실버 2
 * @author hoseong
 * @category 누적 합, 슬라이딩 윈도우
 */
public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P14465\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 총 신호등 수
		int K = Integer.parseInt(st.nextToken()); // 연속해야하는 신호등의 수
		int B = Integer.parseInt(st.nextToken()); // 고장난 신호등 수
		
		int x = 0; //범위 내 고장난 신호등 수
		Queue<Boolean> window = new LinkedList<>(); //신호등 범위
		
		boolean[] fixs = new boolean[N+1]; //전체 신호등 상태 - true: 고장, false: 정상
		for(int i=0; i<B; i++) {
			int index = Integer.parseInt(br.readLine());
			fixs[index] = true;
		}
		
		// 1~K번째 까지 연속해야하는 신호등의 상태 및 고장난 신호등 수 파악
		for(int i=1; i<=K; i++) {
			boolean f = fixs[i];
			window.offer(f);
			
			if(f)
				x++;
		}
		
		//최소한의 고장난 수
		int count = x;
		for(int i=2; i<=N-K+1; i++) {
			boolean front = window.poll();
			if(front)
				x--;
			
			boolean rear = fixs[i+K-1];
			if(rear)
				x++;
			
			if(count > x)
				count = x;
		}
		
		System.out.println(count);
	}
}