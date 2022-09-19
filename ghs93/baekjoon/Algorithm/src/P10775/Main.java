package P10775;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 백준 10775. 공항 - 골드 2
 * @author hoseong
 * @category 그리디, 분리 집합
 */
public class Main {
	static int G, P, gates[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine()); //게이트 수
		P = Integer.parseInt(br.readLine()); //비행기 수
		
		gates = new int[G+1];
		for (int i = 1; i <= G; i++) {
			gates[i] = i;
		}
		
		int docking = 0;
		for (int i = 0; i < P; i++) {
			int plane = Integer.parseInt(br.readLine());
			int gate = find(plane);
			
			if(gate == 0) //비행기를 도킹할 게이트가 없을 경우
				break;
			
			docking++;
			union(gate-1, gate); //비행기가 도킹할 경우 해당 게이트를 앞 게이트의 집합에 포함시킨다.
		}
		
		System.out.println(docking);
	}
	
	static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
		
		if(ar == br) return;
		
		gates[br] = ar;
	}
	
	static int find(int a) {
		if(gates[a] == a)
			return a;
		
		return gates[a] = find(gates[a]);
	}
}
