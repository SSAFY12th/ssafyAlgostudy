
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int q;
    static int[][] arr;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][2];

        q = Integer.parseInt(st.nextToken());

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // lower bound

            int low = 0;
            int high = n;

            while (low < high) {

                int mid = (low+high)/2;

                if (arr[mid][0] < u) {
                    low = mid+1;
                }else {
                    high = mid;
                }
            }

            int index1 = low;

            low = 0;
            high = n;

            while (low < high) {

                int mid = (low+high)/2;

                if (arr[mid][0] <= v) {
                    low = mid+1;
                }else {
                    high = mid;
                }
            }

            int index2 = low;

            int count = 0;
            for (int j=index1; j<index2; j++ ){
                if ( x <= arr[j][1] && arr[j][1] <= y) {
                    count++;
                }
            }

            System.out.println(count);

        }

    }
}
