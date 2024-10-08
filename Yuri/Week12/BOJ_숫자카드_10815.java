import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] cards;
    static int[] findCards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            cards[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(cards);

        M = Integer.parseInt(br.readLine());
        findCards = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            int returnValue = Arrays.binarySearch(cards, n);
            if(returnValue >= 0) sb.append("1 ");
            else sb.append("0 ");
        }

        System.out.println(sb.toString());

    }
}
