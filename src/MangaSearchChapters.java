import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cjvnj on 24.07.2016.
 */
public class MangaSearchChapters {

    //получение списка глав из ссылки
    //формат ссылки http://site/page
    //                  site/page
    //                  http://site/page/etc/...
    //                  site/page/etc/...
    String[] searchChapters(String mUrl, boolean makeUrl) throws IOException {
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

        LinkedList<String> als = new LinkedList<>();
        int i = 0;
        String str = "";
        String chapUrl;
        boolean table = false;
        boolean tr = false;
        boolean td = false;

        char sep;
        if(makeUrl){
            sep = '/';
        } else {
            sep = '-';
        }

        while (i != -1) {
            i = inpStream.read();
            fout.write(i);
            //разбиваем по строкам
            if(i != '\n'){
                str += (char) i;
            } else {
                //вход в таблицу (ссылки на главы в таблице)
                if (str.contains("<table")) {
                    table = true;
                }
                //выход из таблицы
                if (str.contains("</table")) {
                    table = false;
                }
                //вход в строку
                if (str.contains("<tr")) {
                    tr = true;
                }
                //выход из строки
                if (str.contains("</tr")) {
                    tr = false;
                }
                //вход в столбец
                if (str.contains("<td")) {
                    td = true;
                }
                //выход из столбца
                if (str.contains("</td")) {
                    td = false;
                }
                if(table && tr && td && str.contains("<a href=\"")){
                    //System.out.println("MangaSearchChapters - searchChapters");
                    //System.out.println("предположительные ссылки: "+str);
                    str = str.replaceAll("<a href=\"","").replaceAll(" ", "");
                    chapUrl = str.substring(0, str.indexOf('\"'));
                    chapUrl = chapUrl.replaceAll((mangaName), "");
                    String[] splittedChapUrl = lc.splitUrl(chapUrl);
                    System.out.println(chapUrl);
                    //выбрать разделитель false = "-" true = "/"
                    als.push(splittedChapUrl[0]+sep+splittedChapUrl[1]);
                }
                str = "";
            }
        }
        String chaps[] = new String[als.size()];
        chaps = als.toArray(chaps);
        /*
        System.out.println("MangaSearchChapters - searchChapters");
        for(String s: chaps){
            System.out.println("Содержимое массива: "+s);
        }
        */
        //System.out.println(als);
        return chaps;
    }
}
