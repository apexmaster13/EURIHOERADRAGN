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
    private Frog player = new Frog();
    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;

    MovingItems truck, car, car2, car3, car4, log;

    Image truckPic, carPic, car2Pic, car3Pic, car4Pic, logpic;

    LinkedList<MovingItems> moveList;


    public GamePanel(Frogger m)
    {
        keys = new boolean[KeyEvent.KEY_LAST+1];
        back = new ImageIcon("Data/background.png").getImage();
        back = back.getScaledInstance(460, 524, Image.SCALE_SMOOTH);
        //back = back.getScaledInstance(back.getWidth(null)*2,back.getHeight(null)*2, Image.SCALE_SMOOTH);
        frog = new ImageIcon("Data/frog-V.png").getImage();
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);

        mainFrame = m;
        setSize(466, 553);
        addKeyListener(this);
        createObjects();

    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
        mainFrame.start();
    }

    public void createObjects()
    {
        moveList = new LinkedList<MovingItems>();

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
        logpic = new ImageIcon("Data/log.png").getImage();

                            //
        truck = new MovingItems(459, 292, -3, 0, "car", truckPic, 68, 34);
        moveList.add(truck);

        car = new MovingItems(0, 390, 1, 0, "car", carPic, 30, 27);
        moveList.add(car);

        car2 = new MovingItems(459, 364, -3, 0, "car", car2Pic, 46, 28);
        moveList.add(car2);

        car3 = new MovingItems(0, 328, 3, 0, "car", car3Pic, 46, 28);
        moveList.add(car3);

        car4 = new MovingItems(459, 423, -3, 0, "car", car4Pic, 46, 28);
        moveList.add(car4);

        log = new MovingItems(0, 100, 5, 0, "log", logpic, 30, 20);
        moveList.add(log);
    }


    public boolean checkCollision(MovingItems o)
    {
        if(o.getRect().intersects(player.getRect()) == true )
        {
            player.update();
            return true;
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

        System.out.println(player.getX()+" "+player.getY());

        for(MovingItems o : moveList)
        {
            o.draw(g, this);
            o.update();
            checkCollision(o);
        }

        player.draw(g);
    }
}









