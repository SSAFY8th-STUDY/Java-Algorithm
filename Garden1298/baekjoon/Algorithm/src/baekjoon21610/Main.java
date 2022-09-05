package baekjoon21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Fireball
	{
		int posX, posY, mass, dir, speed;

		public Fireball(int r, int c, int m, int d, int s) {
			super();
			this.posX = r;
			this.posY = c;
			this.mass = m;
			this.dir = d;
			this.speed = s;
		}

		@Override
		public String toString() {
			return "Fireball [posX=" + posX + ", posY=" + posY + ", value=" + mass + ", dir=" + dir + ", speed="
					+ speed + "]";
		}
	}
	
	static int N, M, K;
	static int r, c, m, d, s;
	static int map[][];
	static int direction[][] = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};//0번부터 7번
	
	static List<Fireball> fireballs = new ArrayList<>();

	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//맵의 가로 세로 크기(2 ≤ N ≤ 50)
		M = Integer.parseInt(st.nextToken());//파이어볼의 개수(1 ≤ M ≤ 100)
		K = Integer.parseInt(st.nextToken());//명령의 수(1 ≤ M ≤ 100)
		
		map = new int[N][N];
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;//r행(1 ≤ r, c ≤ N)
			c = Integer.parseInt(st.nextToken())-1;//c열(1 ≤ r, c ≤ N)
			m = Integer.parseInt(st.nextToken());//질량(1 ≤ m ≤ 1,000)
			s = Integer.parseInt(st.nextToken());//속력(1 ≤ s ≤ 1,000)
			d = Integer.parseInt(st.nextToken());//방향(0 ≤ d ≤ 7)
			
			fireballs.add(new Fireball(r, c, m, d, s));
			map[r][c]+=1;
		}
		
		for(int test = 0; test<fireballs.size(); test++)
		{
			fireballs.get(test).toString();
		}

		for(int i = 0; i < K; i++)
		{
			for(int test = 0; test<fireballs.size(); test++)
			{
				fireballs.get(test).toString();
			}
			
			moveFireball();//파이어볼 이동
			combineFireball();//파이어볼 합치기	
			
		}
		
		System.out.println(sum());
	}
	
//	private static void print()
//	{
//		System.out.println("<=====맵 출력=====>");
//		for(int i = 0; i < N; i++)
//		{
//			for(int j = 0; j < N; j++)
//			{
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
	
	private static int sum()
	{
		int sum = 0;
		
		for(int i = 0; i<fireballs.size(); i++)
		{
			sum+=fireballs.get(i).mass;
		}

		return sum;
	}

	private static void combineFireball() 
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(map[i][j]>=2)//파이어볼이 2보다 크거나 같다면
				{
					int newMass  = 0;
					int newSpeed = 0;
					int samePosFireballCnt = 0;
					
					int oddEven = -1;//0이면 짝수 1이면 홀수
					boolean sameDirection = true;
					
					for(int k = fireballs.size()-1; k>=0; k--)
					{
						Fireball fireball = fireballs.get(k);
						
						if(fireball.posX == i && fireball.posY == j)//같은 칸에 있는 파이어볼 찾기
						{
							samePosFireballCnt+=1;
							newMass += fireball.mass;
							newSpeed += fireball.speed;
							
							if(sameDirection)//합쳐진 파이어볼의 방향이 계속 같았다면 더하기
							{
								if(oddEven == -1)//가장 처음 발견한 파이어볼의 방향 넣기
								{
									oddEven = fireball.dir%2;
								}
								else
								{
									int checkOE = fireball.dir%2;
									
									if(oddEven!=checkOE)//방향이 다르다면 false
									{
										sameDirection = false;
									}
								}
							}
							fireballs.remove(fireball);//합친 파이어볼 없애기
						}
					}
					
					newMass = newMass/5;//질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다
					newSpeed = newSpeed/samePosFireballCnt;//속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
					
					if(newMass == 0)//질량이 0인 파이어볼은 소멸되어 없어진다.
					{
						map[i][j] = 0;
						continue;
					}

					map[i][j] = 4;//파이어볼은 4개의 파이어볼로 나누어진다.
					
					if(sameDirection)//합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면
					{
						for(int dir = 0; dir <= 6; dir += 2)//방향은 0, 2, 4, 6
						{
							fireballs.add(new Fireball(i, j, newMass, dir, newSpeed));
						}
					}
					else
					{
						for(int dir = 1; dir <= 7; dir += 2)//방향은 1, 3, 5, 7
						{
							fireballs.add(new Fireball(i, j, newMass, dir, newSpeed));
						}
					}
				}
			}
		}
		//print();
	}


	private static void moveFireball() 
	{
		for(int i = 0; i < fireballs.size(); i++)
		{
			Fireball fireball = fireballs.get(i);
			
			int nextX = fireball.posX + ( direction[fireball.dir][0] * fireball.speed );
			int nextY = fireball.posY + ( direction[fireball.dir][1] * fireball.speed );
		
			//맵을 넘어갔다면
			if(nextX<0 || nextX>=N || nextY<0 || nextY>=N)
			{
				nextX = connectMap(nextX);
				nextY = connectMap(nextY);
			}
			
			map[fireball.posX][fireball.posY] -=1;
			
			fireball.posX = nextX;
			fireball.posY = nextY;
			
			map[fireball.posX][fireball.posY] +=1;
		}
		//print();
	}
	
	static int connectMap(int position)
	{
		if(position<0)
		{
			while(position<0)
			{
				position = position + N;
			}
		}
		else if(position>=N)
		{
			while(position>=N)
			{
				position = position - N;
			}
		}
		return position;
	}

}
