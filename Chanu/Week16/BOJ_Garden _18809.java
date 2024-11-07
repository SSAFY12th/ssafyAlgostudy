public class Main {

    static int[][] map;
    static int[][] copyMap;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static int max = Integer.MIN_VALUE;

    static int n;
    static int m;
    static int g;
    static int r;
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        copyMap = new int[n][m];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(0, 0);
        System.out.println(max);

    }

    static void back(int start, int depth) {

        if (depth == g) {
            back2(0,0);
            return;
        }

        for (int i=start; i<n*m; i++) {

            int x = i / m;
            int y = i % m;

            if (map[x][y] == 2) {
                map[x][y] = 3;
                back(i+1, depth+1);
                map[x][y] = 2;

            }

        }

    }

    static void back2(int start, int depth) {

        if (depth == r) {
            queue.clear();


            // 0 은 호수
            // 1 이동가능
            // 2 이동 가능
            // 3 그린
            // 4 레드

            // map을 카피한다
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    copyMap[i][j] = map[i][j];

                    if (copyMap[i][j] == 3 || copyMap[i][j] ==4) {
                        queue.offer(new int[]{i,j,map[i][j]});
                    }

                }
            }


            int count = 0;
            while (!queue.isEmpty()) {
                int len = queue.size();

                // queue에 있는 값을 모두 빼서 갈 수 있는 위치를 모두 저장
                for (int i=0; i<len; i++) {
                    int[] node = queue.poll();

                    if (copyMap[node[0]][node[1]] == -1) {
                        continue;
                    }

                    for (int d=0; d<4; d++) {
                        int nx = node[0] + dx[d];
                        int ny = node[1] + dy[d];

                        if (nx >=0 && nx < n && ny >= 0 && ny < m && (copyMap[nx][ny] == 1 || copyMap[nx][ny] == 2)) {
                            queue.offer(new int[] {nx,ny,node[2]});
                        }
                    }
                }


                // queue 사이즈 만큼 빼서 이동시키기
                len = queue.size();

                for (int i=0; i<len; i++) {
                    int[] node = queue.poll();
                    int x = node[0];
                    int y = node[1];
                    int num = node[2];

                    if (copyMap[x][y] == num){
                        queue.offer(new int[]{x,y,num});
                        continue;
                    }

                    if (copyMap[x][y] == 1) {
                        copyMap[x][y] = num;
                        queue.offer(new int[]{x,y,num});
                    }else if (copyMap[x][y] == 2) {
                        copyMap[x][y] = num;
                        queue.offer(new int[]{x,y,num});
                    }
                    else {
                        copyMap[x][y] += num;
                    }

                }



                for (int i=0; i<n; i++) {
                    for (int j=0; j<m; j++) {
                        if (copyMap[i][j]>=7) {
                            copyMap[i][j] = -1;
                            count++;
                        }
                    }
                }



            }

            max = Math.max(max,count);
            return;
        }

        for (int i=start; i<n*m; i++) {

            int x = i / m;
            int y = i % m;

            if (map[x][y] == 2) {
                map[x][y] = 4;
                back2(i+1, depth+1);
                map[x][y] = 2;

            }

        }


    }
}
