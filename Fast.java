import java.util.Arrays;

public class Fast {
    
   private static Point[] points;
   public static void main(String[] args)
   {
       if (args.length < 1) return;
       
       readInput(args);
       int N = Fast.points.length;
       if (N < 1) return;
       
       // initialize scales for drawing
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
              
       Point[] copy = new Point[N]; // use copy for sorting
       for (int i = 0; i < N; i++)
           copy[i] = Fast.points[i];

       for (int i = 0; i < N; i++)
       {     
           Fast.points[i].draw();
           Arrays.sort(copy, Fast.points[i].SLOPE_ORDER);
           
           // scan for collinear points 
           double s1 = Fast.points[i].slopeTo(copy[0]);
           int lo = -1;
           int hi = -1;
           for (int j = 1; j < N; j++)
           {
               if (Fast.points[i].compareTo(copy[j]) == 0) continue; 
               
               double s2 = Fast.points[i].slopeTo(copy[j]);
               if (s1 == s2) 
               {
                   hi = j;
                   if (lo == -1) lo = j - 1;        
                   if (j == N - 1 && j - lo >= 2)   // end of array reached
                       drawLine(copy, Fast.points[i], lo, j);         
               }
               else
               {
                   s1 = s2;
                   if (lo != -1 && hi - lo >= 2) 
                       drawLine(copy, Fast.points[i], lo, hi);
                   
                   lo = hi = -1; // reset and continue. There may be other lines crossing same point
               }
           }
       }
   }
   
   private static void readInput(String[] args)
   {
       In input = new In(args[0]);
       int N = input.readInt();
       if (N < 1) return;
       Fast.points = new Point[N];
       for (int i = 0; i < N; i++)            
           Fast.points[i] = new Point(input.readInt(), input.readInt());
   }
   
   private static void drawLine(Point[] pts, Point ref, int lo, int hi)
   {
       Point[] line = new Point[hi - lo + 2];
       line[0] = ref; 
       for (int i = 1, j = lo; i < line.length; i++, j++)
           line[i] = pts[j];
           
       Arrays.sort(line); // by natural order to draw line
       
       // To avoid permutation of the same line segment -
       // Only draw if first point after sort is the same as reference
       // point used to compute slope
       if (ref.compareTo(line[0]) == 0) 
       {
           for (int i = 0; i < line.length - 1; i++)
               StdOut.printf("%s -> ", line[i]);
           
           StdOut.printf("%s\n", line[line.length - 1]);
           line[0].drawTo(line[line.length - 1]);
       }
   }
}