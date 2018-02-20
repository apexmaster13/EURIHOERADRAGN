import java.awt.*;
import javax.swing.*;

// Class to control the player
public class Frog
{

    private int x = 298;
    private int y = 460;
    private int dir = 0;
    private int width = 24;
    private int height = 24;
    private Rectangle frogRect;
    private Image frog, pic;
    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;

    //used for checkcollisions
    public Rectangle getRect()
    {
        frogRect = new Rectangle(x,y, pic.getWidth(null), pic.getHeight(null));
        return frogRect;
    }
    //Setting Picture
    public void setPic(Image p)
    {
        pic = p;
    }
    // Set Direction
    public void setDir(int d)
    {
        dir = d;
    }

    //start position
    public void update()
    {
        x = 298;
        y = 460;
    }
    //Change position
    public void moveX(int x)
    {
        this.x += x;
    }

    public void moveY(int y)
    {
        this.y += y;
    }

    //
    public void draw(Graphics g)
    {
        if(dir == UP){
            //System.out.println("working");

            g.drawImage(pic , x, y, null);
        }
        else if(dir == LEFT){

            g.drawImage(pic , x, y, null);
        }
        else if(dir == DOWN){

            g.drawImage(pic , x, y + 18, 24, -18, null);
        }
        else if(dir == RIGHT){

            g.drawImage(pic , x + 18, y, -18, 24, null);

        }
        if(pic!= null){
         //g.drawRect(x, y, pic.getWidth(null), pic.getHeight(null));
        }
    }
   //-----------------------------getter methods----------------------------------------------

    public int getX()
    {
        return x;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getY()
    {
        return y;
    }
    public int getDir()
    {
        return dir;
    }

}
