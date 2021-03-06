import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import sun.audio.*;

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
    private int timerLength = 240;
    private int tCounter, sCounter, dCounter, moveCounter, moveSpace, points, objectSpeed;
    private int lives = 5;
    private boolean pos = true;
    private boolean freeMove = true;



    private ArrayList<Image> carPics = new ArrayList<Image>();

    MovingItems truck, car, car2, car3, car4, log, log2, log3, turtle, turtle2, snake, pFrog;

    Image back, frog, pFrogPic, pic, logPic, bLogPic, turtlePic, snakePic, lifePic, winPic, losePic;

    LinkedList<MovingItems> moveList; // Linkedlist for all the main game objects


//====================================constructor=================================================

    public GamePanel(Frogger m)
    {

        //=================================Setting Images=========================================
        back = new ImageIcon("images/back.jpg").getImage();
        back = back.getScaledInstance(back.getWidth(null)*2,back.getHeight(null)*2, Image.SCALE_SMOOTH);
        for(int i=0; i<5; i++){
            pic = new ImageIcon("images/cars/car"+i+".png").getImage();
            pic = pic.getScaledInstance(pic.getWidth(null)*2, pic.getHeight(null)*2, Image.SCALE_SMOOTH);
            carPics.add(pic);
        }
        logPic = new ImageIcon("images/logs/log0.png").getImage();
        logPic = logPic.getScaledInstance(logPic.getWidth(null)*2, logPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        bLogPic = new ImageIcon("images/logs/log1.png").getImage();
        bLogPic = bLogPic.getScaledInstance(bLogPic.getWidth(null)*2, bLogPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        winPic = new ImageIcon("images/win.png").getImage();
        winPic = winPic.getScaledInstance(winPic.getWidth(null)*2, winPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        losePic = new ImageIcon("images/lose.png").getImage();
        losePic = losePic.getScaledInstance(losePic.getWidth(null)*2, losePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        turtlePic = new ImageIcon("images/turtle/turtle4.png").getImage();
        turtlePic = turtlePic.getScaledInstance(turtlePic.getWidth(null)*2, turtlePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        pFrogPic = new ImageIcon("images/frog/blankFrog.png").getImage();
        pFrogPic = pFrogPic.getScaledInstance(pFrogPic.getWidth(null)*2, pFrogPic.getHeight(null)*2, Image.SCALE_SMOOTH);
        snakePic = new ImageIcon("images/snake/snake0.png").getImage();
        snakePic = snakePic.getScaledInstance(snakePic.getWidth(null)*2, snakePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        lifePic = new ImageIcon("images/frog/life.png").getImage();
        lifePic = lifePic.getScaledInstance(lifePic.getWidth(null)*2, lifePic.getHeight(null)*2, Image.SCALE_SMOOTH);
        frog = new ImageIcon("images/frog/frog-V2.png").getImage();
        frog = frog.getScaledInstance(24, 18, Image.SCALE_SMOOTH);
        player.setPic(frog);

        //=========================================================================================

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
        playSound("sounds/coin.wav");
        //====================Timers========================
        carSpawner.start();
        waterSpawner.start();
        snakeSpawner.start();
        bigLogSpawner.start();
        aniTimer.start();
        updateTimer.start();


    }
    
//===================================sound================================================
    
    public void playSound(String path){
        try{
            InputStream in = new FileInputStream(path);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        } 
    }
    
//==================================spawning=============================================
    //CARS-------------------------------------------------------------------------------
    Timer carSpawner = new Timer(3000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            rng = rand.nextInt(90);

            truck = new MovingItems(459+rng, 300, -3, 0, "car", carPics.get(0));
            moveList.add(truck);

            car = new MovingItems(0-rng, 390, 3, 0, "car", carPics.get(1));
            moveList.add(car);

            car2 = new MovingItems(459+rng, 364, -4, 0, "car", carPics.get(2));
            moveList.add(car2);

            car3 = new MovingItems(0-rng, 328, 5, 0, "car", carPics.get(3));
            moveList.add(car3);

            car4 = new MovingItems(459+rng, 423, -5, 0, "car", carPics.get(4));
            moveList.add(car4);
        }
    });
    //WATER-----------------------------------------------------------------------------
    Timer waterSpawner = new Timer(6000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            rng = rand.nextInt(50);
            //TURTLE
            if(rand.nextInt(3)+1 == 1){
                for(int i=0; i<rand.nextInt(4)+2; i++){
                    turtle = new MovingItems(465+i*26, 236, -2, 0, "turtle", turtlePic);
                    moveList.add(turtle);
                }
            }
            else{
                for(int i=0; i<rand.nextInt(4)+2; i++){
                    turtle = new MovingItems(465+i*26, 236, -2, 0, "turtle1", turtlePic);
                    moveList.add(turtle);
                }
            }
            //LOG
            rng = rand.nextInt(50);
            log = new MovingItems(-30-rng, 203, 2, 0, "log", logPic);
            moveList.add(log);

            /*rng = rand.nextInt(90);
            log2 = new MovingItems(-170, 171, 1, 0, "log", bLogPic);
            moveList.add(log2);*/

            rng = rand.nextInt(90);
            //TURTLE-2
            if(rand.nextInt(3)+1 == 1){
                for(int i=0; i<rand.nextInt(4)+2; i++){
                    turtle2 = new MovingItems(465+i*26, 136, -2, 0, "turtle", turtlePic);
                    moveList.add(turtle2);
                }
            }
            else{
                for(int i=0; i<rand.nextInt(4)+2; i++){
                    turtle2 = new MovingItems(465+i*26, 136, -2, 0, "turtle1", turtlePic);
                    moveList.add(turtle2);
                }
            }

            rng = rand.nextInt(50);
            log3 = new MovingItems(-30-rng, 108, 2, 0, "log", logPic);
            moveList.add(log3);
        }
    });
    //SNAKE============================================================
    Timer snakeSpawner = new Timer(20000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            snake = new MovingItems(0, 268, 2, 0, "snake", snakePic);
            moveList.add(snake);
        }
    });

    Timer bigLogSpawner = new Timer(6500, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            rng = rand.nextInt(90);
            log2 = new MovingItems(-170+rng, 171, 3, 0, "log", bLogPic);
            moveList.add(log2);
        }
    });

    Timer updateTimer = new Timer(50, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            player.moveX(objectSpeed);
            if(player.getY()<250 && player.getY()>100&& objectSpeed == 0){
                freeMove = false;
            }
            objectSpeed = 0;
            for(MovingItems o : moveList)
            {
                //o.draw(g, this);
                o.update();
                //checkCollision(o);
            }
        }
    });

//=================================moving=================================================

    Timer moveTimer = new Timer(70, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(freeMove){

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
            for(MovingItems o : moveList)
            {
                if(player.getRect().intersects(o.getRect()) && o.getType().equals("point")){
                    frog = new ImageIcon("images/frog/frog-V2.png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                    pFrogPic = new ImageIcon("images/frog/pointFrog.png").getImage();
                    pFrogPic = pFrogPic.getScaledInstance(pFrogPic.getWidth(null)*2, pFrogPic.getHeight(null)*2, Image.SCALE_SMOOTH);
                    o.setPic(pFrogPic);
                    points+=100;
                    timerLength=240;
                    player.update();
                    moveCounter = 0;
                    moveTimer.stop();
                    break;

                }
                else if(player.getY()<90 && player.getRect().intersects(o.getRect()) == false && o.getType()!="point" && moveCounter == 0){
                    freeMove = false;
                }

            }
        }
    });

//============================animation======================================

    Timer aniTimer = new Timer(200, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            sCounter+=1;
            timerLength-=1;
            if(timerLength == 0){
                System.out.println("GameOver");

            }
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
                lives-=1;
                System.out.println(lives);
                if(lives == 0){
                    System.out.println("GameOver");
                }
                player.setDir(UP);
                frog = new ImageIcon("images/frog/frog-V2.png").getImage();
                frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                player.setPic(frog);
            }

            if(freeMove == false){
                dCounter+=1;
                if(player.getY()<250){
                    if(dCounter == 1){
                        playSound("sounds/drown.wav");
                    }
                    frog = new ImageIcon("images/hit/hit"+dCounter+".png").getImage();
                    frog = frog.getScaledInstance(frog.getWidth(null)*2, frog.getHeight(null)*2, Image.SCALE_SMOOTH);
                    player.setPic(frog);
                }
                else{
                    if(dCounter == 1){
                        playSound("sounds/hit.wav");
                    }
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

//===============================================collusion====================================================

    public boolean checkCollision(MovingItems o)
    {
        //COLLISIONS IN WATER(logs & turtles)
        if(player.getY()<250)
        {
            if(o.getRect().intersects(player.getRect()) == false && o.getType().equals("log") && moveCounter == 0 || o.getRect().intersects(player.getRect()) == false && o.getType().equals("turtle1") && moveCounter == 0 || o.getRect().intersects(player.getRect()) == false && o.getType().equals("turtle") && tCounter<4 && moveCounter == 0)
            {
                return true;
            }

            if(o.getRect().intersects(player.getRect()) == true && o.getType().equals("log") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("turtle1") || o.getRect().intersects(player.getRect()) == true && o.getType().equals("turtle") && tCounter<4)
            {

                if(freeMove){
                    objectSpeed = o.getSpeedX();
                }
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
        //keyboard input
        if(!moveTimer.isRunning()){
            if(allowMove && freeMove){
                if(keys[KeyEvent.VK_RIGHT] && player.getX()!= 426){
                    player.setDir(RIGHT);
                    moveTimer.start();
                    playSound("sounds/hop.wav");
                }
                if(keys[KeyEvent.VK_LEFT] && player.getX() != 10){
                    player.setDir(LEFT);
                    moveTimer.start();
                    playSound("sounds/hop.wav");
                }
                // && player.getY() != 108
                if(keys[KeyEvent.VK_UP]){
                    player.setDir(UP);
                    moveTimer.start();
                    playSound("sounds/hop.wav");
                }

                if(keys[KeyEvent.VK_DOWN] && player.getY()!=460){
                    player.setDir(DOWN);
                    moveTimer.start();
                    playSound("sounds/hop.wav");
                }
                allowMove = false;
            }
        }
    }

    public void keyTyped(KeyEvent e){}//auto generated

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

         for(int i=1; i<=lives; i++)
         {
            g.drawImage(lifePic, 8+i*16, 490, null);
         }

         if(points == 500)
         {
             g.drawImage(winPic, 0, 100, null);

         }
         if(lives<1||timerLength==0)
         {
            g.drawImage(losePic, 0, 100, null);
            freeMove = false;

           /* JButton restartButton = new JButton();
            restartButton.setBackground(new Color(255,255,255));
           // restartButton.setOpaque(false);
            restartButton.setSize(100,30);
            restartButton.setLocation(190,270);
        */
        }

        //System.out.println(player.getX()+" "+player.getY());
        //side borders=====================================================================
        if(player.getX()<-10 || player.getX()>440)
        {
            freeMove = false;
        }
        //timer display =====================================================================
        if(timerLength>0){
            g.setColor(Color.green);
            //g.fillRect(boxx,boxy,40,40);
            g.fillRect(150, 504, timerLength, 16);
        }
        //MovingItems display ===================================================================
        for(MovingItems o : moveList)
        {
            o.draw(g, this);
            //o.update();
            checkCollision(o);
        }

        for(int i=moveList.size()-1; i>=0; i--){
            MovingItems o = moveList.get(i);
            if(o.getX()>600 || o.getX()<-200){
                moveList.remove(o);
            }
        }
        // Displays Character if there are lifes and time left
        if(lives>=1||timerLength<0)
        {
           player.draw(g);
        }
    }


}


