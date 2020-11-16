package Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.ActionModel;
import Model.DowntimeModel;

public class ReadFromCsv {

	public static HashMap<Integer, DowntimeModel> ReadDowntimeDb(String path) throws IOException {

		BufferedReader csvReader = null;
		String row;

		HashMap<Integer, DowntimeModel> downtimeData = new HashMap<Integer, DowntimeModel>();

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
	public static List<String> ParseGenericCsv(String path) throws IOException {

		BufferedReader csvReader = null;
		String row;
		int i = 0;

		List<String> stringArr = new ArrayList<String>();

		try {
			csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while ((row = csvReader.readLine()) != null) {
			String[] data = row.split(";");

			stringArr.add(data[0]);
		}

		csvReader.close();

		return stringArr;
	}

}
