import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by cjvnj on 24.07.2016.
 */
public class ToArray {
    public int[][] chapArr(LinkedHashSet<String> linkHash, String mangaName) throws IOException {

        LinkCutter lcut = new LinkCutter();


        String colletionArr[][] = new String[linkHash.size()][];
        int i = 0;

        for(String str2: linkHash){
            String[] arr1 = lcut.splitUrl(str2);
            //System.out.println("Размер массива"+arr1.length);
            if(arr1.length>2) {
                //System.out.println(arr1);
                colletionArr[i] = arr1;
                i++;
            }
        }
        int num = 0;
        if(colletionArr[1][1].compareTo(mangaName)==0)
            num = 1;
        if(colletionArr[1][1].compareTo(mangaName)==0)
            num = 2;
        //System.out.println(colletionArr[1][1]);
        System.out.println(num);

        for(int count = 0; count < colletionArr.length; count++){
            System.out.print(colletionArr[count][num]+"  ");

            if(colletionArr[count][num].contains("vol")){
                colletionArr[count][num] = colletionArr[count][num].replace("vol", "");
            }
        }

        //Список глав
        ArrayList<Integer> chap = new ArrayList<>();
        for(String[] col1: colletionArr){
            chap.add(Integer.parseInt(col1[num+1]));

        }

        chap.sort(new IntComparator());
        System.out.println("_");
        // System.out.println(chap);

        Integer chaps[] = new Integer[chap.size()];
        chap.toArray(chaps);
        //for(int ch1: chaps)
        //System.out.print(ch1+" ");
        // Список томов
        ArrayList<Integer> vol = new ArrayList<>();
        for(String[] col1: colletionArr){
            vol.add(Integer.parseInt(col1[num]));

        }
        vol.sort(new IntComparator());
        //System.out.println(vol);
        Integer vols[] = new Integer[vol.size()];
        vol.toArray(vols);
        for(int vol1: vols)
            System.out.print(vol1+" ");

        //проверка размерности коллекций
        if(chap.size()==vol.size())
            System.out.println("Размеры совпадают");
        else
            System.out.println("Размеры не совпадают");


        //Проверка размерности массивов и обьеднение
        int[][] volChap = new int[vols.length][2];
        if(vols.length == chaps.length) {

            for(int it = 0; it<vols.length;it++){
                volChap[it][0]=vols[it];
                volChap[it][1]=chaps[it];
            }
        } else {
            System.out.println("Размеры массивов не совпадают");
        }
        for(int volC1[]: volChap) {
            int counter = 1;
            for(int volC2: volC1){

                if(counter%2==0)
                    System.out.print("_");
                counter++;
                System.out.print(volC2);
            }
            System.out.println("");
        }
        // System.out.println(mld.downloadList(link));
        return volChap;
    }

    public ArrayList<String> fromArray(int[][] arr) {
        ArrayList<String> arrList = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            arrList.add("vol"+arr[i][0]+"/"+arr[i][1]);
        }
        return arrList;
    }
    public ArrayList<String> fromArray(int[][] arr, int Start, int End, boolean volOrChap) {
        ArrayList<String> arrList = new ArrayList<>();
        boolean key = false;
        for(int i = 0; i < arr.length; i++){
            if(volOrChap) {
                if(key || Start == arr[i][0]) {
                    if(End == arr[i][0]){
                        break;
                    }
                    key = true;
                    arrList.add("vol" + arr[i][0] + "/" + arr[i][1]);
                }
            } else {
                if(key || Start == arr[i][1]) {
                    if(End == arr[i][1]){
                        break;
                    }
                    key = true;
                    arrList.add("vol" + arr[i][0] + "/" + arr[i][1]);
                }
            }
        }
        return arrList;
    }

    public Integer[] toOneArray(int[][] arr, int what){
        Integer[] oneArr = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++){
            System.out.println( arr[i][what]);
            oneArr[i] = arr[i][what];
        }
        return oneArr;
    }

}