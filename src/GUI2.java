import javax.swing.*;
import java.awt.*;
/**
 * Created by cjvnj on 27.07.2016.
 */
public class GUI2 extends JFrame {

    JPanel jpnl_top, jpnl_middle, jpnl_bottom;
    JButton jbtn_start;
    JTextField jtf_adress;
    JScrollPane jscr_left, jscr_right;
    DefaultListModel<String> dlm_left, dlm_right;
    JList<String> jlist_left, jlist_right;
    String[] chapters_view, chapters_adress;
    String url;
    int first_index, last_index;
    String start, finish;
    JCheckBox jcb;
    static JProgressBar jpb;

    GUI2() {
        JFrame jfrm = new JFrame();
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfrm.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        jfrm.setSize(new Dimension(400,380));
        jfrm.setMinimumSize(new Dimension(360,360));

        jpnl_top = new JPanel();
        jcb = new JCheckBox("Download all chapters in one folder?");
        jcb.setSelected(true);
      //  jpnl_top.setPreferredSize(new Dimension(40,400));
        jpnl_middle = new JPanel();
        jpnl_bottom = new JPanel();
        dlm_left = new DefaultListModel<>();
        dlm_right = new DefaultListModel<>();
        jlist_left = new JList<>(dlm_left);
        jlist_right = new JList<>(dlm_right);


        jpnl_middle.setLayout(new GridLayout(1,2));
        jlist_left.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist_right.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jscr_left = new JScrollPane(jlist_left);
        jscr_right = new JScrollPane(jlist_right);
        jpb = new JProgressBar();

        jbtn_start = new JButton("Start");

        jtf_adress = new JTextField("http://readmanga.me/one__piece", 30);
        //jtf_adress.setPreferredSize(new Dimension(400,40));

        jtf_adress.addActionListener(ae -> {
            MangaSearchChapters msc = new MangaSearchChapters();
            url = ae.getActionCommand();
            chapters_view = msc.searchChapters(url, false);
            chapters_adress = msc.searchChapters(url, true);
            for(String addChap: chapters_adress){
               System.out.println(addChap);
            }
            dlm_right.removeAllElements();
            dlm_left.removeAllElements();
            for(String addChap: chapters_view){
                dlm_left.addElement(addChap);
            }
        });

//добавляем список глав конца загрузки
        //ошибка при выборе в левом списке первой главы повторно
        jlist_left.addListSelectionListener(lse -> {
            if(!lse.getValueIsAdjusting()) {
                first_index = jlist_left.getSelectedIndex();
                if(first_index < 0)
                    first_index = 0;
                System.out.println("начало: "+first_index);
                //System.out.println(chapters_view[first_index]);
                start = chapters_adress[first_index];
                System.out.println("старт: "+start);
              //  jlist_left.setEnabled(false);
                //last_index = 0;
                dlm_right.removeAllElements();

                for(int i = first_index; i < chapters_view.length; i++) {
                    dlm_right.addElement(chapters_view[i]);
                }
            }

        });

        jlist_right.addListSelectionListener(lse -> {
            if(!lse.getValueIsAdjusting()){
                last_index = jlist_right.getSelectedIndex();
                if(last_index < 0)
                    last_index = 0;
               // System.out.println("конец: "+last_index);
                //System.out.println(chapters_view[last_index+first_index]);
                finish = chapters_adress[last_index+first_index];
                jpb.setMinimum(0);
                jpb.setMaximum(last_index);
                jpb.setStringPainted(true);
                //System.out.println("Финиш: "+finish);
               // jlist_right.setEnabled(false);
            }
        });

        jbtn_start.addActionListener(ae -> {
            //jcb.isSelected();
            //jbtn_start.setEnabled(false);
            new DownloadRange(chapters_adress, start, finish, url, jcb.isSelected());
            //jpb.setIndeterminate(true);
            //jpb.setString("Downloading");
            System.out.println(last_index);
            //jbtn_start.setEnabled(false);
        });


      //  jpnl_top.setBorder(BorderFactory.createLineBorder(Color.RED));
   //     jpnl_middle.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    //    jpnl_bottom.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        jpnl_middle.add(jscr_left);
        jpnl_middle.add(jscr_right);
        jpnl_top.add(jtf_adress);
        jpnl_bottom.add(jcb);
        jpnl_bottom.add(jbtn_start);
        jpnl_bottom.add(jpb);
c.fill = GridBagConstraints.HORIZONTAL;
        //c.gridx = 0;
     // c.gridy = 25;
        c.gridwidth = 3;
        c.gridy = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.insets = new Insets(10,0 , 0, 0);
        jfrm.add(jpnl_top, c);
        c.gridy = 1;
        c.ipady = 200;
        c.weightx = 1;
        c.insets = new Insets(10,0 , 0, 0);
        jfrm.add(jpnl_middle, c);
        c.gridy = 2;
        c.gridwidth=3;
        //c.gridx = 1;
        c.ipady = 35;
        c.insets = new Insets(10,0 , 0, 0);
        jfrm.add(jpnl_bottom, c);



        jfrm.setVisible(true);
    }
}
