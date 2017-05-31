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
        List<String> re=new ArrayList<String>();
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

    public static void main(String[] args) {
        System.out.println(anagrams(new String[]{"lint","intl","inlt","code"}));

    }

}
