import java.io.File;
import java.io.IOException;

/**
 * Created by cjvnj on 26.07.2016.
 */
public class ImagesDownloader {
    /*
    Загрузка изображений из глав
    формат ссылки   http://host.xu/name/vol/chap
                    host.xu/name/vol/chap
     */
    public void downloadImages(String chapUrl) {
        ChapterSearchImages csi = new ChapterSearchImages();
        String imageUrls[] = csi.searchImages(chapUrl);
        LinkCutter lc = new LinkCutter();
        String mangaName = lc.takeMangaName(chapUrl);
        String mangaChap = lc.takeMangaChap(chapUrl);
        String mangaVol = lc.takeMangaVol(chapUrl);
        String mangaHost = lc.takeMangaHost(chapUrl);

        String path = mangaHost+"/"+mangaName+"/"+mangaVol+"/"+mangaChap+"/";

        String[] splittedImageUrl;
        File f = new File(path);
        if(!f.exists()) {
            f.mkdirs();
            for (String im : imageUrls) {

                splittedImageUrl = lc.splitUrl(im);
                String imageName = splittedImageUrl[splittedImageUrl.length - 1];
                new DownloadImageRun(im, path+imageName);
            }
        } else {
            System.out.println("Эта глава уже скачана");
        }
    }


    public void downloadImagesToOneFolder(String chapUrl) {
        ChapterSearchImages csi = new ChapterSearchImages();
        String imageUrls[] = csi.searchImages(chapUrl);
        LinkCutter lc = new LinkCutter();
        String mangaName = lc.takeMangaName(chapUrl);
        String mangaChap = lc.takeMangaChap(chapUrl);
        String mangaVol = lc.takeMangaVol(chapUrl);
        String mangaHost = lc.takeMangaHost(chapUrl);

        String path = mangaHost+"/"+mangaName+"/";

        String[] splittedImageUrl;
        File f = new File(path);
        if(!f.exists()) {
            f.mkdirs();
        }
            for (String im : imageUrls) {
                splittedImageUrl = lc.splitUrl(im);
                String imageName = mangaName+"_"+mangaVol+"_"+mangaChap+"_"+splittedImageUrl[splittedImageUrl.length - 1];
                new DownloadImageRun(im, path+imageName);
            }
        }


    }
