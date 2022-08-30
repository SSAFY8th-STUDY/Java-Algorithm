//package baekjoon2206;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class Main {
//
//	static class Block
//	{
//		boolean isHit;
//		int x;
//		int y;
//		int count;
//		public Block(boolean isHit,int x,int y,int count) {
//			this.x=x;
//			this.y=y;
//			this.isHit=isHit;
//			this.count=count;
//		}
//	}
//
//	public static void main(String arg[]) {
//		Scanner input =new Scanner(System.in);
//		int n=input.nextInt();
//		int m=input.nextInt();
//		int map[][]=new int[n+1][m+1];
//		for(int i=1;i<=n;i++) 
//		{
//			String c=input.next();
//			for(int j=1;j<=m;j++) 
//			{
//				map[i][j]=c.charAt(j-1)-'0';
//			}
//		}
//		
//		Queue<Block> q = new LinkedList<>();
//		
//		q.offer(new Block(false,1,1,1));
//		
//		int ax[]= {1,-1,0,0};
//		int ay[]= {0,0,1,-1};
//
//		int vs=0;
//		int v[][]=new int[n+1][m+1];
//		for(int i=1;i<=n;i++) {
//
//			for(int j=1;j<=m;j++) 
//			{
//				v[i][j]=0;
//			}
//		}
//		
//		v[1][1]=1;
//		int zx=0; 
//		int zy=0;
//		int count=1;
//		
//		while(!q.isEmpty()) 
//		{
//			for(int j=0;j<q.size();j++) 
//			{
//				int x=q.element().x;
//				int y=q.element().y;
//				boolean wall=q.element().isHit;
//				count=q.element().count;
//			
//				q.poll();
//			
//				if(x==n&&y==m) 
//				{
//					vs=count;
//					break;
//				}
//				
//				for(int i=0;i<4;i++) 
//				{
//					zx= x+ax[i];
//					zy=y+ay[i];
//					if(zx>0 && zx<=n && zy>0 && zy<=m ) 
//					{
//						
//						if(map[zx][zy]==1&&wall==false&& v[zx][zy]==0)
//						{
//							System.out.println(zx+" "+zy+" "+(count+1));
//							q.offer(new Block(true,zx,zy,count+1));
//							v[zx][zy]=(count+1);
//							
//						}
//						
//						if(map[zx][zy]==1&&wall==true&& v[zx][zy]==0) continue;
//					
//						if(map[zx][zy]==0&&wall==true&& v[zx][zy]==0) 
//						{
//							System.out.println(zx+" "+zy+" "+(count+1));
//							q.offer(new Block(true,zx,zy,count+1));
//							v[zx][zy]=(count+1);
//						}
//					
//						if(map[zx][zy]==0&&wall==false&& v[zx][zy]==0) 
//						{
//							System.out.println(zx+" "+zy+" "+(count+1));
//							q.offer(new Block(false,zx,zy,count+1));
//							v[zx][zy]=(count+1);
//						}
//					}	
//				}
//			}	
//		}
//		if(vs==0)System.out.print(-1);
//		else System.out.print(vs);
//	}
//}
