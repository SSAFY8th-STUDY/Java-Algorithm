package P13471;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class UserSolution {
	class Line {
		int start, end, pId, eId;

		public Line(int start, int end, int pId, int eId) {
			this.start = start;
			this.end = end;
			this.pId = pId;
			this.eId = eId;
		}
	}
	
	int L, M;
	boolean eq[];
	List<Line> lines[];
	PriorityQueue<int[]> endTime;
	
	void init(int L, int M) {
		this.L = L;
		this.M = M;
		
		eq = new boolean[M];
		lines = new LinkedList[L];
		endTime = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		
		for (int i = 0; i < L; i++) {
			lines[i] = new LinkedList<>();
		}
		
		return;
	}

//	 tStamp : 생산 요청이 들어온 시각 ( 1 ≤ tStamp < 500,000 )
//	 pId : 제품의 ID ( 0 ≤ pId < 1,000,000,000 )
//	 mLine : 요청이 들어온 생산 라인 번호 ( 0 ≤ mLine < L )
//	 eId : 제품 생산에 필요한 장비 번호 ( 0 ≤ eId < M )
//	 mTime : 제품 생산에 소요되는 시간 ( 1 ≤ mTime ≤ 2,000 )
	int request(int tStamp, int pId, int mLine, int eId, int mTime) {
		//생산 종료 처리
		if(endTime.peek() != null && endTime.peek()[0] == tStamp) {
			lines[endTime.poll()[1]].remove(0);
		}
		
		//생산라인에 넣기
		Line line = new Line(tStamp, mTime, pId, eId);
		
		return 0;
	}

	int status(int tStamp, int pId) {
		return 0;
	}
}