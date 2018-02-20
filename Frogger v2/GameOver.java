import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class GameOver extends JFrame implements ActionListener{
    private Frogger me3;
    JButton menuBtn = new JButton();

    public GameOver(Frogger m){
        super ("Frogger");
        setSize (466, 553);
        me3 = m;
        menuBtn.addActionListener(this);
        ImageIcon back = new ImageIcon("images/menus/menu.png");
        //back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);
        JLabel backLabel = new JLabel(back);
        JLayeredPane mPage=new JLayeredPane();
        mPage.setLayout(null);

        backLabel.setSize(466, 524);
        backLabel.setLocation(0,0);
        mPage.add(backLabel,1);


        //menuBtn.setBackground(new Color(0,0,0));
        menuBtn.setOpaque(false);
        menuBtn.setSize(100,30);
        menuBtn.setLocation(190,270);
        mPage.add(menuBtn,2);

        add(mPage);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        setVisible(false);
        //music();
        me3.start();
    }
}