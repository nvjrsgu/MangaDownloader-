import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.File;
public class Main {

    public static void main(String[] args) throws Exception {

	   LinkCutter lc= new LinkCutter();
        String cuttedLink[] = lc.splitUrl("http://readmanga.me/fairytail/vol58/494/ / / ");
        String name = lc.takeMangaName("http://readmanga.me/fairytail/vol58/494/ / / ");
        String host = lc.takeMangaHost("http://readmanga.me/fairytail/vol58/494/ / / ");

        MangaSearchChapters msc = new MangaSearchChapters();
        msc.searchChapters("http://readmanga.me/bleach_", false);
        /*
        String manga = "http://readmanga.me/noblesse";
        MangaList mn = new MangaList();
        LinkedHashSet<String> lhs = mn.downloadList(manga);
        System.out.println(lhs);

        String mangaHost = lc.takeMangaHost(manga);
        String mangaName = lc.takeMangaName(manga);
        ToArray ta = new ToArray();
        int[][] chapList = ta.chapArr(lhs, mangaName);
        //какие главы скачивать
        ArrayList<String> arrList = ta.fromArray(chapList,1,5, false);

        ArrayList<String> list = ta.fromArray(chapList);

        System.out.println(list);
       // System.out.println(lc.toOneURL("fairytail","34", "766"));

      //  ImageLinks iml = new ImageLinks();

       // String arrIm[] = iml.imLink("http://readmanga.me/noblesse/vol7/415");
       // for(String st: arrIm)
        //    System.out.println(st+"\n");

        String chap1 = "";
        String url1 = "http://"+mangaHost+"/"+mangaName+"/";
        System.out.println(url1);
        //DownloadImage di = new DownloadImage();
        //загрузка нужных глав (не по порядку)

        ChapDownloader cd = new ChapDownloader();
        for(String chap: arrList) {
            System.out.println("глава: "+chap);
          //cd.chapD(url1+chap);
        }
*/
        //SwingUtilities.invokeLater(() -> new MainGUI());

    }
}
