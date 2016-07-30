import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

/**
 * Created by cjvnj on 24.07.2016.
 */
public class MangaSearchChapters {

    URL url = null;
    InputStreamReader inpStream = null;
    int i = 0;
    String str = "";
    String chapUrl;
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
        String mangaUrl;
        if(mangaHost.compareTo("mangafox.me")==0){
            mangaUrl = "http://" + mangaHost + "/manga/" + mangaName;
        }
        else {
            mangaUrl = "http://" + mangaHost + "/" + mangaName;
        }
        LinkedList<String> als = new LinkedList<>();
        char sep;
        if (makeUrl) {
            sep = '/';
        } else {
            sep = '-';
        }

        try {
            url = new URL(mangaUrl);
        } catch (MalformedURLException e) {
            System.out.println("MangaSearchChapters - searchChapters");
            System.out.println("Ошибка url: " + e);
        }
        if(mangaHost.compareTo("mintmanga.com")==0||mangaHost.compareTo("readmanga.me")==0) {
            boolean table = false;
            boolean tr = false;
            boolean td = false;
           // URLConnection conn = null;
            try {
               // conn = url.openConnection();
                inpStream = new InputStreamReader(url.openStream());
            } catch (IOException e) {
                System.out.println("MangaSearchChapters - searchChapters");
                System.out.println("Ошибка IO(url.openStream): " + e);
            }
        /*
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(mangaName+".txt");

        } catch (FileNotFoundException e) {
            System.out.println("MangaSearchChapters - searchChapters");
            System.out.println("ошибка файла: "+e);
        }
*/
            while (i != -1) {
                try {
                    i = inpStream.read();
                    //  fout.write(i);
                } catch (IOException e) {
                    System.out.println("MangaSearchChapters - searchChapters");
                    System.out.println("Ошибка IO(read/write): " + e);
                }
                //разбиваем по строкам
                if (i != '\n') {
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
                    if (table && tr && td && str.contains("<a href=\"")) {
                        //System.out.println("MangaSearchChapters - searchChapters");
                        //System.out.println("предположительные ссылки: "+str);
                        str = str.replaceAll("<a href=\"", "").replaceAll(" ", "");
                        chapUrl = str.substring(0, str.indexOf('\"'));
                        chapUrl = chapUrl.replaceAll((mangaName), "");
                        String[] splittedChapUrl = lc.splitUrl(chapUrl);
                        // System.out.println("MangaSearchChapters - searchChapters");
                        //System.out.println("chapUrl "+chapUrl);
                        //выбрать разделитель false = "-" true = "/"
                        als.push(splittedChapUrl[0] + sep + splittedChapUrl[1]);
                       // System.out.println("chapUrl "+splittedChapUrl[0]+" "+splittedChapUrl[1]+" "+sep);
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
         // System.out.println(als);
            url = null;
            try {
                inpStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i = 0;
            str = "";
            chapUrl="";
            return chaps;
        } else if(mangaHost.compareTo("mangafox.me")==0){
            //boolean div = false;
            int div = 0;
            boolean ul = false;
            boolean li = false;
            try {
                inpStream = new InputStreamReader(url.openStream());
            } catch (IOException e){
                System.out.println("MangaSearchChapters - searchChapters");
                System.out.println("Ошибка IO(read/write): " + e);
            }
            while(i != -1){
                try {
                    i = inpStream.read();
                   // System.out.print((char)i);
                    if(i != '\n')
                    str += (char) i;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(i == '\n') {

                    if (str.contains("<div")) {
                    //    System.out.println("<div");
                        div++;
                    }
                    if (str.contains("</div")) {
                     //   System.out.println("</div");
                        div--;
                    }
                    if (str.contains("<li")) {
                     //   System.out.println("<li");
                        li = true;
                    }
                    if (str.contains("</li")) {
                    //    System.out.println("</li");
                        li = false;
                    }
                    if (str.contains("<ul")) {
                   //     System.out.println("<ul");
                        ul = true;
                    }
                    if (str.contains("</ul")) {
                     //   System.out.println("</ul");
                        ul = false;
                    }

                    if (ul && li && div==4 && str.contains("<a href=\"")) {
                       str = str.replaceAll(" ", "");
                    //    System.out.println(str);
                        int firstIndex = str.indexOf('\"')+1;
                        int secondIndex = str.indexOf('\"',firstIndex);
                       // System.out.println(firstIndex+" "+secondIndex);

                        chapUrl = str.substring(firstIndex, secondIndex);
                        //chapUrl = chapUrl.replaceAll(("manga"+(mangaName)), "");
                        String[] splittedChapUrl = lc.splitUrl(chapUrl);
                       // for(String split: splittedChapUrl)
                          //  System.out.println("chap "+chapUrl);
                        // System.out.println("MangaSearchChapters - searchChapters");
                        //System.out.println("chapUrl "+chapUrl);
                        //выбрать разделитель false = "-" true = "/"
                        als.push(splittedChapUrl[3] + sep + splittedChapUrl[4]);


                      //  System.out.println(str);
                    }
                    str = "";
                }


            }
          //  System.out.println(als);
            String chaps[] = new String[als.size()];
            chaps = als.toArray(chaps);
            return chaps;
        } else
            return new String[2];
    }
}
