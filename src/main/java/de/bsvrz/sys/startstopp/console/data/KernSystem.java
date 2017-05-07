package de.bsvrz.sys.startstopp.console.data;

public class KernSystem {
	private  String inkarnationsName;
	private String warteZeit;
	private boolean mitInkarnationsName;
	
	public KernSystem(String inkarnationsName) {
		this.inkarnationsName = inkarnationsName;
	}
	
	public String getWarteZeit() {
		return warteZeit;
	}
	public void setWarteZeit(String warteZeit) {
		this.warteZeit = warteZeit;
	}
	public boolean isMitInkarnationsName() {
		return mitInkarnationsName;
	}
	public void setMitInkarnationsName(boolean mitInkarnationsName) {
		this.mitInkarnationsName = mitInkarnationsName;
	}
	public String getInkarnationsName() {
		return inkarnationsName;
	}
}
