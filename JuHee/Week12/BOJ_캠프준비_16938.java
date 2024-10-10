import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int ans = 0;
	static int arr[];
	static boolean visited[];
	static int l, r, x;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");

		int n = Integer.parseInt(input[0]);
		l = Integer.parseInt(input[1]);
		r = Integer.parseInt(input[2]);
		x = Integer.parseInt(input[3]);

		arr = new int[n];
		visited = new boolean[n];

		input = br.readLine().split(" ");
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(input[i]);

		for (int i = 2; i <= n; i++)
			combination(0, i);

		System.out.println(ans);
	}

	public static void combination(int start, int select) {
		if (select == 0) {

			int sum = 0;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			for (int i = 0; i < arr.length; i++) {
				if (visited[i]) {
					sum += arr[i];
					min = Math.min(min, arr[i]);
					max = Math.max(max, arr[i]);
				}
			}

			if (sum >= l && sum <= r && max - min >= x)
				ans++;

			return;
		}

		for (int i = start; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, select - 1);
				visited[i] = false;
			}
		}
	}
}
