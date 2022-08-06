import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class 괄호_9012 {

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		Scanner scan = new Scanner(System.in);
		Stack<Character> stack;
		boolean answer = true;
		int T = scan.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			char[] p = scan.next().toCharArray();
			stack = new Stack<>();
			
			answer = true;
			for(char ch : p) {
				if(ch == '(') {
					stack.add('(');
				} else if(ch == ')') {
					if(!stack.isEmpty()) stack.pop();
					else {
						answer = false;
						break;
					}
				}
			}
			if(!stack.isEmpty()) answer = false;
			System.out.println(answer?"YES" : "NO");
			
		}
	}

}
