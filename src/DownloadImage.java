import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import javax.imageio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by cjvnj on 25.07.2016.
 */
public class DownloadImage {
    public void imageDownload(String imageLink, String path) throws IOException {
        try(InputStream in = new URL(imageLink).openStream()){
            Files.copy(in, Paths.get(path));
        }
    }
}
