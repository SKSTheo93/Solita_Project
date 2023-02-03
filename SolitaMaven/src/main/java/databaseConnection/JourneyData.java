package databaseConnection;

public class JourneyData extends Object implements Comparable<JourneyData>{
	
	private int id;
	private String departureStationName;
	private String returnStationName;
	private int coveredDistance;
	private int duration;
	
	public JourneyData() {
		this("\0", "\0", 0, 0);
	}
	
	public JourneyData(String departureStationName, String returnStationName, int coveredDistance, int duration) {
		this.departureStationName = departureStationName;
		this.returnStationName = returnStationName;
		this.coveredDistance = coveredDistance;
		this.duration = duration;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDepartureStationName() {
		return departureStationName;
	}
	public void setDepartureStationName(String departureStationName) {
		this.departureStationName = departureStationName;
	}
	public String getReturnStationName() {
		return returnStationName;
	}
	public void setReturnStationName(String returnStationName) {
		this.returnStationName = returnStationName;
	}
	public int getCoveredDistance() {
		return coveredDistance;
	}
	public void setCoveredDistance(int coveredDistance) {
		this.coveredDistance = coveredDistance;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public int compareTo(JourneyData jd) {
		return id - jd.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(!(obj instanceof JourneyData))
			return false;
		JourneyData jd = (JourneyData)obj;
		if(id == jd.id && departureStationName.equals(jd.departureStationName) && returnStationName.equals(jd.returnStationName)
				&& coveredDistance == jd.coveredDistance && duration == jd.duration)
			return true;
		else
			return false;
			
	}
}