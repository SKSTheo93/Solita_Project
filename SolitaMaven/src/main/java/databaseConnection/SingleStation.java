package databaseConnection;

public class SingleStation extends Object {
	
	private String name;
	private String Adress;
	private int sumDepartures;
	private int sumReturns;
	
	public SingleStation() {
		this("\0", "\0", 0, 0);
	}
	
	public SingleStation(String name, String adress, int sumDepartures, int sumReturns) {
		this.name = name;
		Adress = adress;
		this.sumDepartures = sumDepartures;
		this.sumReturns = sumReturns;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public int getSumDepartures() {
		return sumDepartures;
	}

	public void setSumDepartures(int sumDepartures) {
		this.sumDepartures = sumDepartures;
	}

	public int getSumReturns() {
		return sumReturns;
	}

	public void setSumReturns(int sumReturns) {
		this.sumReturns = sumReturns;
	}
}