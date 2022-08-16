package P1644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 백준 1644. 소수의 연속합 - 골드 3
 * 
 * @author hoseong
 * @category 수학, 정수론, 투 포인터, 소수 판정, 에라토스테네스의 체
 */
public class Main {
	static ArrayList<Integer> arr = new ArrayList<>();
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 연속된 소수의 합
		getPrimeSum(); // 소수 구하기
		
		int r = 0, l = 0; //투 포인터
		int count = 0, sum = 0;
		int size = arr.size();
		
		while(r < size) { //오른쪽 포인터가 끝에 도달할 때 까지
			if(sum > N) { //누적합이 N보다 클 경우 왼쪽 값 빼고 포인터 옮기기
				sum -= arr.get(l++);
				
			} else if(sum < N) { //누적합이 N보다 작을 경우 오른쪽 값 더하고 포인터 옮기기
				sum += arr.get(r++);
				
			} else { //누적합과 N이 같을 경우 count 증가 후 왼쪽 값 빼고 포인터 옮기기
				count++;
				sum -= arr.get(l++);
			}
		}
		
		//마지막 소수의 값이 N과 같을 경우 count 증가 && size > 0 => 1일 경우
		if(size > 0 && arr.get(--r) == N)
			count++;
		
		System.out.println(count);
	}

	private static void getPrimeSum() {
		boolean[] selectedPrime = new boolean[N + 1];
		
		//에라토스테네스의 체
		selectedPrime[0] = selectedPrime[1] = true;
		for(int i=2; i<=N; i++) {
			if(!selectedPrime[i]) {
				for(int j=i*2; j<=N; j+=i) {
					selectedPrime[j] = true;
				}
			}
		}
			
		for(int i=2; i<=N; i++) {
			if(!selectedPrime[i])
				arr.add(i);
		}
	}
}