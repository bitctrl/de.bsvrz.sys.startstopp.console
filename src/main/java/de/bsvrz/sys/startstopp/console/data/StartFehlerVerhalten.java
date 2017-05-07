package de.bsvrz.sys.startstopp.console.data;

import de.bsvrz.sys.startstopp.console.data.StartFehlerVerhalten.StartFehlerVerhaltenOption;

public class StartFehlerVerhalten {
	
	public enum StartFehlerVerhaltenOption {
		OPTION("option"),
		BEENDEN("beenden"),
		ABBRUCH("abbruch"),
		IGNORIEREN("ignorieren");
		
		private String externalName;

		private StartFehlerVerhaltenOption(String externalName) {
			this.externalName = externalName;
		}

		public static StartFehlerVerhaltenOption getStartFehlerVerhaltenOption(String externalName) {
			for( StartFehlerVerhaltenOption option : values()) {
				if( option.externalName.equals(externalName)) {
					return option;
				}
			}
			
			throw new IllegalArgumentException("Die StartFehlerVerhaltenOption " + externalName + " wird nicht unterstützt");
		}
	};
	
	private StartFehlerVerhaltenOption option = StartFehlerVerhaltenOption.IGNORIEREN;
	private int wiederholungen = 0;
	
	public StartFehlerVerhaltenOption getOption() {
		return option;
	}
	public void setOption(StartFehlerVerhaltenOption option) {
		this.option = option;
	}
	public int getWiederholungen() {
		return wiederholungen;
	}
	public void setWiederholungen(int wiederholungen) {
		this.wiederholungen = wiederholungen;
	}
}
