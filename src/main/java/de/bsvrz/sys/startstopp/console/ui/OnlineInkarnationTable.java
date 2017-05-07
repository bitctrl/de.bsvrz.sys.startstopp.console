package de.bsvrz.sys.startstopp.console.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.googlecode.lanterna.gui2.table.Table;

import de.bsvrz.sys.startstopp.console.StartStoppConsole;
import de.bsvrz.sys.startstopp.console.data.Inkarnation;
import de.bsvrz.sys.startstopp.console.data.OnlineInkarnation;

public class OnlineInkarnationTable extends Table<Object> {

	private final class Simulator extends Thread {
		private Simulator() {
			super("StatusUpdater");
			setDaemon(true);
		}

		public void run() {

			Random random = new Random();

			while (true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int row = 0; row < getTableModel().getRowCount(); row++) {
					int offset = Math.abs(random.nextInt() % 4);
					switch (offset) {
					case 0:
						getTableModel().setCell(1, row, OnlineInkarnation.StartStoppStatus.AKTIV);
						break;
					case 1:
						getTableModel().setCell(1, row, OnlineInkarnation.StartStoppStatus.GESTOPPT);
						break;
					case 2:
						getTableModel().setCell(1, row, OnlineInkarnation.StartStoppStatus.INAKTIV);
						break;
					case 3:
						getTableModel().setCell(1, row, OnlineInkarnation.StartStoppStatus.STARTET);
						break;
					}
				}
			}
		}
	}

	private List<OnlineInkarnation> inkarnations = new ArrayList<>();

	public OnlineInkarnationTable() {
		super("Name", "Status", "Startzeit");

		for (Inkarnation inkarnation : StartStoppConsole.konfiguration.getInkarnationen()) {
			OnlineInkarnation onlineInkarnation = new OnlineInkarnation(inkarnation);
			getTableModel().addRow(onlineInkarnation.getApplikationsName(), onlineInkarnation.getStatus(),
					onlineInkarnation.getErsteStartZeit());
			inkarnations.add(onlineInkarnation);
		}

		Thread simulator = new Simulator();
		simulator.start();
	}
	
	public OnlineInkarnation getSelectedOnlineInkarnation() {
		int row = getSelectedRow();
		if(( row < 0 ) || (row >= inkarnations.size())) {
			return null;
		}
		
		return inkarnations.get(row);
	}

}