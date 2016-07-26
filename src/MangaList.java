/**
 * Created by cjvnj on 26.07.2016.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashSet;

/**
 * Created by cjvnj on 24.07.2016.
 */
public class MangaList {

    //получение списка глав из ссылки
    //формат ссылки http://site/page
    //                  site/page
    //                  http://site/page/etc/...
    //                  site/page/etc/...
    LinkedHashSet<String> downloadList(String mUrl) throws IOException {
        LinkCutter lc = new LinkCutter();
        //берем из ссылки нужные части
        String mangaName = lc.takeMangaName(mUrl);
        String mangaHost = lc.takeMangaHost(mUrl);

        //заново создаем ссылку
        String mangaUrl = "http://"+mangaHost+"/"+mangaName;

        System.out.println("MangaSearchChapters - searchChapters");
        System.out.println("mangaUrl: "+mangaUrl);

        URL url = new URL(mangaUrl);
        InputStreamReader inpStream = new InputStreamReader(url.openStream());
        FileOutputStream fout = new FileOutputStream(mangaName+".txt");

        LinkedHashSet<String> arrr = new LinkedHashSet<>();
        int i = 0;
        String str = "";
        int count = 0;
        boolean linkT = false;

        while (i != -1) {
            i = inpStream.read();
            fout.write(i);

            if (linkT || i == '<') {
                if (i == '\"')
                    count++;
                linkT = true;
                if (i != '\"' && i != '\'')
                    str += (char) i;
                if(i == '>'){
                    str = "";
                    linkT = false;
                    count = 0;
                }
            }
            //если в строке есть "..."
            if (count == 2) {
                linkT = false;
                count = 0;
                //поиск ссылок
                if (str.contains("<a href=")) {
                    str = str.replace("<a href=", "");
                    //System.out.println("Ссыки: " + str);
                    if (str.contains(mangaName)) {
                        if (str.contains("vol")&& !str.contains("page")) {
                            arrr.add(str);
                        }
                    }
                }
                str = "";
            }
        }
        //System.out.println("Ссылки на главы: \n" + arrr);
        return arrr;
    }
}
