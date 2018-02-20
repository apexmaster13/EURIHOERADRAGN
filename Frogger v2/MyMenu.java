import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//import sun.audio.*;
import java.io.*;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.*;

class MyMenu extends JFrame implements ActionListener{
    private Frogger me3;
    JButton playBtn = new JButton();

    public MyMenu(Frogger m){
        super ("Frogger");
        setSize (466, 553);
        me3 = m;
        playBtn.addActionListener(this);
        ImageIcon back = new ImageIcon("images/menus/menu.png");
        //back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);
        JLabel backLabel = new JLabel(back);
        JLayeredPane mPage=new JLayeredPane();
        mPage.setLayout(null);

        backLabel.setSize(466, 524);
        backLabel.setLocation(0,0);
        mPage.add(backLabel,1);


        //playBtn.setBackground(new Color(0,0,0));
        playBtn.setOpaque(false);
        playBtn.setSize(100,30);
        playBtn.setLocation(190,270);
        mPage.add(playBtn,2);

        add(mPage);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent evt) {
        setVisible(false);
        //music();
        me3.start();
    }
    /*public static void music()
    {
        AudioPlayer BGP = AudioPlayer.player;
        AudioStream BM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;
        try
        {
            BM = new AudioStream(new FileInputStream("Sound/main.wav"));
            MD = BM.getData();
            loop = new ContinuousAudioDataStream(MD);
        }

        catch(IOException error){}

        //BGP.start(loop);

    }*/
}