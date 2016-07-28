import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by cjvnj on 28.07.2016.
 */
public class DownloadImageRun implements Runnable {
    String imageLink;
    String path;
    DownloadImageRun(String imageLink, String path) {
        this.imageLink = imageLink;
        this.path = path;
        Thread myThready = new Thread(this);	//Создание потока "myThready"
        myThready.start();
    }
    public void run(){
        try(InputStream in = new URL(imageLink).openStream()){
            Files.copy(in, Paths.get(path));
          //  System.out.println("поток");
        } catch (IOException e){
            System.out.println("DownloadImage - imageDownload");
            System.out.println("Ошибка IO: "+e);

        }
    }
}
