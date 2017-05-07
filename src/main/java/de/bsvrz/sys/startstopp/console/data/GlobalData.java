package de.bsvrz.sys.startstopp.console.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalData {
	
	private final Map<String, String> makrodefinitionen = new LinkedHashMap<>();
	private final Map<String, KernSystem> kernsysteme = new LinkedHashMap<>(); 
	private ZugangDav zugangDav = new ZugangDav();
	private String usv;
	private final Map<String, String> rechner = new LinkedHashMap<>();
	private final Map<String, ProtokollDatei> protokollDateien = new LinkedHashMap<>();
	
	public void addKernSystem(KernSystem kernsystem) {
		kernsysteme.put(kernsystem.getInkarnationsName(), kernsystem);
	}

	public void addMakroDefinition(String name, String wert) {
		makrodefinitionen.put(name, wert);
	}

	public void addRechner(String name, String adresse) {
		rechner.put(name, adresse);
	}
	
	public void addProtokollDatei(ProtokollDatei protokollDatei) {
		protokollDateien.put(protokollDatei.getName(), protokollDatei);
	}

	public ZugangDav getZugangDav() {
		return zugangDav;
	}

	public String getUsv() {
		return usv;
	}

	public void setUsv(String usv) {
		this.usv = usv;
	}

}
