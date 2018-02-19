import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

class GamePanel extends JPanel implements KeyListener{
    private boolean []keys;
    private boolean allowMove = true;
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
    private int tCounter, sCounter, dCounter, moveCounter, moveSpace, points;
    private boolean pos = true;
    private boolean freeMove = true;
    private ArrayList<Image> carPics = new ArrayList<Image>();

    MovingItems truck, car, car2, car3, car4, log, log2, log3, turtle, turtle2, snake, pFrog;

    Image back, frog, pFrogPic, pic, logPic, turtlePic, snakePic;

    LinkedList<MovingItems> moveList;

//====================================constructor=================================================

    public GamePanel(Frogger m)
    {
        back = new ImageIcon("images/back.jpg").getImage();
        back = back.getScaledInstance(back.getWidth(null)*2,back.getHeight(null)*2, Image.SCALE_SMOOTH);
        for(int i=0; i<5; i++){
            pic = new ImageIcon("images/cars/car"+i+".png").getImage();
            pic = pic.getScaledInstance(pic.getWidth(null)*2, pic.getHeight(null)*2, Image.SCALE_SMOOTH);
            carPics.add(pic);
        }
        logPic = new ImageIcon("images/logs/log0.png").getImage();
        logPic = logPic.getScaledInstance(logPic.getWidth(null)*2, logPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        turtlePic = new ImageIcon("images/turtle/turtle4.png").getImage();
        turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        pFrogPic = new ImageIcon("images/frog/blankFrog.png").getImage();
        pFrogPic = pFrogPic.getScaledInstance(pFrogPic.getWidth(null)*2, pFrogPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        snakePic = new ImageIcon("images/snake/snake0.png").getImage();
        snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        frog = new ImageIcon("images/frog/frog-V2.png").getImage();
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
        player.setPic(frog);

        keys = new boolean[KeyEvent.KEY_LAST+1];
        moveList = new LinkedList<MovingItems>();

        mainFrame = m;
        setSize(466, 553);
        addKeyListener(this);

        snake = new MovingItems(0, 268, 1, 0, "snake", snakePic);
        moveList.add(snake);

        for(int i=0; i<5; i++){
            pFrog = new MovingItems(22+i*96, 70, 0, 0, "point", pFrogPic);
            moveList.add(pFrog);
        }

        spawnTimer.start();
        spawnTimer2.start();
        aniTimer.start();

    }

//==================================spawning=============================================

    Timer spawnTimer = new Timer(2000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            rng = rand.nextInt(90);

            truck = new MovingItems(459+rng, 300, -1, 0, "car", carPics.get(0));
            moveList.add(truck);

            car = new MovingItems(0-rng, 390, 1, 0, "car", carPics.get(1));
            moveList.add(car);

            car2 = new MovingItems(459+rng, 364, -2, 0, "car", carPics.get(2));
            moveList.add(car2);

            car3 = new MovingItems(0-rng, 328, 3, 0, "car", carPics.get(3));
            moveList.add(car3);

            car4 = new MovingItems(459+rng, 423, -3, 0, "car", carPics.get(4));
            moveList.add(car4);

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

            log = new MovingItems(0-rng, 203, 1, 0, "log", logPic);
            moveList.add(log);

            log2 = new MovingItems(-30-rng, 171, 2, 0, "log", logPic);
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

            log3 = new MovingItems(0-rng, 108, 1, 0, "log", logPic);
            moveList.add(log3);
        }
    });

    Timer spawnTimer2 = new Timer(9000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            snake = new MovingItems(0, 268, 1, 0, "snake", snakePic);
            moveList.add(snake);
        }
    });

//=================================moving=================================================

    Timer moveTimer = new Timer(70, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(freeMove){
                for(MovingItems o : moveList)
                    {
                        if(player.getRect().intersects(o.getRect()) && o.getType().equals("point")){
                            pFrogPic = new ImageIcon("images/frog/pointFrog.png").getImage();
                            pFrogPic = pFrogPic.getScaledInstance(pFrogPic.getWidth(null)*2, pFrogPic.getHeight(null)*2, Image.SCALE_SMOOTH);
                            o.setPic(pFrogPic);
                            points+=100;
                            player.update();
                            moveTimer.stop();

                        }
                        if(player.getY()<108 && !player.getRect().intersects(o.getRect()) && o.getType()!="point"){
                            freeMove = false;
                        }
                        /*if (points>=500)
                        {
                            winPic = new ImageIcon("images/menu.png").getImage();

                        }*/
                    }
                moveSpace = 11;
                if(moveCounter == 2){
                    moveSpace = 10;
                }
                if(player.getDir() == UP){
                    player.moveY(-moveSpace);
                    frog = new ImageIcon("images/frog/frog-V"+moveCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
                if(player.getDir() == DOWN){
                    player.moveY(moveSpace);
                    frog = new ImageIcon("images/frog/frog-V"+moveCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
                if(player.getDir() == RIGHT){
                    player.moveX(moveSpace);
                    frog = new ImageIcon("images/frog/frog-H"+moveCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
                if(player.getDir() == LEFT){
                    player.moveX(-moveSpace);
                    frog = new ImageIcon("images/frog/frog-H"+moveCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
                moveCounter+=1;
                if(moveCounter == 3){
                    moveCounter = 0;
                    moveTimer.stop();
                }
            }
            else{
                moveTimer.stop();
            }
        }
    });

//============================animation======================================

    Timer aniTimer = new Timer(200, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sCounter+=1;

            if(pos){
                tCounter+=1;
            }
            else{
                tCounter-=1;
            }
            if(tCounter == 5){
                pos = false;
            }
            if(tCounter == 0){
                pos = true;
            }
            if(sCounter == 3){
                sCounter = 0;
            }
            if(dCounter == 4)
            {
                dCounter = 0;
                freeMove = true;
                player.update();
                player.setDir(UP);
                frog = new ImageIcon("images/frog/frog-V2.png").getImage();
                frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                player.setPic(frog);
            }

            if(freeMove == false){
                dCounter+=1;
                if(player.getY()<250){
                    frog = new ImageIcon("images/hit/hit"+dCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
                else{
                    frog = new ImageIcon("images/drown/drown"+dCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
            }

            for(MovingItems o : moveList)
            {
                if(o.getType().equals("turtle"))
                {
                    turtlePic = new ImageIcon("images/turtle/turtle"+tCounter+".png").getImage();
                    turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
                    o.setPic(turtlePic);
                }

                if(o.getType().equals("snake"))
                {
                    snakePic = new ImageIcon("images/snake/snake"+sCounter+".png").getImage();
                    snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
                    o.setPic(snakePic);
                }

                if(o.getType().equals("turtle1"))
                {
                    turtlePic = new ImageIcon("images/turtle/turtle"+sCounter+".png").getImage();
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

//===============================================collision====================================================

    public boolean checkCollision(MovingItems o)
    {
        if(player.getY()<250){
            if(o.getRect().intersects(player.getRect()) == true && o.getType().equals("log") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("turtle1") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("turtle") && tCounter<4)
            {
                //System.out.println("hit");
                if(freeMove){
                    player.moveX(o.getSpeedX());
                }
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
        if(player.getY()>250){ //not on water
            if(o.getRect().intersects(player.getRect()) == true && o.getType().equals("car") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("snake"))
            {
                freeMove = false;
                //player.update();
                return true;
            }
        }
        return true;
    }

//===================================input======================================

    public void move()
    {
        if(!moveTimer.isRunning()){
            if(allowMove && freeMove){
                if(keys[KeyEvent.VK_RIGHT] && player.getX()!= 426){
                    player.setDir(RIGHT);
                    moveTimer.start();
                }
                if(keys[KeyEvent.VK_LEFT] && player.getX() != 10){
                    player.setDir(LEFT);
                    moveTimer.start();
                }
                // && player.getY() != 108
                if(keys[KeyEvent.VK_UP]){
                    player.setDir(UP);
                    moveTimer.start();
                }

                /*for(int i=0;i<5;i++){
                    if(keys[KeyEvent.VK_UP] && player.getY() == 108 && player.getRect().intersects(pointSpaces.get(i))){
                        points+=100;
                        frog = new ImageIcon("images/frog/pointFrog.png").getImage();
                        frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);


                    }
                }*/

                if(keys[KeyEvent.VK_DOWN] && player.getY()!=460){
                    player.setDir(DOWN);
                    moveTimer.start();
                }
                allowMove = false;
            }
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

//=============================drawing=====================================

    public void paintComponent(Graphics g)
    {
        g.drawImage(back,0,0,this);
        waterRect = new Rectangle(6, 100, 224*2, 164);
        g.drawRect(6, 100, 224*2, 82*2);

        //System.out.println(player.getX()+" "+player.getY());

        if(player.getX()<-10 || player.getX()>440){
            freeMove = false;
        }

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
