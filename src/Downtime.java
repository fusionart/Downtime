
import java.io.IOException;

import javax.swing.JOptionPane;

import Controller.Base;
import View.MainView;

public class Downtime {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Base.LoadBasics();
		
		
		if (Base.isDowntimeDb && Base.isActionDb) {
			new MainView();
		} else {
			JOptionPane.showMessageDialog(null, "���� ����� �� � ��������. �������� �� ��� �������������.", "������", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
