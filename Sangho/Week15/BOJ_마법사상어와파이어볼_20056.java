import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static ArrayList<Fireball>[][] map;
    static final int[][] DIRECTIONS = {
        {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, 
        {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };

    static class Fireball {
        int mass, speed, direction;
        
        Fireball(int mass, int speed, int direction) {
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            map[r][c].add(new Fireball(mass, speed, direction));
        }

        for (int i = 0; i < K; i++) {
            moveFireballs();
            mergeAndSplitFireballs();
        }

        System.out.println(calculateTotalMass());
    }

    static void moveFireballs() {
        ArrayList<Fireball>[][] newMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                newMap[i][j] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fireball fireball : map[i][j]) {
                    int newRow = (i + DIRECTIONS[fireball.direction][0] * fireball.speed % N + N) % N;
                    int newCol = (j + DIRECTIONS[fireball.direction][1] * fireball.speed % N + N) % N;
                    newMap[newRow][newCol].add(fireball);
                }
            }
        }
        map = newMap;
    }

    static void mergeAndSplitFireballs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() < 2) continue;

                int totalMass = 0, totalSpeed = 0;
                boolean allEven = true, allOdd = true;

                for (Fireball fireball : map[i][j]) {
                    totalMass += fireball.mass;
                    totalSpeed += fireball.speed;
                    if (fireball.direction % 2 == 0) allOdd = false;
                    else allEven = false;
                }

                int newMass = totalMass / 5;
                int newSpeed = totalSpeed / map[i][j].size();
                map[i][j].clear();

                if (newMass == 0) continue;

                int[] newDirections = (allEven || allOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                for (int direction : newDirections) {
                    map[i][j].add(new Fireball(newMass, newSpeed, direction));
                }
            }
        }
    }

    static int calculateTotalMass() {
        int totalMass = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (Fireball fireball : map[i][j])
                    totalMass += fireball.mass;
        return totalMass;
    }
}
