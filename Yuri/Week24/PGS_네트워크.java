import java.util.*;

class Solution {

        static int[] parents;
        static int N;
        static Set<Integer> network = new HashSet<>();

        public int solution(int n, int[][] computers) {

            N = n;

            make();

            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers[0].length; j++) {
                    if(i == j) continue;
                    if(computers[i][j] == 1) {
                        union(i, j);
                    }
                }
            }
          
            // 부모를 연결을 최신화하기 위해 한 모든 컴퓨터에 대해 find 연산 수행
            for (int i = 0; i < n; i++) 
                find(i);

            for (int num : parents) 
                network.add(num);
            
            return network.size();
        }

        public int find(int a) {
            if(parents[a] == a) return a;
            return parents[a] = find(parents[a]);
        }

        public boolean union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);

            if(aRoot == bRoot) return false;
            parents[bRoot] = aRoot;
            return true;
        }

        public void make() {
            parents = new int[N];
            for (int i = 0; i < N; i++)
                parents[i] = i ;
        }
    }
