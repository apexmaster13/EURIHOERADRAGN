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
    private boolean []keys;
    private boolean allowMove = true;
    private Image back, frog;
    private Frogger mainFrame;
    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;
    private Frog player = new Frog();


    movingItems bus, car, car2, log;

	LinkedList<movingItems> moveList;

    public GamePanel(Frogger m){
        keys = new boolean[KeyEvent.KEY_LAST+1];
        back = new ImageIcon("background.png").getImage();
        back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);
        frog = new ImageIcon("frog-V.png").getImage();
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
        mainFrame = m;
        setSize(466, 553);
        addKeyListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

   public void createCars()
	{
		moveList = new LinkedList<MovingObject>();
		busR = new ImageIcon("data/busR.gif");
		carR = new ImageIcon("data/carR.gif");
		car2R = new ImageIcon("data/car2Right.gif");

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

    /*
    public void changeSprite(Image img, String sprite, int x, int y){
        img = new ImageIcon(sprite).getImage();
        img = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }
    */

    public void move(){
        if(allowMove == true){
            //&& frogx+20 < 554 && frogx-20 > -1 && frogy+20 < 259 && frogy-20 > -1
            if(keys[KeyEvent.VK_RIGHT]  && player.getX()!= 426){
                player.moveX(32);
                player.setDir(RIGHT);
                //changeSprite(frog, "frog-H.png", 18, 24);
                frog = new ImageIcon("frog-H.png").getImage();
                frog = frog.getScaledInstance(18, 24, Image.SCALE_SMOOTH);
            }
            if(keys[KeyEvent.VK_LEFT] && player.getX() != 10){
                player.moveX(-32);
                player.setDir(LEFT);
                //changeSprite(frog, "frog-H.png", 18, 24);
                frog = new ImageIcon("frog-H.png").getImage();
                frog = frog.getScaledInstance(18, 24, Image.SCALE_SMOOTH);
            }
            if(keys[KeyEvent.VK_UP]  && player.getY() != 108){
                player.moveY(-32);
                player.setDir(UP);
                //changeSprite(frog, "frog-V.png", 24, 18);
                frog = new ImageIcon("frog-V.png").getImage();
                frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
            }
            if(keys[KeyEvent.VK_DOWN] && player.getY()!=460){
                player.moveY(32);
                player.setDir(DOWN);
                //changeSprite(frog, "frog-V.png", 24, 18);
                frog = new ImageIcon("frog-V.png").getImage();
                frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
            }
            allowMove = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e)
    {
        allowMove = true;
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(back,0,0,this);
        System.out.println(player.getX()+" "+player.getY());
        if(player.getDir() == UP){
            g.drawImage(frog,player.getX(),player.getY(),this);
        }
        else if(player.getDir() == LEFT){
            g.drawImage(frog,player.getX(),player.getY(),this);
        }
        else if(player.getDir() == DOWN){
            g.drawImage(frog, player.getX(), player.getY() + 18, 24, -18, null);
        }
        else if(player.getDir() == RIGHT){
            g.drawImage(frog, player.getX() + 18, player.getY(), -18, 24, null);
        }

    }
}









