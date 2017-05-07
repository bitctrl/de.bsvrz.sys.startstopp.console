package de.bsvrz.sys.startstopp.console.data;

public class StoppBedingung {
	private String nachfolger;
	private String rechner;
	private int warteZeit;
	
	
	public StoppBedingung(String nachfolger) {
		super();
		this.nachfolger = nachfolger;
	}


	public String getRechner() {
		return rechner;
	}


	public void setRechner(String rechner) {
		this.rechner = rechner;
	}


	public int getWarteZeit() {
		return warteZeit;
	}


	public void setWarteZeit(int warteZeit) {
		this.warteZeit = warteZeit;
	}


	public String getNachfolger() {
		return nachfolger;
	}
	
	
}
