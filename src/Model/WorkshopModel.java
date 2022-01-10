package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkshopModel {
	private List<String> workshopData = new ArrayList<String>();
	private HashMap<Integer, List<String>> workshopMachines = new HashMap<Integer, List<String>>();
	private int key = 0;

	public WorkshopModel() {

	}

	public List<String> getWorkshopData() {
		return workshopData;
	}

	public void setWorkshopData(String[] data) {
		workshopData.add(data[0]);
		setWorkshopMachines(data);
	}

	public List<String> getWorkshopMachines(int key) {
		return workshopMachines.get(key);
	}

	private void setWorkshopMachines(String[] data) {
		List<String> machineList = MachineList(data);

		if (workshopMachines.size() == 0) {
			workshopMachines.put(0, machineList);
			key++;
		} else {
			workshopMachines.put(key, machineList);
			key++;
		}
	}

	private List<String> MachineList(String[] data) {
		List<String> machineList = new ArrayList<String>();

		for (int i = 1; i < data.length; i++) {
			machineList.add(data[i]);
		}

		return machineList;
	}
}
