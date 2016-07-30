/**
 * Created by cjvnj on 28.07.2016.
 */
public class ImagesDownloaderRun extends ImagesDownloader implements Runnable {
    String chapUrl;
    boolean toOneFolder;
    ImagesDownloaderRun(String chapUrl, boolean tOneFolder){
        this.chapUrl = chapUrl;
        this.toOneFolder = tOneFolder;
        Thread th = new Thread(this);
        th.start();
    }
    public void run(){
        if(toOneFolder) {

            downloadImagesToOneFolder(chapUrl);
            DownloadRange.doqnloadProgress++;
            int pre = GUI2.jpb.getValue();
            pre++;
            GUI2.jpb.setValue(pre);
        }
        else {
            downloadImages(chapUrl);
            DownloadRange.doqnloadProgress++;
            int pre = GUI2.jpb.getValue();
            pre++;
            GUI2.jpb.setValue(pre);
        }
    }
}
