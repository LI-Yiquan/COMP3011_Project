import java.util.Arrays;

public class BM {
    public int indexOf(String source, String pattern) {
        char[] src = source.toCharArray();
        char[] ptn = pattern.toCharArray();
        int sLen = src.length;
        int pLen = ptn.length;
        if (pLen == 0) {
            return 0;
        }
        if (sLen < pLen) {
            return -1;
        }
        int[] BC = buildBadCharacter(ptn);
        int[] GS = buildGoodSuffix(ptn);
        for (int i = pLen - 1; i < sLen; ) {
            int j = pLen - 1;
            for (; src[i] == ptn[j]; i--, j--) {
                if (j == 0) {
                    return i;
                }
            }

            i += Math.max(BC[src[i]], GS[pLen - 1 - j]);
        }

        return -1;
    }

    private static int[] buildBadCharacter(char[] pattern) {
        int pLen = pattern.length;
        final int CHARACTER_SIZE = 99999;
        int[] BC = new int[CHARACTER_SIZE];

        Arrays.fill(BC, pLen);
        for (int i = 0; i < pLen - 1; i++) {
            int ascii = pattern[i];
            BC[ascii] = pLen - 1 - i;
        }
        return BC;
    }
    private static int[] buildGoodSuffix(char[] pattern) {
        int pLen = pattern.length;
        int[] GS = new int[pLen];
        int lastPrefixPos = pLen;

        for (int i = pLen - 1; i >= 0; i--) {
            if (isPrefix(pattern, i + 1)) {
                lastPrefixPos = i + 1;
            }
            GS[pLen - 1 - i] = lastPrefixPos - i + pLen - 1;
        }
        for (int i = 0; i < pLen - 1; i++) {
            int suffixLen = suffixLength(pattern, i);
            GS[suffixLen] = pLen - 1 - i + suffixLen;
        }

        return GS;
    }
    private static boolean isPrefix(char[] pattern, int begin) {
        for (int i = begin, j = 0; i < pattern.length; i++, j++) {
            if (pattern[i] != pattern[j]) {
                return false;
            }
        }
        return true;
    }
    private static int suffixLength(char[] pattern, int begin) {
        int suffixLen = 0;

        int i = begin;
        int j = pattern.length - 1;
        while (i >= 0 && pattern[i] == pattern[j]) {
            suffixLen++;
            i--;
            j--;
        }
        return suffixLen;
    }
}