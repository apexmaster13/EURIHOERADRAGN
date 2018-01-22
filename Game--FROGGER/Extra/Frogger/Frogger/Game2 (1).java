import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Game2 extends JFrame implements ActionListener{
	Timer myTimer;

	GamePanel game = new GamePanel();

    public Game2() {
    	super("Move the Box");
    	myTimer = new Timer(100,this);
    	myTimer.start();
    	setSize(500,500);
    	setVisible(true);
    }

    public static void main(String[]arguments){
    	new Game2();
    }

    public void actionPerformed(ActionEvent e){
    	game.refresh();
    	game.repaint();
    }
}

class GamePanel extends JPanel implements KeyListener{
	private int boxx, boxy, dx, dy;
	private boolean [] keys;

	public GamePanel(){
		addKeyListener(this);
		requestFocus();
		boxx = 170;
		boxy = 170;
		keys = new boolean [KeyEvent.KEY_LAST+1];
	}

	@Override
	public void paintComponent(Graphics g){
		g.setColor(new Color(222,222,255));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.blue);
		g.fillRect(boxx, boxy,40,40);
	}

	public void shift(){
		boxx +=dx;
		boxy +=dy;
		//repaint();
	}

	public void setDirection(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}

	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e){

	}

	public void refresh(){
		if(keys[KeyEvent.VK_UP]){
			boxy -=10;
		}
		if(keys[KeyEvent.VK_DOWN]){
			boxy +=10;
		}
		if(keys[KeyEvent.VK_RIGHT]){
			boxx +=10;
		}
		if(keys[KeyEvent.VK_LEFT]){
			boxy -=10;
		}
	}
}