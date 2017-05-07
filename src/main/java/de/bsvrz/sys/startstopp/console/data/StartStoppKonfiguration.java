package de.bsvrz.sys.startstopp.console.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.bsvrz.sys.startstopp.console.data.StartArt.StartArtOption;
import de.bsvrz.sys.startstopp.console.data.StartFehlerVerhalten.StartFehlerVerhaltenOption;
import de.bsvrz.sys.startstopp.console.data.StoppFehlerVerhalten.StoppFehlerVerhaltenOption;

public class StartStoppKonfiguration {

	private static class StartStoppParserHandler extends DefaultHandler {

		private enum Tags {
			tagIsUndefined, konfiguration, startStopp, global, makrodefinition, kernsystem, zugangdav, rechner, protokolldatei, applikationen, inkarnation, applikation, aufrufparameter, startart, standardAusgabe, standardFehlerAusgabe, startFehlerverhalten, stoppFehlerverhalten, usv, startbedingung, stoppbedingung;

			static Tags getTag(String tagStr) {
				for (Tags tag : values()) {
					if (tag.name().equalsIgnoreCase(tagStr)) {
						return tag;
					}
				}
				return Tags.tagIsUndefined;
			}
		};

		private StartStoppKonfiguration destination;
		private Inkarnation currentInkarnation;

		public StartStoppParserHandler(StartStoppKonfiguration destination) {
			this.destination = destination;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {

			switch (Tags.getTag(qName)) {
			case applikation:
				currentInkarnation.setApplikation(attributes.getValue("name"));
				break;
			case applikationen:
				break;
			case aufrufparameter:
				currentInkarnation.addAufrufParameter(attributes.getValue("wert"));
				break;
			case global:
				break;
			case inkarnation:
				currentInkarnation = new Inkarnation(attributes.getValue("name"));
				break;
			case kernsystem:
				KernSystem kernSystem = new KernSystem(attributes.getValue("inkarnationsname"));
				if (attributes.getValue("wartezeit") != null) {
					kernSystem.setWarteZeit(attributes.getValue("wartezeit"));
				}
				if (attributes.getValue("mitInkarnationsname") != null) {
					kernSystem.setMitInkarnationsName(attributes.getValue("mitInkarnationsname").equals("ja"));
				}
				break;
			case konfiguration:
				break;
			case makrodefinition:
				destination.global.addMakroDefinition(attributes.getValue("name"), attributes.getValue("wert"));
				break;
			case protokolldatei:
				ProtokollDatei protokollDatei = new ProtokollDatei(attributes.getValue("name"));
				if (attributes.getValue("nameDatei") != null) {
					protokollDatei.setDateiName(attributes.getValue("nameDatei"));
				}
				if (attributes.getValue("groesse") != null) {
					protokollDatei.setMaxGroesse(Integer.parseInt(attributes.getValue("groesse")));
				}
				destination.global.addProtokollDatei(protokollDatei);
				break;
			case rechner:
				destination.global.addRechner(attributes.getValue("name"), attributes.getValue("tcpAdresse"));
				break;
			case standardAusgabe:
				currentInkarnation.setStandardAusgabe(attributes.getValue("dateiname"));
				break;
			case standardFehlerAusgabe:
				currentInkarnation.setStandardFehlerAusgabe(attributes.getValue("dateiname"));
				break;
			case startart:
				if (attributes.getValue("option") != null) {
					currentInkarnation.getStartArt()
							.setOption(StartArtOption.getStartArtOption(attributes.getValue("option")));
				}
				if (attributes.getValue("neustart") != null) {
					currentInkarnation.getStartArt().setNeuStart(attributes.getValue("neustart").equals("ja"));
				}
				if (attributes.getValue("intervall") != null) {
					currentInkarnation.getStartArt().setIntervall(attributes.getValue("intervall"));
				}
				break;
			case startFehlerverhalten:
				if (attributes.getValue("option") != null) {
					currentInkarnation.getStartFehlerVerhalten().setOption(
							StartFehlerVerhaltenOption.getStartFehlerVerhaltenOption(attributes.getValue("option")));
				}
				if (attributes.getValue("wiederholungen") != null) {
					currentInkarnation.getStartFehlerVerhalten()
							.setWiederholungen(Integer.parseInt(attributes.getValue("wiederholungen")));
				}
				break;
			case startStopp:
				destination.versionsNummer = attributes.getValue("Versionsnummer");
				destination.erstelltAm = attributes.getValue("ErstelltAm");
				destination.erstelltDurch = attributes.getValue("ErstelltDurch");
				destination.aenderungsGrund = attributes.getValue("Aenderungsgrund");
				break;
			case stoppFehlerverhalten:
				if (attributes.getValue("option") != null) {
					currentInkarnation.getStoppFehlerVerhalten().setOption(
							StoppFehlerVerhaltenOption.getStoppFehlerVerhaltenOption(attributes.getValue("option")));
				}
				if (attributes.getValue("wiederholungen") != null) {
					currentInkarnation.getStoppFehlerVerhalten()
							.setWiederholungen(Integer.parseInt(attributes.getValue("wiederholungen")));
				}
				break;
			case usv:
				destination.global.setUsv(attributes.getValue("pid"));
				break;
			case zugangdav:
				destination.global.getZugangDav().setAdresse(attributes.getValue("adresse"));
				destination.global.getZugangDav().setPort(attributes.getValue("port"));
				destination.global.getZugangDav().setUserName(attributes.getValue("username"));
				destination.global.getZugangDav().setPassWord(attributes.getValue("passwort"));
				break;
			case startbedingung:
				StartBedingung startBedingung = new StartBedingung(attributes.getValue("vorgaenger"));
				if (attributes.getValue("warteart") != null) {
					startBedingung.setWarteArt(StartBedingung.WarteArt.getWarteArt(attributes.getValue("warteart")));
				}
				if (attributes.getValue("rechner") != null) {
					startBedingung.setRechner(attributes.getValue("rechner"));
				}
				if (attributes.getValue("wartezeit") != null) {
					startBedingung.setWarteZeit(Integer.parseInt(attributes.getValue("wartezeit")));
				}
				currentInkarnation.addStartBedingung(startBedingung);
				break;
			case stoppbedingung:
				StoppBedingung stoppBedingung = new StoppBedingung(attributes.getValue("nachfolger"));
				if (attributes.getValue("rechner") != null) {
					stoppBedingung.setRechner(attributes.getValue("rechner"));
				}
				if (attributes.getValue("wartezeit") != null) {
					stoppBedingung.setWarteZeit(Integer.parseInt(attributes.getValue("wartezeit")));
				}
				currentInkarnation.addStoppBedingung(stoppBedingung);
				break;
			case tagIsUndefined:
				throw new IllegalArgumentException("Tag not supported: " + qName);

			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch (Tags.getTag(qName)) {
			case applikation:
				break;
			case applikationen:
				break;
			case aufrufparameter:
				break;
			case global:
				break;
			case inkarnation:
				if (currentInkarnation != null) {
					destination.addInkarnation(currentInkarnation);
					currentInkarnation = null;
				}
				break;
			case kernsystem:
				break;
			case konfiguration:
				break;
			case makrodefinition:
				break;
			case protokolldatei:
				break;
			case rechner:
				break;
			case standardAusgabe:
				break;
			case standardFehlerAusgabe:
				break;
			case startart:
				break;
			case startFehlerverhalten:
				break;
			case startStopp:
				break;
			case stoppFehlerverhalten:
				break;
			case tagIsUndefined:
				break;
			case usv:
				break;
			case zugangdav:
				break;
			case startbedingung:
				break;
			case stoppbedingung:
				break;
			}
		}

	}

	private String versionsNummer;
	private String erstelltAm;
	private String erstelltDurch;
	private String aenderungsGrund;

	private GlobalData global = new GlobalData();

	private Map<String, Inkarnation> inkarnationen = new LinkedHashMap<>();

	public StartStoppKonfiguration(InputStream stream) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(stream, new StartStoppParserHandler(this));
	}

	private void addInkarnation(Inkarnation inkarnation) {
		inkarnationen.put(inkarnation.getInkarnationsName(), inkarnation);
	}

	public Collection<Inkarnation> getInkarnationen() {
		return inkarnationen.values();
	}
}
