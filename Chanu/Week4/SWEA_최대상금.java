

import java.util.*;
import java.io.*;
import java.lang.Math;


class Solution
{

    static int[] change = new int[2];
    static int[] result;
    static int[] arr;
    static ArrayList<int[]> list;
    static boolean[] visited;;
    static int k,len;
    static int max;

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {

            list = new ArrayList<>();
            max = -1;
            visited = new boolean[6];
            arr = new int[6];

            String str = sc.next();

            len = str.length();

            for (int i=0; i<len; i++) {
                arr[i] = str.charAt(i) - '0';
            }

            k = sc.nextInt();
            
            if (k > len){

                k = len;

            }


            result = new int[k];

            back(0,0);


            backTracking(0);

            System.out.println("#"+test_case +" "+max);


        }
    }

    static void back (int depth, int start) {

        if (depth == 2) {
            list.add(new int[]{change[0],change[1]});
            return;
        }

        for (int i=start; i<len; i++) {
            if (!visited[i]){
                visited[i] = true;
                change[depth] = i;
                back(depth+1,i+1);
                visited[i] = false;
            }
        }
    }

    static void backTracking (int depth){

        if (depth == k) {

            int sum = 0;

            int[] b  = Arrays.copyOfRange(arr, 0, len);;

            for (int i=0; i < k; i++) {

                int[] arr2 = list.get(result[i]);
                int indexX = arr2[0];
                int indexY = arr2[1];

                int temp = b[indexX];

                b[indexX] = b[indexY];
                b[indexY] = temp;

            }

            int p = 1;
            for (int i=len-1; i>=0; i--) {
                sum += (b[i] * p);
                p*=10;
            }

            max = Math.max(max,sum);
            return;
        }
        for (int i=0; i<list.size(); i++) {

            result[depth] = i;
            backTracking(depth+1);
        }
    }
}
