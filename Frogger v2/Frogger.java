import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.Graphics;
//import java.awt.Image;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.*;

public class Frogger extends JFrame implements ActionListener
{
        Timer myTimer;
        MyMenu menu;
        GamePanel game = new GamePanel(this);

        public Frogger() {
        super("Frogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(466, 553);
        add(game);
        myTimer = new Timer(10, this); // trigger every 10 ms

        new MyMenu(this);

        /*try
        {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new FileInputStream("Sound/main.wav"));
            clip.open(inputStream);
            clip.start();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }*/


        setResizable(false);



    }

    public void actionPerformed(ActionEvent evt){
        game.move();
        game.repaint();
    }

    public void start()
    {
        myTimer.start();
        setVisible(true);
    }
    public static void main(String[] args)
    {
        Frogger frame = new Frogger();
    }
}

