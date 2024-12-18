import java.util.*;
import java.io.*;
class Solution {
    int[] parent = new int[2551];
    String[][] map = new String[51][51];
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
     
        for(int i = 1; i <= 50; i++){
            for(int j = 1; j <= 50; j++){
                map[i][j] = "EMPTY";
                int idx = getIndex(i, j);
                parent[idx] = idx;
            }
        }
        
        for(String command : commands){
         String[] cmd = command.split(" ");
         
         if(cmd[0].equals("UPDATE")){
             if(cmd.length == 4){ // 부분 문자열 개수가 4면 새로운 값 입력
                 int r = Integer.parseInt(cmd[1]);
                 int c = Integer.parseInt(cmd[2]);
                 String value = cmd[3];
                 
                 int p = find(getIndex(r,c));
                 
                 for(int i = 1; i <= 50; i++){
                     for(int j = 1; j <= 50; j++){
                         if(find(getIndex(i, j)) == p){
                             map[i][j] = value;
                         }
                     }
                 }
             }
             
             else { // 부분 문자열 개수가 3이면 기존 값 수정
                 String v1 = cmd[1];
                 String v2 = cmd[2];
                 
                 for(int i = 1; i <= 50; i++){
                     for(int j = 1; j <= 50; j++){
                         if(map[i][j].equals(v1)){
                             map[i][j] = v2;
                         }
                     }
                 }
             }    
         }
         
         else if(cmd[0].equals("MERGE")){  
             int r1 = Integer.parseInt(cmd[1]);
             int c1 = Integer.parseInt(cmd[2]);
             int r2 = Integer.parseInt(cmd[3]);
             int c2 = Integer.parseInt(cmd[4]);
             
             // 셀 병합 시 같은 위치의 값이면 패스
             if(r1 == r2 && c1 == c2) continue;
             
             int p1 = find(getIndex(r1, c1));
             int p2 = find(getIndex(r2, c2));
             
             if(p1 == p2) continue; // 셀 병합 시 같은 부모를 가진 경우도 패스
             
             // (r1,c1) 위치의 값이 있으면 해당 값으로 병합, 아니면 (r2,c2) 위치의 값으로 병합
             String value = !map[r1][c1].equals("EMPTY") ? map[r1][c1] : map[r2][c2];
             parent[p2] = p1; // union
             
             for(int i = 1; i <= 50; i++){
                for(int j = 1; j <= 50; j++){
                    if(find(getIndex(i, j)) == p1){
                        map[i][j] = value;
                    }
                }
            } 
         }
         
         else if(cmd[0].equals("UNMERGE")){
             int r = Integer.parseInt(cmd[1]);
             int c = Integer.parseInt(cmd[2]);
             int p = find(getIndex(r, c));
             String target = map[r][c]; // 셀 해제 후 (r,c) 자리에 삽입할 문자열
             
             for(int i = 1; i <= 50; i++){
                for(int j = 1; j <= 50; j++){
                    int idx = getIndex(i, j);
                    if(find(idx) == p){
                        map[i][j] = "EMPTY"; // 병합된 부분 모두 EMPTY로 초기화
                        parent[idx] = idx; // 합쳐져있던 집합도 모두 분리(자기자신을 부모로하여)
                    }
                }
            }
             
            map[r][c] = target; //(r,c) 자리에 target 문자열 삽입
         }
         
         else if(cmd[0].equals("PRINT")){
            int r = Integer.parseInt(cmd[1]);
            int c = Integer.parseInt(cmd[2]);
            answer.add(map[r][c]); // 결과 문자열을 asnwer에 저장
         }
     }
    
        return answer.toArray(new String[0]); // List를 String[] 형태로 변환하여 리턴
    }

    public int getIndex(int r, int c){
        return r * 50 + c;
    }
    
    public int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
