
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek20056 {

    static Queue<int[]> queue = new ArrayDeque<>();
    static Result[][] visited;
    static int n,m,k;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new Result[n][n];


        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            queue.offer(new int[]{Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())});
        }

        for (int t=0; t<k; t++) {

            int size = queue.size();

            for (int i=0; i<size; i++) {

                int[] node = queue.poll();
                int nx = node[0] + dx[node[4]]*node[3];
                int ny = node[1] + dy[node[4]]*node[3];

                if (nx < 0) {
                    nx = (nx % n + n) % n;
                }else {
                    nx = nx % n;
                }

                if (ny < 0) {
                    ny = (ny % n + n) % n;
                }else {
                    ny = ny % n;
                }

                node[0] = nx;
                node[1] = ny;
                queue.offer(node);

            }
            
            size = queue.size();

            for (int i=0; i<size; i++) {
                int[] node = queue.poll();
                int x = node[0];
                int y = node[1];

                if (visited[x][y] != null) {
                    visited[x][y].fast += node[3];
                    visited[x][y].ballSum += 1;
                    visited[x][y].wight += node[2];

                    if (!visited[x][y].check && visited[x][y].d % 2 == 0) {
                        if (node[4] % 2 != 0) {
                            visited[x][y].check = true;
                        }
                    }else if(!visited[x][y].check && visited[x][y].d % 2 != 0) {
                        if (node[4] % 2 == 0) {
                            visited[x][y].check = true;
                        }
                    }

                }else {
                    visited[x][y] = new Result(node[3],1,node[2],node[4]);
                }

            }

            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {

                    if (visited[i][j] != null) {

                        if (visited[i][j].ballSum == 1) {

                            queue.offer(new int[]{i,j,visited[i][j].wight,visited[i][j].fast,visited[i][j].d});

                        } else if (visited[i][j].ballSum > 1) {

                            int m = visited[i][j].wight/5;
                            int s = visited[i][j].fast/visited[i][j].ballSum;

                            if (m == 0) {
                                continue;
                            }

                            if (s == 0) {
                                continue;
                            }

                            if (!visited[i][j].check) {
                                for (int k=0; k<8; k+=2) {
                                    queue.offer(new int[]{i,j,m,s,k});
                                }
                            }else {
                                for (int k=1; k<8; k+=2) {
                                    queue.offer(new int[]{i,j,m,s,k});
                                }
                            }
                        }

                        visited[i][j] = null;
                    }
                }
            }

            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    visited[i][j] = null;
                }
            }

        }
        
        int answer=0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            answer+=poll[2];
        }
        System.out.println(answer);

    }

    static class Result{

        int fast;
        int ballSum;
        int wight;
        int d = 0;
        boolean check = false;

        public Result(int fast, int ballSum, int wight, int d) {
            this.fast = fast;
            this.ballSum = ballSum;
            this.wight = wight;
            this.d = d;
        }
    }
}
