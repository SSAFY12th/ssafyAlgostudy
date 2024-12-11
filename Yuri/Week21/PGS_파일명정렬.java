import java.util.ArrayList;
import java.util.Collections;

class Solution {

        static class MyString implements Comparable<MyString> {
            String head, number, tail;

            public MyString(String head, String number, String tail) {
                this.head = head;
                this.number = number;
                this.tail = tail;
            }

            @Override
            public int compareTo(MyString o) {
                // 비교할 문자열을 전부 소문자로 변경한다.
                String thisHead = head.toLowerCase();
                String nextHead = o.head.toLowerCase();
                // 비교할 숫자 문자열을 전부 int형으로 변경한다.
                int thisNumber = Integer.parseInt(number);
                int nextNumber = Integer.parseInt(o.number);
                
                if(!thisHead.equals(nextHead))
                    return thisHead.compareTo(nextHead);    // 문자열을 사전순으로(오름차순) 정렬하여 반환
                return thisNumber - nextNumber;             // 숫자가 더 작은 순서대로(오름차순) 정렬하여 반환
            }
        }
        public String[] solution(String[] files) {
            ArrayList<MyString> list = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                // 문자 한개당 head와 number, tail을 구분해야한다.
                String now = files[i];
                String head = null;
                String number = null;
                String tail = null;
                for (int j = 1; j < now.length(); j++) {
                    int compare = now.charAt(j) - '0';
                    if(head == null && compare >= 0 && compare <= 9) {
                        // 숫자인데 아직 head를 못찾았다면.
                        head = now.substring(0, j);
                    }
                    if(head != null && number == null && !(compare >= 0 && compare <= 9)) {
                        // head를 찾았는데, 숫자 뒤에 문자가 더 있다면 문자 전까지만 잘라 number에 넣는다.
                        number = now.substring(head.length(), j);
                        tail = now.substring(j);
                        break;
                    }
                }
                if(number == null) {
                    // 숫자 뒤에 문자가 더 없어서 숫자로만 끝나는 경우, head뒤부터 끝까지 잘라 number에 넣는다.
                    number = now.substring(head.length());
                }

                list.add(new MyString(head, number, tail));
            }

            Collections.sort(list);

            ArrayList<String> answer = new ArrayList<>();

            for (MyString m : list) {
                StringBuilder sb = new StringBuilder();
                // null인건 sb에 추가하지 않는다.
                if(m.head != null)
                    sb.append(m.head);
                if(m.number != null)
                    sb.append(m.number);
                if(m.tail != null)
                    sb.append(m.tail);

                answer.add(sb.toString());
            }

            return answer.toArray(new String[0]);   // ArrayList를 String[]로 변환하여 반환
        }
    }
