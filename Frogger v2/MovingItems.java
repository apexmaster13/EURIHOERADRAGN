//Simple class that deals with returning and setting MovingItem object values. Is also used to draw and move the objects.

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MovingItems
{
    private int dx, dy, dh, dw, width, height, x, srtX, y;
    private String type;
    private Image pic;
    private Rectangle rect;
    private Random rand = new Random();
    private int rng;

    MovingItems(int x, int y, int dx, int dy, String type, Image pic)
    {
        this.x = x;
        //srtX = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.pic = pic;
        this.type = type;
       // this.width = width;
        //this.height = height;
    }

    public void setSpeed(int xspeed, int yspeed)
    {
        dx = xspeed;
        dy = yspeed;
    }

    public void setXY(int xpos, int ypos)
    {
        x = xpos;
        y = ypos;
    }

    public void update() //makes the moving items move horizonally according to their speed
    {
        x += dx;
    }

     public void setPic(Image p)
    {
        pic = p;
    }

//-----------------------------getter methods----------------------------------------------

    public Rectangle getRect()
    {
        rect = new Rectangle(x,y,pic.getWidth(null),pic.getHeight(null));
        return rect;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getSpeedX(){
        return dx;
    }

    public String getType(){
        return type;
    }
    //--------------------------------------------------------------------------------------------------------

    public void draw(Graphics g, Component c)
    {
        dh = c.getHeight();
        dw = c.getWidth();

        g.drawImage(pic, x, y, c);
        //g.drawRect(x, y, pic.getWidth(null), pic.getHeight(null));
    }
}
