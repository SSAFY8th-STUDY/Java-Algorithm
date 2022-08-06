import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        String str = st.nextToken();
        st = new StringTokenizer(br.readLine()," ");
        int p = Integer.parseInt(st.nextToken());
        char[] a = new char[p];
        int[] start = new int[p];
        int[] end = new int[p];
        int[] count = new int[str.length()+2];

        for(int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine()," ");
            a[i] = st.nextToken().charAt(0);
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(a));
        for(int i=1; i<=str.length(); i++) {
            System.out.println(str.charAt(i - 1));
            if(str.charAt(i-1)==a[0])
            {
                count[i]=count[i-1]+1;
            }
            else {
                count[i]=count[i-1];
            }
        }
        for(int i=0; i<p; i++) {
            System.out.println(count[end[i]+1]-count[start[i]]);
        }
    }

}