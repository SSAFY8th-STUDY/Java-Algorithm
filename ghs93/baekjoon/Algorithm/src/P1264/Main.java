package P1264;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 백준 1264. 모음의 개수 - 브론즈 4
 * @author hoseong
 * @category 구현, 문자열
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Character> arr = new ArrayList<>();
		arr.add('a');
		arr.add('e');
		arr.add('i');
		arr.add('o');
		arr.add('u');
		arr.add('A');
		arr.add('E');
		arr.add('I');
		arr.add('O');
		arr.add('U');
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str = br.readLine();
			if(str.equals("#"))
				break;
			
			int cnt = 0;
			for (int i = 0, size = str.length(); i < size; i++) {
				if(arr.contains(str.charAt(i))) {
					cnt++;
				}
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.println(sb);
	}
}
