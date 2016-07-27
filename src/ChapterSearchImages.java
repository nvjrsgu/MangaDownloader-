import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by cjvnj on 26.07.2016.
 */
public class ChapterSearchImages {
    /*
    Поиск ссылок на изображения в главах
    формат ссылки   http://host.xu/name/vol/chap
                    host.xu/name/vol/chap
     */
    public String[] searchImages(String chapUrl) {
        LinkCutter lc = new LinkCutter();
        String mangaName = lc.takeMangaName(chapUrl);
        String mangaVol = lc.takeMangaVol(chapUrl);
        String mangaChap = lc.takeMangaChap(chapUrl);
        String mangaHost = lc.takeMangaHost(chapUrl);
        if(mangaHost.compareTo("mintmanga.com")==0) {
            chapUrl = "http://" + mangaHost + "/" + mangaName + "/" + mangaVol + "/" + mangaChap+"?mature=1";
        } else {
            chapUrl = "http://" + mangaHost + "/" + mangaName + "/" + mangaVol + "/" + mangaChap;
        }
        URL url = null;
        try {
            url = new URL(chapUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader inpStream = null;
      //  FileOutputStream files = null;
        try {
            inpStream = new InputStreamReader(url.openStream());
      //     files = new FileOutputStream(mangaName+"_"+mangaVol+"_"+"ch"+mangaChap+".txt");
      } catch (IOException e) {
           e.printStackTrace();
        }

        int i=0;

        String str = "";
        String[] imagesLinks = null;

        while(i != -1) {
            try {
                i = inpStream.read();
       //         files.write(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(i != '\n'){
                str += (char) i;
            } else {
                if(str.contains("rm_h.init")){
                  //  System.out.println(str);
                    str = str.replaceAll("\'",",").replaceAll(" ", ",").replaceAll("\\[",",").
                            replaceAll("]", ",").replaceAll("\"",",").replaceAll("\\(", ",").replaceAll("\\)", ",");
                   //System.out.println(str);
                    imagesLinks = str.split(",");

                    break;
                }
                str = "";
            }
        }

        String[] imageParts = new String[3];
        LinkedList<String> llImages = new LinkedList<>();
        int count = 0;

        for(int n = 0; n < imagesLinks.length; n++){
            if(imagesLinks[n].contains("/")){
                switch (count) {
                    case 0: imageParts[1] = imagesLinks[n];
                        count++;
                        break;
                    case 1: imageParts[0] = imagesLinks[n];
                        count++;
                        break;
                    case 2: imageParts[2] = imagesLinks[n];
                        llImages.push(imageParts[0]+imageParts[1]+imageParts[2]);
                        count = 0;
                        break;
                }
            }
        }

       // System.out.println(llImages);

        String[] fullImageLinks = new String[llImages.size()];
        fullImageLinks = llImages.toArray(fullImageLinks);
        return fullImageLinks;
    }
}
