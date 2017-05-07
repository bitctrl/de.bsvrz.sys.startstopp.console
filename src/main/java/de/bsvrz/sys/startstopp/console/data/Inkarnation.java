package de.bsvrz.sys.startstopp.console.data;

import java.util.ArrayList;
import java.util.List;

public class Inkarnation {
	private String inkarnationsName;

	private String applikation;
	private List<String> aufrufParameter = new ArrayList<>();
	private final StartArt startArt = new StartArt();
	private List<StartBedingung> startBedingungen = new ArrayList<>();
	private List<StoppBedingung> stoppBedingungen = new ArrayList<>();
	private String standardAusgabe;
	private String standardFehlerAusgabe;
	private StartFehlerVerhalten startFehlerVerhalten = new StartFehlerVerhalten();
	private StoppFehlerVerhalten stoppFehlerVerhalten = new StoppFehlerVerhalten();

	public Inkarnation(String inkarnationsName) {
		this.inkarnationsName = inkarnationsName;
	}
	
	public String getInkarnationsName() {
		return inkarnationsName;
	}

	public String getApplikation() {
		return applikation;
	}

	public void setApplikation(String applikation) {
		this.applikation = applikation;
	}

	public void addAufrufParameter(String value) {
		aufrufParameter.add(value);
	}

	public String getStandardAusgabe() {
		return standardAusgabe;
	}

	public void setStandardAusgabe(String standardAusgabe) {
		this.standardAusgabe = standardAusgabe;
	}

	public String getStandardFehlerAusgabe() {
		return standardFehlerAusgabe;
	}

	public void setStandardFehlerAusgabe(String standardFehlerAusgabe) {
		this.standardFehlerAusgabe = standardFehlerAusgabe;
	}

	public StartArt getStartArt() {
		return startArt;
	}

	public void addStoppBedingung( StoppBedingung bedingung) {
		stoppBedingungen.add(bedingung);
	}
	
	public List<StoppBedingung> getStoppBedingungen() {
		return stoppBedingungen;
	}

	public StartFehlerVerhalten getStartFehlerVerhalten() {
		return startFehlerVerhalten;
	}

	public StoppFehlerVerhalten getStoppFehlerVerhalten() {
		return stoppFehlerVerhalten;
	}

	public void addStartBedingung( StartBedingung bedingung) {
		startBedingungen.add(bedingung);
	}
	
	public List<StartBedingung> getStartBedingungen() {
		return startBedingungen;
	}

}
