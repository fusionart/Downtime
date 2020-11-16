package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;

import Controller.Base;
import Controller.BaseMethods;
import Controller.SaveToCsv;
import Model.ActionModel;
import Model.DowntimeModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class DowntimeView {

	private JFrame frmMain;
	private static JTextField txtNumber;
	private static JTextField txtFieldMachine;
	private static JTextField txtNotified;
	private static JTextField txtNotifier;
	private static JTextField txtConfirm;
	private static JTextField txtOther;
	private static JTextField txtAuthor;
	private static JRadioButton rdbtnSignal;
	private static JRadioButton rdbtnBreakdown;
	private static JRadioButton rdbtnMaterial;
	private static JRadioButton rdbtnCleaning;
	private static JRadioButton rdbtnRepair;
	private static JRadioButton rdbtnNoElectricity;
	private static JRadioButton rdbtnOther;
	private static DatePicker cboEntryDate;
	private static TimePicker cboEntryTime;
	private static JTextArea txtDescription;
	private static JLabel lblBackground;
	private static ActionPanel actionPanel;
	private static JCheckBox chckbxEnterAction;
	private static Boolean isDowntimeLoaded;
	private static JComboBox cboWorkshop;
	private ButtonGroup radioButtonGroup;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DowntimeView window = new DowntimeView();
//					window.frmMain.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public DowntimeView(String s) {
		actionPanel = new ActionPanel();
		isDowntimeLoaded = false;
		initialize();

		if (!(s == null)) {
			isDowntimeLoaded = true;
			FillData(s);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmMain = new JFrame(Base.FRAME_CAPTION);
		frmMain.setBounds(100, 100, Base.WIDTH, Base.HEIGHT);
		frmMain.setResizable(false);
		Image frameIcon = Toolkit.getDefaultToolkit().getImage(Base.icon);
		frmMain.setIconImage(frameIcon);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);

		JPanel pnlDowntime = new JPanel();
		pnlDowntime.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(100, 149, 237), new Color(160, 160, 160)),
				"Непродуктивно време", TitledBorder.LEADING, TitledBorder.TOP, Base.DEFAULT_FONT, null));
		pnlDowntime.setBounds(100, 37, 555, 680);
		pnlDowntime.setBackground(new Color(255, 255, 255, 150));
		frmMain.getContentPane().add(pnlDowntime);
		pnlDowntime.setLayout(null);

		JPanel pnlNumber = new JPanel();
		pnlNumber.setBackground(new Color(255, 255, 255, 0));
		pnlNumber.setBounds(15, 37, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlNumber);
		GridBagLayout gbl_pnlNumber = new GridBagLayout();
		gbl_pnlNumber.columnWidths = new int[] { 250, 0 };
		gbl_pnlNumber.rowHeights = new int[] { 37, 37 };
		gbl_pnlNumber.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlNumber.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlNumber.setLayout(gbl_pnlNumber);

		JLabel lblNumber = new JLabel("Номер");
		lblNumber.setFont(Base.DEFAULT_FONT);
		lblNumber.setLabelFor(txtNumber);
		GridBagConstraints gbc_lblNumber = new GridBagConstraints();
		gbc_lblNumber.anchor = GridBagConstraints.WEST;
		gbc_lblNumber.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumber.gridx = 0;
		gbc_lblNumber.gridy = 0;
		pnlNumber.add(lblNumber, gbc_lblNumber);

		txtNumber = new JTextField();
		txtNumber.setForeground(Base.TEXT_FIELD_COLOR);
		txtNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!txtNumber.getText().equals("")) {
					Boolean isNumber = BaseMethods.CheckIsNumber(txtNumber.getText());
					if (!isNumber) {
						txtNumber.setText("");
						txtNumber.requestFocus();
					} else if (BaseMethods.CheckIfNegative(txtNumber.getText())) {
						txtNumber.setText("");
						txtNumber.requestFocus();
					}
				}
			}
		});
		txtNumber.setFont(Base.DEFAULT_FONT);
		// txtNumber.setForeground(Colors.);
		GridBagConstraints gbc_txtNumber = new GridBagConstraints();
		gbc_txtNumber.fill = GridBagConstraints.BOTH;
		gbc_txtNumber.gridx = 0;
		gbc_txtNumber.gridy = 1;
		pnlNumber.add(txtNumber, gbc_txtNumber);
		txtNumber.setColumns(10);

		JPanel pnlDate = new JPanel();
		pnlDate.setBounds(15, 121, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlDate);
		pnlDate.setBackground(new Color(255, 255, 255, 0));
		GridBagLayout gbl_pnlDate = new GridBagLayout();
		gbl_pnlDate.columnWidths = new int[] { 250, 0 };
		gbl_pnlDate.rowHeights = new int[] { 37, 37 };
		gbl_pnlDate.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlDate.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlDate.setLayout(gbl_pnlDate);

		JLabel lblDate = new JLabel("Дата");
		lblDate.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 0;
		pnlDate.add(lblDate, gbc_lblDate);
		DatePickerSettings dateSettings = new DatePickerSettings(Base.LOCALE);
		dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		dateSettings.setColor(DateArea.DatePickerTextValidDate, Base.TEXT_FIELD_COLOR);
		dateSettings.setFontValidDate(Base.RADIO_BUTTON_FONT);
		cboEntryDate = new DatePicker(dateSettings);
		GridBagConstraints gbc_cboEntryDate = new GridBagConstraints();
		gbc_cboEntryDate.fill = GridBagConstraints.BOTH;
		gbc_cboEntryDate.gridx = 0;
		gbc_cboEntryDate.gridy = 1;
		pnlDate.add(cboEntryDate, gbc_cboEntryDate);
		cboEntryDate.setDateToToday();

		JPanel pnlTime = new JPanel();
		pnlTime.setBounds(15, 205, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlTime.setBackground(new Color(255, 255, 255, 0));
		pnlDowntime.add(pnlTime);
		TimePickerSettings timeSettings = new TimePickerSettings(Base.LOCALE);
		timeSettings.setColor(TimeArea.TimePickerTextValidTime, Base.TEXT_FIELD_COLOR);
		// timeSettings.fontValidTime(Base.DEFAULT_FONT);
		// timeSettings.initialTime = LocalTime.now();
		GridBagLayout gbl_pnlTime = new GridBagLayout();
		gbl_pnlTime.columnWidths = new int[] { 250, 0 };
		gbl_pnlTime.rowHeights = new int[] { 37, 37 };
		gbl_pnlTime.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlTime.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlTime.setLayout(gbl_pnlTime);

		JLabel lblTime = new JLabel("Час (hh:mm)");
		lblTime.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.WEST;
		gbc_lblTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 0;
		pnlTime.add(lblTime, gbc_lblTime);
		cboEntryTime = new TimePicker(timeSettings);
		GridBagConstraints gbc_cboEntryTime = new GridBagConstraints();
		gbc_cboEntryTime.fill = GridBagConstraints.BOTH;
		gbc_cboEntryTime.gridx = 0;
		gbc_cboEntryTime.gridy = 1;
		pnlTime.add(cboEntryTime, gbc_cboEntryTime);

		JPanel pnlWorkshop = new JPanel();
		pnlWorkshop.setBounds(275, 121, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlWorkshop.setBackground(new Color(255, 255, 255, 0));
		pnlDowntime.add(pnlWorkshop);
		GridBagLayout gbl_pnlWorkshop = new GridBagLayout();
		gbl_pnlWorkshop.columnWidths = new int[] { 250, 0 };
		gbl_pnlWorkshop.rowHeights = new int[] { 37, 37 };
		gbl_pnlWorkshop.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlWorkshop.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlWorkshop.setLayout(gbl_pnlWorkshop);

		JLabel lblWorkshop = new JLabel("Цех");
		lblWorkshop.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblWorkshop = new GridBagConstraints();
		gbc_lblWorkshop.insets = new Insets(0, 0, 5, 0);
		gbc_lblWorkshop.anchor = GridBagConstraints.WEST;
		gbc_lblWorkshop.gridx = 0;
		gbc_lblWorkshop.gridy = 0;
		pnlWorkshop.add(lblWorkshop, gbc_lblWorkshop);

		String[] arr = Base.workshopData.toArray(new String[Base.workshopData.size()]);
		cboWorkshop = new JComboBox(arr);
		cboWorkshop.setFont(Base.RADIO_BUTTON_FONT);
		cboWorkshop.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_cboWorkshop = new GridBagConstraints();
		gbc_cboWorkshop.fill = GridBagConstraints.BOTH;
		gbc_cboWorkshop.gridx = 0;
		gbc_cboWorkshop.gridy = 1;
		pnlWorkshop.add(cboWorkshop, gbc_cboWorkshop);

		JPanel pnlFieldMachine = new JPanel();
		pnlFieldMachine.setBackground(new Color(255, 255, 255, 0));
		pnlFieldMachine.setBounds(275, 205, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlFieldMachine);
		GridBagLayout gbl_pnlFIeldMachine = new GridBagLayout();
		gbl_pnlFIeldMachine.columnWidths = new int[] { 250, 0 };
		gbl_pnlFIeldMachine.rowHeights = new int[] { 37, 37 };
		gbl_pnlFIeldMachine.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlFIeldMachine.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlFieldMachine.setLayout(gbl_pnlFIeldMachine);

		JLabel lblFieldMachine = new JLabel("Участък/Машина");
		lblFieldMachine.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblFieldMachine = new GridBagConstraints();
		gbc_lblFieldMachine.anchor = GridBagConstraints.WEST;
		gbc_lblFieldMachine.insets = new Insets(0, 0, 0, 5);
		gbc_lblFieldMachine.gridx = 0;
		gbc_lblFieldMachine.gridy = 0;
		pnlFieldMachine.add(lblFieldMachine, gbc_lblFieldMachine);

		txtFieldMachine = new JTextField();
		txtFieldMachine.setFont(Base.DEFAULT_FONT);
		txtFieldMachine.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtFieldMachine = new GridBagConstraints();
		gbc_txtFieldMachine.fill = GridBagConstraints.BOTH;
		gbc_txtFieldMachine.gridx = 0;
		gbc_txtFieldMachine.gridy = 1;
		pnlFieldMachine.add(txtFieldMachine, gbc_txtFieldMachine);
		txtFieldMachine.setColumns(10);

		JPanel pnlNotified = new JPanel();
		pnlNotified.setBackground(new Color(255, 255, 255, 0));
		pnlNotified.setBounds(15, 289, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlNotified);
		GridBagLayout gbl_pnlNotified = new GridBagLayout();
		gbl_pnlNotified.columnWidths = new int[] { 250, 0 };
		gbl_pnlNotified.rowHeights = new int[] { 37, 37 };
		gbl_pnlNotified.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlNotified.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlNotified.setLayout(gbl_pnlNotified);

		txtNotified = new JTextField();
		txtNotified.setFont(Base.DEFAULT_FONT);
		txtNotified.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtNotified = new GridBagConstraints();
		gbc_txtNotified.fill = GridBagConstraints.BOTH;
		gbc_txtNotified.gridx = 0;
		gbc_txtNotified.gridy = 1;
		pnlNotified.add(txtNotified, gbc_txtNotified);
		txtNotified.setColumns(10);

		JLabel lblNotified = new JLabel("Уведомен");
		lblNotified.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblNotified = new GridBagConstraints();
		gbc_lblNotified.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotified.anchor = GridBagConstraints.WEST;
		gbc_lblNotified.gridx = 0;
		gbc_lblNotified.gridy = 0;
		pnlNotified.add(lblNotified, gbc_lblNotified);

		JPanel pnlNotifier = new JPanel();
		pnlNotifier.setBackground(new Color(255, 255, 255, 0));
		pnlNotifier.setBounds(15, 373, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlNotifier);
		GridBagLayout gbl_pnlNotifier = new GridBagLayout();
		gbl_pnlNotifier.columnWidths = new int[] { 250, 0 };
		gbl_pnlNotifier.rowHeights = new int[] { 37, 37 };
		gbl_pnlNotifier.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlNotifier.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlNotifier.setLayout(gbl_pnlNotifier);

		JLabel lblNotifier = new JLabel("Уведомил");
		lblNotifier.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblNotifier = new GridBagConstraints();
		gbc_lblNotifier.anchor = GridBagConstraints.WEST;
		gbc_lblNotifier.insets = new Insets(0, 0, 0, 5);
		gbc_lblNotifier.gridx = 0;
		gbc_lblNotifier.gridy = 0;
		pnlNotifier.add(lblNotifier, gbc_lblNotifier);

		txtNotifier = new JTextField();
		txtNotifier.setFont(Base.DEFAULT_FONT);
		txtNotifier.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtNotifier = new GridBagConstraints();
		gbc_txtNotifier.fill = GridBagConstraints.BOTH;
		gbc_txtNotifier.gridx = 0;
		gbc_txtNotifier.gridy = 1;
		pnlNotifier.add(txtNotifier, gbc_txtNotifier);
		txtNotifier.setColumns(10);

		JPanel pnlConfirm = new JPanel();
		pnlConfirm.setBackground(new Color(255, 255, 255, 0));
		pnlConfirm.setBounds(15, 457, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlConfirm);
		GridBagLayout gbl_pnlConfirm = new GridBagLayout();
		gbl_pnlConfirm.columnWidths = new int[] { 250, 0 };
		gbl_pnlConfirm.rowHeights = new int[] { 37, 37 };
		gbl_pnlConfirm.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlConfirm.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlConfirm.setLayout(gbl_pnlConfirm);

		JLabel lblConfirm = new JLabel("Потвърдил");
		lblConfirm.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblConfirm = new GridBagConstraints();
		gbc_lblConfirm.anchor = GridBagConstraints.WEST;
		gbc_lblConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_lblConfirm.gridx = 0;
		gbc_lblConfirm.gridy = 0;
		pnlConfirm.add(lblConfirm, gbc_lblConfirm);

		txtConfirm = new JTextField();
		txtConfirm.setFont(Base.DEFAULT_FONT);
		txtConfirm.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtConfirm = new GridBagConstraints();
		gbc_txtConfirm.fill = GridBagConstraints.BOTH;
		gbc_txtConfirm.gridx = 0;
		gbc_txtConfirm.gridy = 1;
		pnlConfirm.add(txtConfirm, gbc_txtConfirm);
		txtConfirm.setColumns(10);

		JPanel pnlRadioButtons = new JPanel();
		pnlRadioButtons.setBackground(new Color(255, 255, 255, 0));
		pnlRadioButtons.setBounds(275, 289, 250, 158);
		pnlDowntime.add(pnlRadioButtons);
		pnlRadioButtons.setLayout(new GridLayout(0, 2, 0, 0));

		radioButtonGroup = new ButtonGroup();

		rdbtnSignal = new JRadioButton("Сигнал");
		// rdbtnSignal.setBackground(new Color(255, 255, 255, 0));
		rdbtnSignal.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnSignal);
		radioButtonGroup.add(rdbtnSignal);
		rdbtnSignal.setActionCommand("Signal");

		rdbtnBreakdown = new JRadioButton("Авария");
		// rdbtnBreakdown.setBackground(new Color(255, 255, 255, 0));
		rdbtnBreakdown.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnBreakdown);
		radioButtonGroup.add(rdbtnBreakdown);
		rdbtnBreakdown.setActionCommand("Breakdown");

		rdbtnMaterial = new JRadioButton("Материал");
		// rdbtnMaterial.setBackground(new Color(255, 255, 255, 0));
		rdbtnMaterial.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnMaterial);
		radioButtonGroup.add(rdbtnMaterial);
		rdbtnMaterial.setActionCommand("Material");

		rdbtnCleaning = new JRadioButton("Почистване");
		// rdbtnCleaning.setBackground(new Color(255, 255, 255, 0));
		rdbtnCleaning.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnCleaning);
		radioButtonGroup.add(rdbtnCleaning);
		rdbtnCleaning.setActionCommand("Cleaning");

		rdbtnRepair = new JRadioButton("Поправка");
		// rdbtnRepair.setBackground(new Color(255, 255, 255, 0));
		rdbtnRepair.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnRepair);
		radioButtonGroup.add(rdbtnRepair);
		rdbtnRepair.setActionCommand("Repair");

		rdbtnNoElectricity = new JRadioButton("Липса на ток");
		// rdbtnNoElectricity.setBackground(new Color(255, 255, 255, 0));
		rdbtnNoElectricity.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnNoElectricity);
		radioButtonGroup.add(rdbtnNoElectricity);
		rdbtnNoElectricity.setActionCommand("NoElectricity");

		rdbtnOther = new JRadioButton("Друго");
		rdbtnOther.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					txtOther.setEnabled(true);
					txtOther.requestFocus();
				} else {
					txtOther.setEnabled(false);
					txtOther.setText(null);
				}
			}
		});
		// rdbtnOther.setBackground(new Color(255, 255, 255, 0));
		rdbtnOther.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnOther);
		radioButtonGroup.add(rdbtnOther);
		rdbtnOther.setActionCommand("Other");

		txtOther = new JTextField();
		txtOther.setEnabled(false);
		txtOther.setFont(Base.DEFAULT_FONT);
		txtOther.setForeground(Base.TEXT_FIELD_COLOR);
		pnlRadioButtons.add(txtOther);
		txtOther.setColumns(10);

		JPanel pnlDescription = new JPanel();
		pnlDescription.setBackground(new Color(255, 255, 255, 0));
		pnlDescription.setBounds(15, 541, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlDescription);
		pnlDescription.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblDescription = new JLabel("Описание:");
		lblDescription.setAlignmentY(Component.TOP_ALIGNMENT);
		lblDescription.setFont(Base.DEFAULT_FONT);
		pnlDescription.add(lblDescription);

		JScrollPane scrlPnlDescription = new JScrollPane();
		pnlDescription.add(scrlPnlDescription);

		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		txtDescription.setForeground(Base.TEXT_FIELD_COLOR);
		scrlPnlDescription.setViewportView(txtDescription);
		txtDescription.setFont(Base.DEFAULT_FONT);

		JPanel pnlAuthor = new JPanel();
		pnlAuthor.setBackground(new Color(255, 255, 255, 0));
		pnlAuthor.setBounds(275, 541, Base.PANEL_WIDTH, Base.PANEL_HEIGHT);
		pnlDowntime.add(pnlAuthor);
		GridBagLayout gbl_pnlAuthor = new GridBagLayout();
		gbl_pnlAuthor.columnWidths = new int[] { 250, 0 };
		gbl_pnlAuthor.rowHeights = new int[] { 37, 37 };
		gbl_pnlAuthor.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlAuthor.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlAuthor.setLayout(gbl_pnlAuthor);

		JLabel lblAuthor = new JLabel("Въведено от");
		lblAuthor.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblAuthor.insets = new Insets(0, 0, 0, 5);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 0;
		pnlAuthor.add(lblAuthor, gbc_lblAuthor);

		txtAuthor = new JTextField();
		txtAuthor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				actionPanel.setTxtActionAuthor(txtAuthor.getText());
			}
		});
		txtAuthor.setFont(Base.DEFAULT_FONT);
		txtAuthor.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtAuthor = new GridBagConstraints();
		gbc_txtAuthor.fill = GridBagConstraints.BOTH;
		gbc_txtAuthor.gridx = 0;
		gbc_txtAuthor.gridy = 1;
		pnlAuthor.add(txtAuthor, gbc_txtAuthor);
		txtAuthor.setColumns(10);

		actionPanel.setLocation(730, 37);
		frmMain.getContentPane().add(actionPanel);
		actionPanel.setVisible(false);

		chckbxEnterAction = new JCheckBox("Въведи Действие към непродуктивно време");
		chckbxEnterAction.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					actionPanel.setVisible(true);
				} else {
					actionPanel.setVisible(false);
				}
			}
		});
		chckbxEnterAction.setFont(Base.DEFAULT_FONT);
		// chckbxEnterAction.setBackground(new Color(255, 255, 255, 0));
		chckbxEnterAction.setBounds(15, 625, 395, 25);
		pnlDowntime.add(chckbxEnterAction);

		JButton btnSave = new JButton("Запази");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Base.LoadDowntimeDb();
				if (!isDowntimeLoaded) {
					if (ValidateDowntime()) {
						DowntimeModel downtime = SaveDowntime();
						SaveToCsv.SaveDowntime(downtime);

						Base.LoadDowntimeDb();

						if (!chckbxEnterAction.isSelected()) {
							frmMain.dispose();
							new MainView();
						}
					}
				}

				if (chckbxEnterAction.isSelected()) {
					if (ValidateAction()) {
						ActionModel action = SaveAction();
						SaveToCsv.SaveAction(action);

						Base.LoadActionDb();

						if (isDowntimeLoaded) {
							frmMain.dispose();
							new MainView();
						} else {
							if (ValidateDowntime()) {
								frmMain.dispose();
								new MainView();
							}
						}
					}
				}
			}
		});
		btnSave.setFont(Base.DEFAULT_FONT);
		btnSave.setBounds(995, 687, 150, 30);
		frmMain.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Отказ");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.dispose();
				new MainView();
			}
		});
		btnCancel.setBounds(1155, 687, 150, 30);
		btnCancel.setFont(Base.DEFAULT_FONT);
		frmMain.getContentPane().add(btnCancel);

		SetBackgroundPicture();
		frmMain.setVisible(true);
	}

	protected boolean ValidateAction() {
		if (actionPanel.getCboStartDate() == null) {
			JOptionPane.showMessageDialog(null, "Въведете начална Дата към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (actionPanel.getCboStartTime() == null) {
			JOptionPane.showMessageDialog(null, "Въведете начален Час към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (actionPanel.getCboEndDate() == null) {
			JOptionPane.showMessageDialog(null, "Въведете крайна Дата към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (actionPanel.getCboEndTime() == null) {
			JOptionPane.showMessageDialog(null, "Въведете краен Час към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (actionPanel.getDatePickerAcceptedDate() == null) {
			JOptionPane.showMessageDialog(null, "Въведете Дата на приемане към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (actionPanel.getTimePickerAcceptedTime() == null) {
			JOptionPane.showMessageDialog(null, "Въведете Час на приемане към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (actionPanel.getTxtPerformer().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Извършил към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtPerformer();
			return false;
		}
		
		if (actionPanel.getTxtAccepted().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Приел към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtAccepted();
			return false;
		}
		
		if (actionPanel.getTxtActionDescription().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Описание към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtActionDescription();
			return false;
		}
		
		if (actionPanel.getTxtActionRecommendation().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Препоръка към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtActionRecommendation();
			return false;
		}
		
		if (actionPanel.getTxtActionDowntime().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Непродуктивно време към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtActionDowntime();
			return false;
		}
		
		if (actionPanel.getTxtApproved().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Утвърдил към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtApproved();
			return false;
		}
		
		if (actionPanel.getTxtActionAuthor().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Въведено от към действието", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			actionPanel.requestFocusTxtActionAuthor();
			return false;
		}
		
		return true;
	}

	protected boolean ValidateDowntime() {
		if (txtNumber.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Номер", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtNumber.requestFocus();
			return false;
		}

		if (Base.downtimeDb.containsKey(Integer.parseInt(txtNumber.getText()))) {
			JOptionPane.showMessageDialog(null, "Номер " + txtNumber.getText() + " вече е въведен", "Грешка",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (cboEntryDate.getDate() == null) {
			JOptionPane.showMessageDialog(null, "Въведете Дата", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (cboEntryTime.getTime() == null) {
			JOptionPane.showMessageDialog(null, "Въведете Час", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (cboWorkshop.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Изберете Цех", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (txtFieldMachine.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Участък/Машина", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtFieldMachine.requestFocus();
			return false;
		}
		
		if (txtNotified.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Уведомен", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtNotified.requestFocus();
			return false;
		}
		
		if (txtNotifier.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Уведомил", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtNotifier.requestFocus();
			return false;
		}

		if (txtConfirm.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Потвърдил", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtConfirm.requestFocus();
			return false;
		}
		
		if (txtAuthor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Въведено от", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtAuthor.requestFocus();
			return false;
		}
		
		if (txtDescription.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Въведете Описание", "Грешка", JOptionPane.INFORMATION_MESSAGE);
			txtDescription.requestFocus();
			return false;
		}

		if (radioButtonGroup.getSelection() == null) {
			JOptionPane.showMessageDialog(null, "Изберете причина от радио бутоните", "Грешка",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
		if (radioButtonGroup.getSelection().getActionCommand() == "Other") {
			if (txtOther.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Въведете Друго", "Грешка", JOptionPane.INFORMATION_MESSAGE);
				txtOther.requestFocus();
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("serial")
	private void FillData(String s) {
		DowntimeModel dtm = BaseMethods.LoadDowntimeModel(s);

		txtNumber.setText(Integer.toString(dtm.getNumber()));
		txtNumber.setEnabled(false);
		txtNumber.setDisabledTextColor(Color.gray);

		cboEntryDate.setDate(dtm.getEntryDate());
		cboEntryDate.setEnabled(false);

		cboEntryTime.setTime(dtm.getEntryTime());
		cboEntryTime.setEnabled(false);

		txtNotified.setText(dtm.getNotified());
		txtNotified.setEnabled(false);
		txtNotified.setDisabledTextColor(Color.gray);

		txtNotifier.setText(dtm.getNotifier());
		txtNotifier.setEnabled(false);
		txtNotifier.setDisabledTextColor(Color.gray);

		txtConfirm.setText(dtm.getConfirm());
		txtConfirm.setEnabled(false);
		txtConfirm.setDisabledTextColor(Color.gray);

		cboWorkshop.setEditable(true);
		cboWorkshop.getEditor().setItem(dtm.getWorkshop());
		cboWorkshop.setEnabled(false);
		cboWorkshop.setRenderer(new DefaultListCellRenderer() {
			@Override
			public void paint(Graphics g) {
				setForeground(Color.gray);
				super.paint(g);
			}
		});

		txtFieldMachine.setText(dtm.getFieldMachine());
		txtFieldMachine.setEnabled(false);
		txtFieldMachine.setDisabledTextColor(Color.gray);

		rdbtnSignal.setSelected(dtm.isSignal());
		rdbtnSignal.setEnabled(false);

		rdbtnMaterial.setSelected(dtm.isMaterial());
		rdbtnMaterial.setEnabled(false);

		rdbtnRepair.setSelected(dtm.isRepair());
		rdbtnRepair.setEnabled(false);

		rdbtnBreakdown.setSelected(dtm.isBreakdown());
		rdbtnBreakdown.setEnabled(false);

		rdbtnCleaning.setSelected(dtm.isCleaning());
		rdbtnCleaning.setEnabled(false);

		rdbtnNoElectricity.setSelected(dtm.isNoElectricity());
		rdbtnNoElectricity.setEnabled(false);

		rdbtnOther.setSelected(dtm.isOther());
		rdbtnOther.setEnabled(false);

		txtOther.setText(dtm.getOtherText());
		txtOther.setEnabled(false);
		txtOther.setDisabledTextColor(Color.gray);

		txtDescription.setText(dtm.getDescription());
		txtDescription.setEnabled(false);
		txtDescription.setDisabledTextColor(Color.gray);

		txtAuthor.setText(dtm.getAuthor());
		txtAuthor.setEnabled(false);
		txtAuthor.setDisabledTextColor(Color.gray);

		chckbxEnterAction.setSelected(true);
		chckbxEnterAction.setEnabled(false);

		actionPanel.setVisible(true);

	}

	private void SetBackgroundPicture() {
		ImageIcon imageIcon = new ImageIcon(Base.backgroundPic);
		lblBackground = new JLabel(imageIcon);
		lblBackground.setBounds(0, 0, Base.WIDTH, Base.HEIGHT);
		frmMain.getContentPane().add(lblBackground);

		// frmMain.setComponentZOrder(lblBackground, 0);
	}

	private ActionModel SaveAction() {
		ActionModel action = new ActionModel();
		action.setNumber(Integer.parseInt(txtNumber.getText()));
		action.setStartDate(actionPanel.getCboStartDate());
		action.setStartTime(actionPanel.getCboStartTime());
		action.setEndDate(actionPanel.getCboEndDate());
		action.setEndTime(actionPanel.getCboEndTime());
		action.setPerformer(actionPanel.getTxtPerformer());
		action.setDescription(actionPanel.getTxtActionDescription());
		action.setRecommendation(actionPanel.getTxtActionRecommendation());
		action.setAccepted(actionPanel.getTxtAccepted());
		action.setDateOfAccept(actionPanel.getDatePickerAcceptedDate());
		action.setTimeOfAccept(actionPanel.getTimePickerAcceptedTime());
		action.setDonwtime(actionPanel.getTxtActionDowntime());
		action.setApproved(actionPanel.getTxtApproved());
		action.setAuthor(actionPanel.getTxtActionAuthor());
		action.setDateOfEntry(LocalDate.now());
		action.setTimeOfEntry(LocalTime.now());

		return action;
	}

	private DowntimeModel SaveDowntime() {
		DowntimeModel downtime = new DowntimeModel();
		StringBuilder sb = new StringBuilder();

		downtime.setNumber(Integer.parseInt(txtNumber.getText()));
		downtime.setEntryDate(cboEntryDate.getDate());
		downtime.setEntryTime(cboEntryTime.getTime());
		downtime.setWorkshop(cboWorkshop.getSelectedItem().toString());
		downtime.setFieldMachine(txtFieldMachine.getText());
		downtime.setNotified(txtNotified.getText());
		downtime.setNotifier(txtNotifier.getText());
		downtime.setConfirm(txtConfirm.getText());
		downtime.setSignal(rdbtnSignal.isSelected());
		downtime.setMaterial(rdbtnMaterial.isSelected());
		downtime.setRepair(rdbtnRepair.isSelected());
		downtime.setBreakdown(rdbtnBreakdown.isSelected());
		downtime.setCleaning(rdbtnCleaning.isSelected());
		downtime.setNoElectricity(rdbtnNoElectricity.isSelected());
		downtime.setOther(rdbtnOther.isSelected());
		downtime.setOtherText(txtOther.getText());

		for (String line : txtDescription.getText().split("\\n")) {
			sb.append(line);
			sb.append(" ");
		}
		downtime.setDescription(sb.toString());

		downtime.setAuthor(txtAuthor.getText());
		downtime.setDateOfEntry(LocalDate.now());
		downtime.setTimeOfEntry(LocalTime.now());

		return downtime;
	}
}
