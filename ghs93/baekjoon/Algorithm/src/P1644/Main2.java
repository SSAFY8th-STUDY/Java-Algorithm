package P1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main2 {
	static boolean prime[];
	static ArrayList<Integer> prime_numbers = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
   
   		// 1. 소수 구하기
        prime = new boolean[N+1];        
        prime[0] = prime[1] = true;       
        for(int i=2; i*i<=N; i++){
            if(!prime[i]) for(int j=i*i; j<=N; j+=i) prime[j]=true;                
        }            
        for(int i=1; i<=N;i++){
        	if(!prime[i]) prime_numbers.add(i);     
        }
        
        // 2. 연속합으로 주어진 정수 구할 수 있는지 판별
        int start=0, end=0, sum=0, count=0;
        while(true) {
        	if(sum >= N ) sum -= prime_numbers.get(start++);
        	else if(end == prime_numbers.size()) break;
        	else sum += prime_numbers.get(end++);       	
        	if(N==sum) count++;        	
        }		
        System.out.println(count);
	}
}
