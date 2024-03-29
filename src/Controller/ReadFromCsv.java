package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import Model.ActionModel;
import Model.DowntimeModel;
import Model.WorkshopModel;

public class ReadFromCsv {

	public static LinkedHashMap<Integer, DowntimeModel> ReadDowntimeDb(String path) throws IOException {

		BufferedReader csvReader = null;
		String row;

		LinkedHashMap<Integer, DowntimeModel> downtimeData = new LinkedHashMap<Integer, DowntimeModel>();

		try {
			csvReader = new BufferedReader(new FileReader(path));

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(Base.DELIMITER);

				DowntimeModel dt = new DowntimeModel(data);
				downtimeData.put(dt.getNumber(), dt);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			csvReader.close();
		}

		return downtimeData;
	}

	public static HashMap<Integer, ActionModel> ReadActionDb(String path) throws IOException {

		BufferedReader csvReader = null;
		String row;

		HashMap<Integer, ActionModel> actionData = new HashMap<Integer, ActionModel>();

		try {
			csvReader = new BufferedReader(new FileReader(path));

			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(Base.DELIMITER);

				ActionModel am = new ActionModel(data);
				actionData.put(am.getNumber(), am);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			csvReader.close();
		}

		return actionData;
	}

	@SuppressWarnings("null")
	public static WorkshopModel ParseGenericCsv(String path) throws IOException {

		BufferedReader csvReader = null;
		String row;
		WorkshopModel wm = new WorkshopModel();

		try {
			csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(";");

			wm.setWorkshopData(data);
		}

		csvReader.close();

		return wm;
	}
}
