package baekjoon2615;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int[][] map = new int[20][20];
		int[][] direction = {{-1,1},{0,1},{1,1},{1,0}};
		
		for(int i = 1; i<20; i++)
		{
			for(int j = 1; j<20; j++)
			{
				map[i][j] = in.nextInt();
			}
		}
		
		for(int i = 1; i<20; i++)
		{
			for(int j = 1; j<20; j++)
			{
				int color = map[i][j];
				
				if(color == 1||color == 2)
				{
					for(int k = 0; k<direction.length;k++)
					{
						int count = 1;
						int x = i;
						int y = j;
						
						while(true)
						{
							x += direction[k][0];
							y += direction[k][1];
							
							if(x>0&&x<20&&y>0&&y<20&&map[x][y]==color)
							{
								count++;
							}
							else
							{
								break;
							}
						}
						
						if(count == 5)
						{
							if(map[i+(direction[k][0]*-1)][j+(direction[k][1]*-1)]==color)
							{
								break;
							}
							
							System.out.println(color);
							System.out.println(i+" "+j);
							return;
						}
					}
				}
			}
		}
		System.out.println("0");
	}
}
