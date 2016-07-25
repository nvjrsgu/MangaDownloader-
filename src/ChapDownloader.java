import org.omg.CORBA.Environment;

import java.io.IOException;
import java.io.File;

/**
 * Created by cjvnj on 25.07.2016.
 */
public class ChapDownloader {
    DownloadImage di;
    ImageLinks il;
    String[] links;
    LinkCutter lc;
    File f;
    public void chapD(String chapUrl) throws IOException {
        il = new ImageLinks();
        links = il.imLink(chapUrl);

        lc = new LinkCutter();
        String[] cutted = lc.cutLink(chapUrl);

        String path = lc.toOneString1(cutted);

        System.out.println("Путь: "+path);
        il = new ImageLinks();

        links = il.imLink(chapUrl);
        di = new DownloadImage();
        String imageName;
        String[] im;

        f = new File(path);

        if(!f.exists()) {
            f.mkdirs();
            for(String link: links){
                im = lc.cutLink(link);
                imageName = im[im.length-1];
                System.out.println("Ссылка: "+link+"\nПуть: "+(path+imageName));
                di.imageDownload(link,path+imageName);
            }
        } else {
            System.out.println("Эта глава уже скачана");
        }




    }
}
