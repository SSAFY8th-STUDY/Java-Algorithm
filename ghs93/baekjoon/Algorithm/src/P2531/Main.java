package P2531;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2531. 회전초밥 - 실버 1
 * @author hoseong
 * @category 투 포인터
 */
public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P2531\\input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 총 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		int[] cb = new int[d+1]; //선택된 초밥을 나타내기 위한 배열
		int[] choBab = new int[n];
		int count = 0;
		
		for(int i=0; i<n; i++) {
			int dish = Integer.parseInt(br.readLine());
			choBab[i] = dish;
			
			if(i<k) {
				if(cb[dish] == 0)
					count++;
				
				cb[dish]++;
			}
		}
		
		int max = cb[c] == 0 ? count + 1 : count;
		for(int i=0; i<n-1; i++) {
			int previous = choBab[i]; //뺄 값
			int next = choBab[(i+k) % n]; //넣을 값
			
			cb[previous]--;
			cb[next]++;
			
			if(cb[previous] == 0) { //제거한 위치의 접시가 없으면 수량 감소
				count--;
			}
			
			if(cb[next] == 1) { //더한 위치의 접시가 새로 추가된 거면 수량 증가
				count++;
			}
			
			int temp = cb[c] == 0 ? count + 1 : count;
			if(max < temp)
				max = temp;
		}
		
		System.out.println(max);
	}
}