import java.awt.*;
import javax.swing.*;

public class MovingItems
{
    private int dx, dy, dh, dw, width, height, x, srtX, y;
    private String type;
    private Image pic;
    private Rectangle rect;
    
    MovingItems(int x, int y, int dx, int dy, String type, Image pic, int width, int height)
    {
        this.x = x;
        srtX = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.pic = pic;
        this.type = type;
        this.width = width;
        this.height = height;
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
        if(x < dw && x > -10){
            x += dx;
        }
        else{
            x = srtX;
        }
    }
    
     public void setPic(Image p)
    {
        pic = p;
    }

//-----------------------------getter methods----------------------------------------------
    
    public Rectangle getRect()
    {
        rect = new Rectangle(x,y,width,height);
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
    
    public void draw(Graphics g, Component c)
    {
        dh = c.getHeight();
        dw = c.getWidth();
        //g.drawImage(pic , x, y, null);
        //g.drawImage(pic.getImage(), x, y%c.getHeight(), width, height, c);
        g.drawImage(pic, x, y, c);
        //g.drawRect(x, y%c.getHeight(), width, height);
        g.drawRect(x, y, width, height);
    }
}
