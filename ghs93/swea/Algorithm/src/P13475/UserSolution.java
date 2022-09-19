package P13475;

class UserSolution {
	final int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	final int N=10000, M=50001, U = 1000;
	
	int[][] map, copyMap, pMap, uVisit;

	void init() {
		map = new int[N][N];
		copyMap = new int[N][N];
		pMap = new int[M][2];
		uVisit = new int[U][100];
	}

	void addPlace(int pID, int r, int c) {
		map[r][c] = pID;
		copyMap[r][c] = pID;
		pMap[pID][0] = r;
		pMap[pID][1] = c;
	}

	void removePlace(int pID) {
		int[] p = pMap[pID];
		map[p[0]][p[1]] = 0;
		copyMap[p[0]][p[1]] = 0;
		
		pMap[pID][0] = 0;
		pMap[pID][1] = 0;
	}

	void contactTracing(int uID, int visitNum, int moveInfo[], int visitList[]) {
		int pID = moveInfo[0];
		int v = 0, maxR = 0;
		
		int r = pMap[pID][0];
		int c = pMap[pID][1];
//		System.out.println("pid: " + pID + ", r: " + r + ", c: " + c);
		
		visitList[v++] = pID;
		copyMap[r][c] *= -1;
		maxR = Math.max(maxR, r);
		
		for (int i = 1; i < visitNum; i++) {
			int dr = dir[moveInfo[i]][0];
			int dc = dir[moveInfo[i]][1];
			
			while(true) {
				r += dr;
				c += dc;
//				System.out.print("r: " + r + ", c: " + c + ", d: " + moveInfo[i]);
//				System.out.println(", map: " + map[r][c]);
				if(map[r][c] != 0 && map[r][c] > 0) {
					visitList[v++] = map[r][c];
					
					int cp = copyMap[r][c];
					copyMap[r][c] = cp>0 ? cp*-1 : cp;
					maxR = Math.max(maxR, r);
					break;
				}
			}
		}
		
		uVisit[uID] = visitList;
		
		for(int i=0; i<=maxR; i++) {
			map[i] = copyMap[i].clone();
			
//			System.out.println("map[i]" + Arrays.toString(map[i]));
//			System.out.println("copy[i]" + Arrays.toString(copyMap[i]));
			
		}
	}

	void disinfectPlaces(int uID) {
		int[] pIDs = uVisit[uID];
		
		for (int i = 0, size = pIDs.length; i < size; i++) {
			int pID = pIDs[i];
			int r = pMap[pID][0];
			int c = pMap[pID][1];
			
			map[r][c] *= -1;
			copyMap[r][c] *= -1;
			
//			System.out.println("clear - map: " + map[r][c] + ", cp: " + copyMap[r][c]);
			
		}
	}
}