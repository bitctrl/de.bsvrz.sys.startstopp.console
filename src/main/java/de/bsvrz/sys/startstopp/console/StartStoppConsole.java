package de.bsvrz.sys.startstopp.console;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import de.bsvrz.sys.startstopp.console.data.StartStoppKonfiguration;
import de.bsvrz.sys.startstopp.console.ui.StartStoppOnlineWindow;

public class StartStoppConsole {

	public static StartStoppKonfiguration konfiguration;

	public static void main(String[] args) throws IOException, InterruptedException {

		String[] files = { "testkonfigurationen/startStopp01_1.xml", "testkonfigurationen/startStopp01_2.xml",
				"testkonfigurationen/startStopp02.xml", "testkonfigurationen/startStopp03.xml",
				"testkonfigurationen/startStopp04.xml", "testkonfigurationen/startStopp05_1.xml",
				"testkonfigurationen/startStopp05_2.xml", "testkonfigurationen/startStopp05_3.xml",
				"testkonfigurationen/startStopp06.xml", "testkonfigurationen/startStopp07_1.xml",
				"testkonfigurationen/startStopp07_2.xml", "testkonfigurationen/startStopp07_3.xml",
				"testkonfigurationen/startStopp07_4.xml", "testkonfigurationen/startStopp07_5.xml",
				"testkonfigurationen/startStopp07_6.xml", "testkonfigurationen/startStopp07_7.xml",
				"testkonfigurationen/startStopp08_1.xml", "testkonfigurationen/startStopp08_2.xml",
				"testkonfigurationen/startStopp09.xml", "testkonfigurationen/startStopp10.xml" };

		for (String file : files) {
			InputStream stream = StartStoppKonfiguration.class.getResourceAsStream(file);
			try {
				konfiguration = new StartStoppKonfiguration(stream);
			} catch (ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DefaultTerminalFactory factory = new DefaultTerminalFactory();
		factory.setTelnetPort(6500);
		factory.setForceTextTerminal(true);
		Terminal term = factory.createTerminal();
		System.err.println("Term: " + term.getClass());
		Screen screen = new TerminalScreen(term);
		WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);

		screen.startScreen();

		StartStoppOnlineWindow onlineWindow = new StartStoppOnlineWindow();
		gui.addWindow(onlineWindow);

		onlineWindow.waitUntilClosed();

		screen.stopScreen();
		System.exit(0);
	}

}
