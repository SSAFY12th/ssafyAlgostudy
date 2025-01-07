import java.util.*;
import java.io.*;
public class 전화번호목록_TRIE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        boolean[] flag = new boolean[T];
      
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            String[] s = new String[N];

            for (int i = 0; i < N; i++) {
                s[i] = br.readLine();
            }
            Arrays.sort(s);
            for (int i = 0; i < N; i++) {
                trie.insert(s[i]);
            }
            for (int i = 0; i < N; i++) {
                if (trie.check(s[i])) flag[t] = true;
            }
        }
      
        for (boolean b : flag) {
            if (b) sb.append("NO\n");
            else sb.append("YES\n");
        }
        System.out.println(sb);
    }

    public static class Node {
        Map<Character, Node> child = new HashMap<Character, Node>();
        boolean endOfword;
    }
  
    public static class Trie {
        Node rootNode = new Node();

        void insert(String str) {
            Node node = this.rootNode;

            // 문자열의 각 글자마다 존재 여부 체크
            // 없으면 자식노드 새로 생성
            for (int i = 0; i < str.length(); i++) {
                node = node.child.computeIfAbsent(str.charAt(i), key -> new Node());
            }
            // 저장 할 문자열의 마지막 단어에 매핑되는 노드에 단어의 끝임을 명시
            node.endOfword = true;
        }

        boolean search(String str) {
            Node node = this.rootNode;

            // 문자열의 각 글자마자 노드 존재여부를 확인
            for (int i = 0; i < str.length(); i++) {
                // 문자열의 각 단어에 매핑된 노드가 존재하면 가져오고 아니면 null
                node = node.child.getOrDefault(str.charAt(i), null);
                if (node == null) return false; // node가 null이면 현재 Trie에 해당 문자열은 없음
                System.out.println(str.charAt(i) + " " + node.endOfword);

            }
            return node.endOfword;
        }

        boolean check(String str) {
            Node node = this.rootNode;
            boolean isContain = false;

            for (int i = 0; i < str.length() - 1; i++) {
                node = node.child.getOrDefault(str.charAt(i), null);
                if (node.endOfword) isContain = true;
            }

            return isContain;
        }
    }
}
