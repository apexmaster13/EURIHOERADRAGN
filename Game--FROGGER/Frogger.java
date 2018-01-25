import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

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

    public static void main(String[] arguments)
    {
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


    movingItems truck, car, car2, log;

    ImageIcon truckP, carP, car2P, logpic;

	LinkedList<movingItems> moveList;

    public GamePanel(Frogger m)
    {
        keys = new boolean[KeyEvent.KEY_LAST+1];
        back = new ImageIcon("background.png").getImage();
        back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);
        frog = new ImageIcon("frog-V.png").getImage();
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
        mainFrame = m;
        setSize(466, 553);
        addKeyListener(this);

        log = new movingItems();
		logpic = new ImageIcon("data/log.png");
		log.setPic(logpic);
		log.y = 100;
		log.dx = 3;
		log.att = "log";

    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

   public void createCars()
	{
        moveList = new LinkedList<movingItems>();

		truckP = new ImageIcon("data/truck.png");
		carP = new ImageIcon("data/car.png");
		car2P = new ImageIcon("data/car2.png");

		truck = new movingItems();
		truck.setPic(truckP);
		moveList.add(truck);

		car = new movingItems();
		car.setPic(carP);
		car.setSpeed(8, 0);
		car.setXY(200, 200);
		moveList.add(car);

		car2 = new movingItems();
		car2.setPic(car2P);
		car2.setSpeed(6, 0);
		car2.setXY(200, 250);
		moveList.add(car2);
	}

    public boolean checkCollision(movingItems o)
	{
		if(o.intersects(frog) == true )
		{
			if(o.att.equalsIgnoreCase("Car"))
			{
				frog.update();
				System.out.println("Run Over");
			}
			else if(o.att.equalsIgnoreCase("log"))
			{
				if(frog.x > o.dw)
					win = true;
				frog.x += o.dx;
			}
			return true;
		}
		return false;
    }

    /*
    public void changeSprite(Image img, String sprite, int x, int y){
        img = new ImageIcon(sprite).getImage();
        img = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }
    */

    public void update()
	{
		for(movingItems o : moveList)
		{
			checkCollision(o);
			o.update();
		}
		if(frog.y  + frog.height <= log.y + log.height)
		{
			if(checkCollision(log))
			{
				System.out.println("Hit log");
			}
			else
			{
				frog.update();
				System.out.println("Drown");
			}
		}
		log.update();
		repaint();
    }

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

    public void keyTyped(KeyEvent e){}

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
        for(movingItems o : moveList)
		{
			o.draw(g, this);
		}
    }
}









