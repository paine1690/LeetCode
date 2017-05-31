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
    
    public static void main(String[] args) {
        System.out.println(isIsomorphic("a`%ii,VEZQc_BSU%ObO5<sX81B/bOw+CNUd#Uav*P!Ax!#>hh,k#b/|>4ixFQZl+l!?bJjakbQbGglEb<g>Hf81m@A9GIvbd]qh?y__t+E(Iyv7zUEfZF{81VaM-0u?]tG=_fFR/XJ=X{-,oRpxE9u*VNYlM", 
                                        "b`%ii-WE[Qc_BSV%OcO5<sX82B/cOw+CNVd#Vbv*P!Bx!#?hh-k#c/|?4ixFQ[l+l!?cJkbkcQcGhlEc<h?Hf82m@B9GIvcd]rh?y__t+E(Iyv7{VEf[F{82WbN/0u?]tG=_fFR/XJ=X{/-oRpxE9u*WNYlN"));

    }

}
