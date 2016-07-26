import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import static java.lang.System.*;

/**
 * Created by cjvnj on 26.07.2016.
 */
public class Consol{
    String chapUrls[];
    String start, finish;
    String mangaUrl;
    String[] startArr, finishArr;
    Consol() {
        out.println("Введите url манги"+
        "\nв формате: http://readmanga.me/manganame");
        //InputStreamReader inStream = new InputStreamReader(new Strin)
        InputStreamReader inStream = new InputStreamReader(System.in);
        String url = "";
        int i = 0;
        while(i != -1) {
            try {
                i = inStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (i != '\n') {
                url += (char) i;
                //System.out.print((char) i);
            } else {
                url = url.replaceAll(" ", "");
                mangaUrl = url;
                url = "";
                System.out.println("Ваша ссылка: " + mangaUrl);
                MangaSearchChapters msc = new MangaSearchChapters();
                chapUrls = msc.searchChapters(mangaUrl, true);     //!!!!!true иначе DownloaderRange не сработает
                System.out.println("Первая доступная глава: " + chapUrls[0] +
                        "\nПоследняя доступная глава: " + chapUrls[chapUrls.length - 1]);
                System.out.println("Введите начальную главу для загрузки (volXX-YY) (volXX/YY):");
                //url = "";
                break;

            }
        }
            i = 0;
            boolean count = true;
            while(i != -1) {
                try {
                    i = inStream.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(count) {
                    if (i != '\n') {
                        start += (char) i;
                    } else {
                        count = false;
                        System.out.println("Начало загрузки: "+start);
                        System.out.println("Введите конечную главу для загрузки (volXX-YY) (volXX/YY):");
                    }
                }else {
                    if (i != '\n') {
                        finish += (char) i;
                    } else {
                        System.out.println("Конец загрузки загрузки: "+finish);
                        break;
                    }
                }
            }
            for(String chaps: chapUrls) {
                System.out.println(chaps);
            }
            System.out.println(start+" fin "+finish);
        System.out.println(mangaUrl);
        start = start.replaceAll("null", "");
        finish = finish.replaceAll("null", "");
            DownloadRange dr = new DownloadRange(chapUrls, start, finish, mangaUrl);
            //System.out.print((char)i);
        }


    }

