/**
 * Created by cjvnjde on 24.07.2016.
 */
public class LinkCutter {
    //делим ссылку по "/"
    //форматы ссылок:   http://site/page
    //                  site/page
    public String[] splitUrl(String url) {
        String splittedUrl[];

        String cleanedUrl = cleanURL(url);

        splittedUrl = cleanedUrl.split("/");

        //проверка
        System.out.println("LinkCutter - cutLink");
        for(String str: splittedUrl) {
            System.out.print("_"+str+"_");
        }
        System.out.println();

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



    //определить название манги
    public String takeMangaName(String url){
        String[] urlArr = splitUrl(url);
        //проверка
        System.out.println("LinkCutter - takeMangaHost\n"+urlArr[1]);
        return urlArr[1];
    }

    //определить ресурс манги
    public String takeMangaHost(String url) {
        String[] urlArr = splitUrl(url);
        //проверка
        System.out.println("LinkCutter - takeMangaHost\n"+urlArr[0]);
        return urlArr[0];
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
