import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List <List <pair>> nodes = new ArrayList<>();
        int[] dist = new int[N];
        PriorityQueue<pair> pq = new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.val - o2.val;
            }
        });
        // 간선 비용이 낮은 순으로 오름차순 정렬하여 PQ를 쓴다
        for (int i = 0; i < N; i++) {
            nodes.add(new ArrayList<>());
        }
        // 인접 리스트 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            nodes.get(a - 1).add(new pair(b - 1, weight));
        } // 노드 간 정보 저장
        
        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        Arrays.fill(dist, Integer.MAX_VALUE);
        int curNode = startNode - 1;
        int curDist = 0;
        // 시작 노드에서는 거리 0으로 넣어줌
        dist[curNode] = curDist;
        pq.add(new pair(curNode, curDist));
        while (!pq.isEmpty()) {
            curNode = pq.peek().curNode;
            curDist = pq.peek().val;
            pq.poll();
            if (curDist > dist[curNode]) break;
            for (int i = 0; i < nodes.get(curNode).size(); i++) {
                int nextNode = nodes.get(curNode).get(i).curNode;
                int nextval = nodes.get(curNode).get(i).val;
                if (nextval + dist[curNode] < dist[nextNode]) {
                    dist[nextNode] = nextval + dist[curNode];
                    pq.offer(new pair(nextNode, nextval));
                }
            }
        }
        System.out.println(dist[endNode - 1]);
    }
    public static class pair {
        int curNode, val;
        pair(int curNode, int val) {
            this.curNode = curNode;
            this.val = val;
        }
    }
}

/*
C++
#include <iostream>
#include <vector>
#include <queue>
using namespace std;
/*
다익스트라 알고리즘 - 시작 노드에서 도착 노드까지의 최소 비용을 계산

1) 현 정점에서 방문하지 않은 다른 정점들까지의 최단 경로를 모두 찾는다.
1-1) 최소 거리에 대한 노드를 방문할 예정으로 방문 처리하고 현 노드를 걔로 바꾼다.
2) 걔를 방문해서 갈 때 다른 노드로 넘어가는 가중치를 계산해서 Dist 테이블 업데이트
*/
int main() {
	int N, M;
	cin >> N;
	cin >> M;
	vector <vector <pair <int, int>>> nodes(N + 1);
	priority_queue <pair <int, int>> pq;
	vector <int> dist(N + 1, 1000000000);
	for (int i = 0; i < M; i++) {
		int a, b, weight;
		cin >> a >> b >> weight;
		nodes[a].push_back(make_pair(b, weight));
	}
	int startnode, endnode;
	cin >> startnode >> endnode;
	int curnode = startnode;
	int curdist = 0;
	dist[curnode] = curdist;
	pq.push(make_pair(curnode, 0));
	while (!pq.empty()) {
		curnode = pq.top().first;
		curdist = -pq.top().second;
		pq.pop();
		if (curdist > dist[curnode]) continue;
		//if (curnode == endnode) break;
		for (int i = 0; i < nodes[curnode].size(); i++) {
			int nextnode = nodes[curnode][i].first;
			int nextval = nodes[curnode][i].second;
			if (dist[nextnode] > nextval + curdist) {
				dist[nextnode] = nextval + curdist;
				pq.push(make_pair(nextnode, -dist[nextnode]));
			}
		}
	}
	cout << dist[endnode];
}



*/
