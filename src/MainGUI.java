
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import javax.swing.event.*;
/**
 * Created by cjvnj on 25.07.2016.
 */
public class MainGUI extends Frame implements ListSelectionListener {

    //панели для списка глав/томов
    JScrollPane jscrpStart, jscrlEnd;
    //список глав/томов
    JList<String> jlstStart, jlstEnd;
    JLabel jlabURL, jlabStart, jlabEnd;
    JButton jbtnBegin;
    //поле ввода адреса
    JTextField jtfURL;
    //панели разметки
    JPanel jpnlStart, jpnlEnd, jpnlURL, jpnlStEnd, jpnlButton;

    String mangaURL;
    String mangaName;
    String mangaHost;
    int[][] mangaListArr;
    LinkCutter lc;
    Integer[] vol, chap;
    int start, end;

    DefaultListModel<String> dlm;

    MainGUI() {
        //основное окно
        JFrame jfrm = new JFrame("MangaDownloader");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //jfrm.setMinimumSize(new Dimension(300, 300));
        jfrm.setSize(280, 100);
        start = -1;
        end = -1;

        dlm = new DefaultListModel<>();
        //создать все основные элементы
        initialize();
        arrangement();

        jtfURL.addActionListener(ae -> {
            mangaURL = ae.getActionCommand();
            mangaList(mangaURL);
            ListModel<String> lmStart, lmEnd;
            lmStart = jlstStart.getModel();
            lmEnd = jlstEnd.getModel();
            for(int i = 0; i < chap.length; i++){
                dlm.addElement("vol"+vol[i]+"-"+chap[i]);
                //dlm.addElement("vol"+vol[i]+"-"+chap[i]);
            }
            jscrpStart = new JScrollPane(jlstStart);
            jscrlEnd = new JScrollPane(jlstEnd);
            Dimension d = new Dimension(100, 150);
            jscrpStart.setPreferredSize(d);
            jscrlEnd.setPreferredSize(d);
            jfrm.add(jscrpStart);
            jfrm.add(jscrlEnd);

            jfrm.add(jbtnBegin);
            jfrm.setSize(280, 280);
        });

        jlstStart.addListSelectionListener(this);
        jlstEnd.addListSelectionListener(this);

        jbtnBegin.addActionListener(ae -> {
            ListModel<String> lmsSt, lmsEnd;
            lmsSt = jlstStart.getModel();
            lmsEnd = jlstEnd.getModel();

            String startStr = lmsSt.getElementAt(start);
            String endStr = lmsEnd.getElementAt(end);
            System.out.println("Start1: "+startStr+"End: "+endStr);
            ChapStringToInt chstr = new ChapStringToInt();
            final int start1 = chstr.chapToInt(startStr);
            final int end1 = chstr.chapToInt(endStr);
            System.out.println("Start1: "+start1+"End: "+end1);

            String manga = mangaURL;

            MangaList mn = new MangaList();
            LinkedHashSet<String> lhs = null;
            try {
                lhs = mn.downloadList(manga);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Start2: "+start1+"End: "+end1);
            //System.out.println(lhs);
            ToArray ta = new ToArray();
            //int[][] chapList = new int[0][];
            System.out.println("Start3: "+start1+"End: "+end1);
            try{
                int[][] chapList = ta.chapArr(lhs, mangaName);
                for(int i1[]: chapList){

                    System.out.println("Массив: "+i1[0]+"_-"+i1[1]);
              }
                System.out.println("Start4: "+start1+"End: "+end1);

                ArrayList<String> arrList = ta.fromArray(chapList, start1, end1+1, false);
                System.out.println("Массив: "+arrList);
                String url1 = "http://"+mangaHost+"/"+mangaName+"/";
                ChapDownloader cd = new ChapDownloader();
                for(String chap: arrList) {
                    System.out.println("глава: "+chap);
                     try {
                    cd.chapD(url1+chap);
                     } catch (IOException e) {
                           e.printStackTrace();
                      }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //fot()


        });
        jfrm.add(jpnlURL);
        //jfrm.add(jpnlStEnd);
        //jfrm.add(jpnlButton);

        jfrm.setVisible(true);

    }

    //создание основных элементов
    public void initialize() {
        //панель адреса и метки адреса
        jpnlURL = new JPanel();
        jpnlURL.setLayout(new GridLayout(2,1));

        //списки глав
        jlstStart = new JList<>(dlm);
        jlstEnd = new JList<>(dlm);

        //кнопка начала загрузки
        jbtnBegin = new JButton("Start");

/*
        jpnlStart = new JPanel();
        jpnlEnd = new JPanel();
        jpnlStart.setLayout(new FlowLayout());
        jpnlEnd.setLayout(new FlowLayout());
        Dimension d1 = new Dimension(150,250);
        jpnlStart.setSize(d1);
        jpnlEnd.setSize(d1);

        jpnlStEnd = new JPanel();

        //панели прокрутки для выбора глав/разделов
        jscrpStart = new JScrollPane();
        jscrlEnd = new JScrollPane();
        Dimension d2 = new Dimension(200,250);
        jscrpStart.setSize(d2);
        jscrlEnd.setSize(d2);

        jpnlButton = new JPanel();
        jpnlButton.setSize(new Dimension(300,50));



        jlabURL = new JLabel("Введите url манги:");
        jlabStart = new JLabel("Начать с");
        jlabEnd = new JLabel("Закончить");

        //техническая переменная
        Integer[] test = {1, 2, 3, 4, 5, 6};


*/
        //поле ввода адреса
        jlabURL = new JLabel("Введите url манги:");
        jtfURL = new JTextField(20);
    }

    public void arrangement() {
        jpnlURL.add(jlabURL);
        jpnlURL.add(jtfURL);
/*
        //временно установить данные
        jscrpStart.setViewportView(jlstStart);

        jpnlStart.add(jlabStart);
        jpnlStart.add(jscrpStart);

        //временно установить данные
        jscrlEnd.setViewportView(jlstEnd);

        jpnlEnd.add(jlabEnd);
        jpnlEnd.add(jscrlEnd);

        jpnlStEnd.add(jpnlStart);
        jpnlStEnd.add(jpnlEnd);

        jpnlButton.add(jbtnBegin);
        */

    }

    public void mangaList(String url) {
        MangaList mn = new MangaList();
        ToArray ta = new ToArray();
        LinkCutter lc = new LinkCutter();
        mangaName = lc.takeMangaName(url);
        mangaHost = lc.takeMangaHost(url);
        LinkedHashSet<String> lhlist;
        try {
            lhlist = mn.downloadList(url);
            mangaListArr = ta.chapArr(lhlist, mangaName);
            vol = ta.toOneArray(mangaListArr, 0);
            chap = ta.toOneArray(mangaListArr, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> jl;
        jl = (JList) e.getSource();

        if(jl.equals(jlstStart)){
            start = e.getFirstIndex();
        } else {
            end = e.getLastIndex();
        }
    }
}
