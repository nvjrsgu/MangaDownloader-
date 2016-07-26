import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by cjvnj on 26.07.2016.
 */
public class ChapterSearchImages {
    public String[] imLink(String chapUrl) {
        URL url1 = new URL(mangaLink);
        InputStreamReader buffStr = new InputStreamReader(url1.openStream());
        //FileOutputStream files = new FileOutputStream("enigma1.txt");
        String str = "";
        String str2 = "";
        int read=0;
        boolean key = false;
        ArrayList<String> arr1 = new ArrayList<>();
        while(read != -1){
            read = buffStr.read();
            str +=  (char) read;
            if(read == '\n'){
                //находим строку с ссылками на изображения
                if(str.contains("rm_h.init")){
                    str2 = str;
                }
                //System.out.print(str);
                str = "";
            }
        }
        str2 = str2.replaceAll("\\[", "|").replaceAll("\\]","|").replaceAll("'","\"").replaceAll(",", "");
        String[] strArr = str2.split("\"");
        for(String h: strArr)
            System.out.println(h);
        System.out.print(str2);

        String arr4;
        String arr2;
        String arr3;
        arr4 = "";
        arr2 = "";
        arr3 ="";
        ArrayList<String> arrL = new ArrayList<>();
        int counter = 0;

        for(int i = 0; i < strArr.length; i++){
            if(strArr[i].contains("/")){
                System.out.println(strArr[i]);
                switch (counter) {
                    case 0: arr2 = strArr[i];
                        counter++;
                        break;
                    case 1: arr4 = strArr[i];
                        counter++;
                        break;
                    case 2: arr3 = strArr[i];
                        arrL.add(arr4+arr2+arr3);
                        counter = 0;
                        break;
                }
            }
        }
        System.out.println(arrL);
        String[] stf = new String[arrL.size()];

        stf = arrL.toArray(stf);
        for(String gg: stf)
            System.out.println(gg);
        return stf;
    }
}
