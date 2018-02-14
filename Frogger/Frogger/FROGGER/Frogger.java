import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import java.util.*;

import java.awt.*;
import javax.swing.*;
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
    private Frog player = new Frog();
    private Random rand = new Random();
    private int rng = rand.nextInt(1300) + 1200;
    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;
    private Rectangle waterRect;
    private int spawnCounter = 2;
    private int tCounter = 0;
    private int sCounter = 0;

    MovingItems truck, car, car2, car3, car4, log, turtle, snake;

    Image truckPic, carPic, car2Pic, car3Pic, car4Pic, logPic, turtlePic, snakePic;

    LinkedList<MovingItems> moveList;


    public GamePanel(Frogger m)
    {
        keys = new boolean[KeyEvent.KEY_LAST+1];
        back = new ImageIcon("Data/background.jpg").getImage();
        back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);
        //back = back.getScaledInstance(back.getWidth(null)*2,back.getHeight(null)*2, Image.SCALE_SMOOTH);
        frog = new ImageIcon("Data/frog-V.png").getImage();
        truckPic = new ImageIcon("Data/truck.png").getImage();
        truckPic = truckPic.getScaledInstance(truckPic.getWidth(null)*2, truckPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        carPic = new ImageIcon("Data/car.png").getImage();
        carPic = carPic.getScaledInstance(carPic.getWidth(null)*2, carPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        car2Pic = new ImageIcon("Data/car2.png").getImage();
        car2Pic = car2Pic.getScaledInstance(car2Pic.getWidth(null)*2, car2Pic.getHeight(null)*2, Image.SCALE_SMOOTH);
        car3Pic = new ImageIcon("Data/car3.png").getImage();
        car3Pic = car3Pic.getScaledInstance(car3Pic.getWidth(null)*2, car3Pic.getHeight(null)*2, Image.SCALE_SMOOTH);
        car4Pic = new ImageIcon("Data/car4.png").getImage();
        car4Pic = car4Pic.getScaledInstance(car4Pic.getWidth(null)*2, car4Pic.getHeight(null)*2, Image.SCALE_SMOOTH);
        logPic = new ImageIcon("Data/log.png").getImage();
        logPic = logPic.getScaledInstance(logPic.getWidth(null)*2, logPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        turtlePic = new ImageIcon("Data/turtle0.png").getImage();
        turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        snakePic = new ImageIcon("Data/snake0.png").getImage();
        snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
        moveList = new LinkedList<MovingItems>();

        mainFrame = m;
        setSize(466, 553);
        addKeyListener(this);
        //spawnCars();
        //spawnLogs();
        t.start();
        aniTimer.start();

    }
    /*
    Timer t = new Timer(rng, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            spawnCars();
            spawnLogs();
            rng = rand.nextInt(1225) + 1200;
            spawnCounter -=1;
            if(spawnCounter==0){
                System.out.println("stopped");
                t.stop();
                spawnCounter = 3;
            }
        }
    });*/

    Timer t = new Timer(3000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            rng = rand.nextInt(90);
            //rng = 0;
            truck = new MovingItems(459+rng, 300, -1, 0, "car", truckPic);
            moveList.add(truck);

            car = new MovingItems(0-rng, 390, 1, 0, "car", carPic);
            moveList.add(car);

            car2 = new MovingItems(459+rng, 364, -2, 0, "car", car2Pic);
            moveList.add(car2);

            car3 = new MovingItems(0-rng, 328, 3, 0, "car", car3Pic);
            moveList.add(car3);

            car4 = new MovingItems(459+rng, 423, -3, 0, "car", car4Pic);
            moveList.add(car4);

            snake = new MovingItems(0, 268, 3, 0, "snake", snakePic);
            moveList.add(snake);
            //--------------------------water---------------------------------

            for(int i=0; i<rand.nextInt(4)+2; i++){
                turtle = new MovingItems(459+i*26, 236, -2, 0, "turtle", turtlePic);
                moveList.add(turtle);
            }

            log = new MovingItems(0, 203, 1, 0, "log", logPic);
            moveList.add(log);


            //spawnCars();
            //spawnLogs();
            //spawnCounter -=1;

        }
    });

    Timer aniTimer = new Timer(500, new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		tCounter+=1;
	  		sCounter+=1;
	        	if(tCounter == 5)
	        	{
	        		tCounter = 0;
	        	}
	        	if(sCounter == 4)
	        	{
	        		sCounter = 0;
	        	}
			for(MovingItems o : moveList)
	        {
	        	if(o.getType() == "turtle")
	        	{
	        		turtlePic = new ImageIcon("Data/turtle"+tCounter+".png").getImage();
	        		turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
	        		o.setPic(turtlePic);
	        	}

	        	if(o.getType() == "snake")
	        	{
	        		snakePic = new ImageIcon("Data/snake"+sCounter+".png").getImage();
	        		snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
	        		o.setPic(snakePic);
	        	}
	        }
	    }
    });

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public boolean checkCollision(MovingItems o)
    {
        if(player.getRect().intersects(waterRect)){
            if(o.getRect().intersects(player.getRect()) == true && o.getType() == "log")
            {
                System.out.println("hit");
                player.moveX(o.getSpeedX());
            }
            else{
                //player.update();
                return true;
            }
        }
        else{ //not on water
            if(o.getRect().intersects(player.getRect()) == true && o.getType() == "car")
            {
                player.update();
                return true;
            }
        }

        return false;
    }

   /*public void update()
   {
       for(MovingItems o : moveList)
       {
           checkCollision(o);
           o.update();
       }
       log.update();
       repaint();
   }
*/
    public void move()
    {
        if(allowMove == true){
            if(keys[KeyEvent.VK_RIGHT]  && player.getX()!= 426){
                player.moveX(32);
                player.setDir(RIGHT);
                frog = new ImageIcon("Data/frog-H.png").getImage();
                frog = frog.getScaledInstance(18, 24, Image.SCALE_SMOOTH);
            }
            if(keys[KeyEvent.VK_LEFT] && player.getX() != 10){
                player.moveX(-32);
                player.setDir(LEFT);
                frog = new ImageIcon("Data/frog-H.png").getImage();
                frog = frog.getScaledInstance(18, 24, Image.SCALE_SMOOTH);
            }
            if(keys[KeyEvent.VK_UP]  && player.getY() != 108){
                player.moveY(-32);
                player.setDir(UP);
                frog = new ImageIcon("Data/frog-V.png").getImage();
                frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
            }
            if(keys[KeyEvent.VK_DOWN] && player.getY()!=460){
                player.moveY(32);
                player.setDir(DOWN);
                frog = new ImageIcon("Data/frog-V.png").getImage();
                frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
            }
            allowMove = false;
            player.setPic(frog);
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
        waterRect = new Rectangle(6, 100, 224*2, 164);
        g.drawRect(6, 100, 224*2, 82*2);

        System.out.println(player.getX()+" "+player.getY());

        for(MovingItems o : moveList)
        {
            o.draw(g, this);
            o.update();
            checkCollision(o);
        }

        for(int i=moveList.size()-1; i>=0; i--){
         MovingItems o = moveList.get(i);
         if(o.getX()>600 || o.getX()<-200){
          moveList.remove(o);
         }
        }

        player.draw(g);
    }
}
