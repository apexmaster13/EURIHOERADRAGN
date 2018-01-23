<<<<<<< HEAD
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Game3 extends JFrame implements ActionListener{
	Timer myTimer;
	GamePanel game;

    public Game3() {
    	super("Cat and Mouse");
    	setSize(500,500);
    	game = new GamePanel();
    	myTimer = new Timer(10, this);
    	myTimer.start();
    	add(game);
    	setResizable(false);
    	setVisible(true);
    }

    public static void main(String[]args){
    	new Game3();
    }

    public void actionPerformed(ActionEvent evt){
    	if(game!=null){
    		game.refresh();
    		game.repaint();
    	}
    }
}

class GamePanel extends JPanel implements MouseListener{
	private int mousex, mousey, katx, katy;
	Image back, kat, mouse;

	public GamePanel(){
		addMouseListener(this);
		back = new ImageIcon("back.jpg").getImage();
		kat = new ImageIcon("Kat.png").getImage();
		mouse = new ImageIcon("mouse.png").getImage();
		mouse = mouse.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		kat = kat.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		mousex = 0;
		mousey = 0;
		katx = 500;
		katy = 500;
	}

	public void refresh(){
		if(mousex > katx){
			katx+=5;
		}
		if(mousex < katx){
			katx-=5;
		}
		if(mousey > katy){
			katy+=5;
		}
		if(mousey < katy){
			katy-=5;
		}
	}

	public void paintComponent(Graphics g){
		g.drawImage(back, 0, 0, this);
		g.drawImage(kat, katx, katy, this);
		g.drawImage(mouse, mousex, mousey, this);

		/*g.setColor(new Color(150, 200, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillOval(mousex-5, mousey-5, 10, 10);
		g.setColor(new Color(125, 0, 125));
		g.fillRect(katx-10, katy-10, 20, 20);*/
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

	public void mousePressed(MouseEvent e){
		mousex = e.getX();
		mousey = e.getY();
	}


=======
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Game3 extends JFrame implements ActionListener{
	Timer myTimer;
	GamePanel game;

    public Game3() {
    	super("Cat and Mouse");
    	setSize(500,500);
    	game = new GamePanel();
    	myTimer = new Timer(10, this);
    	myTimer.start();
    	add(game);
    	setResizable(false);
    	setVisible(true);
    }

    public static void main(String[]args){
    	new Game3();
    }

    public void actionPerformed(ActionEvent evt){
    	if(game!=null){
    		game.refresh();
    		game.repaint();
    	}
    }
}

class GamePanel extends JPanel implements MouseListener{
	private int mousex, mousey, katx, katy;
	Image back, kat, mouse;

	public GamePanel(){
		addMouseListener(this);
		back = new ImageIcon("back.jpg").getImage();
		kat = new ImageIcon("Kat.png").getImage();
		mouse = new ImageIcon("mouse.png").getImage();
		mouse = mouse.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		kat = kat.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		mousex = 0;
		mousey = 0;
		katx = 500;
		katy = 500;
	}

	public void refresh(){
		if(mousex > katx){
			katx+=5;
		}
		if(mousex < katx){
			katx-=5;
		}
		if(mousey > katy){
			katy+=5;
		}
		if(mousey < katy){
			katy-=5;
		}
	}

	public void paintComponent(Graphics g){
		g.drawImage(back, 0, 0, this);
		g.drawImage(kat, katx, katy, this);
		g.drawImage(mouse, mousex, mousey, this);

		/*g.setColor(new Color(150, 200, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillOval(mousex-5, mousey-5, 10, 10);
		g.setColor(new Color(125, 0, 125));
		g.fillRect(katx-10, katy-10, 20, 20);*/
	}

	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

	public void mousePressed(MouseEvent e){
		mousex = e.getX();
		mousey = e.getY();
	}


>>>>>>> 62e36704eda8dfc6574678ab1a98f1fdf88e4ff0
}