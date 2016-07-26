import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by cjvnjde on 24.07.2016.
 */
public class LinkCutter {
    //делим ссылку по "/"
    //форматы ссылок:   http://site/page
    //                  site/page
    public String[] cutLink(String url) {
        try {
            System.setErr(new PrintStream(new File("log.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String splittedUrl[];

        String cleanedUrl = cleanURL(url);

        splittedUrl = cleanedUrl.split("/");

        //----------------------------------------
        System.err.println("LinkCutter - cutLink");
        for(String str: splittedUrl) {
            System.err.println("*_"+str+"_*");
        }
        //-----------------------------------------

        return splittedUrl;
    }


    public String toOneURL(String ... someURL) {
        String url="http://";
        for(int i = 0; i < someURL.length; i++){
            url = url+someURL[i]+"/";
        }
        return url;
    }

    public String toOneString1(String ... someURL) {
        String url="";
        boolean key = false;
        for(int i = 0; i < someURL.length; i++){
            if(someURL[i].contains("vol")||key) {
                key = true;
                url = url + someURL[i-1]+"/";
            }
        }
        url += someURL[someURL.length-1]+"/";
        System.out.println("toOneString: "+url);
        return url;
    }

    public String toOneString(String ... someURL) {
        String url="";
        boolean key = false;
        for(int i = 0; i < someURL.length; i++){
            if(someURL[i].contains("vol")||key) {
                key = true;
                url = url + someURL[i]+"\\";
            }
        }
        System.out.println("toOneString: "+url);
        return url;
    }

    public String takeMangaName(String url){
        String[] urlArr;
        urlArr = url.split("/");
        return urlArr[urlArr.length-1];
    }

    public String takeMangaHost(String url) {
        //String
        String linkArr[];
        //Убираем ненужные символы
        if (url.contains("http://")) {
            url = url.replaceFirst("http://", "");
        }
        if (url.contains("\n")) {
            url = url.replaceAll("\n", "");
        }

        linkArr = url.split("/");
        /*
        for(String str: linkArr)
            System.out.print(str+"_");
        System.out.println();
        */
        return linkArr[0];
    }

    
    //очистка мусора из ссылки
    private String cleanURL(String url) {
        //Убираем ненужные символы
        if (url.contains("http://")) {
            url = url.replaceFirst("http://", "");
        }
        if (url.contains("\n")) {
            url = url.replaceAll("\n", "");
        }
        if (url.contains(" ")){
            url = url.replaceAll(" ", "");
        }
        return url;
    }
}
