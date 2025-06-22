import java.lang.Math;
// Point is a class which represents a point in a two-dimensional way
 
public class Point
{  
    private double _radius;//radius of the point
    private double _alpha;//the angle in degrees of the point

    public Point(double x,double y)
    {
    //Constructor for objects of class Point. Construct a new point with the specified x y coordinates. If the x coordinate is negative it is set to zero. If the y coordinate is negative it is set to zero.
    //x - The x coordinate(can be or positive or zero)
    //y - The y coordinate(can be or positive or zero)
    
        if(x<=0)
            x=0;
        if(y<=0)
            y=0;
        _radius=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));//finding the radius of the point
        _alpha=Math.atan(y/x)*180/Math.PI;//finding the angle in degrees of the point 

    }

    public Point(Point other)// copy constructor of point ,gets other from class point and building copy of the point
    {
        if(other!=null)
        {
            _radius=other._radius;
            _alpha=other._alpha;
        }
    }

    public double distance (Point p) //gets other point, calculates the the distance between this point and the other point and return the distance 
    {
        if(p!=null)
            return Math.round((Math.sqrt(Math.pow((getY()-p.getY()), 2) + Math.pow((getX()-p.getX()), 2))*10000))/(double)10000;
        else return 0;
    }

    public boolean equals(Point other)//gets other point and checking if this point is the same as the other point
    {
        return (other!=null)&&(_radius==other._radius&& _alpha==other._alpha );

    }

    public double getX()//return the  X coordinate of the point
    {
        return Math.round((_radius*Math.cos(Math.PI*_alpha/180))*10000)/(double)10000;

    }

    public double getY()//return the  Y coordinate of the point
    {
        return Math.round((_radius*Math.sin(Math.PI*_alpha/180))*10000)/(double)10000;

    }

    public boolean isAbove (Point other)//gets other point and returns if this point is above the other point
    {

        return (other!=null)&&(getY()>other.getY())&&(getY()!=other.getY());

    }

    public boolean isLeft (Point other)//gets other point and returns if this point is left to the other point
    {
        return (other!=null)&&(getX()<other.getX())&&(getX()!=other.getX());

    }

    public boolean isRight (Point other) //gets other point and returns if this point is right to the other point using isLeft() method
    {
        return (other!=null)&&(isLeft(other));
    }

    public boolean isUnder (Point other) //gets other point and returns if this point is under the other point using isAbove() method
    {
        return (other!=null)&&(isAbove(other));

    }

    public void move(double dx,double dy)//gets two parameters and moving this point by her coordinates 
    {
        setX(getX()+dx);
        setY(getY()+dy);
    }

    public void setX(double num)//gets parameter and changing the X coordinate of this point,checking if the new coordinate is in the first quadrant 
    { 
        double yPast;
        yPast=getY();
        if (num>=0)
        {
            _radius=Math.sqrt(Math.pow(num, 2)+Math.pow(yPast, 2));
            _alpha=Math.atan(yPast/num)*180/Math.PI;

            num=_radius*Math.cos(Math.PI*_alpha/180);
        }

    }

    public void setY(double num)//gets parameter and changing the Y coordinate of this point,checking if the new coordinate is in the first quadrant 
    { 
        double xPast;
        xPast=getX();
        if(num>=0)
        {

            _radius=Math.sqrt(Math.pow(xPast, 2)+Math.pow(num, 2));
            _alpha=Math.atan(num/xPast)*180/Math.PI;

            num=_radius*Math.sin(Math.PI*_alpha/180);
            }

    }

 
    
   public String toString()//return a string of this point 
    {
        return "("+ Math.round((_radius*Math.cos(Math.PI*_alpha/180))*10000)/(double)10000
        + ","+ Math.round((_radius*Math.sin(Math.PI*_alpha/180))*10000)/(double)10000+")";

    }
   

}
