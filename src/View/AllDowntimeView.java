package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Controller.Base;
import Controller.BaseMethods;
import Controller.MaintenanceColorRenderer;
import Model.ActionModel;
import Model.DowntimeModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AllDowntimeView extends JFrame {

	private JPanel contentPane;
	private JLabel lblBackground;
	private JTable tbl;
	private ButtonGroup rdbtnGroup;
	private ButtonGroup rdbtnSecondFilter;
	private JRadioButton rdbtnAll;
	private static DefaultTableModel defaultTableModel;
	private String header[] = new String[] { "Вид", "Номер", "Дата", "Час на оповестяване", "Описание", "Цех",
			"Участък/Машина", "Уведомен", "Уведомил", "Действие начало дата", "Действие начало час",
			"Действие край дата", "Действие край час", "Извършил", "Описание", "Приел", "Приел дата", "Приел час",
			"Непродуктивно време", "Утвърдил" };

	private String excelHeader[] = new String[] { "Вид", "Номер", "Дата", "Час на оповестяване", "Описание", "Цех",
			"Участък/Машина", "Уведомен", "Уведомил", "Въведено от", "Въведено на", "Въведено в",
			"Действие начало дата", "Действие начало час", "Действие край дата", "Действие край час", "Извършил",
			"Описание", "Приел", "Приел дата", "Приел час", "Непродуктивно време", "Утвърдил", "Въведено от",
			"Въведено на", "Въведено в" };

	private static FileTime initialTime;
	private static FileTime lastModifiedTime;

	/**
	 * Create the frame.
	 */
	public AllDowntimeView() {
		Image frameIcon = Toolkit.getDefaultToolkit().getImage(Base.icon);
		setIconImage(frameIcon);
		setTitle(Base.FRAME_CAPTION);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Base.WIDTH, Base.HEIGHT);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel pnlMain = new JPanel();
		pnlMain.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(100, 149, 237), new Color(160, 160, 160)),
				"Непродуктивно време", TitledBorder.LEADING, TitledBorder.TOP, Base.DEFAULT_FONT, null));
		pnlMain.setBounds(Base.ELEMENT_OFFSET, Base.ELEMENT_OFFSET,
				(Base.WIDTH - Base.ELEMENT_OFFSET * 3 + Base.ELEMENT_OFFSET / 2),
				(Base.HEIGHT - Base.ELEMENT_OFFSET * 3));
		pnlMain.setBackground(new Color(255, 255, 255, 150));
		contentPane.add(pnlMain);
		pnlMain.setLayout(null);

		JPanel pnlRadioButtons = new JPanel();
		pnlRadioButtons.setBounds(25, 40, 1257, 23);
		pnlMain.add(pnlRadioButtons);
		pnlRadioButtons.setLayout(null);

		rdbtnGroup = new ButtonGroup();

		rdbtnAll = new JRadioButton("Всички");
		rdbtnAll.setActionCommand("All");
		rdbtnAll.setSelected(true);
		rdbtnAll.setBounds(10, 0, 75, 23);
		rdbtnAll.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnAll);
		rdbtnGroup.add(rdbtnAll);
		rdbtnAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnBreakdown = new JRadioButton("Авария");
		rdbtnBreakdown.setActionCommand("Breakdown");
		rdbtnBreakdown.setBounds(95, 0, 80, 23);
		rdbtnBreakdown.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnBreakdown);
		rdbtnGroup.add(rdbtnBreakdown);
		rdbtnBreakdown.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnSignal = new JRadioButton("Сигнал");
		rdbtnSignal.setActionCommand("Signal");
		rdbtnSignal.setBounds(185, 0, 75, 23);
		rdbtnSignal.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnSignal);
		rdbtnGroup.add(rdbtnSignal);
		rdbtnSignal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnMaterial = new JRadioButton("Материал");
		rdbtnMaterial.setActionCommand("Material");
		rdbtnMaterial.setBounds(270, 0, 100, 23);
		rdbtnMaterial.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnMaterial);
		rdbtnGroup.add(rdbtnMaterial);
		rdbtnMaterial.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnCleaning = new JRadioButton("Почистване");
		rdbtnCleaning.setActionCommand("Cleaning");
		rdbtnCleaning.setBounds(380, 0, 115, 23);
		rdbtnCleaning.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnCleaning);
		rdbtnGroup.add(rdbtnCleaning);
		rdbtnCleaning.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnRepair = new JRadioButton("Поправка");
		rdbtnRepair.setActionCommand("Repair");
		rdbtnRepair.setBounds(500, 0, 100, 23);
		rdbtnRepair.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnRepair);
		rdbtnGroup.add(rdbtnRepair);
		rdbtnRepair.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnNoElectricity = new JRadioButton("Липса на ток");
		rdbtnNoElectricity.setActionCommand("NoElectricity");
		rdbtnNoElectricity.setBounds(600, 0, 120, 23);
		rdbtnNoElectricity.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnNoElectricity);
		rdbtnGroup.add(rdbtnNoElectricity);
		rdbtnNoElectricity.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});
		
		JRadioButton rdbtnShortReadjustment = new JRadioButton("Кратка пренастройка");
		rdbtnShortReadjustment.setActionCommand("ShortReadjustment");
		rdbtnShortReadjustment.setBounds(730, 0, 190, 23);
		rdbtnShortReadjustment.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnShortReadjustment);
		rdbtnGroup.add(rdbtnShortReadjustment);
		rdbtnShortReadjustment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});
		
		JRadioButton rdbtnLongReadjustment = new JRadioButton("Дълга пренастройка");
		rdbtnLongReadjustment.setActionCommand("LongReadjustment");
		rdbtnLongReadjustment.setBounds(930, 0, 180, 23);
		rdbtnLongReadjustment.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnLongReadjustment);
		rdbtnGroup.add(rdbtnLongReadjustment);
		rdbtnLongReadjustment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnOther = new JRadioButton("Други");
		rdbtnOther.setActionCommand("Other");
		rdbtnOther.setBounds(1120, 0, 80, 23);
		rdbtnOther.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnOther);
		rdbtnGroup.add(rdbtnOther);
		rdbtnOther.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JPanel pnlSecondFilter = new JPanel();
		pnlSecondFilter.setBounds(25, 70, 293, 23);
		pnlMain.add(pnlSecondFilter);
		pnlSecondFilter.setLayout(null);

		rdbtnSecondFilter = new ButtonGroup();

		JRadioButton rdbtnAllOf = new JRadioButton("Всички");
		rdbtnAllOf.setSelected(true);
		rdbtnAllOf.setBounds(10, 0, 75, 23);
		pnlSecondFilter.add(rdbtnAllOf);
		rdbtnAllOf.setFont(Base.RADIO_BUTTON_FONT);
		rdbtnAllOf.setActionCommand("AllOf");
		rdbtnSecondFilter.add(rdbtnAllOf);
		rdbtnAllOf.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnOnlySignaled = new JRadioButton("Въведен");
		rdbtnOnlySignaled.setBounds(95, 0, 90, 23);
		pnlSecondFilter.add(rdbtnOnlySignaled);
		rdbtnOnlySignaled.setFont(Base.RADIO_BUTTON_FONT);
		rdbtnOnlySignaled.setActionCommand("Entered");
		rdbtnSecondFilter.add(rdbtnOnlySignaled);
		rdbtnOnlySignaled.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		JRadioButton rdbtnOnlyAction = new JRadioButton("Действие");
		rdbtnOnlyAction.setBounds(185, 0, 100, 23);
		pnlSecondFilter.add(rdbtnOnlyAction);
		rdbtnOnlyAction.setFont(Base.RADIO_BUTTON_FONT);
		rdbtnOnlyAction.setActionCommand("Action");
		rdbtnSecondFilter.add(rdbtnOnlyAction);
		rdbtnOnlyAction.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable();
				}
			}
		});

		defaultTableModel = new DefaultTableModel(0, 0);

		defaultTableModel.setColumnIdentifiers(header);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 100, pnlMain.getWidth() - 50, pnlMain.getHeight() - 150);
		pnlMain.add(scrollPane);
		tbl = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tbl.setBounds(121, 261, 1200, 265);
		scrollPane.setViewportView(tbl);
		tbl.setModel(defaultTableModel);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.getTableHeader().setResizingAllowed(true);
		MaintenanceColorRenderer colorRenderer = new MaintenanceColorRenderer();

		JButton btnRefresh = new JButton("Презареди");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReloadDbs();
				FillTable();
				BaseMethods.ResizeColumnWidth(tbl);
			}
		});
		btnRefresh.setBounds(1132, 66, 150, 30);
		btnRefresh.setFont(Base.DEFAULT_FONT);
		pnlMain.add(btnRefresh);

		JButton btnExportToExcel = new JButton("Експорт");
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportToExcel();
			}
		});
		btnExportToExcel.setBounds(976, 66, 150, 30);
		btnExportToExcel.setFont(Base.DEFAULT_FONT);
		pnlMain.add(btnExportToExcel);

		JButton btnBack = new JButton("Назад");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainView();
			}
		});
		btnBack.setBounds(1132, 676, 150, 30);
		btnBack.setFont(Base.DEFAULT_FONT);
		pnlMain.add(btnBack);

		FillTable();
		BaseMethods.ResizeColumnWidth(tbl);

		ReloadTimer();

		SetBackgroundPicture();
		setVisible(true);
	}

	private void FillTable() {

		Boolean all = false;
		Boolean breakdown = false;
		Boolean signal = false;
		Boolean material = false;
		Boolean cleaning = false;
		Boolean repair = false;
		Boolean noElectricity = false;
		Boolean shortReadjustment = false;
		Boolean longReadjustment = false;
		Boolean other = false;

		Boolean allOf = false;
		Boolean onlyEntered = false;
		Boolean onlyAction = false;

		Boolean isAction = false;

		String actionName;
		DowntimeModel dtm;
		ActionModel actm;

		switch (rdbtnGroup.getSelection().getActionCommand()) {
		case "All":
			all = true;
			break;
		case "Breakdown":
			breakdown = true;
			break;
		case "Signal":
			signal = true;
			break;
		case "Material":
			material = true;
			break;
		case "Cleaning":
			cleaning = true;
			break;
		case "Repair":
			repair = true;
			break;
		case "NoElectricity":
			noElectricity = true;
			break;
		case "ShortReadjustment":
			shortReadjustment = true;
			break;
		case "LongReadjustment":
			longReadjustment = true;
			break;
		case "Other":
			other = true;
			break;

		default:
			break;
		}

		switch (rdbtnSecondFilter.getSelection().getActionCommand()) {
		case "AllOf":
			allOf = true;
			break;
		case "Entered":
			onlyEntered = true;
			break;
		case "Action":
			onlyAction = true;
			break;

		default:
			break;
		}

		// clear the table
		defaultTableModel.setRowCount(0);

		// sort downtimeDb by key
		// Map<Integer, DowntimeModel> treeMap = new TreeMap<>(Base.downtimeDb);
		
		//reverse the order of the Base.downtimeDb
		List<Integer> reverseOrderedKeys = new ArrayList<Integer>(Base.downtimeDb.keySet());
		Collections.reverse(reverseOrderedKeys);

		for (Integer key : reverseOrderedKeys) {

			dtm = BaseMethods.LoadDowntimeModel(Integer.toString(key));

			isAction = Base.actionDb.containsKey(key);

			if (isAction) {
				actm = BaseMethods.LoadActionModel(Integer.toString(key));
			} else {
				actm = new ActionModel();
			}

			if (dtm.isBreakdown() && (all || breakdown)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}

			if (dtm.isSignal() && (all || signal)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}

			if (dtm.isMaterial() && (all || material)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}

			if (dtm.isCleaning() && (all || cleaning)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}

			if (dtm.isRepair() && (all || repair)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}

			if (dtm.isNoElectricity() && (all || noElectricity)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}
			
			if (dtm.isShortReadjustment() && (all || shortReadjustment)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}
			
			if (dtm.isLongReadjustment() && (all || longReadjustment)) {
				actionName = BaseMethods.ActionName(dtm);

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(actionName, dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(actionName, dtm, actm);
				}
			}

			if (dtm.isOther() && (all || other)) {

				if (isAction && (allOf || onlyAction)) {
					AddRowsToTable(dtm.getOtherText(), dtm, actm);
				}

				if (!isAction && (allOf || onlyEntered)) {
					AddRowsToTable(dtm.getOtherText(), dtm, actm);
				}
			}
		}
	}

	private void AddRowsToTable(String actionName, DowntimeModel dtm, ActionModel actm) {
		defaultTableModel.addRow(new Object[] { actionName, dtm.getNumber(), FormatDate(dtm.getEntryDate()), dtm.getEntryTime(),
				dtm.getDescription(), dtm.getWorkshop(), dtm.getFieldMachine(), dtm.getNotified(), dtm.getNotifier(),
				FormatDate(actm.getStartDate()), actm.getStartTime(), FormatDate(actm.getEndDate()), actm.getEndTime(), actm.getPerformer(),
				actm.getDescription(), actm.getAccepted(), FormatDate(actm.getDateOfAccept()), actm.getTimeOfAccept(),
				actm.getDonwtime(), actm.getApproved() });
	}
	
	private static String FormatDate(LocalDate date) {
		
		if (date != null) {
			String formattedDate = date.format(Base.dateFormat);

			return formattedDate;
		} else {
			return null;
		}
	}

	private void ExportToExcel() {

		ReloadDbs();

		DowntimeModel dtm;
		ActionModel actm = null;
		String actionName = null;
		boolean isAction;

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Downtime");

		int rowCount = 0;
		int columnCount = 0;
		Row row = sheet.createRow(0);

		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellDateStyle = workbook.createCellStyle();
		cellDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

		for (String field : excelHeader) {
			Cell cell = row.createCell(columnCount++);
			cell.setCellValue((String) field);
		}

		Map<Integer, DowntimeModel> treeMap = new TreeMap<>(Base.downtimeDb);

		for (Integer key : treeMap.keySet()) {

			dtm = BaseMethods.LoadDowntimeModel(Integer.toString(key));

			isAction = Base.actionDb.containsKey(key);

			if (isAction) {
				actm = BaseMethods.LoadActionModel(Integer.toString(key));
			}

			actionName = BaseMethods.ActionName(dtm);

			row = sheet.createRow(++rowCount);

			Cell cell = row.createCell(0);
			cell.setCellValue(actionName);

			cell = row.createCell(1);
			cell.setCellValue(dtm.getNumber());

			cell = row.createCell(2);
			cell.setCellValue(dtm.getEntryDate());
			cell.setCellStyle(cellDateStyle);

			cell = row.createCell(3);
			cell.setCellValue(dtm.getEntryTime().toString());

			cell = row.createCell(4);
			cell.setCellValue(dtm.getDescription());

			cell = row.createCell(5);
			cell.setCellValue(dtm.getWorkshop());

			cell = row.createCell(6);
			cell.setCellValue(dtm.getFieldMachine());

			cell = row.createCell(7);
			cell.setCellValue(dtm.getNotified());

			cell = row.createCell(8);
			cell.setCellValue(dtm.getNotifier());

			cell = row.createCell(9);
			cell.setCellValue(dtm.getAuthor());

			cell = row.createCell(10);
			cell.setCellValue(dtm.getDateOfEntry());
			cell.setCellStyle(cellDateStyle);

			cell = row.createCell(11);
			cell.setCellValue(dtm.getTimeOfEntry().toString());

			if (isAction) {

				cell = row.createCell(12);
				cell.setCellValue(actm.getStartDate());
				cell.setCellStyle(cellDateStyle);

				cell = row.createCell(13);
				cell.setCellValue(actm.getStartTime().toString());

				cell = row.createCell(14);
				cell.setCellValue(actm.getEndDate());
				cell.setCellStyle(cellDateStyle);

				cell = row.createCell(15);
				cell.setCellValue(actm.getEndTime().toString());

				cell = row.createCell(16);
				cell.setCellValue(actm.getPerformer());

				cell = row.createCell(17);
				cell.setCellValue(actm.getDescription());

				cell = row.createCell(18);
				cell.setCellValue(actm.getAccepted());

				cell = row.createCell(19);
				cell.setCellValue(actm.getDateOfAccept());
				cell.setCellStyle(cellDateStyle);

				cell = row.createCell(20);
				cell.setCellValue(actm.getTimeOfAccept().toString());

				cell = row.createCell(21);
				cell.setCellValue(actm.getDonwtime());

				cell = row.createCell(22);
				cell.setCellValue(actm.getApproved());

				cell = row.createCell(23);
				cell.setCellValue(actm.getAuthor());

				cell = row.createCell(24);
				cell.setCellValue(actm.getDateOfEntry());
				cell.setCellStyle(cellDateStyle);

				cell = row.createCell(25);
				cell.setCellValue(actm.getTimeOfEntry().toString());
			}
		}

		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Workbook", "xlsx");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(getComponent(0)) == JFileChooser.APPROVE_OPTION) {
			try {
				// save to file
				OutputStream outputStream;
				if (new File(fileChooser.getSelectedFile().toString()).isFile()) {
					outputStream = new FileOutputStream(fileChooser.getSelectedFile());
				} else {
					outputStream = new FileOutputStream(fileChooser.getSelectedFile() + ".xlsx");
				}

				workbook.write(outputStream);

				outputStream.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void ReloadDbs() {
		Base.LoadDowntimeDb();
		Base.LoadActionDb();
	}

	private void ReloadTimer() {
		Path path = Paths.get(Base.downtimeDbFile);

		try {
			initialTime = Files.getLastModifiedTime(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;

		Timer timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					lastModifiedTime = Files.getLastModifiedTime(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (initialTime.compareTo(lastModifiedTime) < 0) {
					initialTime = lastModifiedTime;
					// reload the dbs

					ReloadDbs();
					FillTable();
					BaseMethods.ResizeColumnWidth(tbl);
				}
			}
		});

		timer.setDelay(Base.refreshTime);
		timer.start();
	}

	private void SetBackgroundPicture() {
		ImageIcon imageIcon = new ImageIcon(Base.backgroundPic);
		contentPane.setLayout(null);
		lblBackground = new JLabel(imageIcon);
		lblBackground.setBounds(5, 5, 1374, 777);
		getContentPane().add(lblBackground);
	}
}