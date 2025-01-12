package algorithm;

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 도시 연결 정보 입력, 유니온 연산
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j);
                }
            }
        }

        // 여행 계획 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] plan = new int[m];
        for (int i = 0; i < m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한지 확인 하는 변수
        boolean possible = true;

        // 루트를 계획의 첫번째로 지정
        int root = find(plan[0]);

        // find 연산
        for (int i = 1; i < m; i++) {
            // 부모가 다르면 연결 안된거임
            if (find(plan[i]) != root) {
                possible = false;
                break;
            }
        }

        // 결과 출력
        System.out.println(possible ? "YES" : "NO");
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
