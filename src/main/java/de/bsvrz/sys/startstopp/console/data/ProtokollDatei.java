package de.bsvrz.sys.startstopp.console.data;

public class ProtokollDatei {
	private String name;
	private String dateiName;
	private int maxGroesse;
	
	public ProtokollDatei(String name) {
		this.name = name;
	}

	public String getDateiName() {
		return dateiName;
	}

	public void setDateiName(String dateiName) {
		this.dateiName = dateiName;
	}

	public int getMaxGroesse() {
		return maxGroesse;
	}

	public void setMaxGroesse(int maxGroesse) {
		this.maxGroesse = maxGroesse;
	}

	public String getName() {
		return name;
	}
}
