/**
 * A class used to represent and manage information on a country using
 a linked list, as requested in MMN15.
 * @author Guy Even
 */
public class Country
{
	private CityNode _head;
	private String _name;
	/**
	 * Constructor for objects of class Country, which constructs an empty new country with the specified name.
	 * Runtime Complexity: O(1).
	 * Storage Complexity: O(1).
	 *
	 * @param countryName The desired name for the new country.
	 */
	public Country(String name)
	{
		if (name != null)
		{
			this._head = null;
			this._name = name;
		}
	}

	/**
	 * Adds a new city with the given details to the country.<br />
	 * If there's already a copy of the given city in the country, the duplicate won't be added.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @param name The name of the new city.
	 * @param xCenter The X value of the new city's center.
	 * @param yCenter The Y value of the new city's center.
	 * @param xStation The X value of the new city's station.
	 * @param yStation The Y value of the new city's station.
	 * @param numOfResidents The number of residents in the new city.
	 * @param noOfNeighborhoods The number of neighborhoods in the new city.
	 * @return False if the city wasn't added in order to avoid duplicates, otherwise returns true (boolean).
	 */
	public boolean addCity(String name, int xCenter, int yCenter, int xStation, int yStation, long numOfResidents, int noOfNeighborhoods)
	{
		City newCity = new City(name, xCenter, yCenter, xStation, yStation, numOfResidents, noOfNeighborhoods);

		if (this._head == null) // If the list is empty, simply add the new City (in a new node).


		{
			this._head = new CityNode(newCity);
			return true;
		}

		// Otherwise, search for a duplicate in the whole list.
		CityNode temp = this._head;
		CityNode prev;
		do {
			City currentCity = temp.getCity();
			// Unfortunately, we didn't override the method equals() of class Object in City, so each instance variable has to be checked manually.
			if (currentCity.getCityName().equals(name)|| (currentCity.getCityCenter().equals(new Point(xCenter, yCenter))&& currentCity.getCentralStation().equals(new Point(xStation, yStation))&& currentCity.getNumOfResidents() == numOfResidents&& currentCity.getNoOfNeighborhoods() == noOfNeighborhoods))
			{
				return false;
			}
			prev = temp;
			temp = temp.getNext();
		}
		while (temp != null);

		/*
		 * If the method hasn't returned false up to this point, it means that there's no duplicate and we can add the new city to the list.
		 * Moreover, the variable "prev" holds the last node in the list and we can make use of it.
		 */
		prev.setNext(new CityNode(newCity));
		return true;
	}

	/**
	 * Calculates the total number of residents in the country, from all the cities.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @return The total amount of residents in the country (long).
	 */
	public long getNumOfResidents()
	{
		long totalResidents = 0;

		if (this._head == null)
		{
			return totalResidents;
		}
		CityNode temp = this._head;
		// Add the amount of residents in each city.
		do
		{
			totalResidents += temp.getCity().getNumOfResidents();
			temp = temp.getNext();
		}
		while (temp != null);

		return totalResidents;
	}

	/**
	 * Calculates the distance between the two most distant cities in the country.
	 * Runtime Complexity: O(n^2). 
	 * Storage Complexity: O(1).
	 *
	 * @return The distance between the two most distant cities (double).
	 */
	public double longestDistance() 
	{
		double longestDistance = 0.0;

		if (this.getNumOfCities() < 2)
		{
			return longestDistance;
		}

		CityNode a = this._head;
		CityNode b = a.getNext(); 
		do
		{
			do
			{
				double currentDistance = a.getCity().getCityCenter().distance(b.getCity().getCityCenter());
				if (currentDistance > longestDistance)
				{
					longestDistance = currentDistance;
				}
				b = b.getNext();
			}
			while (b != null);

			a = a.getNext();
			b = this._head;
		}
		while (a != null);

		return longestDistance;
	}

	/**
	 * Checks which cities are located northern to a given city.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @param cityName The name of the city on which the check will be performed.
	 * @return A string containing detailed information about each and every city which is located northern to the given city (String).
1
	 */
	public String citiesNorthOf(String cityName)
	{
		CityNode city = null;
		String citiesDetails = "";

		if (this._head == null) // If there are no cities in the country.
		{
			citiesDetails = "There is no city with the name " + cityName;
			return citiesDetails;
		}

		CityNode temp = this._head; // Search for a city with the requested name and save a pointer to it if found.
		do
		{
			if (temp.getCity().getCityName().equals(cityName))
			{
				city = temp;
			}
			temp = temp.getNext();
		}
		while (temp != null && city == null);

		if (city == null) // If a city with the requested name wasn't found in the country.
		{
			citiesDetails = "There is no city with the name " + cityName;
			return citiesDetails;
		}

		temp = this._head;
		// Add the details of each city located above the requested city.
		do
		{
			if (temp.getCity().getCityCenter().isAbove(city.getCity().getCityCenter()))
			{
				citiesDetails += "\n\n" + temp.getCity();
			}
			temp = temp.getNext();
		}
		while (temp != null);

		if (citiesDetails.equals("")) // If the given city is the northernmost in the country.
		{
			citiesDetails = "There are no cities north of " + cityName;
			return citiesDetails;
		}

		citiesDetails = "The cities north of " + cityName + " are:" +citiesDetails;
		return citiesDetails;

	}
	/**
	 * Finds the southernmost city in the country.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @return The southernmost city in the country (City).
	 */
	public City southernmostCity()
	{
		CityNode southCity = this._head;

		if (southCity == null) // If there are no cities in the country.
		{
			return null;
		}
		if (southCity.getNext() == null) // If there's only one city in the country.
		{
			return new City(southCity.getCity());
		}
		CityNode temp = southCity.getNext();
		// Find the southernmost city in the country.
		do
		{
			if (temp.getCity().getCityCenter().isUnder(southCity.getCity().getCityCenter()))
			{
				southCity = temp;
			}
			temp = temp.getNext();
		}
		while (temp != null);

		return new City(southCity.getCity());
	}

	/**
	 * Returns the country's name.
	 * Runtime Complexity: O(1).
	 * Storage Complexity: O(1).
	 *
	 * @return The country's name (String).
	 */
	public String getCountryName()
	{
		return this._name;
	}

	/**
	 * Returns the number of cities in the country.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @return The number of cities in the country (int).
	 */
	public int getNumOfCities()
	{
		int counter = 0;

		if (this._head == null)
		{
			return counter;
		}

		CityNode temp = this._head;

		do
		{
			counter++;
			temp = temp.getNext();
		}
		while (temp != null);

		return counter;
	}

	/**
	 * Creates a linked list of Cities with copies of the country's current cities.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(n).
	 *
	 * @return the created list of copied cities (Country).
	 */
	public Country getCities()
	{
		if (this._head == null)
		{
			return null;
		}

		CityNode temp = this._head;
		CityNode newHead = new CityNode(new City(temp.getCity()));
		CityNode newTemp = newHead;
		CityNode newNext;

		while (temp.getNext() != null)
		{
			temp = temp.getNext();
			newNext = new CityNode(new City(temp.getCity()));
			newTemp.setNext(newNext);
			newTemp = newTemp.getNext();
		}

		Country copy = new Country(this._name);
		copy._head = newHead;

		return copy;
	}

	/**
	 * Merges two cities into one according to the given names.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @param cityName1 The first city's name.
	 * @param cityName2 The second city's name.
	 * @return The unified city object (City).
	 */
	public City unifyCities(String cityName1, String cityName2)
	{
		CityNode firstCity = null, secondCity = null;

		if (this.getNumOfCities() < 2)
		{
			return null;
		}

		CityNode temp = this._head;

		do

		{
			if (temp.getCity().getCityName().equals(cityName1))
			{
				firstCity = temp;
			}
			else if (temp.getCity().getCityName().equals(cityName2))
			{
				secondCity = temp;
			}
			temp = temp.getNext();
		}
		while (temp != null && (firstCity == null || secondCity == null));

		String unifiedName = firstCity.getCity().getCityName() + "-" +secondCity.getCity().getCityName();
		double unifiedCenterX = (firstCity.getCity().getCityCenter().getX() + secondCity.getCity().getCityCenter().getX()) / 2.0;
		double unifiedCenterY = (firstCity.getCity().getCityCenter().getY() + secondCity.getCity().getCityCenter().getY()) / 2.0;
		double unifiedStationX, unifiedStationY;
		if (firstCity.getCity().getCentralStation().isLeft(secondCity.getCity().getCentralStation()))
		{
			unifiedStationX = firstCity.getCity().getCentralStation().getX();
			unifiedStationY = firstCity.getCity().getCentralStation().getY();
		}
		else
		{
			unifiedStationX = secondCity.getCity().getCentralStation().getX();
			unifiedStationY = secondCity.getCity().getCentralStation().getY();
		}
		long unifiedResidents = firstCity.getCity().getNumOfResidents() + secondCity.getCity().getNumOfResidents();
		int unifiedNeighborhoods = firstCity.getCity().getNoOfNeighborhoods() + secondCity.getCity().getNoOfNeighborhoods();

		if (firstCity.getCity().getNumOfResidents() >= secondCity.getCity().getNumOfResidents())
		{
			firstCity.setCity(new City(unifiedName, unifiedCenterX, unifiedCenterY, unifiedStationX, unifiedStationY, unifiedResidents, unifiedNeighborhoods));
			this.removeCity(secondCity);
			return new City(firstCity.getCity());
		}
		else
		{
			secondCity.setCity(new City(unifiedName, unifiedCenterX, unifiedCenterY, unifiedStationX, unifiedStationY, unifiedResidents, unifiedNeighborhoods));
			this.removeCity(firstCity);
			return new City(secondCity.getCity());
		}
	}

	/**
	 * Creates a string representation of the country, containing details on each and every city.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @return The created string representation of the country (String).
	 */
	public String toString()
	{
		String result = "";

		if (this._head == null)
		{
			result = "There are no cities in this country.";
			return result;
		}

		CityNode temp = this._head;
		result = "Cities of " + this._name + ":";
		do
		{
			result += "\n\n" + temp.getCity();
			temp = temp.getNext();
		}
		while (temp != null);

		return result;
	}

	/**
	 * Removes a given city from the country.
	 * Runtime Complexity: O(n).
	 * Storage Complexity: O(1).
	 *
	 * @param city The CityNode object containing the city which should be removed.
	 * @return True if the city was successfully removed, otherwise returns false.
	 */
	private boolean removeCity(CityNode city)
	{
		if (this._head == null) // If there are no cities in the country.
		{

			return false;
		}

		if (this._head.getNext() == null && this._head.equals(city)) // If there's only one city in the country which is the one we want toremove.
		{
			this._head = null;
			return true;
		}

		CityNode temp = this._head;
		CityNode prev;
		if (temp.equals(city))
		{
			this._head = this._head.getNext();
			return true;
		}

		while (temp.getNext() != null)
		{
			prev = temp;
			temp = temp.getNext();
			if (temp.equals(city))
			{
				prev.setNext(temp.getNext());
				return true;
			}
		}
		return false;
	}
}