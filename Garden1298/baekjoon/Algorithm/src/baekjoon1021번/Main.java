package baekjoon1021번;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> nums = new LinkedList<>();
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int answer = 0;
		
		//queue에 인덱스 값 넣기
		for(int i = 1; i<= N; i++)
		{
			queue.add(i);
		}
		
		//찾을 인덱스 번호 입력아 nums에 넣기
		for(int i = 0; i<M; i++)
		{
			nums.add(sc.nextInt());
		}
		
		//nums에 값이 없을때까지 반복
		while(!nums.isEmpty())
		{
			int num = nums.poll();//이번에 찾을 인덱스 번호
			int count = 0;
			
			//다음 값이 num일때까지 반복
			while(num != queue.peek())
			{
				int front = queue.poll();//큐의 가장 앞 값을 빼서
				queue.add(front);//큐의 가장 뒤에 넣어준다.
				count++;
			}
			
			//1~num까지의 길이와 num~queue.size의 길이 중 작은 값 반환
			answer += Math.min(count, queue.size()-count);
			queue.poll();//해당 값 뽑아내기
		}
		System.out.println(answer);
	}
}
