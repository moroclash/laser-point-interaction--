
package test_2;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Test_2 {

    
   /*183.0f
,183.0f
,175.0f
,170.0f
,157.0f
,132.0f
,134.0f
,171.0f
,200.0f
,212.0f
,198.0f
,161.0f
,150.0f
,169.0f
,176.0f
,159.0f
,98.0f
,125.0f
,138.0f
,132.0f
,129.0f
,163.0f
,171.0f
,159.0f
,140.0f};*/
 
     /*183.0
183.0
175.0
170.0
157.0
132.0
134.0
171.0
200.0
212.0
198.0
161.0
150.0
169.0
176.0
159.0
98.0
125.0
138.0
132.0
129.0
163.0
171.0
159.0
140.0*/ 

 private static float cicle[] = {
 135.0f
,108.0f
,41.0f
,79.0f
,92.0f
,109.0f
,150.0f
,193.0f
,131.0f
,118.0f
,89.0f
,64.0f
,44.0f
,91.0f
,135.0f
  };

    
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m1 = Imgcodecs.imread("pop.jpg");
        Mat m2 = Imgcodecs.imread("moro.jpg");
        ArrayList<org.opencv.core.Point> a1 = test(m1);
        ArrayList<org.opencv.core.Point> a2 = test(m2);
        float[] n2 = new float[a1.size()];   //{X1,X2,X3........Xn}
        float[] n1 = new float[a2.size()]; //{Y1,Y2,Y3........Yn}
        int i = 0;
        for(org.opencv.core.Point p : a1)
        {
            if(i==10)
                break;
            n1[i] = (float) p.x;
            i++;
        }
        i=0;
        for(org.opencv.core.Point p : a2)
        {
            if(i==10)
                break;
            n2[i] = (float) p.x;
            i++;
        }
        
        /*94.78905447070915*/
        DTW d = new DTW(n1,n2);
        
        System.out.println(d);
    }
    
    
    
    
    private static float dis_between_two_points(org.opencv.core.Point p1 ,org.opencv.core.Point p2 )
    {
        return (float) (Math.abs(p2.x-p1.x)+Math.abs(p2.y-p1.y));
    }
    
    
    private static org.opencv.core.Point cinterpoint(ArrayList<org.opencv.core.Point> points)
    {
        int X=0 , Y=0 ;
        double lsX=points.get(0).x , lsY = points.get(0).y , maX=points.get(0).x , maY=points.get(0).y ;
        org.opencv.core.Point Maxxpoint= new org.opencv.core.Point();
        org.opencv.core.Point Minxpoint= new org.opencv.core.Point();
        org.opencv.core.Point Maxypoint= new org.opencv.core.Point();
        org.opencv.core.Point Minypoint= new org.opencv.core.Point();
        for(int i =0;i<points.size()-1;i++)
        {
            org.opencv.core.Point p = points.get(i);     
            X=0 ; Y=0 ;
            if(p.x>maX)
            {
                maX = p.x;
                Maxxpoint.x = p.x;
                Maxxpoint.y = p.y;
            }
            else
            {
                lsX=p.x;
                Minxpoint.x = (int) p.x;
                Minxpoint.y = (int) p.y;
            }
            if(p.y>maY)
            {
                maY = p.y;
                maX = p.x;
                Maxypoint.x = (int) p.x;
                Maxypoint.y = (int) p.y;
            }
            else
            {
                lsY=p.y;
                maX = p.x;
                Minypoint.x = (int) p.x;
                Minypoint.y = (int) p.y;
            }

        }
        float d1,d2,distance;
        d1=dis_between_two_points(Minxpoint,Maxxpoint);
        d2=dis_between_two_points(Minypoint,Maxypoint);
        int x,y;
        if(d1>d2)
        {
            distance = d1 ;
            x = (int) ((Minxpoint.x+Maxxpoint.x) / 2);
            y = (int) ((Minxpoint.y+Maxxpoint.y) / 2);
        }
        else
        {
            distance = d2 ;
            x = (int) ((Minypoint.x+Maxypoint.x) / 2);
            y = (int) ((Minypoint.y+Maxypoint.y) / 2);
        }
        return new org.opencv.core.Point(x, y);
    }
    
   
    private static boolean iscircel(ArrayList<org.opencv.core.Point> points,Mat mat)
{ 
    org.opencv.core.Point midpoint = cinterpoint(points) ;
    float[] avrages = new float[points.size()]; 
    int i =0;
    for(org.opencv.core.Point p : points)
    {
        Imgproc.circle(mat, p, 5, new Scalar(255, 255, 10));
        avrages[i] = dis_between_two_points(p, midpoint);
       // System.out.println(avrages[i]);
        i++;
    }

    DTW x = new DTW(cicle, avrages);
    System.out.println("defrance : " + x.getWarpingDistance() );
    Imgcodecs.imwrite("moro.jpg", mat);
    if(x.getWarpingDistance()<=800)
    return true;
    else
        return false;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*that my first function */
   /* private static boolean iscircel(ArrayList<org.opencv.core.Point> points,Mat mat)
    { 
    int X=0 , Y=0 ;
    double lsX=points.get(0).x , lsY = points.get(0).y , maX=points.get(0).x , maY=points.get(0).y ;
    if((points.get(points.size()-1).x<points.get(0).x)||(points.get(points.size()-1).y>points.get(0).y))
    {
        return false;
    }
    for(int i =0;i<points.size()-1;i++)
    {
        org.opencv.core.Point p = points.get(i);
        Imgproc.circle(mat, p, 5, new Scalar(255, 0, 0));
        Imgproc.line(mat, points.get(i), points.get(i+1), new Scalar(0, 255, 0));
        X=0 ; Y=0 ;
        if(p.x>maX)
            maX = p.x;
        else
            lsX=p.x;
        if(p.y>maY)
            maY = p.y;
        else
            lsY=p.y;
        for(org.opencv.core.Point pp : points)
        {
            if(pp.x == p.x)
                X++;
            if(pp.y==p.y)
                Y++;
            if(X==6||Y==6)
            {
                return false;
            }
        }
    }
    double wid =(maX-lsX);
    double lenth =(maY-lsY);
    double size = wid*lenth;
    if(size<stander||wid<100||lenth<100)
        return false;
    Imgcodecs.imwrite("moro.jpg", mat);
    return true;
    }*/
    
    private static boolean ccircel = false;    
    
    public static ArrayList<org.opencv.core.Point> test(Mat mat)
    {
            ArrayList<org.opencv.core.Point> color = new ArrayList<>();
        try {
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
            int width = mat.width();
            int height = mat.height();
            MatOfByte m = new MatOfByte();
            Imgcodecs.imencode(".jpg", mat, m);
            InputStream in = new ByteArrayInputStream(m.toArray());
            BufferedImage buffer = ImageIO.read(in);
            java.awt.Color co = new java.awt.Color(buffer.getRGB(0, 0));
            int max = co.getRed(),Xmax=0,Ymax=0;
            int wap=0;
            for(int i =0;i<height;i++)
            {
                for(int j=1;j<width;j++)
                {
                    co = new java.awt.Color(buffer.getRGB(j, i));
                    if(max==co.getRed())
                    {
                        color.add(new org.opencv.core.Point(j, i));
                    }
                    else if(co.getRed() > max)
                    {
                        max = co.getRed();
                        color = new ArrayList<>();
                        color.add(new org.opencv.core.Point(j, i));
                    }
                }
            }
           //Mat ma = draw(color,mat,1);
           return color;
        } catch (IOException ex) {
            System.out.println("error in function points");
        }
        return null;
    }

    public static boolean isCcircel() {
        return ccircel;
    }    

    public static void setCcircel(boolean ccircel) {
        Test_2.ccircel = ccircel;
    }
    
    
 private boolean flage = false;
 private static int stander = 200,ch=0,kk=0;
 public static boolean left = false,right = false,up = false,booton = false,havegreen=false;
 private static ArrayList<org.opencv.core.Point> circel = new ArrayList<>(16);
 
  public static Mat points(Mat comat,Mat mat,int choise,int  pwidth,int pheight)
    {
        kk++;
            ArrayList<org.opencv.core.Point> color = new ArrayList<>();
        try { 
            //havegreen=false;
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
            int width = mat.width();
            int height = mat.height();
            MatOfByte m = new MatOfByte();
            Imgcodecs.imencode(".jpg", mat, m);
            InputStream in = new ByteArrayInputStream(m.toArray());
            BufferedImage buffer = ImageIO.read(in);
            java.awt.Color co = new java.awt.Color(buffer.getRGB(0, 0));
            int max = co.getRed(),Xmax=0,Ymax=0;
            int wap=0;
            for(int i =0;i<height;i++)
            {
                for(int j=1;j<width;j++)
                {
                    co = new java.awt.Color(buffer.getRGB(j, i));
                    if(co.getRed()>250)
                    {
                        color.add(new org.opencv.core.Point(j, i));
                        //havegreen=true;
                    }
                }
            }
            if(color.size() < 15 )
           {
               if(choise == 1)
                  return mat;
               else
                  return comat;
           }
           if(color.size()>5&&kk<=17)
           {
                if(circel.size() >= 15)
                {
                    ccircel = iscircel(circel,comat);
                    System.out.println(ccircel);
                    circel = new ArrayList<>();
                }
                else
                {
                    circel.add(cinterpoint(color));
                }
           }
           else
           {
               circel = new ArrayList<>();
               kk=0;
           }
            
            for(org.opencv.core.Point p : color)
            {
                if(p.x < width * 0.01)
                {
                    left = false;
                    right = true;
                    up = false;
                    booton = false;
                }
                else if(p.x > width * 0.99)
                {
                        right = false;
                        left = true;
                        up = false;
                        booton = false;
                        ch=0;
                }
                else if(p.y < height * 0.01)
                {
                    up = true;
                    left = false;
                    right = false;
                    booton = false;
                }
                else if(p.y > height * 0.99)
                {
                    booton = true;
                    up = false;
                    left = false;
                    right = false;
                }
            }
           Mat ma;
          if(choise == 1)
                ma = draw(color,mat,choise);
           else
                ma = draw(color,comat,choise);
           return ma;
        } catch (IOException ex) {
            System.out.println("error in function points");
        }
        return null;
    }
 
  
/*private static boolean checkpoints(ArrayList<org.opencv.core.Point> points)
{
    for(org.opencv.core.Point p : points)
    {
        
    }

}*/
  
  
  


 
 
 public static boolean isleft()
 {
     return left;
 }

 public static boolean isBooton() {
      return booton;
 }

 public static boolean isUp() {
      return up;
 }

 public static boolean isRight() {
     return right;
 }

    public static void setLeft(boolean left) {
        Test_2.left = left;
    }

    public static void setBooton(boolean booton) {
        Test_2.booton = booton;
    }

    public static void setRight(boolean right) {
        Test_2.right = right;
    }

    public static void setUp(boolean up) {
        Test_2.up = up;
    }
 
 


 


 
 
 
 
 
 public static Mat pointsimg(Mat mat)
    {
            ArrayList<org.opencv.core.Point> color = new ArrayList<>();
        try {
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
            int width = mat.width();
            int height = mat.height();
            MatOfByte m = new MatOfByte();
            Imgcodecs.imencode(".jpg", mat, m);
            InputStream in = new ByteArrayInputStream(m.toArray());
            BufferedImage buffer = ImageIO.read(in);
            java.awt.Color co = new java.awt.Color(buffer.getRGB(0, 0));
            int max = co.getRed(),Xmax=0,Ymax=0;
            int wap=0;
            for(int i =0;i<height;i++)
            {
                for(int j=1;j<width;j++)
                {
                    co = new java.awt.Color(buffer.getRGB(j, i));
                    if(max==co.getRed())
                    {
                        color.add(new org.opencv.core.Point(j, i));
                    }
                    else if(co.getRed() > max)
                    {
                        max = co.getRed();
                        color = new ArrayList<>();
                        color.add(new org.opencv.core.Point(j, i));
                    }
                }
            }
           Mat ma = draw(color,mat,1);
           return ma;
        } catch (IOException ex) {
            System.out.println("error in function points");
        }
        return null;
    }
       
            

    private static Mat draw(ArrayList<org.opencv.core.Point> points,Mat mat,int chose)
    {
        org.opencv.core.Point p1 ;
        if(chose==1)
           Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);
        for(int i=0 ; i< points.size(); i++)
        {
            p1 = points.get(i);
            Imgproc.circle(mat,p1,5,new Scalar(0,255,0));
        }
        return mat;
    }

    private static Mat print (Mat mat)
    {
        Imgproc.putText(mat, "welcome left",new org.opencv.core.Point(50, 50), 2, 2,new Scalar(255, 0, 0) );
        return mat ;
    }


}
