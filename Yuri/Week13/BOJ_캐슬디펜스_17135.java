import java.io.*;
import java.util.*;

public class Main {

    static int N, M, D;
    static List<Position> enemies = new ArrayList<>();
    static List<Position> copyEnemies = new ArrayList<>();
    static List<Position[]> team = new ArrayList<>();
    static Position[] combi = new Position[3];
    static int max = 0;

    static class Position {
        int r, c, dis;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public Position(Position p) {
            this.r = p.r;
            this.c = p.c;
            this.dis = p.dis;
        }
        public boolean isSame(Position o) {
            if(this.r == o.r && this.c == o.c) return true;
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1)
                    enemies.add(new Position(i, j));
            }
        }

         // 조합을 뽑는다.
        combination(0, 0);
        // 뽑은 조합으로 적을 죽일 수 있는 최대값을 찾아보자.
        defend();

        System.out.println(max);

    }

    public static void defend() {
        for (int i = 0; i < team.size(); i++) {
            Position[] nowTeamCombi = team.get(i);
            int cnt = 0;

            copyEnemies.clear();
            // 깊은 복사를 해야함......
            for (Position p : enemies)
                copyEnemies.add(new Position(p));

            for (int t = 0; t < N; t++) {
                Position[] kill = new Position[3];
                Arrays.fill(kill, null);
                // 적의 위치를 전체 순회한다.
                // 궁수 123의 위치와 계산하여 거리를 구한다!
                // 만약 사거리 내에 있다면 죽일 수 있다!
                // 이미 죽일 적을 설정한 경우, 거리가 더 짧은 적을 죽인다!
                // 거리가 같을 경우, 왼쪽에 있는 적을 죽인다!
                // 각 궁수는 별개로 계산한다! (같은 적을 죽일 수 있다!)
                // 죽일 적을 모두 구했을 때! 다시 적의 위치를 전체 순회한다.
                // 만약 죽은 적이라면 cnt++하고 리스트에서 제거.
                // 만약 안죽은 적이라면 r++;
                // 만약 맵을 벗어났다면 리스트에서 제거.

                for (Position enemy : copyEnemies) {
                    for (int j = 0; j < 3; j++) {
                        int dis = Math.abs(enemy.r-nowTeamCombi[j].r) + Math.abs(enemy.c-nowTeamCombi[j].c);
                        if(dis <= D) {
                            if(kill[j] == null) {
                                kill[j] = new Position(enemy);
                                kill[j].dis = dis;
                            }
                            else {
                                if(kill[j].dis > dis) {
                                    kill[j] = new Position(enemy);
                                    kill[j].dis = dis;
                                }
                                else if(kill[j].dis == dis && kill[j].c > enemy.c) {
                                    kill[j] = new Position(enemy);
                                    kill[j].dis = dis;
                                }
                            }
                        }
                    }
                }


                for (int j = 0; j < copyEnemies.size(); j++) {
                    Position enemy = copyEnemies.get(j);
                    boolean isKill = false;

                    for (int k = 0; k < 3; k++) {
                        if (kill[k] != null && kill[k].isSame(enemy)) {
                            copyEnemies.remove(j);
                            isKill = true;
                            cnt++;
                            j--;
                            break;
                        }
                    }

                    if (isKill) continue;

                    enemy.r++;
                    if (enemy.r >= N) {
                        copyEnemies.remove(j);
                        j--;
                    } else {
                        copyEnemies.set(j, enemy);
                    }
                }

                max = Math.max(max, cnt);
            }
        }
    }

    public static void combination(int cnt, int now) {

        if(cnt == 3) {
            team.add(combi.clone());
            return;
        }

        for (int i = now; i < M; i++) {
            combi[cnt] = new Position(N, i);
            combination(cnt+1, i+1);
        }
    }
}
