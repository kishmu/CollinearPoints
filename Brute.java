import java.util.Arrays;

public class Brute 
{   
   private static Point[] points;
   public static void main(String[] args)
   {
       if (args.length < 1) return;
       readInput(args);
       int N = Brute.points.length;
       
       // initialize scales for drawing
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       
       // 4-tuple collinearity check
       for (int p = 0; p < N; p++)
       {
           Brute.points[p].draw();
           for(int q = p + 1; q < N; q++)
           {
               for (int r = q + 1; r < N; r++)
               {
                   for (int s = r + 1; s < N; s++)
                   {
                       Point pp = Brute.points[p];
                       Point qq = Brute.points[q];
                       Point rr = Brute.points[r];
                       Point ss = Brute.points[s];
                       
                       if (pp.SLOPE_ORDER.compare(qq, rr) != 0) continue; // p,q,r not collinear. No need to check s.
                       if (pp.SLOPE_ORDER.compare(rr, ss) == 0)
                       {
                           StdOut.printf("%s -> %s -> %s -> %s\n", pp, qq, rr, ss);
                           pp.drawTo(ss); // since the points array was sorted to begin with
                       }
                   }
               }
           }
       }   
   }
   
   private static void readInput(String[] args)
   {
       In input = new In(args[0]);
       int N = input.readInt();
       Brute.points = new Point[N];
       for (int i = 0; i < N; i++)            
           Brute.points[i] = new Point(input.readInt(), input.readInt());
       Arrays.sort(Brute.points); // sort by natural order for drawing lines
   }
}