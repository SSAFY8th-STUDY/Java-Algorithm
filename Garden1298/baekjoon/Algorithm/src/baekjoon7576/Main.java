package baekjoon7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Tomato
	{
		int posX;
		int posY;
		int date = 0;
		
		public Tomato(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
		
		public Tomato(int posX, int posY, int date) {
			super();
			this.posX = posX;
			this.posY = posY;
			this.date = date;
		}
	}
	
	static int box[][];
	static int direction[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Tomato> queue = new LinkedList<>();
	static int N, M, unriped = 0, date = 0;

	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());//세로
		N = Integer.parseInt(st.nextToken());//가로
		box = new int[N][M];
		
		for(int i = 0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++)
			{
				int input = Integer.parseInt(st.nextToken());
				if(input == 1)
				{
					queue.offer(new Tomato(i, j));
				}
				else if(input == 0)
				{
					unriped++;
				}
				box[i][j] = input;
			}
		}
		
		bfs();
		if(unriped==0)
		{
			System.out.println(date);			
		}
		else
		{
			System.out.println("-1");
		}
		
	}

	private static void bfs() 
	{
		while(!queue.isEmpty())
		{
			Tomato currentTomato = queue.poll();
			
			int a = currentTomato.posX;
			int b = currentTomato.posY;
			
			for(int i = 0; i<direction.length; i++)
			{
				if(date != currentTomato.date)
				{
					date++;
				}
				
				int A = currentTomato.posX + direction[i][0];
				int B = currentTomato.posY + direction[i][1];
				
				if(A<0 || A>=N || B<0 || B>= M) continue;
				
				if(box[A][B] == 0)
				{
					box[A][B] = 1;
					queue.offer(new Tomato(A,B, date+1));
					unriped--;
				}
			}
		}
	}
}
