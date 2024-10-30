import java.util.*;
import java.io.*;

public class 문자열폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String original = br.readLine();
		int oLen = original.length();

		String trigger = br.readLine();
		int tLen = trigger.length();
		char end = trigger.charAt(tLen - 1);
		for (int i = 0; i < oLen; i++) {
			sb.append(original.charAt(i));
			
			int lastIndex = sb.length();
			if (sb.charAt(lastIndex - 1) == end && lastIndex >= tLen) {
				if(sb.substring(lastIndex - tLen, lastIndex).equals(trigger)) {
					sb.delete(lastIndex - tLen, lastIndex);
				}
			}
		}
		System.out.println(sb.toString().equals("") ? "FRULA" : sb.toString());
	}
}
