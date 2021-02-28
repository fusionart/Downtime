package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.Today;

import Model.ActionModel;
import Model.DowntimeModel;

public class SaveToCsv {
	public static void SaveDowntime(DowntimeModel downtime) {

		String newLine;
		Writer outputfile = null;
		File dtFile = new File(Base.downtimeDbFile);

		if (Base.downtimeDb.size() == 0) {
			newLine = ConcatDowntimeLine(downtime);
		} else {
			newLine = System.lineSeparator() + ConcatDowntimeLine(downtime);
		}

		boolean fileIsNotLocked = dtFile.renameTo(dtFile);

		while (!fileIsNotLocked) {
			try {
				Thread.sleep(500);
				fileIsNotLocked = dtFile.renameTo(dtFile);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (fileIsNotLocked) {
			try {
				outputfile = new BufferedWriter(new FileWriter(Base.downtimeDbFile, true));

				outputfile.append(newLine);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Възникна проблем. Опитайте отново.", "Грешка",
						JOptionPane.INFORMATION_MESSAGE);
			} finally {
				try {
					if (outputfile != null) {
						outputfile.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void SaveAction(ActionModel action) {

		String newLine;
		Writer outputfile = null;
		File dtFile = new File(Base.actionDbFile);

		if (Base.actionDb.size() == 0) {
			newLine = ConcatActionLine(action);
		} else {
			newLine = System.lineSeparator() + ConcatActionLine(action);
		}

		boolean fileIsNotLocked = dtFile.renameTo(dtFile);

		while (!fileIsNotLocked) {
			try {
				Thread.sleep(500);
				fileIsNotLocked = dtFile.renameTo(dtFile);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (fileIsNotLocked) {
			try {
				outputfile = new BufferedWriter(new FileWriter(Base.actionDbFile, true));

				outputfile.append(newLine);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Възникна проблем. Опитайте отново.", "Грешка",
						JOptionPane.INFORMATION_MESSAGE);
			} finally {
				try {
					if (outputfile != null) {
						outputfile.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static String ConcatActionLine(ActionModel action) {
		StringBuilder line = new StringBuilder();

		line.append(action.getNumber());
		line.append(Base.DELIMITER);
		line.append(FormatDate(action.getStartDate()));
		line.append(Base.DELIMITER);
		line.append(FormatTime(action.getStartTime()));
		line.append(Base.DELIMITER);
		line.append(FormatDate(action.getEndDate()));
		line.append(Base.DELIMITER);
		line.append(FormatTime(action.getEndTime()));
		line.append(Base.DELIMITER);
		line.append(action.getPerformer());
		line.append(Base.DELIMITER);
		line.append(action.getDescription());
		line.append(Base.DELIMITER);
		line.append(action.getRecommendation());
		line.append(Base.DELIMITER);
		line.append(action.getAccepted());
		line.append(Base.DELIMITER);
		line.append(FormatDate(action.getDateOfAccept()));
		line.append(Base.DELIMITER);
		line.append(FormatTime(action.getTimeOfAccept()));
		line.append(Base.DELIMITER);
		line.append(action.getDonwtime());
		line.append(Base.DELIMITER);
		line.append(action.getApproved());
		line.append(Base.DELIMITER);
		line.append(action.getAuthor());
		line.append(Base.DELIMITER);
		line.append(FormatDate(action.getDateOfEntry()));
		line.append(Base.DELIMITER);
		line.append(FormatTime(action.getTimeOfEntry()));

		return line.toString();
	}

	private static String ConcatDowntimeLine(DowntimeModel downtime) {
		StringBuilder line = new StringBuilder();

		line.append(downtime.getNumber());
		line.append(Base.DELIMITER);
		line.append(FormatDate(downtime.getEntryDate()));
		line.append(Base.DELIMITER);
		line.append(FormatTime(downtime.getEntryTime()));
		line.append(Base.DELIMITER);
		line.append(downtime.getWorkshop());
		line.append(Base.DELIMITER);
		line.append(downtime.getFieldMachine());
		line.append(Base.DELIMITER);
		line.append(downtime.getNotified());
		line.append(Base.DELIMITER);
		line.append(downtime.getNotifier());
		line.append(Base.DELIMITER);
		line.append(downtime.getConfirm());
		line.append(Base.DELIMITER);
		line.append(downtime.isSignal());
		line.append(Base.DELIMITER);
		line.append(downtime.isMaterial());
		line.append(Base.DELIMITER);
		line.append(downtime.isRepair());
		line.append(Base.DELIMITER);
		line.append(downtime.isBreakdown());
		line.append(Base.DELIMITER);
		line.append(downtime.isCleaning());
		line.append(Base.DELIMITER);
		line.append(downtime.isNoElectricity());
		line.append(Base.DELIMITER);
		line.append(downtime.isOther());
		line.append(Base.DELIMITER);
		line.append(downtime.getOtherText());
		line.append(Base.DELIMITER);
		line.append(downtime.getDescription());
		line.append(Base.DELIMITER);
		line.append(downtime.getAuthor());
		line.append(Base.DELIMITER);
		line.append(FormatDate(downtime.getDateOfEntry()));
		line.append(Base.DELIMITER);
		line.append(FormatTime(downtime.getTimeOfEntry()));
		line.append(Base.DELIMITER);
		line.append(downtime.isShortReadjustment());
		line.append(Base.DELIMITER);
		line.append(downtime.isLongReadjustment());

		return line.toString();
	}

	private static String FormatDate(LocalDate date) {
		String formattedDate = null;
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		if (date != null) {
			formattedDate = date.format(dateFormat);
		} else {
			formattedDate = LocalDate.now().format(dateFormat);
		}
		
		return formattedDate;
	}

	private static String FormatTime(LocalTime time) {
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

		String formattedTime = time.format(timeFormat);

		return formattedTime;
	}

	private static String TimeDifference(LocalTime finishTime) {
		long duration = Duration.between(finishTime, finishTime).getSeconds();
		String minutes = String.valueOf(duration / 60);
		return minutes;
	}
}
