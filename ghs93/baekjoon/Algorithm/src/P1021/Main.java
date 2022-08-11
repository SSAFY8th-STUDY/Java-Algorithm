package P1021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 백준 1021. 회전하는 큐 - 실버 4
 * @author hoseong
 * @category 자료구조, 덱
 */
public class Main {
	static Deque<Integer> queue = new LinkedList<>(); //덱 사용

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); //큐의 크기
		int M = Integer.parseInt(st.nextToken()); //뽑아낼 수의 개수

		int[] find = new int[M]; //뽑아낼 숫자

		//큐 초기화 작업
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			find[i] = Integer.parseInt(st.nextToken());
		}

		int count = 0; //2, 3번 연산 횟수
		for (int i = 0; i < M; i++) {
			//큐의 첫 번째 요소가 뽑으려는 수이면 제거
			if (queue.peek() == find[i]) {
				queue.pop();

			} else {
				int left = leftCount(find[i]); //2번연산 - 왼쪽으로 돌렸을 경우 이동 횟수 
				int right = rightCount(find[i]); //3번연산 - 오른쪽으로 돌렸을 경우 이동 횟수

				//이동거리 비교 후 더 적은 쪽으로 회전 및 회전 수 누적  
				if (left > right) {
					count += right;
					turnRight(find[i]);

				} else {
					count += left;
					turnLeft(find[i]);
				}
			}
		}

		System.out.println(count);
	}

	private static int leftCount(int find) {
		//기존 큐 복사
		Deque<Integer> q = new LinkedList<>();
		q.addAll(queue);

		int count = 0;

		while (true) {
			count++;
			
			//왼쪽으로 회전
			int front = q.pollFirst();
			q.offerLast(front);

			if (q.peek() == find) {
				break;
			}
		}

		return count;
	}

	//기존 큐 왼쪽으로 회전
	private static void turnLeft(int find) {
		while (true) {
			int front = queue.pollFirst();
			queue.offerLast(front);

			if (queue.peek() == find) {
				queue.poll();
				break;
			}
		}
	}

	private static int rightCount(int find) {
		//기존 큐 복사
		Deque<Integer> q = new LinkedList<>();
		q.addAll(queue);

		int count = 0;

		while (true) {
			count++;
			
			//오른쪽으로 회전
			int rear = q.pollLast();
			q.offerFirst(rear);

			if (q.peek() == find) {
				break;
			}
		}

		return count;
	}

	//기존 큐 오른쪽으로 회전
	private static void turnRight(int find) {
		while (true) {
			int rear = queue.pollLast();
			queue.offerFirst(rear);

			if (queue.peek() == find) {
				queue.poll();
				break;
			}
		}
	}
}
