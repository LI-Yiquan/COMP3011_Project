import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class Application {
    public static void main(String[] args){
        BM bm = new BM();//Can also try other algorithms!
        String[] queries = {"diffusion","Stanford","PDE","Song","MIT"};//Choose the word you want!
        try {
            for(String query :queries){
                System.out.println("Search for "+query+":");
                String path = "Papers";
                int num=0;
                File f = new File(path);
                if (!f.exists()) {
                    System.out.println(path + " not exists");
                    return;
                }
                File[] fa = f.listFiles();
                for (File fs : fa) {
                    if (!fs.isDirectory()) {
                        num++;
                        String pathname = path+"/"+fs.getName();
                        File filename = new File(pathname);
                        InputStreamReader reader = new InputStreamReader(
                                new FileInputStream(filename));
                        BufferedReader br = new BufferedReader(reader);
                        String line = "";
                        StringBuilder article = new StringBuilder();
                        line = br.readLine();
                        while (line != null) {
                            line = br.readLine();
                            article.append(line);
                        }
                        int res = bm.indexOf(article.toString(), query);
                        System.out.println("Paper"+num+" "+query+" at "+res);
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
