package baekjoon17508;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();//막대기의 개수
		Stack<Integer> stick = new Stack<>();//막대기의 높이를 나타내는 스택
		int answer = 0;
		
		for(int i = 0; i<N; i++)
		{
			stick.add(sc.nextInt());
		}
		
		int beforeStick = 0;//이전 막대기 중 가장 높이가 높은 막대기의 높이
		for(int i = 0; i<N; i++)
		{
			int currentStick = stick.pop();//현재 막대기의 높이
			if(beforeStick<currentStick)//현재 막대기가 더 높다면
			{
				answer++;//답에 1더하기
				beforeStick = currentStick;//이전 최장 막대기 업데이트
			}
		}
		
		System.out.println(answer);
	}

}
