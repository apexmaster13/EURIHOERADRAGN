/*import javax.swing.*;

public class UpdateThread extends Thread
{
 GamePanel panel;

 UpdateThread(GamePanel p)
 {
  panel = p;
 }

 public void run()
 {
  //while(!panel.win)
  //{
   panel.update();
   try
   {
    Thread.sleep(50);
   } catch (InterruptedException e)
   {
    e.printStackTrace();
   }
  }
}*/