package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DowntimeModel {	
	private int Number;
	private LocalDate EntryDate;
	private LocalTime EntryTime;
	private String Workshop;
	private String FieldMachine;
	private String Notified;
	private String Notifier;
	private String Confirm;
	private boolean Signal;
	private boolean Material;
	private boolean Repair;
	private boolean Breakdown;
	private boolean Cleaning;
	private boolean NoElectricity;
	private boolean Other;
	private String OtherText;
	private String Description;
	private String Author;
	private LocalDate DateOfEntry;
	private LocalTime TimeOfEntry;
	
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public DowntimeModel() {
		
	}
	
	public DowntimeModel(String[] data) {
		this.Number = Integer.parseInt(data[0]);
		this.EntryDate = LocalDate.parse(data[1], dateFormat);
		this.EntryTime = LocalTime.parse(data[2]);
		this.Workshop = data[3];
		this.FieldMachine = data[4];
		this.Notified = data[5];
		this.Notifier = data[6];
		this.Confirm = data[7];
		this.Signal = Boolean.parseBoolean(data[8]);
		this.Material = Boolean.parseBoolean(data[9]);
		this.Repair = Boolean.parseBoolean(data[10]);
		this.Breakdown = Boolean.parseBoolean(data[11]);
		this.Cleaning = Boolean.parseBoolean(data[12]);
		this.NoElectricity = Boolean.parseBoolean(data[13]);
		this.Other = Boolean.parseBoolean(data[14]);
		this.OtherText = data[15];
		this.Description = data[16];
		this.Author = data[17];
		this.DateOfEntry = LocalDate.parse(data[18], dateFormat);
		this.TimeOfEntry = LocalTime.parse(data[19]);
	}
	
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public LocalDate getEntryDate() {
		return EntryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		EntryDate = entryDate;
	}
	public LocalTime getEntryTime() {
		return EntryTime;
	}
	public void setEntryTime(LocalTime entryTime) {
		EntryTime = entryTime;
	}
	public String getWorkshop() {
		return Workshop;
	}
	public void setWorkshop(String workshop) {
		Workshop = workshop;
	}
	public String getFieldMachine() {
		return FieldMachine;
	}
	public void setFieldMachine(String fieldMachine) {
		FieldMachine = fieldMachine;
	}
	public String getNotified() {
		return Notified;
	}
	public void setNotified(String notified) {
		Notified = notified;
	}
	public String getNotifier() {
		return Notifier;
	}
	public void setNotifier(String notifier) {
		Notifier = notifier;
	}
	public String getConfirm() {
		return Confirm;
	}
	public void setConfirm(String confirm) {
		Confirm = confirm;
	}
	public boolean isSignal() {
		return Signal;
	}
	public void setSignal(boolean signal) {
		Signal = signal;
	}
	public boolean isMaterial() {
		return Material;
	}
	public void setMaterial(boolean material) {
		Material = material;
	}
	public boolean isRepair() {
		return Repair;
	}
	public void setRepair(boolean repair) {
		Repair = repair;
	}
	public boolean isBreakdown() {
		return Breakdown;
	}
	public void setBreakdown(boolean breakdown) {
		Breakdown = breakdown;
	}
	public boolean isCleaning() {
		return Cleaning;
	}
	public void setCleaning(boolean cleaning) {
		Cleaning = cleaning;
	}
	public boolean isNoElectricity() {
		return NoElectricity;
	}
	public void setNoElectricity(boolean noElectricity) {
		NoElectricity = noElectricity;
	}
	public boolean isOther() {
		return Other;
	}
	public void setOther(boolean other) {
		Other = other;
	}
	public String getOtherText() {
		return OtherText;
	}
	public void setOtherText(String otherText) {
		OtherText = otherText;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public LocalDate getDateOfEntry() {
		return DateOfEntry;
	}
	public void setDateOfEntry(LocalDate dateOfEntry) {
		DateOfEntry = dateOfEntry;
	}
	public LocalTime getTimeOfEntry() {
		return TimeOfEntry;
	}
	public void setTimeOfEntry(LocalTime timeOfEntry) {
		TimeOfEntry = timeOfEntry;
	}
	
	
}
