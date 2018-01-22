import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Frogger extends JFrame implements ActionListener{
    Timer myTimer;
    GamePanel game;

    public Frogger() {
        super("Frogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(466, 553);

        myTimer = new Timer(10, this);  // trigger every 10 ms

        game = new GamePanel(this);
        add(game);

        setResizable(false);
        setVisible(true);
    }

    public void start(){
        myTimer.start();
    }

    public void actionPerformed(ActionEvent evt){
        game.move();
        game.repaint();
    }

    public static void main(String[] arguments) {
        Frogger frame = new Frogger();
    }
}

class GamePanel extends JPanel implements KeyListener{
    private int frogx, frogy, dir;
    private boolean []keys;
    private boolean allowMove = true;
    private Image back, frog;

    private Image bus, car, carR, logPic;
    private Frogger mainFrame;

    public GamePanel(Frogger m){
        keys = new boolean[KeyEvent.KEY_LAST+1];
        back = new ImageIcon("background.png").getImage();
        back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);

        //public void changeSprite(Image img, String sprite, int x, int y){
            //img = new ImageIcon(sprite).getImage();
            //img = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        //}

        //changeSprite(frog, "frog-u.png", 24, 18);

        frog = new ImageIcon("frog-u.png").getImage();
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);

        mainFrame = m;
        frogx = 298;
        frogy = 460;
        setSize(466, 553);
        addKeyListener(this);
    }

    public void createCars()
	{
		moveList = new LinkedList<MovingObject>();
		busR = new ImageIcon("data/bus.png");
		carR = new ImageIcon("data/car.png");
		car2R = new ImageIcon("data/carR.png");

		bus = new MovingObject();
		bus.setPic(busR);
		moveList.add(bus);

		car = new MovingObject();
		car.setPic(carR);
		car.setSpeed(8, 0);
		car.setXY(200, 200);
		moveList.add(car);

		car2 = new MovingObject();
		car2.setPic(car2R);
		car2.setSpeed(6, 0);
		car2.setXY(200, 250);
		moveList.add(car2);
	}

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void move(){
        if(allowMove == true){
            //&& frogx+20 < 554 && frogx-20 > -1 && frogy+20 < 259 && frogy-20 > -1
            if(keys[KeyEvent.VK_RIGHT] ){
                frogx += 32;
            }
            if(keys[KeyEvent.VK_LEFT] ){
                frogx -= 32;
            }
            if(keys[KeyEvent.VK_UP] ){
                frogy -= 32;
            }
            if(keys[KeyEvent.VK_DOWN] ){
                frogy += 32;
            }
            allowMove = false;
        }
    }



    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        allowMove = true;
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        //allowMove = true;
        keys[e.getKeyCode()] = false;
    }

    public void paintComponent(Graphics g){
        g.drawImage(back,0,0,this);
        //g.setColor(Color.blue);
        g.drawImage(frog,frogx,frogy,this);
        //g.fillRect(frogx,frogy,24,20);
    }
}





