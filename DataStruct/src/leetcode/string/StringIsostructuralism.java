package leetcode.string;

/**
 * @author wang
 * @create 2023-2023-21-9:58
 */
public class StringIsostructuralism {
    public static void main(String[] args) {
        String s1 = "foo";
        String s2 = "bar";

        StringIsostructuralism stringIsostructuralism = new StringIsostructuralism();
        stringIsostructuralism.isIsomorphic(s1,s2);
    }

    public boolean isIsomorphic(String s, String t) {
        if ((s == null && t == null) || (s.length() == 0 && t.length() == 0)) {
            return true;
        } else if (s == null || s.length() == 0) {
            return false;
        } else if (t == null || t.length() == 0) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        char[] chS = s.toCharArray();

        char[] chT = t.toCharArray();
        int nums[] = new int[256];

        for (int i = 0; i < chS.length; i++) {
            if (chT[i] == chS[i]) {
                continue;
            } else {
                if (nums[chT[i]] == 0) {
                    nums[chT[i]] = chS[i];
                } else if (nums[chT[i]] != chS[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
