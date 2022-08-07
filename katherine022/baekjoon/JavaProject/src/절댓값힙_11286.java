import java.util.*;
import java.io.*;

public class 절댓값힙_11286 {
	static int []arr = new int[100001];
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("input.txt"));
		Scanner scan = new Scanner(System.in);
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 만일 2차원 배열의 첫 번째 원소가 같다면, 2번째 원소를 기준으로 오름차순 정렬한다.
				if(o1[0] == o2[0]) {
					return Integer.compare(arr[o1[1]], arr[o2[1]]);
				}
				// 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬한다.
				return Integer.compare(o1[0], o2[0]);
			}
		});
		int N = scan.nextInt();
		
		int idx = 0;
		
		for(int i = 0; i<N; i++) {
			int tmp = scan.nextInt();
			if(tmp != 0) {
				pq.add(new int[] {Math.abs(tmp), idx});
				arr[idx++] = tmp;
			} else {
				if(pq.isEmpty()) {
					System.out.println(0);
					continue;
				}
				
				System.out.println(arr[pq.poll()[1]]);
			}
		}
	}

}
