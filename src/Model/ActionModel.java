package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ActionModel {
	private int number;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalDate endDate;
	private LocalTime endTime;
	private String performer;
	private String description;
	private String recommendation;
	private String accepted;
	private LocalDate dateOfAccept;
	private LocalTime timeOfAccept;
	private String donwtime;
	private String approved;
	private String author;
	private LocalDate dateOfEntry;
	private LocalTime timeOfEntry;

	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public ActionModel(String[] data) {
		this.number = Integer.parseInt(data[0]);
		this.startDate = LocalDate.parse(data[1], dateFormat);
		this.startTime = LocalTime.parse(data[2]);
		this.endDate = LocalDate.parse(data[3], dateFormat);
		this.endTime = LocalTime.parse(data[4]);
		this.performer = data[5];
		this.description = data[6];
		this.recommendation = data[7];
		this.accepted = data[8];
		this.dateOfAccept = LocalDate.parse(data[9], dateFormat);
		this.timeOfAccept = LocalTime.parse(data[10]);
		this.donwtime = data[11];
		this.approved = data[12];
		this.author = data[13];
		this.dateOfEntry = LocalDate.parse(data[14], dateFormat);
		this.timeOfEntry = LocalTime.parse(data[15]);
	}

	public ActionModel() {

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public LocalDate getDateOfAccept() {
		return dateOfAccept;
	}

	public void setDateOfAccept(LocalDate dateOfAccept) {
		this.dateOfAccept = dateOfAccept;
	}

	public LocalTime getTimeOfAccept() {
		return timeOfAccept;
	}

	public void setTimeOfAccept(LocalTime timeOfAccept) {
		this.timeOfAccept = timeOfAccept;
	}

	public String getDonwtime() {
		return donwtime;
	}

	public void setDonwtime(String donwtime) {
		this.donwtime = donwtime;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getDateOfEntry() {
		return dateOfEntry;
	}

	public void setDateOfEntry(LocalDate dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}

	public LocalTime getTimeOfEntry() {
		return timeOfEntry;
	}

	public void setTimeOfEntry(LocalTime timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}
}
