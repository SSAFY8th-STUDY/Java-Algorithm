package baekjoon17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Dust
	{
		int posX, posY;
		int Value;
		int spreadCnt = 0;
		
		public Dust(int posX, int posY, int value) {
			super();
			this.posX = posX;
			this.posY = posY;
			Value = value;
		}
		
		public void calculateLeftDust()
		{
			Value = Value - (spreadCnt * ( Value / 5 ));
		}
		
		public void addDust(int dust)
		{
			Value += dust;
		}
	}
	
	static class Purifier
	{
		int posX;
		int posY;
		
		public Purifier(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
	}
	
	static int N,M;
	static int dustMap[][];
	static int direction[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int directionPurifierUp[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int directionPurifierDown[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	static List<Dust> dustList = new ArrayList<>();
	static Purifier purifierUp = null;
	static Purifier purifierDown = null;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int time = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j <M; j++)
			{
				int value = Integer.parseInt(st.nextToken());
				
				if(value == -1)
				{
					if(purifierUp == null)
					{
						purifierUp = new Purifier(i, j);						
					}
					else
					{
						purifierDown = new Purifier(i, j);	
					}
				}
				else if (value != 0)
				{
					dustList.add(new Dust(i, j, value));
				}			
			}
		}
		
		for(int i = 0; i<time; i++)
		{
			spreadDust();
		}
		
		System.out.println(sumDust(dustMap));		
	}

	private static void spreadDust() {
		
		dustMap = new int[N][M];//퍼진 먼지 정보를 담은 맵
		
		for(int i = 0; i<dustList.size();i++)
		{
			Dust currentDust = dustList.get(i);
			
			if(currentDust.Value < 5) continue;//먼지가 5보다 작다면 나누어지지않는다
			
			int spreadValue = currentDust.Value/5;
			
			for(int dir = 0; dir<direction.length; dir++)
			{
				int nextA = currentDust.posX + direction[dir][0];
				int nextB = currentDust.posY + direction[dir][1];
				
				if(nextA<0 || nextA >=N || nextB<0 || nextB>=M) continue;
				if(purifierUp.posX == nextA && purifierUp.posY == nextB) continue;//청정기가 있는 자리라면 나가기
				if(purifierDown.posX == nextA && purifierDown.posY == nextB) continue;//청정기가 있는 자리라면 나가기
				
				dustMap[nextA][nextB] += spreadValue;
				currentDust.spreadCnt+=1;
			}
			
			currentDust.calculateLeftDust();//남은 먼지양 계산
		}

		for(int i = 0; i<dustList.size(); i++)
		{
			Dust dust = dustList.get(i);
			
			dustList.get(i).addDust(dustMap[dust.posX][dust.posY]);
			dustMap[dust.posX][dust.posY] = dust.Value;
		}
		
		rotate(true, dustMap, directionPurifierUp, 0, 0, purifierUp.posX);
		rotate(false, dustMap, directionPurifierDown, purifierDown.posX, purifierDown.posY, N-1);
		
		findDust(dustMap);
	}
	
	static void rotate(boolean isUp, int map[][], int direction[][], int startA, int startB, int leftDown)
	{
		int upPosA = startA;
		int upPosB = startB;
		int dir = 0;
		int startValue = map[upPosA][upPosB];
	
		while(dir < 4)
		{
			int nextUpPosA = upPosA + direction[dir][0];
			int nextUpPosB = upPosB + direction[dir][1];
			
			if(nextUpPosA < startA || nextUpPosA > leftDown || nextUpPosB < 0 || nextUpPosB >= M)
			{
				dir++;
				continue;
			}
			
			map[upPosA][upPosB] = map[nextUpPosA][nextUpPosB];//당기기
			
			upPosA = nextUpPosA;
			upPosB = nextUpPosB;
		}
		
		if(isUp)
		{
			map[upPosA+1][upPosB] = startValue;	
			map[purifierUp.posX][purifierUp.posY] = 0;
		}
		else
		{
			map[upPosA][upPosB+1] = startValue;	
			map[purifierDown.posX][purifierDown.posY] = 0;
		}
	}
	
	private static void findDust(int map[][]) 
	{
		dustList = new ArrayList<>();
		
		for(int i = 0; i<N; i++)
		{
			for(int j = 0; j<M; j++)
			{
				if(map[i][j]!=0)
				{
					dustList.add(new Dust(i, j, map[i][j]));
				}
			}
		}
		
	}
	
	static int sumDust(int map[][])
	{
		int sum = 0;
		
		for(int i = 0; i<N; i++)
		{
			for(int j = 0; j<M; j++)
			{
				sum += map[i][j];
			}
		}
		
		return sum;
	}
	
	
}
