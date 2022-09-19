package S13469;

import java.util.ArrayList;

public class UserSolution
{
	int H, W, cur;
	ArrayList<Character> str;
	
	void init(int H, int W, char mStr[])
	{
		this.H = H;
		this.W = W;
		
		cur = 0;
		str = new ArrayList<>();
		for(char s : mStr) {
			if(s == '\0') 
				break;
			
			str.add(s);
		}
	}
	
	void insert(char mChar)
	{
		str.add(cur++, mChar);
	}

	char moveCursor(int mRow, int mCol)
	{
		mRow--; mCol--;
		
		cur = mRow*W + mCol;
		
		if(cur >= str.size()) {
			cur = str.size();
			return '$';
			
		} else {
			return str.get(cur);			
		}
	}

	int countCharacter(char mChar)
	{
		int cnt = 0;
		
		int size = str.size();
		int length = size - cur;
		int loopCnt = (length-1)/2;
		
		if(cur == size)
			return 0;
		
		for (int i = 0, j=size-1; i <= loopCnt; i++, j--) {
			if(str.get(cur+i) == mChar)
				cnt++;
			
			if(str.get(j) == mChar)
				cnt++;
		}
		
		if(length%2 != 0 && str.get(cur+loopCnt) == mChar)
			cnt--;

		return cnt;
	}
}