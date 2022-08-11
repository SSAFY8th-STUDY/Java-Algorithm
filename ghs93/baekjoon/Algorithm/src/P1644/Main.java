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
		getPrimeSum();
		
		int r = 0, l = 0;
		int count = 0, sum = 0;
		int size = arr.size();
		
		while(r < size) {
			if(sum > N) {
				sum -= arr.get(l++);
				
			} else if(sum < N) {
				sum += arr.get(r++);
				
			} else {
				count++;
				sum -= arr.get(l++);
			}
		}
		
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