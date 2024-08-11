import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void turnRight(int[] arr){
		int temp = arr[7];
		for(int i=7;i>0;i--){
			arr[i] = arr[i-1];
		}
		arr[0] = temp;
	}

	public static void turnLeft(int[] arr){
		int temp = arr[0];
		for(int i=0;i<7;i++){
			arr[i] = arr[i+1];
		}
		arr[7] = temp;	
	}

	public static boolean[] cr(int[][] wheels){ 
		boolean[] cr = new boolean[3];
		for(int i=0;i<3;i++){
			if(wheels[i][2] != wheels[i+1][6]){
				cr[i] = true;
			} else {
				cr[i] = false;
			}
		}
		return cr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] wheels = new int[4][8];
		boolean before_rotate = false;
		boolean[] can_rotate = new boolean[3];

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheels[i][j] = s.charAt(j)-'0';
			}
		}

		int rotation_count = Integer.parseInt(br.readLine()); // 회전 횟수
		int[] wheel_num = new int[rotation_count];
		int[] direction = new int[rotation_count];

		for(int i=0;i<rotation_count;i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			wheel_num[i] = Integer.parseInt(st.nextToken()); // 회전할 톱니바퀴 번호
			direction[i] = Integer.parseInt(st.nextToken()); // 1이면 시계방향, -1이면 반시계방향
		}
		// 12시방향 -> index : 0 , N극 : 0 /  S극 : 1
		
		for(int i=0;i<rotation_count;i++){
			int num = wheel_num[i] - 1;
			int direct = direction[i];

			can_rotate = cr(wheels); // 전체 휠의 극 확인

			if(direct == 1){ // 현재 휠 회전
				turnRight(wheels[num]); // 1이면 오른쪽으로 회전
			} else {
				turnLeft(wheels[num]); //// -1이면 왼쪽으로 회전
			}

			before_rotate = true; // 현재 휠이 회전했으므로 before_rotate를 true로 변경
			
			for(int j=num;j<3;j++){ // 현재 휠의 오른쪽 휠을 모두 검사
				if(before_rotate && can_rotate[j]){
					if(direct == 1){
						turnLeft(wheels[j+1]);
					} else {
						turnRight(wheels[j+1]);
					}
					direct*= (-1);
				} else{
					before_rotate = false;
				}
			}
			before_rotate = true; // 오른쪽 검사 후 before_rotate 초기화
			direct = direction[i]; // 오른쪽 검사 후 방향 초기화

			for(int k=num;k>0;k--){ // 현재 휠의 왼쪽 휠을 모두 검사
				if(before_rotate && can_rotate[k-1]){
					if(direct == 1){
						turnLeft(wheels[k-1]);
					} else {
						turnRight(wheels[k-1]);
					}
					direct*= (-1);
				} else{
					before_rotate = false;
				}
			}
		}

		int sum =0;
		int cnt = 1;
		for(int i=0;i<4;i++){
			if(wheels[i][0] == 1){
				sum +=cnt;
			}
			cnt = cnt*2;
		}
		System.out.println(sum);
	}
}
