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
    DefaultListModel<String> dlm_ledft, dlm_right;
    JList<String> jlist_left, jlist_right;
    GUI2() {
        JFrame jfrm = new JFrame();
        jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jfrm.setLayout(new GridLayout(3,1));
        jfrm.setSize(new Dimension(400,500));
        jpnl_top = new JPanel();
        jpnl_middle = new JPanel();
        jpnl_bottom = new JPanel();
        dlm_ledft = new DefaultListModel<>();
        dlm_right = new DefaultListModel<>();
        jlist_left = new JList<>(dlm_ledft);
        jlist_right = new JList<>(dlm_right);

        for(int i = 0; i < 100; i++) {
            dlm_right.addElement(i*i+"-"+i*i);
            dlm_ledft.addElement(i + "-" + i);
        }
        jpnl_middle.setLayout(new GridLayout(1,2));

        jscr_left = new JScrollPane(jlist_left);
        jscr_right = new JScrollPane(jlist_right);


        jbtn_start = new JButton("Start");

        jtf_adress = new JTextField("http://readmanga.me/one__piece", 30);

        jpnl_top.setBorder(BorderFactory.createLineBorder(Color.RED));
        jpnl_middle.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        jpnl_bottom.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        jpnl_middle.add(jscr_left);
        jpnl_middle.add(jscr_right);
        jpnl_top.add(jtf_adress);
        jpnl_bottom.add(jbtn_start);

        jfrm.add(jpnl_top);
        jfrm.add(jpnl_middle);
        jfrm.add(jpnl_bottom);



        jfrm.setVisible(true);
    }
}
