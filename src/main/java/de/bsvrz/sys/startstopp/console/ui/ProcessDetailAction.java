package de.bsvrz.sys.startstopp.console.ui;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.input.KeyStroke;

import de.bsvrz.sys.startstopp.console.data.OnlineInkarnation;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListenerAdapter;

public class ProcessDetailAction implements Runnable {

	private WindowBasedTextGUI gui;
	private OnlineInkarnation inkarnation;

	public ProcessDetailAction(WindowBasedTextGUI gui, OnlineInkarnation inkarnation) {
		this.gui = gui;
		this.inkarnation = inkarnation;
	}

	@Override
	public void run() {
		BasicWindow window = new BasicWindow("Details: " + inkarnation.getApplikationsName());
		window.setHints(Collections.singleton(Hint.EXPANDED));
		window.addWindowListener(new WindowListenerAdapter() {
			@Override
			public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
				window.close();
			}
		});
		gui.addWindow(window);
	}

	@Override
	public String toString() {
		return "Details anzeigen";
	}
}
