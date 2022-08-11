package baekjoon11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Abs implements Comparable<Abs>
{
	int abs;
	int original;
	
	Abs(int abs, int original)
	{
		this.abs = abs;
		this.original = original;
	}

	public int getOriginal() {
		return original;
	}
	
	@Override
	public int compareTo(Abs o) {
		
		if(this.abs<o.abs)//절대값이 더 작으면
		{
			return -1;
		}
		else if (this.abs==o.abs)//절대값이 같으면
		{
			if(this.original<o.original)//원본값 비교
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
		else if (this.abs>o.abs)//절대값이 더 크면
		{
			return 1;
		}
		
		return 1;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Abs> pq = new PriorityQueue<>();
		
		for(int i = 0; i<N; i++)
		{
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0)//받은 값이 0이라면
			{
				if(pq.isEmpty())//우선순위 큐에 값이 없다면
				{
					System.out.println("0");//0을 출력한다.
				}
				else//값이 있다면
				{
					Abs abs = pq.poll();//값을 poll해서 저장한다
					int output = abs.getOriginal();//저장한 값에 들어있는 원본값을 저장한다.
					System.out.println(output);//나온 값을 출력한다.
				}
			}
			else//0이 아니라면
			{
				pq.add(new Abs(Math.abs(num),num));//절대값과 원본값을 저장한다.
			}
		}
	}
}
