package databaseConnection;

public class Station extends Object implements Comparable<Station> {

	private int id;
	private String namn;
	private String nimi;
	private String name;
	private String osoite;
	private String adress;
	
	public Station() {
		this(0, "\0", "\0", "\0", "\0", "\0");
	}
	
	public Station(int id, String namn, String nimi, String name, String osoite, String adress) {
		this.id = id;
		this.namn = namn;
		this.nimi = nimi;
		this.name = name;
		this.osoite = osoite;
		this.adress = adress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOsoite() {
		return osoite;
	}

	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
		
	}
	
	@Override
	public int compareTo(Station st) {
		return id - st.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(!(obj instanceof Station))
			return false;
		Station st = (Station)obj;
		if(id == st.id && namn.equals(st.namn) && nimi.equals(st.nimi)
				&& name.equals(st.name) && osoite.equals(st.osoite) && adress.equals(st.adress))
			return true;
		else
			return false;
			
	}
}