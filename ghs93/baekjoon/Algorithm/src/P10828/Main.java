package P10828;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 백준 10828. 스택 - 실버 4
 * 
 * @author hoseong
 * @category 자료구조, 스택
 */
public class Main {
	static int[] stack; //스택으로 사용할 배열
	static int size = 0; //스택의 크기

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P10828\\input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		stack = new int[N]; //스택의 최대 입력수를 배열의 크기로 지정
		for (int i = 0; i < N; i++) {
			String command = br.readLine();
			
			switch (command) {
			case "pop":
				System.out.println(pop());

				break;

			case "size":
				System.out.println(size);

				break;

			case "empty":
				System.out.println(empty());

				break;

			case "top":
				System.out.println(top());

				break;

			default: //push
				push(Integer.parseInt(command.split(" ")[1]));
				break;
			}
		}
	}

	private static void push(int i) {
		//스택에 데이터  push후 size 증가
		stack[size++] = i;
	}

	private static int pop() {
		//size 감소 후 스택 값 출력
		return size == 0 ? -1 : stack[--size];
	}

	private static int empty() {
		return size > 0 ? 0 : 1;
	}

	private static int top() {
		return size == 0 ? -1 : stack[size - 1];
	}
}
