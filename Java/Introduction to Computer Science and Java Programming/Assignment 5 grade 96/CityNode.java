/**
 * Used to represent a single City node, as part of a linked list.
 * @author Guy Even
 */
public class CityNode
{
	private City _city;
	private CityNode _next;

	/**
	 * Constructor for objects of class CityNode, which constructs a new CityNode of the given city and points to null.
	 * @param c The city object which the new node should hold.
	 */
	public CityNode(City c)
	{
		if (c != null)
		{
			this._city = new City(c);
			this._next = null;
		}
	}

	/**
	 * Constructor for objects of class CityNode, which constructs a new CityNode of the given city and pointer.
	 * @param c The city object which the new node should hold.
	 * @param n The next CityNode after the given City.
	 */
	public CityNode(City c, CityNode n)
	{
		if (c != null)
		{
			this._city = new City(c);
			this._next = n;
		}
	}

	/**
	 * Copy Constructor for objects of class CityNode, which constructs a new CityNode with the exact data as the given node.
	 * @param c The existing CityNode object which should be copied.
	 */
	public CityNode(CityNode c)
	{
		if (c != null)
		{
			this._city = new City(c._city);
			this._next = c._next;
		}
	}

	/**

	 * Returns the City object which the node holds.
	 * @return A copy of the City object which the node holds.
	 */
	public City getCity()
	{
		return new City(this._city);
	}

	/**
	 * Returns the pointer to the next CityNode object.
	 * @return A pointer to the following CityNode object.
	 */
	public CityNode getNext()
	{
		return this._next;
	}

	/**
	 * Changes the node's city to a different one, based on the given City object.
	 * @param c The new City object to replace the current one with.
	 */
	public void setCity(City c)
	{
		if (c != null)
		{
			this._city = new City(c);
		}
	}

	/**
	 * Alters the pointer to the next CityNode object.
	 * @param next The new pointer to replace the current one with.
	 */
	public void setNext(CityNode next)
	{
		this._next = next;
	}
}

