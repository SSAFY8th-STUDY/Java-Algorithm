package P13475;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static BufferedReader br;
	private static UserSolution usersolution = new UserSolution();

	private final static int INIT = 100;
	private final static int ADD_PLACE = 200;
	private final static int REMOVE_PLACE = 300;
	private final static int CONTACT_TRACING = 400;
	private final static int DISINFECT_PLACES = 500;

	private static boolean run() throws Exception {
		boolean ret = false;
		int cmd;
		int pID, uID, r, c, visitNum;
		int moveInfo[] = new int[100];
		int visitList[] = new int[100];
		int ans;

		StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

		int queryCnt = Integer.parseInt(stdin.nextToken());

		for (int q = 1; q <= queryCnt; q++) {
			stdin = new StringTokenizer(br.readLine(), " ");

			cmd = Integer.parseInt(stdin.nextToken());

			switch (cmd) {
			case INIT:
				usersolution.init();
				ret = true;
				break;
			case ADD_PLACE:
				pID = Integer.parseInt(stdin.nextToken());
				r = Integer.parseInt(stdin.nextToken());
				c = Integer.parseInt(stdin.nextToken());
				usersolution.addPlace(pID, r, c);
				break;
			case REMOVE_PLACE:
				pID = Integer.parseInt(stdin.nextToken());
				usersolution.removePlace(pID);
				break;
			case CONTACT_TRACING:
				uID = Integer.parseInt(stdin.nextToken());
				visitNum = Integer.parseInt(stdin.nextToken());
				stdin = new StringTokenizer(br.readLine(), " ");
				for (int i = 0; i < visitNum; i++)
					moveInfo[i] = Integer.parseInt(stdin.nextToken());
				usersolution.contactTracing(uID, visitNum, moveInfo, visitList);
				stdin = new StringTokenizer(br.readLine(), " ");
				for (int i = 0; i < visitNum; i++) {
					ans = Integer.parseInt(stdin.nextToken());
					if (visitList[i] != ans)
						ret = false;
				}
				break;
			case DISINFECT_PLACES:
				uID = Integer.parseInt(stdin.nextToken());
				usersolution.disinfectPlaces(uID);
				break;
			default:
				ret = false;
				break;
			}
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
		int T, MARK;

		System.setIn(new java.io.FileInputStream("src\\P13475\\sample_input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
		T = Integer.parseInt(stinit.nextToken());
		MARK = Integer.parseInt(stinit.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			int score = run() ? MARK : 0;
			System.out.println("#" + tc + " " + score);
		}

		br.close();
	}
}