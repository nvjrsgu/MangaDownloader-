/**
 * Created by cjvnj on 26.07.2016.
 */
public class ChapStringToInt {

    public Integer chapToInt(String str) {
        System.out.println("chapToInt String "+str);
        char c[] = str.toCharArray();
        String c2 = c[c.length-1]+"";
        Integer i = Integer.parseInt(c2);

        System.out.println("chapYoInt: "+i);
        return i;
    }
}

