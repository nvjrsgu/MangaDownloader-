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
        //System.out.println("LinkCutter - cutLink");
        //for(String str: splittedUrl) {
       //     System.out.print("*"+str+"*");
       // }
        //System.out.println();

        return splittedUrl;
    }

    //определить ресурс манги
    public String takeMangaHost(String url) {
        String[] urlArr = splitUrl(url);
        //проверка
        if(urlArr.length>=1) {
            //System.out.println("LinkCutter - takeMangaHost\n"+urlArr[0]);
            return urlArr[0];
        } else {
            //System.out.println("LinkCutter - takeMangaHost\nnot found host");
            return "not found host";
        }
    }

    //определить название манги
    public String takeMangaName(String url){
        String[] urlArr = splitUrl(url);
        //проверка
        if(urlArr.length>=2) {
            //System.out.println("LinkCutter - takeMangaName\n"+urlArr[1]);
            return urlArr[1];
        } else {
            //System.out.println("LinkCutter - takeMangaName\nnot found name");
            return "not found name";
        }
    }

    public String takeMangaVol(String url) {
        String[] urlArr = splitUrl(url);
        if(urlArr.length>=3) {
          //  System.out.println("LinkCutter - takeMangaHost\n"+urlArr[2]);
            return urlArr[2];
        } else {
         //   System.out.println("LinkCutter - takeMangaHost\nnot found vol");
            return "not found vol";
        }
    }

    public String takeMangaChap(String url) {
        String[] urlArr = splitUrl(url);
        //проверка

        if(urlArr.length>=4) {
           // System.out.println("LinkCutter - takeMangaHost\n"+urlArr[3]);
            return urlArr[3];
        } else {
           // System.out.println("LinkCutter - takeMangaHost\nnot found chapter");
            return "not found chapter";
        }
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
        if(url.indexOf("/")==0){

            url = url.substring(2);
          // System.out.println("cleanURL: "+url);
        }
        return url;
    }
}
