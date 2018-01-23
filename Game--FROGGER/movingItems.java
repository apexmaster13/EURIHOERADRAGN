
import java.awt.*;
import javax.swing.*;


public class movingItems extends Rectangle
{
	int dx, dy, dh, dw;
	String att;

	ImageIcon pic;
	movingItems()
	{
		x = 100;
		y = 150;

		dx = 5;
		dy = 0;

		width = 100;
		height = 50;
		att = "car";
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
		if(x < dw )
			x += dx;
		else
			x = 0;
	}
	public void setPic(ImageIcon p)
	{
		pic = p;
	}

	public void draw(Graphics g, Component c)
	{
		dh = c.getHeight();
		dw = c.getWidth();
		g.drawImage(pic.getImage(), x, c.getHeight(), width, height, c);
		g.drawRect(x, c.getHeight(), width, height);
	}
}
