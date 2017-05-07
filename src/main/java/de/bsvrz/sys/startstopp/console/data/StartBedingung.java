package de.bsvrz.sys.startstopp.console.data;

import de.bsvrz.sys.startstopp.console.data.StartFehlerVerhalten.StartFehlerVerhaltenOption;

public class StartBedingung {
	
	public static enum WarteArt {
		BEGINN("beginn"),
		ENDE("ende");
		
		private String externalName;

		private WarteArt(String externalName) {
			this.externalName = externalName;
			// TODO Auto-generated constructor stub
		}
		
		public static WarteArt getWarteArt(String externalName) {
			for( WarteArt warteArt : values()) {
				if( warteArt.externalName.equals(externalName)) {
					return warteArt;
				}
			}
			
			throw new IllegalArgumentException("Die Startbedingunswarteart " + externalName + " wird nicht unterstützt");
		}
	}
	private String vorgaenger;
	private WarteArt warteArt = WarteArt.BEGINN;
	private String rechner;
	private int warteZeit;

	public StartBedingung(String vorgaenger) {
		super();
		this.vorgaenger = vorgaenger;
	}

	public WarteArt getWarteArt() {
		return warteArt;
	}

	public void setWarteArt(WarteArt warteArt) {
		this.warteArt = warteArt;
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

	public String getVorgaenger() {
		return vorgaenger;
	}

}
