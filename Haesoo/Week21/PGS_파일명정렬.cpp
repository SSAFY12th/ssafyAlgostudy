#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

struct file {
    int order;
    string head;
    int number;
    bool operator <(const file& f) const {
        if (head == f.head) {
            if (number == f.number) {
                return order < f.order;
            }
            else return number < f.number;
        }
        else return head < f.head;
    }
};

vector <file> v;

vector<string> solution(vector<string> files) {
    vector<string> answer;

    for(int i = 0; i < files.size(); i++) {
        vector <int> idx;
        for(int j = 0; j < files[i].size(); j++) {
            if('0' <= files[i][j] && files[i][j] <= '9') {
                idx.push_back(j);
            }
        }
        string head = "";
        for(int j = 0; j < idx[0]; j++){
            head += tolower(files[i][j]);
        }
        string number = files[i].substr(idx[0], idx.size());

        file f;
        f.order = i;
        f.head = head;
        f.number = stoi(number);

        v.push_back(f);
    }
    sort(v.begin(), v.end());
    
    for(int i = 0; i < v.size(); i++) {
        answer.push_back(files[v[i].order]);
    }
    return answer;
}
