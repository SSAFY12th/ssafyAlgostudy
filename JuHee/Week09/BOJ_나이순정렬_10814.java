import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	public static void main(String args[]) throws IOException{
	   	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   	 
	   	 	int num = Integer.parseInt(br.readLine());
	   	 	
	   	 	String[][] arr = new String[num][2];
	   	 	
	   	 	for(int i =  0 ; i < num ; i++) {
	   	 		String[] st = br.readLine().split(" ");
	   	 		arr[i][0] = st[0];
	   	 		arr[i][1] = st[1];
	   	 	}
	   	 	
	   	 	
	   	 	Arrays.sort(arr, new Comparator<String[]>() {
	   	 		@Override
	   	 		public int compare(String[] s1, String[] s2) {
	   	 		if(s1[0] == s2[0]) {
	   	 			return 1;
	   	 		}else {
	   	 			return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
	   	 		}
	   	 	}
	   	 	});
	   
	   	 	
	   	 	for(int i = 0; i < num; i++) {
				System.out.println(arr[i][0] +" "+ arr[i][1]);
	   	 		}
	}
}
