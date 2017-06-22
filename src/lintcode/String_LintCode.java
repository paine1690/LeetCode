package lintcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class String_LintCode {

    // 171 乱序字符串
    private static String getHash(String s) {
        StringBuilder re = new StringBuilder();
        int[] a = new int[128];
        for (char c : s.toCharArray()) {
            a[c]++;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (a[c] > 0) {
                re.append(c).append(a[c]);
            }
        }
        return re.toString();
    }

    public static List<String> anagrams(String[] strs) {
        List<String> re = new ArrayList<String>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            String s = getHash(str);
            List<String> list = map.get(s);
            if (list == null) {
                list = new ArrayList<String>();
            }
            list.add(str);
            map.put(s, list);
        }
        for (Entry<String, List<String>> entry: map.entrySet()){
            if (entry.getValue().size() > 1) {
                re.addAll(entry.getValue());
            }
        }
        return re;
    }
    
    //两个字符串是变位词
    public boolean anagram2(String s, String t) {
        int[] a = new int[128];
        for (char c : s.toCharArray()) {
            a[c]++;
        }
        for (char c : t.toCharArray()) {
            a[c]--;
        }
        for (char c = 'a'; c <= 'z'; c++){
            if (a[c] != 0) {
                return false;
            }
        }
        return true;
    }

    //Strings Homomorphism
    private static boolean isIsomorphic2(String s, String t) {
        int[] a = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (a[c1] == 0) {
                System.out.println(c1); 
                a[c1] = c2;
            } else if (a[c1] != c2) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isIsomorphic(String s, String t) {
        return isIsomorphic2(s, t)&&isIsomorphic2(t, s);
    }
    
    //Substring Anagrams
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> re = new ArrayList<Integer>();
        int[] a = new int[26];
        int len = p.length();
        for (int i = 0; i < p.length(); i++) {
            a[p.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (i >= len) {
                a[s.charAt(i - len) - 'a']++;
            }
            a[s.charAt(i) - 'a']--;
            int j = 0;
            for (; j < a.length; j++) {
                if (a[j] != 0) {
                    break;
                }
            }
            if (j == a.length) {
                re.add(i - len + 1);
            }
        }        
        return re;
    }
    
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebbacd", "abc"));

    }

}
