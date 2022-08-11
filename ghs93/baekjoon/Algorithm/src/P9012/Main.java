package P9012;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 9012. 괄호 - 실버 4
 * @author hoseong
 * @category 자료구조, 문자열, 스택
 */
public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P9012\\input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		Stack<Character> stack = new Stack<>(); //여는 괄호를 저장할 스택
		loop: for(int i=0; i<T; i++) {
			char[] ps = br.readLine().toCharArray();
			
			if(ps.length%2 != 0) { //입력받은 괄호가 홀수일 경우 NO 출력
				System.out.println("NO");
				continue;
				
			} else {
				for(int j=0; j<ps.length; j++) {
					if(ps[j] == '(') //여는 괄호일 경우 스택에 저장
						stack.push(ps[j]);
					else {
						if(stack.isEmpty()) { //스택이 비었다면 NO 출력
							System.out.println("NO");
							continue loop;
						}
						//스택에는 여는 괄호만 있기때문에 굳이 비교할 필요 없음
						stack.pop();
					}
				}
				
				//모든 여는 괄호를 사용했으면 YES출력 아닐 경우 NO 출력
				if(stack.isEmpty())
					System.out.println("YES");
				else
					System.out.println("NO");
			}
			
			stack.clear();
		}
	}
}