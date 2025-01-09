import java.util.*;
import java.io.*;

public class Main {
	static int N, T;

	static class Node {
		// 각 노드의 자식노드 저장
		HashMap<Character, Node> child;
		// 이 노드가 단어의 끝인지 저장
		boolean endOfWord;
		
		public Node() {
			this.child = new HashMap<Character,Node>();
			this.endOfWord = false;
		}
	}

	static class Trie {
		Node root;

		public Trie() {
			this.root = new Node();
		}

		// 단어 삽입
		public boolean insert(String str) {
			// 시작 노드를 루트노드로 설정
			Node node = this.root;

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				// 문자열의 각 문자를 가져와서 자식 노드 중에 있는 지 체크
				
				node.child.putIfAbsent(c, new Node());
				// 있으면 : node = node.child.get(str.charAt(i));
				// 없으면 : 새로운 노드를 생성하여 넣음
				
				node = node.child.get(c); // 자식 노드로 이동
				
				// 중복 단어 발견 -> 같은 단어가 입력되어 다른 단어의 끝에 도달한 것
				if(node.endOfWord) {
					return false;
				}
			}
			// 접두사 겹침 발견 -> 현재 문자열이 기존의 다른 단어의 접두사인지 확인
			if(!node.endOfWord && !node.child.isEmpty()) {
				return false;
			}
			
			// for문이 끝나면 현재 노드는 마지막 글자이기 떄문에 단어의 끝임을 명시
			node.endOfWord = true;
			return true;
		}

		boolean search(String str) {
			Node node = this.root;

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);

				if (node.child.containsKey(c)) { // 자식노드에 c가 있을 때 계속 탐색 진행
					node = node.child.get(c);
				} else {
					return false;
				}
			}
			// 마지막 노드까지 도달 시
			return node.endOfWord; // 마지막 노드의 endOfWord를 반환
		}

		public boolean delete(String str) { // 사용자 입력을 통한 삭제
			boolean result = delete(this.root, str, 0); // 내부적으로 재귀로 삭제 수행
			return result;
		}

		public boolean delete(Node node, String str, int idx) {
			char c = str.charAt(idx);

			if (!node.child.containsKey(c)) { // 현재 노드의 자식 노드에 c가 존재하지 않으면 false
				return false;
			}
			
			Node cur = node.child.get(c);
			idx++;
			if (idx == str.length()) { // 주어진 문자열의 끝에 도달
				if (!cur.endOfWord) { // 그런데 현재 문자열은 끝이 아님 -> 같은 단어 아님 -> 삭제X
					return false;
				}

				// endOfWord를 false로 바꿔주면 지우려는 문자열을 찾을 수 없게 됨
				cur.endOfWord = false;

				// 지우려는 문자열의 마지막에서 더 뻗어나가는 경우
				if (cur.child.isEmpty()) {
					node.child.remove(c);
				}
			} else { 
				// 문자열의 끝에 도달하지 않았을 때 -> 재귀적으로 현재 노드부터 다시 호출
				if (!this.delete(cur, str, idx)) { // 삭제에 실패한 경우
					return false;
				}
				
				//true를 반환받았고, 자식 노드가 비어있으면 현재 노드를 삭제
				//node는 cur의 부모노드 -> cur 노드를 node의 자식 Map에서 삭제하는 것
				if (!cur.endOfWord && cur.child.isEmpty()) {
					node.child.remove(c);
				}
			}
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			Trie t = new Trie();
			boolean flag = true;
			String[] strs = new String[N];
			for (int i = 0; i < N; i++) {
				strs[i] = br.readLine();
			}

			for (int i = 0; i < N; i++) {
				if(!t.insert(strs[i])) {
					flag = false;
					break;
				}
			}
			if(flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}
}
