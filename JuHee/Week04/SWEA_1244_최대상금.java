import java.io.*;
import java.util.*;

public class Solution{
	static int ans;
	static int N;
	static List<Integer> list;
	static Set<Integer> set;
	public static void dfs(int n) {
    if (n == N) {
        int current = 0;
        for (int i : list) {
            current = current * 10 + i;
        }
        ans = Math.max(ans, current);
        return;
    }

    for (int i = 0; i < list.size() - 1; i++) {
        for (int j = i + 1; j < list.size(); j++) {

            Collections.swap(list, i, j);

            int chk = 0;
            for (int num : list) {
                chk = chk * 10 + num;
            }
            chk = chk * 10 + n;

            if (!set.contains(chk)) {
                dfs(n + 1);
                set.add(chk);
            }

            Collections.swap(list, i, j);
        }
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    sc.nextLine();

    for (int tc = 1; tc <= T; tc++) {
        String[] input = sc.nextLine().split(" ");
        String st = input[0];
        N = Integer.parseInt(input[1]);

        list = new ArrayList<>();
        for (char ch : st.toCharArray()) {
            list.add(ch - '0');
        }

        ans = 0;
        set = new HashSet<>();
        dfs(0);

        System.out.printf("#%d %d\n", tc, ans);
    }
  }
}
