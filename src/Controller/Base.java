package Controller;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.prefs.Preferences;


import Model.ActionModel;
import Model.DowntimeModel;

public class Base {
	public final static String DOT = ".";
	public final static String BACKSLASH = "\\";
	public final static String DELIMITER = ";";

	// Paths
	private final static String MAIN_PATH = "C:\\Downtime\\sys\\settings.ini";

	// database
	public static LinkedHashMap<Integer, DowntimeModel> downtimeDb;
	public static HashMap<Integer, ActionModel> actionDb;
	
	//combobox
	public static String workshopFile;
	public static List<String> workshopData;

	// Labels
	public final static String FRAME_CAPTION = "MONBAT";
	public final static String CREATE_DOWNTIME_LABEL = "Въвеждане на формуляр";
	public final static String ACTION_LABEL = "Въвеждане действие \nкъм формуляр";
	public final static String MAINTENANCE_LABEL = "Поддръжка: статистика";
	
	//buttons
	public final static String MAINTENANCE = "maintenance";
	public final static String STATISTICS = "statistics";

	// Size
	public final static int WIDTH = 1400;
	public final static int HEIGHT = 826;
	public final static int ELEMENT_HEIGHT = 30;
	public final static int ELEMENT_OFFSET = 37;
	public final static int PANEL_HEIGHT = 74;
	public final static int PANEL_WIDTH = 250;

	public final static Locale LOCALE = new Locale("bg");

	// default fonts
	public final static Font DEFAULT_FONT = new Font("Century Gothic", Font.BOLD, 16);
	public final static Font RADIO_BUTTON_FONT = new Font("Century Gothic", Font.BOLD, 14);
	
	//default colors
	public final static Color TEXT_FIELD_COLOR = new Color(51, 51, 204);

	public static Preferences settings;
	public static String backgroundPic;
	public static String logoWhite;
	public static String icon;

	// Files
	public static String downtimeDbFile;
	public static String actionDbFile;
	public static Boolean isDowntimeDb = true;
	public static Boolean isActionDb = true;

	// passwords
	public static String maintenancePassword;
	public static String adminPassword;
	public static String statisticsPassword;
	
	public static Integer refreshTime;

	public static void LoadBasics() {
		LoadPaths();
		AssignVariables();
		LoadDowntimeDb();
		LoadActionDb();
		AssignPasswords();
		LoadComboboxData();
	}

	private static void LoadComboboxData() {
		try {
			workshopData = ReadFromCsv.ParseGenericCsv(workshopFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void LoadPaths() {
		try {
			settings = ReadIni.ParseIni(MAIN_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void LoadDowntimeDb() {
		File file = new File(downtimeDbFile);
		if (file.canRead()) {
			try {
				downtimeDb = ReadFromCsv.ReadDowntimeDb(downtimeDbFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			isDowntimeDb = false;
		}
	}

	public static void LoadActionDb() {
		File file = new File(downtimeDbFile);
		if (file.canRead()) {
			try {
				actionDb = ReadFromCsv.ReadActionDb(actionDbFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			isActionDb = false;
		}
	}

	private static void AssignPasswords() {
		maintenancePassword = settings.node("maintenance").get("password", null);
		adminPassword = settings.node("admin").get("password", null);
		statisticsPassword = settings.node("statistics").get("password", null);
	}

	private static void AssignVariables() {
		StringBuilder sb = new StringBuilder();
		sb.append(settings.node("system").get("mainaddress", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("background").get("address", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("background").get("name", null));
		sb.append(DOT);
		sb.append(settings.node("background").get("extension", null));

		backgroundPic = sb.toString();

		sb = new StringBuilder();
		sb.append(settings.node("system").get("mainaddress", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("logowhite").get("address", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("logowhite").get("name", null));
		sb.append(DOT);
		sb.append(settings.node("logowhite").get("extension", null));

		logoWhite = sb.toString();

		sb = new StringBuilder();
		sb.append(settings.node("system").get("mainaddress", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("logoframe").get("address", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("logoframe").get("name", null));
		sb.append(DOT);
		sb.append(settings.node("logoframe").get("extension", null));

		icon = sb.toString();

		sb = new StringBuilder();
		sb.append(settings.node("downtimedbfile").get("address", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("downtimedbfile").get("name", null));
		sb.append(DOT);
		sb.append(settings.node("downtimedbfile").get("extension", null));

		downtimeDbFile = sb.toString();

		sb = new StringBuilder();
		sb.append(settings.node("actiondbfile").get("address", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("actiondbfile").get("name", null));
		sb.append(DOT);
		sb.append(settings.node("actiondbfile").get("extension", null));

		actionDbFile = sb.toString();
		
		refreshTime = Integer.parseInt(settings.node("refreshtime").get("time", null));
		
		sb = new StringBuilder();
		sb.append(settings.node("combobox").get("address", null));
		sb.append(BACKSLASH);
		sb.append(settings.node("combobox").get("workshop", null));
		sb.append(DOT);
		sb.append(settings.node("combobox").get("extension", null));
		
		workshopFile = sb.toString();
	}
}
