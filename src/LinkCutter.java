/**
 * Created by cjvnjde on 24.07.2016.
 */
public class LinkCutter {

    public String[] cutLink(String link) {

        //String
        String linkArr[];
        //Убираем ненужные символы
        if (link.contains("http://")) {
            link = link.replaceFirst("http://", "");
        }
        if (link.contains("\n")) {
            link = link.replaceAll("\n", "");
        }

        linkArr = link.split("/");
        for(String str: linkArr)
            System.out.print(str+"_");
        System.out.println();
        return linkArr;
    }

    public String toOneURL(String ... someURL) {
        String url="http://";
        for(int i = 0; i < someURL.length; i++){
            url = url+someURL[i]+"/";
        }
        return url;
    }
}
