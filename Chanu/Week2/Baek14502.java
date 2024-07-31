package com.example.codingtest.bfs;

import java.io.*;
import java.util.*;


public class Baek145020 {

    static int n,m;
    static int[][] arr;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};
    static ArrayList<int[]> virus;
    static int[][] copyArr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        copyArr = new int[n][m];
        virus = new ArrayList<>();

        for (int i=0; i<n; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j=0; j<m; j++) {

                int num = Integer.parseInt(st.nextToken());

                if (num == 2) {
                    virus.add(new int[]{i,j});
                }

                arr[i][j] = num;
            }
        }

        backTrack(0);

        System.out.println(max);


    }

    static void backTrack(int depth) {

        if (depth == 3 ) {

            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j=0; j<m; j++) {
                    copyArr[i][j] = arr[i][j];
                }

            }

            for (int i=0; i<virus.size(); i++) {

                int[] node = virus.get(i);

                bfs(node[0],node[1]);

            }


            for (int i = 0; i < n; i++) {
                for (int j=0; j< m; j++) {
                   if (copyArr[i][j] == 0) {
                       count++;
                   }
                }

            }

            max = Math.max(count,max);

            return;
        }

        for (int i=0; i<n; i++) {

            for (int j=0; j<m; j++) {

                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    backTrack(depth+1);
                    arr[i][j] = 0;
                }

            }
        }
    }

    static void bfs (int x, int y) {

        copyArr[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            x = node[0];
            y = node[1];

            for (int i=0; i<4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny <0 || nx >= n || ny >= m) {
                    continue;
                }

                if (copyArr[nx][ny] == 0) {
                    copyArr[nx][ny] = 2;
                    queue.offer(new int[] {nx,ny});
                }

            }
        }

    }

}


