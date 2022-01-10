package Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.DowntimeModel;

public class ExcelFile {
	private static Workbook workbook;
	
	public static void CreateRepairCard(DowntimeModel dt) {
		
		String fileName = String.valueOf(dt.getNumber());
		
		ReadExcelFile();
		FillRepairCard(dt);
		SaveRepairCard(fileName);
		
		try {
			//workbook.save("FinancialKPI.pdf", SaveFileFormat.Pdf);
			//Desktop.getDesktop().print(new File(Base.saveRepairCardsAddress + fileName + ".xlsx"));
			Desktop.getDesktop().open(new File(Base.saveRepairCardsAddress + fileName + ".xlsx"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void ReadExcelFile() {
		File file = new File(Base.repairCardForm);
		FileInputStream streamFile = null;
		try {
			streamFile = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(streamFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			streamFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void SaveRepairCard(String fileName) {

		// Create file system using specific name
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(Base.saveRepairCardsAddress + fileName + ".xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// write operation workbook using file out object
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void FillRepairCard(DowntimeModel dt) {

		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);

		XSSFCell cellToUpdate;

		// set number
		cellToUpdate = sheet.getRow(1).getCell(21, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		cellToUpdate.setCellValue(dt.getNumber());

		// set date
		cellToUpdate = sheet.getRow(3).getCell(3, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		cellToUpdate.setCellValue(BaseMethods.FormatDate(dt.getDateOfEntry()));

		// set time
		cellToUpdate = sheet.getRow(21).getCell(7, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		cellToUpdate.setCellValue(BaseMethods.FormatTime(dt.getTimeOfEntry()));

		// set description
		cellToUpdate = sheet.getRow(27).getCell(6, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		cellToUpdate.setCellValue(dt.getDescription());

	}
}
