package Controller;

import javax.swing.JOptionPane;

import Model.ActionModel;
import Model.DowntimeModel;

public class BaseMethods {
	public static Boolean CheckIsNumber(String s) {
		if ((s == null)) {
	        return false;
	    }
		
	    try {
	        int d = Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	    	JOptionPane.showMessageDialog(null, "Моля, въведете число за номер", "Грешка", JOptionPane.INFORMATION_MESSAGE);
	        return false;
	    }
	    return true;
	}
	
	public static Boolean CheckIfDowntimeNumberExists(String s) {
		if (Base.downtimeDb.containsKey(Integer.parseInt(s))) {
			return true;
		}
		
		JOptionPane.showMessageDialog(null, "Номер " + s + " не е намерен", "Грешка", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
	
	public static Boolean CheckIfActionNumberExists(String s) {
		if (Base.actionDb.containsKey(Integer.parseInt(s))) {
			JOptionPane.showMessageDialog(null, "За номер " + s + " има въведено Действие", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		
		return false;
	}
	
	public static Boolean CheckIfNegative(String s) {
		if (Integer.parseInt(s) <=0) {
			JOptionPane.showMessageDialog(null, "Моля, въведете число по-голямо от 0 за номер", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		
		return false;
	}
	
	public static DowntimeModel LoadDowntimeModel(String s) {
		DowntimeModel dtm = Base.downtimeDb.get(Integer.parseInt(s));
		
		return dtm;
	}
	
	public static ActionModel LoadActionModel(String s) {
		ActionModel actm = Base.actionDb.get(Integer.parseInt(s));
		
		return actm;
	}
}
