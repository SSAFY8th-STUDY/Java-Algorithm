package S13461;

import java.util.PriorityQueue;

class UserSolution {
	final int MAX_N = 10000;
	final int MAX_M = 10;
	
	int N, M;
	char[][][] imageList;

	void init(int N, int M, char mImageList[][][])
	{
		this.N = N;
		this.M = M;
		
		imageList = mImageList;
	}
	
	int findImage(char mImage[][])
	{
		PriorityQueue<int[]> ids = new PriorityQueue<>((m1, m2) -> m1[1] - m2[1]);
		
		for (int i = 0; i < N; i++) {
			char[][] image = imageList[i];
			
			int cnt = 0;
			compareImg: for (int x = 0; x < M; x++) {
				for (int y = 0; y < M; y++) {
					if(image[x][y] != mImage[x][y])
						cnt++;
					
					if(cnt > 2)
						break compareImg;
				}
			}
				
			if(cnt <= 2) {
				ids.add(new int[] {i+1, cnt});
			}
		}

		return ids.poll()[0];
	}
}
