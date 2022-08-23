package baekjoon17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M = 0, D;
	static int map[][];
	static List<Archer> archer = new ArrayList<>();
	static List<Enemy> enemy = new ArrayList<>();
	static boolean isVisited[];
	static int maxRemovedEnemy = 0;

	static class Archer
	{
		int posX;
		int posY;
		int range;
		private Enemy closestEnemy = null;
		
		public Archer(int posX, int posY, int range) 
		{
			super();
			this.posX = posX;
			this.posY = posY;
			this.range = range;
		}
		
		public Enemy findClosestEnemy(List<Enemy> dupEnemy)
		{
			int minDistance = Integer.MAX_VALUE;
			
			for(int i = 0; i< dupEnemy.size(); i++)
			{
				int distance = calculateDistance(dupEnemy.get(i).posX, dupEnemy.get(i).posY);
				
				if(isReachable(distance))
				{
					if(distance == minDistance)
					{
						if(dupEnemy.get(i).posY < closestEnemy.posY)
						{
							closestEnemy = dupEnemy.get(i);
						}
					}
					else if(distance < minDistance)
					{
						closestEnemy = dupEnemy.get(i);
						minDistance = distance;
					}
				}
			}
			
			if(closestEnemy != null)
			{
				Enemy returnEnemy = closestEnemy;
				closestEnemy = null;
				return returnEnemy;
			}
			else
			{
				return null;
			}
		}
		
		public int calculateDistance(int enemyX, int enemyY)
		{
			int distance = Math.abs(posX - enemyX) + Math.abs(posY - enemyY);
			
			return distance;
		}
		
		public boolean isReachable(int distance)
		{
			if(distance > range)
			{
				return false;
			}
			else
			{
				return true;				
			}
		}
	}
	
	static class Enemy
	{
		int posX;
		int posY;
		
		public Enemy(int posX, int posY) 
		{
			super();
			this.posX = posX;
			this.posY = posY;
		}
		
		public void move()
		{
			posX+=1;
		}
	}
	
	static int archerPerm[] = new int[3];
	public static void perm(int cnt, int start)
	{
		if(cnt == 3)
		{
			//System.out.println(Arrays.toString(archerPerm));
			
			archer = new ArrayList<>();
			List<Enemy> dupEnemy = new ArrayList<>();
			int removedEnemy = 0;
			
			for(int i = 0; i<enemy.size(); i++)
			{
				
				dupEnemy.add(new Enemy(enemy.get(i).posX,enemy.get(i).posY));
			}
			
			for(int i = 0; i<3; i++)
			{
				archer.add(new Archer(N, archerPerm[i], D));
			}
			
			while(dupEnemy.size()>0)
			{
				Set<Enemy>enemy2Attack = new HashSet<>();

				for(int i = 0; i< 3; i++)
				{
					Enemy e = archer.get(i).findClosestEnemy(dupEnemy);
					if(e != null)
					{
						enemy2Attack.add(e);						
					}
				}
				
				List<Enemy> removeEnemy = new ArrayList<>(enemy2Attack);
				for(int i = 0; i<removeEnemy.size(); i++)
				{
					dupEnemy.remove(removeEnemy.get(i));
					removedEnemy+=1;
				}
				
				for(int i = dupEnemy.size()-1; i>=0; i--)
				{
					dupEnemy.get(i).move();
					if(dupEnemy.get(i).posX>=N)
					{
						dupEnemy.remove(dupEnemy.get(i));
					}
				}
			}
			
			maxRemovedEnemy = Math.max(maxRemovedEnemy, removedEnemy);
			
			return;
		}
		
		for(int i = start; i<M; i++)
		{
			archerPerm[cnt] = i;
			perm(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//행의 수 N
		M = Integer.parseInt(st.nextToken());//열의 수 M
		D = Integer.parseInt(st.nextToken());//궁수의 공격 거리 제한 D
		isVisited = new boolean[N];
		
		map = new int[N+1][M];
		for(int i = 0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++)
			{
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				if(input == 1)
				{
					enemy.add(new Enemy(i, j));
				}
			}
		}
		
		perm(0, 0);
		
		System.out.println(maxRemovedEnemy);
	}
}
