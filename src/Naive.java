public class Naive {
    public int bruteForce(String main, String ptn){
        if (main == null || ptn == null){
            return -1;
        }
        int m = main.length();
        int n = ptn.length();
        if (n > m){
            return bruteForce(ptn, main);
        }

        for (int i = 0; i <= m - n; i++) {
            int j = i;
            int k = 0;
            while (k < n && main.charAt(j) == ptn.charAt(k)){
                ++j;
                ++k;
            }
            if (k == n){
                return i;
            }
        }
        return -1;
    }
}
