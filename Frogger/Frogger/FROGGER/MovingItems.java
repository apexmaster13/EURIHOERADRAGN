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
        srtX = x;
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

    public void update()
    {
        x += dx;
        /*if(x>-30 && x<480){
            x += dx;
        }
        else{
            x = srtX;
        }
        /*
        rng = rand.nextInt(3)+1;
        
        if(srtX == 0 && x<=459){
            x += dx;
        }
        
        else if(srtX == 459 && x>=-10){
            x += dx;
        }
        
        else if(srtX == 459 && x<-10){
            rng = rand.nextInt(3)+1;
            if(rng == 1){
                x = srtX;
            }
            else if(rng == 2){
                x = srtX + 50;
            }
            else{
                x = srtX + 100;
            }
        }
        else if(srtX == 0 && x>459){
            if(rng == 1){
                x = srtX;
            }
            else if(rng == 2){
                x = srtX - 50;
            }
            else{
                x = srtX - 100;
            }
        }*/
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

    public void draw(Graphics g, Component c)
    {
        dh = c.getHeight();
        dw = c.getWidth();
        //g.drawImage(pic , x, y, null);
        //g.drawImage(pic.getImage(), x, y%c.getHeight(), width, height, c);
        g.drawImage(pic, x, y, c);
        //g.drawRect(x, y%c.getHeight(), width, height);
        g.drawRect(x, y, pic.getWidth(null), pic.getHeight(null));
    }
}
