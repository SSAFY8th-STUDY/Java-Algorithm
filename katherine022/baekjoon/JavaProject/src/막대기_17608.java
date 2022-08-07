import java.util.*;
import java.io.*;

public class 막대기_17608 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		Stack<Integer> height = new Stack<>();
		for(int i = 0; i<N; i++) {
			height.add(scan.nextInt());
		}
		
		int max = height.pop();
		
		int answer = 1;
		while(!height.isEmpty()) {
			if(max < height.peek()) {
				max = height.peek();
				answer++;
			}
			height.pop();
		}
		System.out.println(answer);
	}

}
