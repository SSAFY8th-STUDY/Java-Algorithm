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
		
		Stack<Character> stack = new Stack<>();
		loop: for(int i=0; i<T; i++) {
			char[] ps = br.readLine().toCharArray();
			
			if(ps.length%2 != 0) {
				System.out.println("NO");
				continue;
				
			} else {
				for(int j=0; j<ps.length; j++) {
					if(ps[j] == '(')
						stack.push(ps[j]);
					else {
						if(stack.isEmpty()) {
							System.out.println("NO");
							continue loop;
						}
						
						stack.pop();
					}
				}
				
				if(stack.isEmpty())
					System.out.println("YES");
				else
					System.out.println("NO");
			}
			
			stack.clear();
		}
	}
}