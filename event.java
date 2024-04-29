package Buffer;

public class event implements Comparable<event>{
	int date;
	String name;
	String location;
	public event(int date,String name,String location) {
		this.name=name;
		this.date = date;
		this.location=location;
	}

	@Override
	public int compareTo(event o) {
		// TODO Auto-generated method stub
		if(date<o.date)
			return -1;
		if(date>o.date)
			return 1;
		return 0;
	}

	public String toString()
	{
		String result="Event:" +name+" Date-"+date+" Location-"+location;
		return result;
	}
}

