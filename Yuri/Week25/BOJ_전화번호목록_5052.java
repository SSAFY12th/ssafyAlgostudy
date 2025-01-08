import java.io.*;

public class Main {
  
    static class Node {
        Node[] children;
        boolean isEnd;

        Node() {
            this.children = new Node[10];  // 0-9까지의 숫자를 담을 배열
            this.isEnd = false;
        }
    }

    // 트라이에 삽입함과 동시에 일관성을 체크하는 메서드
    static boolean insert(Node root, String phone) {
        Node current = root;

        // 현재 입력되는 번호가 다른 번호의 접두어인지 확인
        for (int i = 0; i < phone.length(); i++) {
            int num = phone.charAt(i) - '0';

            if (current.children[num] == null) {
                current.children[num] = new Node();
            }

            current = current.children[num];

            // 중간 노드가 이미 끝점이면 일관성이 없는 것
            if (current.isEnd && i < phone.length() - 1) {
                return false;
            }
        }

        // 현재 번호가 다른 번호의 접두어인지 확인
        for (int i = 0; i < 10; i++) {
            if (current.children[i] != null) {
                return false;
            }
        }

        current.isEnd = true;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Node root = new Node();
            boolean isConsistent = true;

            for (int j = 0; j < n; j++) {
                String phone = br.readLine();
                if (isConsistent && !insert(root, phone)) {
                    isConsistent = false;
                }
            }

            if (isConsistent) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }
}
