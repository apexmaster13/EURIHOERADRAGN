<<<<<<< HEAD
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game3 extends JFrame implements ActionListener{
    JButton nButton = new JButton("North");
    JButton sButton = new JButton("South");
    JButton eButton = new JButton("East");
    JButton wButton = new JButton("West");
    JButton cButton = new JButton("Center");
    
	Timer myTimer;   
	GamePanel game= new GamePanel();
		
    public Game3() {
		super("Move the Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLayout(new BorderLayout());
		
		nButton.addActionListener(this);
		sButton.addActionListener(this);
		eButton.addActionListener(this);
		wButton.addActionListener(this);
		
		myTimer = new Timer(100, this);	 // trigger every 100 ms
		myTimer.start();
						
		add(nButton, BorderLayout.NORTH);
		add(sButton, BorderLayout.SOUTH);
		add(eButton, BorderLayout.EAST);
		add(wButton, BorderLayout.WEST);
		add(game, BorderLayout.CENTER);

		setVisible(true);
    }

	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();
		if(source == nButton){
			game.setDirection(0,-5);
		}
		else if(source == sButton){
			game.setDirection(0,5);
		}
		else if(source == eButton){
			game.setDirection(5,0);
		}
		else if(source == wButton){
			game.setDirection(-5,0);
		}
		
		if(source == myTimer){
			game.move();
		}

	}

    public static void main(String[] arguments) {
		Game3 frame = new Game3();
		
    }
}

class GamePanel extends JPanel{
	private int boxx,boxy,dx,dy;
	
	public GamePanel(){
	    boxx = 170;
        boxy = 170;	
	}
	
	public void setDirection(int dx,int dy){
		this.dx=dx;
		this.dy=dy;
	}
	
	public void move(){
		boxx += dx;
		boxy += dy;
		repaint();
	}
	
	public void paintComponent(Graphics g){
         g.setColor(new Color(222,222,222));  
         g.fillRect(0,0,getWidth(),getHeight());  
         g.setColor(Color.blue);  
         g.fillRect(boxx,boxy,40,40);
    }
=======
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game3 extends JFrame implements ActionListener{
    JButton nButton = new JButton("North");
    JButton sButton = new JButton("South");
    JButton eButton = new JButton("East");
    JButton wButton = new JButton("West");
    JButton cButton = new JButton("Center");
    
	Timer myTimer;   
	GamePanel game= new GamePanel();
		
    public Game3() {
		super("Move the Box");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLayout(new BorderLayout());
		
		nButton.addActionListener(this);
		sButton.addActionListener(this);
		eButton.addActionListener(this);
		wButton.addActionListener(this);
		
		myTimer = new Timer(100, this);	 // trigger every 100 ms
		myTimer.start();
						
		add(nButton, BorderLayout.NORTH);
		add(sButton, BorderLayout.SOUTH);
		add(eButton, BorderLayout.EAST);
		add(wButton, BorderLayout.WEST);
		add(game, BorderLayout.CENTER);

		setVisible(true);
    }

	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();
		if(source == nButton){
			game.setDirection(0,-5);
		}
		else if(source == sButton){
			game.setDirection(0,5);
		}
		else if(source == eButton){
			game.setDirection(5,0);
		}
		else if(source == wButton){
			game.setDirection(-5,0);
		}
		
		if(source == myTimer){
			game.move();
		}

	}

    public static void main(String[] arguments) {
		Game3 frame = new Game3();
		
    }
}

class GamePanel extends JPanel{
	private int boxx,boxy,dx,dy;
	
	public GamePanel(){
	    boxx = 170;
        boxy = 170;	
	}
	
	public void setDirection(int dx,int dy){
		this.dx=dx;
		this.dy=dy;
	}
	
	public void move(){
		boxx += dx;
		boxy += dy;
		repaint();
	}
	
	public void paintComponent(Graphics g){
         g.setColor(new Color(222,222,222));  
         g.fillRect(0,0,getWidth(),getHeight());  
         g.setColor(Color.blue);  
         g.fillRect(boxx,boxy,40,40);
    }
>>>>>>> 62e36704eda8dfc6574678ab1a98f1fdf88e4ff0
}