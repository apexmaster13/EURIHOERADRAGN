//import java.awt.Graphics;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import java.util.*;

import java.awt.*;
import javax.swing.*;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
import javax.swing.Timer;

public class Frogger extends JFrame implements ActionListener
{
        Timer myTimer;
        MyMenu menu;
        GamePanel game = new GamePanel(this);


        public Frogger() {
        super("Frogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(466, 553);

        myTimer = new Timer(10, this); // trigger every 10 ms


        add(game);
        new MyMenu(this);


        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt){
        game.move();
        game.repaint();

    }

    public void start()
    {
        myTimer.start();
    }


    public static void main(String[] args)
    {
        Frogger frame = new Frogger();
    }
}

class MyMenu extends JFrame implements ActionListener{
	private Frogger me3;
	JButton playBtn = new JButton("Play");

	public MyMenu(Frogger m){
		super ("Frogger");
		setSize (466, 553);
		me3 = m;
		playBtn.addActionListener(this);
		ImageIcon back = new ImageIcon("data/menu.jpg");
		JLabel backLabel = new JLabel(back);
		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top
		mPage.setLayout(null);

		backLabel.setSize(466, 553);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
												// are just relative to one another. Higher numbers on top.
		playBtn.setSize(100,30);
		playBtn.setLocation(233,275);
		mPage.add(playBtn,2);

		add(mPage);
		setVisible(true);
	}

    public void actionPerformed(ActionEvent evt) {
    	setVisible(false);
		me3.start();
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
  private int dCounter = 0;
 private boolean pos = true;
 private boolean freeMove = true;

 MovingItems truck, car, car2, car3, car4, log, log2, log3, turtle, turtle2, snake;

 Image truckPic, carPic, car2Pic, car3Pic, car4Pic, logPic, turtlePic, snakePic;

 LinkedList<MovingItems> moveList;


 public GamePanel(Frogger m)
 {
     keys = new boolean[KeyEvent.KEY_LAST+1];
     back = new ImageIcon("Data/back.jpg").getImage();
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
     turtlePic = new ImageIcon("Data/turtle4.png").getImage();
     turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
     snakePic = new ImageIcon("Data/snake0.png").getImage();
     snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
     frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
     moveList = new LinkedList<MovingItems>();

     mainFrame = m;
     setSize(466, 553);
     addKeyListener(this);

     snake = new MovingItems(0, 268, 1, 0, "snake", snakePic);
     moveList.add(snake);

     spawnTimer.start();
     spawnTimer2.start();
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

 Timer spawnTimer = new Timer(2000, new ActionListener() {
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

         //snake = new MovingItems(0, 268, 1, 0, "snake", snakePic);
         //moveList.add(snake);

         //--------------------------water---------------------------------

        if(rand.nextInt(2)+1 == 1){
             for(int i=0; i<rand.nextInt(4)+2; i++){
                 turtle = new MovingItems(459+i*26, 236, -1, 0, "turtle", turtlePic);
                 moveList.add(turtle);
             }
         }
         else{
             for(int i=0; i<rand.nextInt(4)+2; i++){
                 turtle = new MovingItems(459+i*26, 236, -1, 0, "turtle1", turtlePic);
                 moveList.add(turtle);
             }
         }

         log = new MovingItems(0+rng, 203, 1, 0, "log", logPic);
         moveList.add(log);

         log2 = new MovingItems(-30+rng, 203-32, 3, 0, "log", logPic);
         moveList.add(log2);

         if(rand.nextInt(2)+1 == 1){
             for(int i=0; i<rand.nextInt(4)+2; i++){
                 turtle2 = new MovingItems(459+i*26, 136, -1, 0, "turtle", turtlePic);
                 moveList.add(turtle2);
             }
         }
         else{
             for(int i=0; i<rand.nextInt(4)+2; i++){
                 turtle2 = new MovingItems(459+i*26, 136, -1, 0, "turtle1", turtlePic);
                 moveList.add(turtle2);
             }
         }

         log3 = new MovingItems(0+rng, 108, 1, 0, "log", logPic);
         moveList.add(log3);
     }
 });

  Timer spawnTimer2 = new Timer(9000, new ActionListener() {
     public void actionPerformed(ActionEvent e) {
         snake = new MovingItems(0, 268, 1, 0, "snake", snakePic);
         moveList.add(snake);
     }
 });

 Timer aniTimer = new Timer(200, new ActionListener() {
     public void actionPerformed(ActionEvent e) {
         if(pos){
             tCounter+=1;
         }
         else{
             tCounter-=1;
         }

         sCounter+=1;

         if(tCounter == 5)
         {
             pos = false;
         }
         if(tCounter == 0){
             pos = true;
         }
         if(sCounter == 3)
         {
             sCounter = 0;
         }

         if(dCounter == 4)
         {
             dCounter = 0;
             freeMove = true;
             player.update();
             player.setDir(UP);
             frog = new ImageIcon("Data/frog-V.png").getImage();
             frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
         }

         if(freeMove == false){
             dCounter+=1;
             if(player.getY()<250){
                 frog = new ImageIcon("Data/hit"+dCounter+".png").getImage();
                 frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                 player.setPic(frog);
             }
             else{
                 frog = new ImageIcon("Data/drown"+dCounter+".png").getImage();
                 frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                 player.setPic(frog);
             }
         }

         for(MovingItems o : moveList)
         {
             if(o.getType().equals("turtle"))
             {
                 turtlePic = new ImageIcon("Data/turtle"+tCounter+".png").getImage();
                 turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
                 o.setPic(turtlePic);
             }

             if(o.getType().equals("snake"))
             {
                 snakePic = new ImageIcon("Data/snake"+sCounter+".png").getImage();
                 snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
                 o.setPic(snakePic);
             }

             if(o.getType().equals("turtle1"))
             {
                 turtlePic = new ImageIcon("Data/turtle"+sCounter+".png").getImage();
                 turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
                 o.setPic(turtlePic);
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

     if(player.getY()<250){
         if(o.getRect().intersects(player.getRect()) == true && o.getType().equals("log") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("turtle1") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("turtle") && tCounter<4)
         {
             //System.out.println("hit");
             player.moveX(o.getSpeedX());
             return true;
         }
         else
         {
             //player.update();
             //freeMove = false;
             //System.out.println("dead");
             return true;
         }
     }
     else{ //not on water
         if(o.getRect().intersects(player.getRect()) == true && o.getType().equals("car") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("snake"))
         {
             freeMove = false;
             //player.update();
             return true;
         }
     }

    return false;
    }

 public void move()
 {
     if(allowMove && freeMove){
         if(keys[KeyEvent.VK_RIGHT] && player.getX()!= 426){
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
         if(keys[KeyEvent.VK_UP] && player.getY() != 108){
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

     //System.out.println(player.getX()+" "+player.getY());

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
