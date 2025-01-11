import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 트라이 풀이
 * 1. 입력받은 문자열을 트라이 자료구조에 모두 저장한다. 저장함과 동시에 검증을 위해 기억을 해야 하므로 따로 리스트에 저장한다.
 * 2. 리스트애 저장한 문자열들을 검증한다. 만약 문자열 검증 후 endNode가 중간 지점이면 false를 반환한다.
 */
public class Main {

    static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean isEndNode;

        // 문자열 삽입
        public void insert(String word) {
            Node trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                //자식노드에 문자 없으면 추가하기 문자 추가!
                trieNode.childNode.putIfAbsent(c, new Node());
                trieNode = trieNode.childNode.get(c);

                if (i == word.length() - 1) {
                    trieNode.isEndNode = true;
                    return;
                }
            }
        }

        //문자열 검색
        public boolean search(String word) {
            Node trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node node = trieNode.childNode.get(c);

                if (node == null) return false;
                trieNode = node;
            }
            // 해당 단어로 종료하는 문자가 있을 경우
            if (trieNode.isEndNode) {
                // 자기 자신 제외하고 자식 노드가 없으면 해당 노드는 리프노드므로 false 반환
                if (trieNode.childNode.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            List<String> strList = new ArrayList<>();
            Node trie = new Node();
            boolean isValid = true;

            for (int j = 0; j < N; j++) {
                String str = br.readLine();
                trie.insert(str);
                strList.add(str);
            }

            for (String word : strList) {
                if (trie.search(word)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) System.out.println("YES");
            else System.out.println("NO");

        }
    }
}
