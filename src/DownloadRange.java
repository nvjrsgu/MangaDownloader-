/**
 * Created by cjvnj on 26.07.2016.
 */
public class DownloadRange {
    DownloadRange(String[] chapArray, String start, String finish, String url) {
        LinkCutter lc = new LinkCutter();
        boolean format;
        System.out.println("name:");
        String mangaName = lc.takeMangaName(url);
        System.out.println("host:");
        String mangaHost = lc.takeMangaHost(url);
        //char ch[] = chapArray[0].toCharArray();



        start = start.replaceAll("-","/");
        finish = finish.replaceAll("-", "/");

        boolean canStart = false;
        String urlChap;
        for(String str1: chapArray){
            if(str1.compareTo(start)==0){
                System.out.println("начало найдено");
            }
            if(str1.compareTo(finish)==0){
                System.out.println("конец найден");
            }
        }
        ImagesDownloader id = new ImagesDownloader();
        for(int i = 0; i < chapArray.length; i++) {
            urlChap = "http://"+mangaHost+"/"+mangaName+"/";
            //System.out.println(urlChap);
            if((chapArray[i].compareTo(start) == 0)||canStart){
                System.out.println(urlChap);
                canStart = true;
                    urlChap = urlChap + chapArray[i].replace("-","/");
                    System.out.println("urlChap: "+urlChap);
                id.downloadImages(urlChap);
                if(chapArray[i].compareTo(finish) == 0){
                    break;
                }
            }
        }

    }
}