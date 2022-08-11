package P17298;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 백준 17298. 오큰수 - 골드 4
 * @author hoseong
 * @category 자료구조, 스택
 */
public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src\\P17298\\input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<Integer> nums = new Stack<>(); //수열의 원소
		Stack<Integer> nge = new Stack<>(); //오큰수
		Stack<Integer> temp = new Stack<>(); //크기 비교를 위한 임시 스택
		
		while(st.hasMoreTokens())
			nums.push(Integer.parseInt(st.nextToken()));
		
		//맨 오른쪽 처리
		temp.push(nums.pop());
		nge.push(-1);
		
		for(int i=1; i<N; i++) {
			int ai = nums.pop(); //Ai번째 숫자
			
			while(!temp.isEmpty()) { //비교할 숫자가 있는동안
				int top = temp.peek(); //비교할 숫자 중 왼쪽 값
				
				if(top > ai) {
					nge.push(top);
					temp.push(ai);
					break;
					
				} else {
					temp.pop();
				}
			}
			
			if(temp.isEmpty()) { //비교할 숫자가 없는 경우
				nge.push(-1);
				temp.push(ai); //비교할 숫자를 해당 숫자로
			}
		}
		
		StringBuilder builder = new StringBuilder();
		while(!nge.isEmpty())
			builder.append(nge.pop()).append(' ');
		
		System.out.println(builder);
	}
}