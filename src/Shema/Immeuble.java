package Shema;

import java.util.ArrayList;
import java.util.List;

public class Immeuble {

    public int g,h,d;

    public Immeuble(int g, int h, int d)
    {
        this.g = g;
        this.h = h;
        this.d = d;
    }

    public String toString()
    {
        List<Point> res = new ArrayList<Point>();
        res.add(new Point(g,h));
        res.add(new Point(d,0));
        return String.valueOf(res);
    }
}

