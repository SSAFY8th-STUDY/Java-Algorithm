package P14425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Trie trie = new Trie();
		
		//insert
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			Trie childTrie = trie;
			for (int j = 0; j < str.length(); j++) {
				int idx = str.charAt(j) - 'a';
				
				if(childTrie.child[idx] == null) {
					childTrie.child[idx] = new Trie();
				}
				
				childTrie = childTrie.child[idx];
			}
			
			childTrie.isLast = true;
		}
		
		int cnt = 0;
		//equals
		mloop: for (int i = 0; i < M; i++) {
			String str = br.readLine();
			
			Trie childTrie = trie;
			for (int j = 0; j < str.length(); j++) {
				int idx = str.charAt(j) - 'a';
				
				if(childTrie.child[idx] == null)
					continue mloop;
				else
					childTrie = childTrie.child[idx];
			}
			
			if(childTrie.isLast)
				cnt++;
		}
		
		System.out.println(cnt);
	}
}

class Trie {
	Trie[] child;
	boolean isLast;
	
	public Trie() {
		child = new Trie[26];
		isLast = false;
	}
}
