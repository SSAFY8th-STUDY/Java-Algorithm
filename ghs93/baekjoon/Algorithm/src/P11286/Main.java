package P11286;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 백준 11286. 절댓값 힙 - 실버 1
 * 
 * @author hoseong
 * @category 자료구조, 우선순위 큐
 */
class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P11286\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 막대기 수

		PriorityQueue<Integer> posQueue = new PriorityQueue<>(); //양수 우선순위 큐
		PriorityQueue<Integer> negQueue = new PriorityQueue<>(Collections.reverseOrder()); //음수 우선순위 큐

		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());

			if (input > 0) { //입력값이 양수일 경우
				posQueue.offer(input);

			} else if (input < 0) { //입력값이 음수일 경우
				negQueue.offer(input);

			} else {
				if (negQueue.isEmpty() && posQueue.isEmpty()) {
					//모든 큐가 비었을 경우
					System.out.println(0);

				} else if (negQueue.isEmpty()) {
					//음수 큐가 비었을 경우
					System.out.println(posQueue.poll());
					
				} else if (posQueue.isEmpty()) {
					//양수 큐가 비었을 경우
					System.out.println(negQueue.poll());
					
				} else {
					//모든 큐에 값이 있을 경우
					int neg = Math.abs(negQueue.peek()); //음수 큐의 절댓값
					int pos = Math.abs(posQueue.peek()); //양수 큐의 절댓값

					if (neg == pos || neg < pos) { //절댓값이 같거나 음수 큐의 절댓값이 작을 경우 음수 큐 값 출력 및 큐에서 제거
						System.out.println(negQueue.poll());

					} else {
						System.out.println(posQueue.poll());
					}
				}
			}
		}
	}
}
