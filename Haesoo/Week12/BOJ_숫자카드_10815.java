//Map 이용 (792ms)

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Integer> cards = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());
            cards.put(key, 1);
        }
        int M = Integer.parseInt(br.readLine());
        int [] target = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            target[i] = Integer.parseInt(st.nextToken());
            if (cards.containsKey(target[i])) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }
}

// 이분탐색 (1104ms)
/*
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int [] cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);
        int M = Integer.parseInt(br.readLine());
        int [] target = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < M; i++) {
        	int left = 0, right = N - 1;
        	while (left <= right) {
        		int mid = (left + right) / 2;
        		if (target[i] < cards[mid]) right = mid - 1;
        		else if (target[i] > cards[mid]) left = mid + 1;
        		else {
        			sb.append(1).append(" ");
        			break;
        		}
        	}
            if (left > right) sb.append(0).append(" ");
        }
        System.out.println(sb);
    }
}
*/
