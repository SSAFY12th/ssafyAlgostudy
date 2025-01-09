import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static char[] letters;
	static char[] currents;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		currents = new char[L];
		letters = new char[C];
			
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			letters[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(letters);
		
		DFS(0, 0, 0, 0);
		
		System.out.println(sb.toString());
	}

	static void DFS(int depth, int start, int mo, int ja) {
	    if (depth == L) {
	        // L길이에 도달했을 때 모음,자음 조건 확인
	        if (mo >= 1 && ja >= 2) {
	            sb.append(String.valueOf(currents)).append("\n");
	        }
	        return;
	    }
	    
	    for (int i = start; i < C; i++) {
	        currents[depth] = letters[i];  // 현재 위치에 문자 저장
	        
	        // 현재 추가하는 문자가 모음인지 자음인지 확인하고 카운트 증가
	        if (isVowel(letters[i])) {
	            DFS(depth + 1, i + 1, mo + 1, ja);
	        } else {
	            DFS(depth + 1, i + 1, mo, ja + 1);
	        }
	    }
	}
	
	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'u' || c == 'o';
	}
}
