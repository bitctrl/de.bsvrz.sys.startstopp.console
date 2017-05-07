package de.bsvrz.sys.startstopp.console.data;

import de.bsvrz.sys.startstopp.console.data.StartFehlerVerhalten.StartFehlerVerhaltenOption;

public class StoppFehlerVerhalten {
	
	public enum StoppFehlerVerhaltenOption {
		STOPP("stopp"),
		ABBRUCH("abbruch"),
		IGNORIEREN("ignorieren");
		
		private String externalName;

		private StoppFehlerVerhaltenOption(String externalName) {
			this.externalName = externalName;
		}
		
		public static StoppFehlerVerhaltenOption getStoppFehlerVerhaltenOption(String externalName) {
			for( StoppFehlerVerhaltenOption option : values()) {
				if( option.externalName.equals(externalName)) {
					return option;
				}
			}
			
			throw new IllegalArgumentException("Die StartFehlerVerhaltenOption " + externalName + " wird nicht unterstützt");
		}
	};
	
	private StoppFehlerVerhaltenOption option = StoppFehlerVerhaltenOption.IGNORIEREN;
	private int wiederholungen = 0;
	
	public StoppFehlerVerhaltenOption getOption() {
		return option;
	}
	public void setOption(StoppFehlerVerhaltenOption option) {
		this.option = option;
	}
	public int getWiederholungen() {
		return wiederholungen;
	}
	public void setWiederholungen(int wiederholungen) {
		this.wiederholungen = wiederholungen;
	}
}
