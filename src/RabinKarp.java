public class RabinKarp {
    public static void RabinKarpAlogrithm(char[] T,char[] P, int d,int q){
        int n=T.length;
        int m=P.length;
        if( n < m) return ;
        int h = 1;
        for(int i=1; i<=m-1; i++)
            h = h*d%q;
        int p=0, t=0;
        for(int i=0; i<m; i++) {
            p = (( d*p + P[i]) % q);
            t = (( d*t + T[i]) % q);
        }
        for(int s=0; s <n-m+1; s++) {
            if( p == t ){
                int i=0;
                for(i=0; i<m; i++)
                    if(P[i]!=T[s+i])
                        break;
                if(i==m) System.out.println("Pattern ocurs with shift:"+s);
            }
            if( s < n-m )
                t= (d* (t - T[s]*h%q) + T[s+m])  % q;
        }
        System.out.println("string matching ends");
    }
    public static void main(String[] args){
        String strT="2359023141526739921";
        String strP="31415";
        char[] T=strT.toCharArray();
        char[] P=strP.toCharArray();
        int d=10;
        int q=13;
        RabinKarp.RabinKarpAlogrithm(T,P,d,q);
    }
}