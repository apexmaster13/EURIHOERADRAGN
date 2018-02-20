import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.*;

public class Frogger extends JFrame implements ActionListener
{
    Timer myTimer;
    MyMenu menu; //Menu
    // create the gamepanel
    GamePanel game = new GamePanel(this);


    public Frogger() {
        super("Frogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(466, 553);
        add(game);  //add the gamepanel to the window
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
        // set window visible
        myTimer.start();
        setVisible(true);
    }

    // main function. Create new Window which starts the program
    public static void main(String[] args)
    {
        Frogger frame = new Frogger();
    }
}

