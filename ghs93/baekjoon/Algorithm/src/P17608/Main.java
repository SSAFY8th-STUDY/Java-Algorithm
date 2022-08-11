package P17608;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 17608. 막대기 - 브론즈 2
 * @author hoseong
 * @category 구현, 자료구조, 스택
 */
public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P17608\\input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //막대기 수
		
		Stack<Integer> bar = new Stack<>();
		for(int i=0; i<N; i++) {
			bar.push(Integer.parseInt(br.readLine()));
		}
		
		int standard = bar.pop(); //맨 오른쪽 막대기
		int count = 1; //보이는 막대기 수
		while(!bar.isEmpty()) {
			int vs = bar.pop();
			if(vs > standard) {
				count++;
				standard = vs;
			}
		}
		
		System.out.println(count);
	}
}