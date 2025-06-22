
public class CostumerRequest {
	private String name;
	private int id;
	private String request;
	
	public CostumerRequest(String name,int id,String request) 
	/*gets name, id and a request details and build a CostumerRequest object
	 * */
	{
		this.setId(id);
		this.setName(name);
		this.setRequest(request);	
	}
	public boolean equals(Object o)
	/*overriding equals
	 * returning if CostumerRequest are equal by their id and request details*/
	{
		if (o == this) {
            return true;
        }
		if (!(o instanceof CostumerRequest)) {
            return false;
        }
		CostumerRequest c = (CostumerRequest) o;
		return this.id==c.getId()&&this.request.equals(c.getRequest());
	}
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String toString() 
	{
		return "name: "+name+" | id:"+id+" | request: "+request;
	}

}
