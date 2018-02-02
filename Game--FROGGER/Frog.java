import java.awt.*;
import javax.swing.*;

public class Frog
{

    private int x = 298;
    private int y = 460;
    private int dir = 0;
    private int width = 30;
    private int height = 30;
    private Rectangle frogRect;
    private ImageIcon pic;

    public Rectangle getRect()
    {
        return frogRect;
    }
	
    public void setPic(ImageIcon p)
    {
        pic = p;
    }

    public void setDir(int d)
    {
        dir = d;
    }

    public int getDir()
    {
        return dir;
    }

    public void update()
	{
		x = 298;
		y = 460;
	}

    public int getX()
    {
        return x;
    }

    public int height()
    {
        return height;
    }

    public int width()
    {
        return width;
    }

     public int getY()
     {
        return y;
    }

    public void moveX(int x)
    {
        this.x += x;
    }

    public void moveY(int y)
    {
        this.y += y;
    }

    public void draw(Graphics g, Component c)
    {
        g.drawImage(pic.getImage(),x,y,width,height,c);
        //g.drawRect(x, y, width, height);
    	Rectangle frogRect = new Rectangle(x,y,width,height);
    }

}
