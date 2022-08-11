import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스택_10828 {

	public static void main(String[] args) throws IOException, FileNotFoundException{
		// TODO Auto-generated method stub
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [] stack = new int[10001];
		int size = -1;
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i<N; i++) {
			String[] command = br.readLine().split(" ");
			
			switch(command[0]) {
			case "push" :
				int n = Integer.parseInt(command[1]);
				stack[++size] = n;
				break;
			case "pop" :
				if(size == -1) System.out.println(-1);
				else System.out.println(stack[size--]);
				break;
			case "size" :
				System.out.println(size+1);
				break;
			case "empty" :
				if(size == -1) System.out.println(1);
				else System.out.println(0);
				break;
			case "top" :
				if(size == -1) System.out.println(-1);
				else System.out.println(stack[size]);
				break;
			}
			
		}
	}

}