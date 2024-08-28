import java.io.*;
import java.util.*;

public class Main {
    static int targetVal = 0;
    static int cnt = 0;
    static int curVal = 0;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       int N = Integer.parseInt(br.readLine());
       int[] arr = new int[N];
       st = new StringTokenizer(br.readLine());
       for (int i = 0; i < N; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
       }
       Arrays.sort(arr);

       for (int i = 0; i < N; i++) {
           targetVal = arr[i];
           int leftIdx = 0, rightIdx = N - 1;
           curVal = 0;
           while (leftIdx < rightIdx) {
               curVal = arr[leftIdx] + arr[rightIdx];
               if (curVal == targetVal && leftIdx != i && rightIdx != i) {
                   cnt++;
                   break;
               }
               if (leftIdx == i) leftIdx++;
               else if (rightIdx == i) rightIdx--; // 타겟 인덱스랑 동일하면 안되니까 인덱스 옮기기
               else if (curVal > targetVal) rightIdx--; // 여기 else if로 안했다가 계속 6개 나옴 (인덱스 비교, 값 비교는 동시에 만족해야 하니까)
               else leftIdx++; //
           }
       }
        System.out.println(cnt);
    }
}

/*
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;


int main() {
    int N;
    cin >> N;
    vector <int> v (N);
    for (int i = 0; i < N; i++) {
        cin >> v[i];
    }
    sort(v.begin(), v.end());
    int targetVal;
    int cnt = 0;
    for (int i = 0; i < N; i++) {
        targetVal = v[i];
        int leftIdx = 0, rightIdx = N - 1;
        int curVal = 0;
        while (leftIdx < rightIdx) {
            curVal = v[leftIdx] + v[rightIdx];
            if (curVal == targetVal && leftIdx != i && rightIdx != i) {
                cnt++;
                break;
            }
            if (i == leftIdx) leftIdx++;
            else if (i == rightIdx) rightIdx--;
            else if (curVal < targetVal) leftIdx++;
            else rightIdx--;
        }
    }
    cout << cnt;
}

*/
