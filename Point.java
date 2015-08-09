/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import java.lang.NullPointerException;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) 
    {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() 
    {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) 
    {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) 
    {
        if (that == null)         throw  new NullPointerException();
        if (compareTo(that) == 0) return Double.NEGATIVE_INFINITY; // degenerate
        if (that.x == this.x)     return Double.POSITIVE_INFINITY;
        if (that.y == this.y)     return +0;
        return                    ((double)(that.y - this.y))/(that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) 
    {
        if (that == null)    throw  new NullPointerException();
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }
    
    // compare points according to their slope
    private class SlopeOrder implements Comparator<Point> 
    {
        public int compare(Point p, Point q) 
        {
            if (p == null || q == null) throw  new NullPointerException();
            Double ps = slopeTo(p);
            Double qs = slopeTo(q);
            return ps.compareTo(qs); 
        }
    }

    // return string representation of this point
    public String toString() 
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) 
    {
        /* YOUR CODE HERE */
        Point p = new Point(5, 0);
        Point q = new Point(0, 0);
        Point r = new Point(9, 0);
        int   s = p.SLOPE_ORDER.compare(q, r);
        double t = p.slopeTo(q);
        double u = p.slopeTo(r);
        StdOut.println(s);
        StdOut.println(t);
        StdOut.println(u);
    }
}