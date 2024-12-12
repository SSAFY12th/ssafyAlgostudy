import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (s1, s2) -> {
            String[] split1 = extractParts(s1);
            String[] split2 = extractParts(s2);
            
            int headCompare = split1[0].compareToIgnoreCase(split2[0]);
            if (headCompare != 0) return headCompare;
            
            return Integer.compare(
                Integer.parseInt(split1[1]), 
                Integer.parseInt(split2[1])
            );
        });
        
        return files;
    }
    
    private String[] extractParts(String file) {
        String[] parts = new String[3];
        
        int numberStart = -1;
        for (int i = 0; i < file.length(); i++) {
            char c = file.charAt(i);
            if (c >= '0' && c <= '9') {
                numberStart = i;
                break;
            }
        }
        
        parts[0] = file.substring(0, numberStart).toLowerCase();
        
        int numberEnd = numberStart;
        while (numberEnd < file.length() && 
               file.charAt(numberEnd) >= '0' && 
               file.charAt(numberEnd) <= '9' && 
               numberEnd - numberStart < 5) {
            numberEnd++;
        }
        parts[1] = file.substring(numberStart, numberEnd);
        
        parts[2] = numberEnd < file.length() ? 
                   file.substring(numberEnd) : "";
        
        return parts;
    }
}
