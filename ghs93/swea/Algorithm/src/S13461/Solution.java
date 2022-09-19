package S13461;

import java.util.Scanner;

class Solution {
	
	private static int seed = 5;

	private static Scanner sc;
	private static UserSolution user = new UserSolution();
	
	private static final int MAX_N = 10000;
	private static final int MAX_M = 10;

	private static char ori_image_list[][][] = new char[MAX_N][MAX_M][MAX_M];
	private static char bak_image_list[][][] = new char[MAX_N][MAX_M][MAX_M];
	private static char dummy[] = new char[5005];
	private static char bak_image[][] = new char[MAX_M][MAX_M];
	 
	private static int pseudo_rand()
	{
		seed = seed * 214013 + 2531011;
		return (seed >> 16) & 0x7fff;
	}

	static int run(int _score)
	{
		int n = sc.nextInt();
		int m = sc.nextInt();
		seed = sc.nextInt();
		int ratio = sc.nextInt();
		int query_cnt = sc.nextInt();
		
		
		for (int i = 0; i < n; i++)
		{
			for (int y = 0; y < m; y++)
			{
				for (int x = 0; x < m; x++)
				{
					ori_image_list[i][y][x] = 0;
					int v = pseudo_rand() % 100;
					if (v >= ratio)
						ori_image_list[i][y][x] = 1;
					
					bak_image_list[i][y][x] = ori_image_list[i][y][x];
				}
			}
		}

		user.init(n, m, bak_image_list);

		int user_ans, correct_ans;

		for (int query = 0; query < query_cnt; query++)
		{
			int num = pseudo_rand() % n;

			for (int y = 0; y < m; y++)
			{
				for (int x = 0; x < m; x++)
				{
					bak_image[y][x] = ori_image_list[num][y][x];
				}
			}

			int bad_sector_cnt = pseudo_rand() % 2 + 1;

			for (int i = 0; i < bad_sector_cnt; i++)
			{
				int by = pseudo_rand() % m;
				int bx = pseudo_rand() % m;

				bak_image[by][bx] ^= 1;
			}

			user_ans = user.findImage(bak_image);
			correct_ans = sc.nextInt();

			if (user_ans != correct_ans)
				_score = 0;
		}

		return _score;
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new java.io.FileInputStream("sample_input.txt"));
		
		sc = new Scanner(System.in);

		int tc = sc.nextInt();
		int score = sc.nextInt();

		for (int t = 1; t <= tc; t++)
		{
			int tc_score = run(score);
			System.out.println("#"+ t + " " + tc_score);
		}

		sc.close();
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

}