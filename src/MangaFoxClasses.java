import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by cjvnj on 29.07.2016.
 */
public class MangaFoxClasses {
    public int maxPage(String chap){
        String str = "";
        URL url = null;
        try {
            url = new URL(chap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader inStream = null;
        try {
           inStream = new InputStreamReader(new GZIPInputStream(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i =0;
        while(i != -1){
            try {
                i = inStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(i != '\n'){
                str += (char) i;
            } else {
                if(str.compareTo("var total_pages=")==0){
                    str = str.replaceAll(" ", "").replaceAll("var total_pages=", "").replaceAll(";", "");
                    return Integer.parseInt(str);
                }
            }
            str = "";
        }
        return -1;
    }


}
