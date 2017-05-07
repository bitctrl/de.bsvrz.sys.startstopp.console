package de.bsvrz.sys.startstopp.console.data;

public class StartArt {

	public enum StartArtOption {
		AUTOMATISCH("automatisch"), MANUELL("manuell"), INTERVALL_REL("intervallrelativ"), INTERVALL_ABS(
				"intervallabsolut");

		private String externalName;

		private StartArtOption(String externalName) {
			this.externalName = externalName;
		}
		
		public static StartArtOption getStartArtOption(String externalName) {
			for( StartArtOption option : values()) {
				if( option.externalName.equals(externalName)) {
					return option;
				}
			}
			
			throw new IllegalArgumentException("StartArtOption: " + externalName + " wird nicht unterstützt");
		}
	}
	
	private StartArtOption option = StartArtOption.AUTOMATISCH;
	private boolean neuStart = true;
	private String intervall;
	public StartArtOption getOption() {
		return option;
	}
	public void setOption(StartArtOption option) {
		this.option = option;
	}
	public boolean isNeuStart() {
		return neuStart;
	}
	public void setNeuStart(boolean neuStart) {
		this.neuStart = neuStart;
	}
	public String getIntervall() {
		return intervall;
	}
	public void setIntervall(String intervall) {
		this.intervall = intervall;
	}
}
