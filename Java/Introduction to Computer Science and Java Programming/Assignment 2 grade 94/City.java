// City is a class which represents a city with its name ,the center for the city,the central station of the city,number of the residents who lived in the city and the number of neighborhoods that the city has 
public class City
{   private String _cityName;
    private Point _cityCenter;
    private Point _centralStation;
    private long _numOfResidents;
    private int _noOfNeighborhoods;

    
    public City(String cityName, double xCityCenter ,double yCityCenter, double xCentralStation,double yCentralStation,long numOfResidents,int noOfNeighborhoods)
    /*constructor which Initializing the city
     *gets a name of the city,coordinates of the center of the city,coordinates of the central station of the city,number of residents which  has to be or positive or zero,number of neighborhoods which has to be  positive 
        */
    {   
        _cityName=cityName;
        
        _cityCenter=new Point(xCityCenter,yCityCenter);
        
        _centralStation=new Point(xCentralStation,yCentralStation);
        
           
        if(numOfResidents<0)
            _numOfResidents=0;
        else 
            _numOfResidents=numOfResidents;

        if(noOfNeighborhoods<=0)
            _noOfNeighborhoods=1;
        else    
            _noOfNeighborhoods=noOfNeighborhoods;
    }


    

    public City(City other)//copy constructor of city,get other city and  copy this city attributes to the other and  initializing it 
    {   if(other!=null)
        {
            _cityName=other._cityName;
            _cityCenter=new Point(other._cityCenter);
            _centralStation= new Point(other._centralStation);
            _numOfResidents=other._numOfResidents;
            _noOfNeighborhoods=other._noOfNeighborhoods;
        }
    }

    public String  getCityName()//return the name of the city
    {
        return _cityName;
    }

    public Point  getCityCenter()//return the point of the center of the city
    {
        return new Point(_cityCenter);
    }

    public Point  getCentralStation()//return the point of the central station in the city
    {
        return new Point(_centralStation);
    }

    public long getNumOfResidents()//return the number of the residents in the city
    {
        return  _numOfResidents;
    }

    public int getNoOfNeighborhoods()//return the number of the neighborhoods in the city
    {
        return _noOfNeighborhoods;
    }

    public void  setCityName(String cn)//gets a string or a name and changing this city name
    { 
        _cityName=cn;
    }

    public void  setCityCenter( Point cc)//gets other point which represents the new location of the city center and set it 
    { if(cc!=null)
        _cityCenter=new Point(cc);
    }

    public void  setCentralStation(Point cs)//gets other point which represents the new location of the central station in the city and set it 
    { if(cs!=null)
        _centralStation= new Point(cs);
    }

    public void setNumOfResidents(long nor )//gets a number which represented the number of residents in the city and set it 
    { 
        if(nor>=0)
            _numOfResidents= nor;
    }

    public void setNoOfNeighborhoods(int non)//gets a number which represented the number of neighborhoods in the city and set it 
    {
        if(non>=1)
            _noOfNeighborhoods=non;
    }

    public boolean addResidents(long residentsUpdate)
    /*Gets a number of residents who are move in or move out
     *  Calculate the number of residents after the changing 
     *  And return true of the number of the residents is positive or return false if the number of residents is zero 
     */
    {
        if(residentsUpdate<0 && _numOfResidents+residentsUpdate<0)
        {
            _numOfResidents=0;
            return false;
        }
        else
        {_numOfResidents+=residentsUpdate;
            return true;
        }

    }

    public void moveCentralStation(double deltaX,double deltaY)
    /*gets two coordinates which the first represents the X and the other represents the Y
     * Moving this central station point a new one   
     * checking if the new point is in the first quadrant 
     * */
    {
        if(_centralStation.getX()+deltaX>=0 )
            _centralStation.setX(_centralStation.getX()+deltaX);
        if(_centralStation.getY()+deltaY>=0 )
            _centralStation.setY(_centralStation.getY()+deltaY);
    }

    public double  distanceBetweenCenterAndStation()//return the distance between center of the city and the central station 
    {
        return _cityCenter.distance(_centralStation);
    }

    public City newCity( String newCityName,double dX, double dY)/*gets a name and two numbers
    *calculate the new center coordinates and the new central station coordinates
    *return a new city which has a new name , a new center and a new central station which has a different location,with no residents and one neighborhood 
    **/ 
    {
        if(_cityCenter.getX()+dX>=0 )
            _cityCenter.setX(_cityCenter.getX()+dX);
        if(_cityCenter.getY()+dY>=0 )
            _cityCenter.setY(_cityCenter.getY()+dY);
        moveCentralStation(dX,dY);
        return new City(newCityName,_cityCenter.getX(),_cityCenter.getY(),_centralStation.getX(),_centralStation.getY(),0,1);

    }
    public String toString()//return a string of the class City  
    {
        return "City Name: "+ _cityName +"\n"+"City Center: "+ _cityCenter+"\n"+"Central Station: "+ _centralStation+
        "\n"+"Number of Residents: " +_numOfResidents+"\n"+"Number of Neighborhoods: "+_noOfNeighborhoods;

    }

}
