import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashSet;

/**
 * Created by cjvnj on 24.07.2016.
 */
public class MangaList {
    LinkedHashSet<String> downloadList(String mangaLink) throws IOException {
        URL url = new URL(mangaLink);
        String str = "";
        //Получение названия манги
        String mangaName;
        LinkCutter lc = new LinkCutter();
        String[] arrLink = lc.cutLink(mangaLink);
        mangaName = arrLink[1];

        //переместить в конец
        System.out.println();
        System.out.println("URL манги: " + url);
        System.out.println("Название манги: " + mangaName);


        LinkedHashSet<String> arrr = new LinkedHashSet<>();
        int i = 0;
        InputStreamReader inpStream = new InputStreamReader(url.openStream());
        int count = 0;
        boolean linkT = false;

        while (i != -1) {
            i = inpStream.read();

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
