<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game6 extends JFrame implements ActionListener{
	Timer myTimer;
	GamePanel game;

    public Game6() {
		super("Move the Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,650);

		myTimer = new Timer(10, this);	 // trigger every 100 ms
		myTimer.start();

		game = new GamePanel();
		add(game);

		setResizable(false);
		setVisible(true);
    }

	public void actionPerformed(ActionEvent evt){
		if(game!= null && game.ready){
			game.move();
			game.repaint();
		}
	}

    public static void main(String[] arguments) {
		Game6 frame = new Game6();
    }
}

class GamePanel extends JPanel implements MouseMotionListener, MouseListener{
	private int destx,desty,boxx,boxy;
	public boolean ready=false;
	private boolean gotName=false;

	public GamePanel(){
		addMouseMotionListener(this);
		addMouseListener(this);
		boxx=200;
		boxy=200;
		destx=500;
		desty=200;
		setSize(800,600);
	}

    public void addNotify() {
        super.addNotify();
        ready = true;
    }

    public void move() {
		if(boxx<destx){
			boxx+=5;
		}
		if(boxx>destx){
			boxx-=5;
		}
		if(boxy<desty){
			boxy+=5;
		}
		if(boxy>desty){
			boxy-=5;
		}
         if((boxx==destx && boxy==desty) && !gotName){
         	gotName = true;
         	String name = JOptionPane.showInputDialog("Name:");
         	System.out.println(name);
         }
    }

    public void paintComponent(Graphics g){
		 g.setColor(new Color(222,222,255));
         g.fillRect(0,0,getWidth(),getHeight());
         g.setColor(new Color(255,111,111));
         g.fillOval(destx,desty,10,10);
         g.setColor(Color.green);
         g.fillRect(boxx,boxy,20,20);


    }

    // ------------ MouseListener ------------------------------------------
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e){
		destx = e.getX();
		desty = e.getY();
		destx -= destx %5;
		desty -= desty %5;
	}

    // ---------- MouseMotionListener ------------------------------------------
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}




=======
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game6 extends JFrame implements ActionListener{
	Timer myTimer;
	GamePanel game;

    public Game6() {
		super("Move the Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,650);

		myTimer = new Timer(10, this);	 // trigger every 100 ms
		myTimer.start();

		game = new GamePanel();
		add(game);

		setResizable(false);
		setVisible(true);
    }

	public void actionPerformed(ActionEvent evt){
		if(game!= null && game.ready){
			game.move();
			game.repaint();
		}
	}

    public static void main(String[] arguments) {
		Game6 frame = new Game6();
    }
}

class GamePanel extends JPanel implements MouseMotionListener, MouseListener{
	private int destx,desty,boxx,boxy;
	public boolean ready=false;
	private boolean gotName=false;

	public GamePanel(){
		addMouseMotionListener(this);
		addMouseListener(this);
		boxx=200;
		boxy=200;
		destx=500;
		desty=200;
		setSize(800,600);
	}

    public void addNotify() {
        super.addNotify();
        ready = true;
    }

    public void move() {
		if(boxx<destx){
			boxx+=5;
		}
		if(boxx>destx){
			boxx-=5;
		}
		if(boxy<desty){
			boxy+=5;
		}
		if(boxy>desty){
			boxy-=5;
		}
         if((boxx==destx && boxy==desty) && !gotName){
         	gotName = true;
         	String name = JOptionPane.showInputDialog("Name:");
         	System.out.println(name);
         }
    }

    public void paintComponent(Graphics g){
		 g.setColor(new Color(222,222,255));
         g.fillRect(0,0,getWidth(),getHeight());
         g.setColor(new Color(255,111,111));
         g.fillOval(destx,desty,10,10);
         g.setColor(Color.green);
         g.fillRect(boxx,boxy,20,20);


    }

    // ------------ MouseListener ------------------------------------------
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e){
		destx = e.getX();
		desty = e.getY();
		destx -= destx %5;
		desty -= desty %5;
	}

    // ---------- MouseMotionListener ------------------------------------------
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}




>>>>>>> 62e36704eda8dfc6574678ab1a98f1fdf88e4ff0
}