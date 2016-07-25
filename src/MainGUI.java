import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedHashSet;
import javax.swing.event.*;
/**
 * Created by cjvnj on 25.07.2016.
 */
public class MainGUI extends Frame {
    JScrollPane jscrpStart, jscrlEnd;
    JList<Integer> jlstStart, jlstEnd;
    JLabel jlabURL, jlabStart, jlabEnd;
    JButton jbtnVol, jbtnChap, jbtnBegin;
    JTextField jtfURL;
    JPanel jpnlStart, jpnlEnd, jpnlURL, jpnlVolChap, jpnlStEnd;
    String mangaURL;
    String mangaName;
    int[][] mangaListArr;
    LinkCutter lc;
    Integer[] vol, chap;
    MainGUI() {
        JFrame jfrm = new JFrame("MangaDownloader");
        jfrm.setLayout(new FlowLayout());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setSize(800, 800);

        jpnlURL = new JPanel();
        jpnlStart = new JPanel();
        jpnlStart.setPreferredSize(new Dimension(150,400));
        jpnlEnd = new JPanel();
        jpnlVolChap = new JPanel();
        jpnlStEnd = new JPanel();
        jpnlEnd.setPreferredSize(new Dimension(150,150));

        jbtnBegin = new JButton("Start");
        jbtnVol = new JButton("vol");
        jbtnChap = new JButton("chap");

        jlabURL = new JLabel("Введите url манги:");
        jlabStart = new JLabel("Начать с");
        jlabEnd = new JLabel("Закончить");

        jtfURL = new JTextField(20);



      //  jscrpStart.setEnabled(false);
       // jscrlEnd.setEnabled(false);
        jbtnChap.setEnabled(false);
        jbtnVol.setEnabled(false);
        jbtnBegin.setEnabled(false);


        jtfURL.addActionListener(ae -> {
            mangaURL = ae.getActionCommand();
            System.out.println("URL манги: "+mangaURL);
            //jscrpStart.setEnabled(true);
           // jscrlEnd.setEnabled(true);
            jbtnChap.setEnabled(true);
            jbtnVol.setEnabled(true);
            jbtnBegin.setEnabled(true);
            setupScrol();


        });

        jpnlURL.add(jlabURL);
        jpnlURL.add(jtfURL);

        jpnlVolChap.add(jbtnVol);
        jpnlVolChap.add(jbtnChap);

        //Integer[] test = {1, 2, 3, 4, 5, 6};

        jlstStart = new JList<Integer>();
        jlstStart.setPreferredSize(new Dimension(100, 400));
        jlstEnd = new JList<Integer>();
        jlstEnd.setPreferredSize(new Dimension(100, 400));

        jscrpStart = new JScrollPane(jlstStart);
        jscrlEnd = new JScrollPane(jlstEnd);

        jpnlStart.add(jlabStart);


        jpnlEnd.add(jlabEnd);
        jpnlStart.add(jscrpStart);
        jpnlEnd.add(jscrlEnd);
        jpnlStEnd.add(jpnlStart);
        jpnlStEnd.add(jpnlEnd);



        jfrm.add(jpnlURL);
        jfrm.add(jpnlVolChap);
        jfrm.add(jpnlStEnd);
        jfrm.add(jbtnBegin);

        jfrm.setVisible(true);

    }

    private void setupScrol() {
        MangaList mn = new MangaList();
        try {
            LinkedHashSet<String> lhs = mn.downloadList(mangaURL);
            ToArray ta = new ToArray();
            lc = new LinkCutter();
            mangaName = lc.takeMangaName(mangaURL);
            mangaListArr = ta.chapArr(lhs, mangaName);
            vol = ta.toOneArray(mangaListArr, 0);
            chap = ta.toOneArray(mangaListArr, 1);
            jlstStart.setListData(chap);
            jlstEnd.setListData(chap);
            for(Integer i: chap){
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
