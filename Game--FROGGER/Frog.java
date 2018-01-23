public class Frog{
    private int x = 298;
    private int y = 460;
    private int dir = 0;

    public void setDir(int d){
        dir = d;
    }

    public int getDir(){
        return dir;
    }

    public int getX(){
        return x;
    }

     public int getY(){
        return y;
    }

    public void moveX(int x){
        this.x += x;
    }

     public void moveY(int y){
        this.y += y;
    }

}