package Project2034;

import java.util.Scanner;

public class MinerGame {

	static int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] map;
	static int N=0;
	static int x=0,y=0;
	static int rocks = 0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		int[][] dynamite = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
		
		 for(int i = 0; i<N; i++)
		 {
			 String tmp = sc.next();
			 for(int j = 0; j<tmp.length(); j++)
			 {
				 map[i][j] = tmp.charAt(j)-'0';
				 if(map[i][j]==0)
				 {
					 x=i;y=j;
				 }
			 }
		 }
		 
		 int M = sc.nextInt();
		 
		 for(int i = 0; i<M; i++)
		 {
			 char move = sc.next().charAt(0);//U:상 D:하 L:좌 R:우 X:다이너마이트
			 
			 if(move == 'U')
			 {
				 doMining(0);
			 }
			 else if(move == 'D')
			 {
				 doMining(1);
			 }
			 else if(move == 'L')
			 {
				 doMining(2);
			 }
			 else if(move == 'R')
			 {
				 doMining(3);
			 }
			 else if(move == 'X')
			 {
				 for(int j =0; j<dynamite.length;j++)
				 {
					 int a = x + dynamite[j][0];
					 int b = y + dynamite[j][1];
					 
					 if(a>=0&&a<N&&b>=0&&b<N&&map[a][b]>0)
					 {
						 rocks +=1;
						 map[a][b]=0;
					 }
				 }
			 }
		 }	
		 
		 System.out.println("광부 위치 : " + "(" + x + "," + y + ")");
		 System.out.println("부순 암석 개수 : " + rocks);
	}
	
	public static void doMining(int d)
	{
		 int a = x + direction[d][0];
		 int b = y + direction[d][1];
		 
		 if(a>=0&&a<N&&b>=0&&b<N&&map[a][b]>0)
		 {
			 map[a][b]-=1;
			 if(map[a][b]==0)
			 {
				 rocks+=1;
			 }
		 }
		 if(map[a][b]==0)
		 {
			 x = a;
			 y = b;
		 }
	}
}
