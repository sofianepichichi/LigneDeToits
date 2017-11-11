package Shema;

public class Point {

    public int x,y;

    /**
     *
     * @param x ça représente le point X d'une ligne de toit
     * @param y ça représente le point Y d'une ligne de toit
     */
    public Point(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString()

    {
        return x+","+y;
    }


}