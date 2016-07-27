import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by cjvnj on 24.07.2016.
 */
public class MangaSearchChapters {

    //получение списка глав из ссылки
    //формат ссылки http://site/page
    //                  site/page
    //                  http://site/page/etc/...
    //                  site/page/etc/...
    String[] searchChapters(String mUrl, boolean makeUrl) {
        LinkCutter lc = new LinkCutter();
        //берем из ссылки нужные части
        String mangaName = lc.takeMangaName(mUrl);
        String mangaHost = lc.takeMangaHost(mUrl);

        //заново создаем ссылку
        String mangaUrl = "http://"+mangaHost+"/"+mangaName;

       // System.out.println("MangaSearchChapters - searchChapters");
        //System.out.println("mangaUrl: "+mangaUrl);

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

        URL url = null;
        try {
            url = new URL(mangaUrl);
        } catch (MalformedURLException e) {
            System.out.println("MangaSearchChapters - searchChapters");
            System.out.println("Ошибка url: "+e);
        }
        InputStreamReader inpStream = null;
        try {
            inpStream = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            System.out.println("MangaSearchChapters - searchChapters");
            System.out.println("Ошибка IO(url.openStream): "+e);
        }
       // FileOutputStream fout = null;
       // try {
      //      fout = new FileOutputStream(mangaName+".txt");

     //   } catch (FileNotFoundException e) {
     //       System.out.println("MangaSearchChapters - searchChapters");
     //       System.out.println("ошибка файла: "+e);
    //    }

        while (i != -1) {
            try {
                i = inpStream.read();
            //    fout.write(i);
            } catch (IOException e) {
                System.out.println("MangaSearchChapters - searchChapters");
                System.out.println("Ошибка IO(read/write): "+e);
            }
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
                   // System.out.println("MangaSearchChapters - searchChapters");
                    //System.out.println("chapUrl "+chapUrl);
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
      //  System.out.println(als);
        return chaps;
    }
}
