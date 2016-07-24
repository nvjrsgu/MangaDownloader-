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
        return linkArr;
    }
}
