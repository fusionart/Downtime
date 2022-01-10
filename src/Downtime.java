
import java.io.IOException;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import Controller.Base;
import View.MainView;

public class Downtime {

	public static void main(String[] args) throws IOException, BackingStoreException {
		// TODO Auto-generated method stub
		Base.LoadBasics();
		
		
		if (Base.isDowntimeDb && Base.isActionDb) {
			new MainView();
		} else {
			JOptionPane.showMessageDialog(null, "Базата данни не е намерена. Моля свържете се с администратор.", "Грешка", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}