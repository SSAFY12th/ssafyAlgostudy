import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int w, h;
    private static List<Integer> shops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int total = (w + h) * 2;

        int shopCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < shopCount + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());

            if (dir == 1) { // 북
                shops.add(pos);
            } else if (dir == 2) { // 남
                shops.add(w + h + (w - pos));
            } else if (dir == 3) { // 서
                shops.add(2 * w + h + (h - pos));
            } else if (dir == 4) { // 동
                shops.add(w + pos);
            }
        }

        int myPos = shops.get(shops.size() - 1);
        int answer = 0;
        for (int value : shops) {
            int temp = Math.abs(myPos - value);
            answer += Math.min(temp, total - temp);
        }

        System.out.println(answer);
    }
}
