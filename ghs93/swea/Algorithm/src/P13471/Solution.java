package P13471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private final static int CMD_INIT = 1;
	private final static int CMD_REQUEST = 2;
	private final static int CMD_STATUS = 3;

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception {
		int q = Integer.parseInt(br.readLine());

		int l, m, timestamp, pid, mline, eid, mtime;
		int cmd, ans, ret = 0;
		boolean okay = false;

		for (int i = 0; i < q; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
				case CMD_INIT:
					l = Integer.parseInt(st.nextToken());
					m = Integer.parseInt(st.nextToken());
					usersolution.init(l, m);
					okay = true;
					break;
				case CMD_REQUEST:
					timestamp = Integer.parseInt(st.nextToken());
					pid = Integer.parseInt(st.nextToken());
					mline = Integer.parseInt(st.nextToken());
					eid = Integer.parseInt(st.nextToken());
					mtime = Integer.parseInt(st.nextToken());
					ans = Integer.parseInt(st.nextToken());
					ret = usersolution.request(timestamp, pid, mline, eid, mtime);
					if (ret != ans)
						okay = false;
					break;
				case CMD_STATUS:
					timestamp = Integer.parseInt(st.nextToken());
					pid = Integer.parseInt(st.nextToken());
					ans = Integer.parseInt(st.nextToken());
					ret = usersolution.status(timestamp, pid);
					if (ret != ans)
						okay = false;
					break;
				default:
					okay = false;
					break;
			}
		}
		return okay;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}