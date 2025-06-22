/**
 *  A class used to represent and manage information on a country
 * @author Guy Even
 */
public class Country {
	private String _countryName;
	private City [] _cities;
	private int noOfCities;
	private final int MAX_NUM_CITIES = 1000;
/**
 *  Constructor for objects of class Country, which constructs an empty new country with the specified name.

 * @param countryName The desired name for the new country
 */
	public Country(String countryName)
	{
		this.noOfCities=0;
		this._countryName=countryName;
		this._cities=new City[MAX_NUM_CITIES];
	}
/**
 *  Adds a new city with the given details to the country.<br />
 * If the current number of cities has reached the maximum capacity, the new city won't be added.
 * @param name The name of the new city.
 * @param xCenter The X value of the new city's center.
 * @param yCenter The Y value of the new city's center.
 * @param xStation The X value of the new city's station.
 * @param yStation The Y value of the new city's station.
 * @param numOfResidents The number of residents in the new city.
 * @param noOfNeighborhoods The number of neighborhoods in the new city.
 * @return False if the city wasn't added due to maximum capacity,
 otherwise returns true (boolean).
 */
	public boolean addCity(String name,double xCenter, double yCenter,double xStation, double yStation,long numOfResidents, int noOfNeighborhoods)
	{
		if(noOfCities<MAX_NUM_CITIES)
		{
			_cities[noOfCities]=new City( name,xCenter,yCenter,xStation,yStation,numOfResidents,noOfNeighborhoods);
			noOfCities++;
			return  true;

		}
		else return false;
	}
/**
 *  Calculates the total number of residents in the country, from all the cities.
 * @return The total amount of residents in the country .
 */
	public long getNumOfResidents()
	{ 
		long sum=0;
		for(int i=0;i<noOfCities;i++)
		{
			sum+=_cities[i].getNumOfResidents();
		}
		return sum;

	}
/**
 * Calculates the distance between the two most distant cities in
the country.
 * @return The distance between the two most distant cities 
 */
	public double longestDistance()
	{
		double maxDistance=0;
		double distance=0;
		for(int i=0;i<noOfCities;i++)
		{  

			for(int j=0;j<noOfCities;j++)
			{  
				distance=_cities[i].getCityCenter().distance(_cities[j].getCityCenter());
				if(maxDistance<distance)
					maxDistance=distance;

			}
		}
		return maxDistance;
	}
/**
 * Checks which cities are located northern to a given city.(use a private method)
 * @param cityName  The name of the city on which the check will be performed
 * @return  A string containing detailed information about each and every city which is located northern to the given city 
 */
	public String citiesNorthOf(String cityName)
	{ String strList = null;

	if(cityIndexNumber(cityName)<0)
		strList= "There is no city with the name "+cityName;

	if(!thereIsNorther(_cities[cityIndexNumber(cityName)].getCityCenter()))
		strList= "There are no cities north of "+cityName;
	else{


		for(int i=0;i<noOfCities;i++)
		{ if(i==0)
			strList= "The cities north of "+cityName+" are:"+ "\n"+ "\n";
		if(_cities[i].getCityCenter().isAbove(_cities[cityIndexNumber(cityName)].getCityCenter()))
		{
			strList+=_cities[i].toString()+"\n"+ "\n";
		}
		}

	}
	return strList;
	}

	private boolean thereIsNorther(Point center)/*private method ,gets center of a city.
	checks if there is any city in the array that has center norther than this.
	return true if there is a city otherwise return false.
	*/
	{ boolean exist=true;
	for(int i=0;i<noOfCities;i++)
	{
		if(_cities[i].getCityCenter().isAbove(center))
		{
			exist= true;
			break;
		}	
		else exist= false;
	}
	if(exist==true)
		return true;
	else return false;

	}

/**
 *  Finds the southernmost city in the country.
 * @return The southernmost city in the country 
 */

	public City southernmostCity()
	{ City south=null;

	if(_cities[0]!=null)
	{
		for(int i=0;i<noOfCities;i++)
		{if(i==0)
			south=new City(_cities[0]);
		if(south.getCityCenter().isAbove(_cities[i].getCityCenter())) 

			south=new City(_cities[i]);


		}

	}
	return south;
	}


/**
 *Returns the country's name.
 * @return  the country's name.
 */
	public String getCountryName()
	{
		return _countryName;
	}
/**
 *  Returns the number of cities in the country.
 * @return  the number of cities in the country.
 */
	public int getNumOfCities()
	{
		return noOfCities;
	}
	/**
	 *  Creates an array of Cities with copies of the country's current cities.
	 * @return  the created array of copied cities 
	 */
	public City[] getCities()
	{ City [] actualCites;
	int noOfCities=this.getNumOfCities();
	actualCites=new City[noOfCities];
	for(int i=0;i<noOfCities;i++)
	{
		actualCites[i]=new City(_cities[i]);

	}
	return actualCites;

	}
	

	private int cityIndexNumber(String cityName)
	/*private method,gets name of a city.
	 * return the index of the city in the array( in the mmn13 written that the name of the city must be in the array,returnvalue = -1 is only the first set in and in the end it will change)
	 */
	{
		int returnvalue = -1;
		for (int i = 0; i < noOfCities; ++i) 
		{
			if ( cityName.equalsIgnoreCase(_cities[i].getCityName()))
			{
				returnvalue = i;
				break;
			}
		}
		return returnvalue;
	}
/**
 * Merges two cities into one according to the given names.(use private methods)
 * @param cityName1 The first city's name.
 * @param cityName2  The second city's name.
 * @return  The unified city object
 */
	public City unifyCities(String cityName1, String cityName2)
	{
		int city1, city2;
		city1=this.cityIndexNumber(cityName1);
		city2=this.cityIndexNumber(cityName2);
		City c=null;
		String name=_cities[city1].getCityName()+"-"+_cities[city2].getCityName();
		Point center=new Point((_cities[city1].getCityCenter().getX()+_cities[city2].getCityCenter().getX())/2.0,(_cities[city1].getCityCenter().getY()+_cities[city2].getCityCenter().getY())/2.0);
		Point station;
		long residents=_cities[city1].getNumOfResidents()+_cities[city2].getNumOfResidents();
		int neighborhoods=_cities[city1].getNoOfNeighborhoods()+_cities[city2].getNoOfNeighborhoods();

		if(_cities[city1].getNumOfResidents()>=_cities[city2].getNumOfResidents())
		{
			if(_cities[city1].getCentralStation().isRight(_cities[city2].getCentralStation()))
				station=new Point(_cities[city1].getCentralStation());//////
			else 
				station=new Point(_cities[city2].getCentralStation());/////

			c=new City(name,center.getX(),center.getY(),station.getX(),station.getY(),residents,neighborhoods);

			_cities[city1]=c;
			this.elimnateCity(cityName2);
			this.countEliminate();
		}

		if(_cities[city1].getNumOfResidents()<=_cities[city2].getNumOfResidents())
		{
			if(_cities[city1].getCentralStation().isRight(_cities[city2].getCentralStation()))
				station=new Point(_cities[city1].getCentralStation());
			else 
				station=new Point(_cities[city2].getCentralStation());

			c=new City(name,center.getX(),center.getY(),station.getX(),station.getY(),residents,neighborhoods);

			_cities[city2]=c;
			this.elimnateCity(cityName1);
			this.countEliminate();

		}
		return c;


	}

	private City [] elimnateCity(String name )
	/*private method,gets a name of city 
	 * eliminate the city from the array
	 * return the array without the eliminated city
	 */
	{ 
		for(int i=0; i<this.getNumOfCities();i++)
		{
			if(name.equals(_cities[i].getCityName()))
			{
				for(int j = i; j <this.getNumOfCities()-1 ; j++)
				{
					_cities[j] =new City(_cities[j+1]);
				}
				break;
			}
			_cities[i]=new City(_cities[i]);
		}
		return _cities;	

	}
	private static int countEliminate()
	/*private static method,
	 * count how many cities were eliminated form the array(the country)
	 * return how many times the private method "elimnateCity" was activated
	 */
	{ int counter=0;
	counter++;
	return counter;

	}
	/**
	 * Creates a string representation of the country, containing details on each and every city.(use a private method)
	 *  @return The created string representation of the country
	 */
	public String toString()
	{String str;
	if(_cities[0]!=null)
	{
		str="Cities of "+_countryName+": "+"\n"+"\n";
		for(int i=0; i<this.getNumOfCities()-this.countEliminate();i++)
		{
			str=str+_cities[i].toString()+"\n"+"\n";
		}
		return  str;
	}
	else {
		str="There are no cities in this country.";
		return str;
	}
	}

}





