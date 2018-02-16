import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MenuEg3 extends JFrame implements ActionListener{

	Timer myTimer;
	GamePanel gamePage = new GamePanel();
	
    public MenuEg3 ()
    {
		super ("Menu Eg");
		setSize (800, 600);
		myTimer = new Timer(10,this);
		add(gamePage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new MyMenu(this);

    }

    public void actionPerformed(ActionEvent evt) {
		gamePage.move();
		gamePage.repaint();
    }

	public void start(){
		myTimer.start();
		setVisible(true);
	}

    public static void main(String[] args){
		MenuEg3 menuEg = new MenuEg3();
    }
}

class MyMenu extends JFrame implements ActionListener{
	private MenuEg3 me3;
	JButton playBtn = new JButton("Play");
		
	public MyMenu(MenuEg3 m){
		super ("This menu is it's own Frame");
		setSize (800, 600);
		me3 = m;	
		playBtn.addActionListener(this);
		ImageIcon back = new ImageIcon("images/frogback.jpg");
		JLabel backLabel = new JLabel(back);
		JLayeredPane mPage=new JLayeredPane(); 	// LayeredPane allows my to control what shows on top
		mPage.setLayout(null);
		
		backLabel.setSize(800,600);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);					// The numbers I use when adding to the LayeredPane
												// are just relative to one another. Higher numbers on top.
		playBtn.setSize(100,30);
		playBtn.setLocation(350,400);
		mPage.add(playBtn,2);
		
		add(mPage);
		setVisible(true);
	}
	
    public void actionPerformed(ActionEvent evt) {
    	setVisible(false);
		me3.start();
    }	
}

class GamePanel extends JPanel implements MouseListener{
	private int destx,desty,boxx,boxy;

	public GamePanel(){
		addMouseListener(this);
		boxx=200;
		boxy=200;
		destx=500;
		desty=200;
		setSize(800,600);
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
	}

}

