package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;

import Controller.Base;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JScrollPane;

public class ActionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatePicker cboStartDate;
	private TimePicker cboStartTime;
	private DatePicker cboEndDate;
	private TimePicker cboEndTime;
	private DatePicker datePickerAcceptedDate;
	private TimePicker timePickerAcceptedTime;
	private JTextField txtPerformer;
	private JTextArea txtActionDescription;
	private JTextArea txtActionRecommendation;
	private JTextField txtAccepted;
	private JTextField txtActionDowntime;
	private JTextField txtApproved;
	private JTextField txtActionAuthor;

	/**
	 * Create the panel.
	 */
	public static void test() {

	}

	public ActionPanel() {
		setSize(575, 600);

		DatePickerSettings dateSettings = new DatePickerSettings(Base.LOCALE);
		dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		dateSettings.setColor(DateArea.DatePickerTextValidDate, Base.TEXT_FIELD_COLOR);
		dateSettings.setFontValidDate(Base.RADIO_BUTTON_FONT);
		TimePickerSettings timeSettings = new TimePickerSettings(Base.LOCALE);
		timeSettings.setColor(TimeArea.TimePickerTextValidTime, Color.blue);
		timeSettings.initialTime = LocalTime.now();
		setLayout(new BorderLayout(0, 0));

		JPanel pnlAction = new JPanel();
		pnlAction.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(200, 149, 150), new Color(160, 160, 160)),
				"Действие към непродуктивно време", TitledBorder.LEADING, TitledBorder.TOP, Base.DEFAULT_FONT, null));
		pnlAction.setBackground(new Color(255, 255, 255, 150));
		pnlAction.setLayout(null);
		add(pnlAction);

		JPanel pnlStart = new JPanel();
		pnlStart.setBackground(new Color(255, 255, 255, 0));
		pnlStart.setBounds(15, 37, 265, 113);
		pnlAction.add(pnlStart);
		pnlStart.setBorder(
				new TitledBorder(null, "Начало", TitledBorder.LEADING, TitledBorder.TOP, Base.DEFAULT_FONT, null));
		pnlStart.setLayout(new GridLayout(2, 1, 0, 10));
		JPanel pnlStartDate = new JPanel();
		pnlStartDate.setBackground(new Color(255, 255, 255, 0));
		pnlStart.add(pnlStartDate);
		pnlStartDate.setBounds(15, 84, 400, 37);
		pnlStartDate.setBackground(new Color(255, 255, 255, 0));
		GridBagLayout gbl_pnlDate = new GridBagLayout();
		gbl_pnlDate.columnWidths = new int[] { 50, 200, 0 };
		gbl_pnlDate.rowHeights = new int[] { 37, 0 };
		gbl_pnlDate.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlDate.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlStartDate.setLayout(gbl_pnlDate);

		JLabel lblStartDate = new JLabel("Дата");
		lblStartDate.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 0;
		pnlStartDate.add(lblStartDate, gbc_lblDate);
		cboStartDate = new DatePicker(dateSettings);
		GridBagConstraints gbc_cboStartDate = new GridBagConstraints();
		gbc_cboStartDate.fill = GridBagConstraints.BOTH;
		gbc_cboStartDate.gridx = 1;
		gbc_cboStartDate.gridy = 0;
		pnlStartDate.add(cboStartDate, gbc_cboStartDate);

		JPanel pnlStartTime = new JPanel();
		pnlStartTime.setBackground(new Color(255, 255, 255, 0));
		pnlStart.add(pnlStartTime);
		pnlStartTime.setBounds(15, 131, 400, 37);
		pnlStartTime.setBackground(new Color(255, 255, 255, 0));
		GridBagLayout gbl_pnlTime = new GridBagLayout();
		gbl_pnlTime.columnWidths = new int[] { 50, 200, 0 };
		gbl_pnlTime.rowHeights = new int[] { 37, 0 };
		gbl_pnlTime.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlTime.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlStartTime.setLayout(gbl_pnlTime);

		JLabel lblStartTime = new JLabel("Час");
		lblStartTime.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.anchor = GridBagConstraints.EAST;
		gbc_lblTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblTime.gridx = 0;
		gbc_lblTime.gridy = 0;
		pnlStartTime.add(lblStartTime, gbc_lblTime);
		cboStartTime = new TimePicker(timeSettings);
		GridBagConstraints gbc_cboEntryTime = new GridBagConstraints();
		gbc_cboEntryTime.fill = GridBagConstraints.BOTH;
		gbc_cboEntryTime.gridx = 1;
		gbc_cboEntryTime.gridy = 0;
		pnlStartTime.add(cboStartTime, gbc_cboEntryTime);

		JPanel pnlEnd = new JPanel();
		pnlEnd.setBackground(new Color(255, 255, 255, 0));
		pnlEnd.setBounds(290, 37, 265, 113);
		pnlAction.add(pnlEnd);
		pnlEnd.setBorder(
				new TitledBorder(null, "Край", TitledBorder.LEADING, TitledBorder.TOP, Base.DEFAULT_FONT, null));
		pnlEnd.setLayout(new GridLayout(2, 1, 0, 10));
		JPanel pnlEndDate = new JPanel();
		pnlEnd.add(pnlEndDate);
		pnlEndDate.setBounds(15, 84, 400, 37);
		pnlEndDate.setBackground(new Color(255, 255, 255, 0));
		GridBagLayout gbl_pnlEndDate = new GridBagLayout();
		gbl_pnlEndDate.columnWidths = new int[] { 50, 200, 0 };
		gbl_pnlEndDate.rowHeights = new int[] { 37, 0 };
		gbl_pnlEndDate.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEndDate.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlEndDate.setLayout(gbl_pnlEndDate);

		JLabel lblEndDate = new JLabel("Дата");
		lblEndDate.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.EAST;
		gbc_lblEndDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 0;
		pnlEndDate.add(lblEndDate, gbc_lblEndDate);
		DatePickerSettings endDateSettings = new DatePickerSettings(Base.LOCALE);
		endDateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		endDateSettings.setColor(DateArea.DatePickerTextValidDate, Base.TEXT_FIELD_COLOR);
		endDateSettings.setFontValidDate(Base.RADIO_BUTTON_FONT);
		cboEndDate = new DatePicker(endDateSettings);
		GridBagConstraints gbc_cboEndDate = new GridBagConstraints();
		gbc_cboEndDate.fill = GridBagConstraints.BOTH;
		gbc_cboEndDate.gridx = 1;
		gbc_cboEndDate.gridy = 0;
		pnlEndDate.add(cboEndDate, gbc_cboEndDate);
		cboEndDate.setDateToToday();

		JPanel pnlEndTime = new JPanel();
		pnlEndTime.setBackground(new Color(255, 255, 255, 0));
		pnlEnd.add(pnlEndTime);
		pnlEndTime.setBounds(15, 131, 400, 37);
		pnlEndTime.setBackground(new Color(255, 255, 255, 0));
		GridBagLayout gbl_pnlEndTime = new GridBagLayout();
		gbl_pnlEndTime.columnWidths = new int[] { 50, 200, 0 };
		gbl_pnlEndTime.rowHeights = new int[] { 37, 0 };
		gbl_pnlEndTime.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEndTime.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlEndTime.setLayout(gbl_pnlEndTime);
		TimePickerSettings endTimeSettings = new TimePickerSettings(Base.LOCALE);
		endTimeSettings.setColor(TimeArea.TimePickerTextValidTime, Color.blue);
		// timeSettings.fontValidTime(Base.DEFAULT_FONT);
		endTimeSettings.initialTime = LocalTime.now();

		JLabel lblEndTime = new JLabel("Час");
		lblEndTime.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblEndTime = new GridBagConstraints();
		gbc_lblEndTime.anchor = GridBagConstraints.EAST;
		gbc_lblEndTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblEndTime.gridx = 0;
		gbc_lblEndTime.gridy = 0;
		pnlEndTime.add(lblEndTime, gbc_lblEndTime);
		cboEndTime = new TimePicker(timeSettings);
		GridBagConstraints gbc_cboEndTime = new GridBagConstraints();
		gbc_cboEndTime.fill = GridBagConstraints.BOTH;
		gbc_cboEndTime.gridx = 1;
		gbc_cboEndTime.gridy = 0;
		pnlEndTime.add(cboEndTime, gbc_cboEndTime);

		JPanel pnlPerformer = new JPanel();
		pnlPerformer.setBackground(new Color(255, 255, 255, 0));
		pnlPerformer.setBounds(15, 160, 265, 74);
		pnlAction.add(pnlPerformer);
		GridBagLayout gbl_pnlPerformer = new GridBagLayout();
		gbl_pnlPerformer.columnWidths = new int[] { 265, 0 };
		gbl_pnlPerformer.rowHeights = new int[] { 37, 37 };
		gbl_pnlPerformer.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlPerformer.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlPerformer.setLayout(gbl_pnlPerformer);

		JLabel lblPerformer = new JLabel("Извършил");
		lblPerformer.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblPerformer = new GridBagConstraints();
		gbc_lblPerformer.anchor = GridBagConstraints.WEST;
		gbc_lblPerformer.gridx = 0;
		gbc_lblPerformer.gridy = 0;
		pnlPerformer.add(lblPerformer, gbc_lblPerformer);

		txtPerformer = new JTextField();
		txtPerformer.setFont(Base.DEFAULT_FONT);
		txtPerformer.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtPerformer = new GridBagConstraints();
		gbc_txtPerformer.fill = GridBagConstraints.BOTH;
		gbc_txtPerformer.gridx = 0;
		gbc_txtPerformer.gridy = 1;
		pnlPerformer.add(txtPerformer, gbc_txtPerformer);
		txtPerformer.setColumns(10);

		JPanel pnlActionDescription = new JPanel();
		pnlActionDescription.setBackground(new Color(255, 255, 255, 0));
		pnlActionDescription.setBounds(15, 245, 265, 111);
		pnlAction.add(pnlActionDescription);
		GridBagLayout gbl_pnlActionDescription = new GridBagLayout();
		gbl_pnlActionDescription.columnWidths = new int[] { 265, 0 };
		gbl_pnlActionDescription.rowHeights = new int[] { 37, 74, 0 };
		gbl_pnlActionDescription.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlActionDescription.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnlActionDescription.setLayout(gbl_pnlActionDescription);

		JLabel lblActionDescription = new JLabel("Описание");
		lblActionDescription.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblActionDescription = new GridBagConstraints();
		gbc_lblActionDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblActionDescription.anchor = GridBagConstraints.WEST;
		gbc_lblActionDescription.gridx = 0;
		gbc_lblActionDescription.gridy = 0;
		pnlActionDescription.add(lblActionDescription, gbc_lblActionDescription);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		pnlActionDescription.add(scrollPane, gbc_scrollPane);

		txtActionDescription = new JTextArea();
		txtActionDescription.setForeground(Base.TEXT_FIELD_COLOR);
		txtActionDescription.setLineWrap(true);
		scrollPane.setViewportView(txtActionDescription);
		txtActionDescription.setRows(3);
		txtActionDescription.setFont(Base.DEFAULT_FONT);

		JPanel pnlRecommendation = new JPanel();
		pnlRecommendation.setBackground(new Color(255, 255, 255, 0));
		pnlRecommendation.setBounds(15, 366, 265, 111);
		pnlAction.add(pnlRecommendation);
		GridBagLayout gbl_pnlRecommendation = new GridBagLayout();
		gbl_pnlRecommendation.columnWidths = new int[] { 265, 0 };
		gbl_pnlRecommendation.rowHeights = new int[] { 37, 74, 0 };
		gbl_pnlRecommendation.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlRecommendation.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnlRecommendation.setLayout(gbl_pnlRecommendation);

		JLabel lblRecommendation = new JLabel("Препоръка");
		lblRecommendation.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblRecommendation = new GridBagConstraints();
		gbc_lblRecommendation.insets = new Insets(0, 0, 5, 0);
		gbc_lblRecommendation.anchor = GridBagConstraints.WEST;
		gbc_lblRecommendation.gridx = 0;
		gbc_lblRecommendation.gridy = 0;
		pnlRecommendation.add(lblRecommendation, gbc_lblRecommendation);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		pnlRecommendation.add(scrollPane_1, gbc_scrollPane_1);

		txtActionRecommendation = new JTextArea();
		txtActionRecommendation.setForeground(Base.TEXT_FIELD_COLOR);
		txtActionRecommendation.setLineWrap(true);
		scrollPane_1.setViewportView(txtActionRecommendation);
		txtActionRecommendation.setRows(3);
		txtActionRecommendation.setFont(Base.DEFAULT_FONT);

		JPanel pnlAccepted = new JPanel();
		pnlAccepted.setBackground(new Color(255, 255, 255, 0));
		pnlAccepted.setBounds(290, 161, 265, 280);
		pnlAction.add(pnlAccepted);
		GridBagLayout gbl_pnlAccepted = new GridBagLayout();
		gbl_pnlAccepted.columnWidths = new int[] { 265, 0 };
		gbl_pnlAccepted.rowHeights = new int[] { 37, 37, 37, 37, 37, 37, 0 };
		gbl_pnlAccepted.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlAccepted.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		pnlAccepted.setLayout(gbl_pnlAccepted);

		JLabel lblAccepted = new JLabel("Приел");
		lblAccepted.setFont(new Font("Century Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_lblAccepted = new GridBagConstraints();
		gbc_lblAccepted.anchor = GridBagConstraints.WEST;
		gbc_lblAccepted.insets = new Insets(0, 0, 5, 0);
		gbc_lblAccepted.gridx = 0;
		gbc_lblAccepted.gridy = 0;
		pnlAccepted.add(lblAccepted, gbc_lblAccepted);

		txtAccepted = new JTextField();
		txtAccepted.setForeground(Base.TEXT_FIELD_COLOR);
		txtAccepted.setFont(new Font("Century Gothic", Font.BOLD, 16));
		txtAccepted.setColumns(10);
		GridBagConstraints gbc_txtAccepted = new GridBagConstraints();
		gbc_txtAccepted.fill = GridBagConstraints.BOTH;
		gbc_txtAccepted.gridx = 0;
		gbc_txtAccepted.gridy = 1;
		pnlAccepted.add(txtAccepted, gbc_txtAccepted);

		JPanel pnlAcceptedDate = new JPanel();
		pnlAcceptedDate.setBackground(new Color(255, 255, 255, 0));
		GridBagConstraints gbc_pnlAcceptedDate = new GridBagConstraints();
		gbc_pnlAcceptedDate.insets = new Insets(10, 0, 5, 0);
		gbc_pnlAcceptedDate.fill = GridBagConstraints.BOTH;
		gbc_pnlAcceptedDate.gridx = 0;
		gbc_pnlAcceptedDate.gridy = 3;
		pnlAccepted.add(pnlAcceptedDate, gbc_pnlAcceptedDate);
		GridBagLayout gbl_pnlAcceptedDate = new GridBagLayout();
		gbl_pnlAcceptedDate.columnWidths = new int[] { 50, 200, 0 };
		gbl_pnlAcceptedDate.rowHeights = new int[] { 37, 0 };
		gbl_pnlAcceptedDate.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlAcceptedDate.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlAcceptedDate.setLayout(gbl_pnlAcceptedDate);

		JLabel lblAcceptedDate = new JLabel("Дата");
		lblAcceptedDate.setFont(new Font("Century Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_lblAcceptedDate = new GridBagConstraints();
		gbc_lblAcceptedDate.anchor = GridBagConstraints.EAST;
		gbc_lblAcceptedDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcceptedDate.gridx = 0;
		gbc_lblAcceptedDate.gridy = 0;
		pnlAcceptedDate.add(lblAcceptedDate, gbc_lblAcceptedDate);

		DatePickerSettings datePickerAcceptedDateSettings = new DatePickerSettings(Base.LOCALE);
		datePickerAcceptedDateSettings.setColor(DateArea.DatePickerTextValidDate, Base.TEXT_FIELD_COLOR);
		datePickerAcceptedDateSettings.setFontValidDate(Base.RADIO_BUTTON_FONT);
		datePickerAcceptedDateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
		datePickerAcceptedDate = new DatePicker(datePickerAcceptedDateSettings);
		datePickerAcceptedDate.setDateToToday();
		GridBagConstraints gbc_datePickerAcceptedDate = new GridBagConstraints();
		gbc_datePickerAcceptedDate.fill = GridBagConstraints.BOTH;
		gbc_datePickerAcceptedDate.gridx = 1;
		gbc_datePickerAcceptedDate.gridy = 0;
		pnlAcceptedDate.add(datePickerAcceptedDate, gbc_datePickerAcceptedDate);

		JPanel pnlAcceptedTime = new JPanel();
		pnlAcceptedTime.setBackground(new Color(255, 255, 255, 0));
		GridBagConstraints gbc_pnlAcceptedTime = new GridBagConstraints();
		gbc_pnlAcceptedTime.insets = new Insets(0, 0, 5, 0);
		gbc_pnlAcceptedTime.fill = GridBagConstraints.BOTH;
		gbc_pnlAcceptedTime.gridx = 0;
		gbc_pnlAcceptedTime.gridy = 4;
		pnlAccepted.add(pnlAcceptedTime, gbc_pnlAcceptedTime);
		GridBagLayout gbl_pnlAcceptedTime = new GridBagLayout();
		gbl_pnlAcceptedTime.columnWidths = new int[] { 50, 200, 0 };
		gbl_pnlAcceptedTime.rowHeights = new int[] { 37, 0 };
		gbl_pnlAcceptedTime.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlAcceptedTime.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlAcceptedTime.setLayout(gbl_pnlAcceptedTime);

		JLabel lblAcceptedTime = new JLabel("Час");
		lblAcceptedTime.setFont(new Font("Century Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_lblAcceptedTime = new GridBagConstraints();
		gbc_lblAcceptedTime.anchor = GridBagConstraints.EAST;
		gbc_lblAcceptedTime.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcceptedTime.gridx = 0;
		gbc_lblAcceptedTime.gridy = 0;
		pnlAcceptedTime.add(lblAcceptedTime, gbc_lblAcceptedTime);

		timePickerAcceptedTime = new TimePicker(timeSettings);
		GridBagConstraints gbc_timePickerAcceptedTime = new GridBagConstraints();
		gbc_timePickerAcceptedTime.fill = GridBagConstraints.BOTH;
		gbc_timePickerAcceptedTime.gridx = 1;
		gbc_timePickerAcceptedTime.gridy = 0;
		pnlAcceptedTime.add(timePickerAcceptedTime, gbc_timePickerAcceptedTime);

		JLabel lblActionDowntime = new JLabel("Непродуктивно време");
		lblActionDowntime.setFont(Base.DEFAULT_FONT);
		GridBagConstraints gbc_lblActionDowntime = new GridBagConstraints();
		gbc_lblActionDowntime.anchor = GridBagConstraints.WEST;
		gbc_lblActionDowntime.insets = new Insets(0, 0, 5, 0);
		gbc_lblActionDowntime.gridx = 0;
		gbc_lblActionDowntime.gridy = 5;
		pnlAccepted.add(lblActionDowntime, gbc_lblActionDowntime);

		txtActionDowntime = new JTextField();
		txtActionDowntime.setFont(Base.DEFAULT_FONT);
		txtActionDowntime.setForeground(Base.TEXT_FIELD_COLOR);
		GridBagConstraints gbc_txtActionDowntime = new GridBagConstraints();
		gbc_txtActionDowntime.fill = GridBagConstraints.BOTH;
		gbc_txtActionDowntime.gridx = 0;
		gbc_txtActionDowntime.gridy = 6;
		pnlAccepted.add(txtActionDowntime, gbc_txtActionDowntime);
		txtActionDowntime.setColumns(10);

		JPanel pnlApproved = new JPanel();
		pnlApproved.setBackground(new Color(255, 255, 255, 0));
		pnlApproved.setBounds(15, 491, 265, 74);
		pnlAction.add(pnlApproved);
		GridBagLayout gbl_pnlApproved = new GridBagLayout();
		gbl_pnlApproved.columnWidths = new int[] { 265, 0 };
		gbl_pnlApproved.rowHeights = new int[] { 37, 37, 0 };
		gbl_pnlApproved.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlApproved.rowWeights = new double[] { 0.0, 4.9E-324, Double.MIN_VALUE };
		pnlApproved.setLayout(gbl_pnlApproved);

		JLabel lblApproved = new JLabel("Утвърдил");
		lblApproved.setFont(new Font("Century Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_lblApproved = new GridBagConstraints();
		gbc_lblApproved.anchor = GridBagConstraints.WEST;
		gbc_lblApproved.insets = new Insets(0, 0, 5, 0);
		gbc_lblApproved.gridx = 0;
		gbc_lblApproved.gridy = 0;
		pnlApproved.add(lblApproved, gbc_lblApproved);

		txtApproved = new JTextField();
		txtApproved.setFont(new Font("Century Gothic", Font.BOLD, 16));
		txtApproved.setForeground(Base.TEXT_FIELD_COLOR);
		txtApproved.setColumns(10);
		GridBagConstraints gbc_txtApproved = new GridBagConstraints();
		gbc_txtApproved.fill = GridBagConstraints.BOTH;
		gbc_txtApproved.gridx = 0;
		gbc_txtApproved.gridy = 1;
		pnlApproved.add(txtApproved, gbc_txtApproved);

		JPanel pnlActionAuthor = new JPanel();
		pnlActionAuthor.setBackground(new Color(255, 255, 255, 0));
		pnlActionAuthor.setBounds(290, 491, 265, 74);
		pnlAction.add(pnlActionAuthor);
		GridBagLayout gbl_pnlActionAuthor = new GridBagLayout();
		gbl_pnlActionAuthor.columnWidths = new int[] { 265, 0 };
		gbl_pnlActionAuthor.rowHeights = new int[] { 37, 37, 0 };
		gbl_pnlActionAuthor.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnlActionAuthor.rowWeights = new double[] { 0.0, 4.9E-324, Double.MIN_VALUE };
		pnlActionAuthor.setLayout(gbl_pnlActionAuthor);

		JLabel lblActionAuthor = new JLabel("Въведено от");
		lblActionAuthor.setFont(new Font("Century Gothic", Font.BOLD, 16));
		GridBagConstraints gbc_lblActionAuthor = new GridBagConstraints();
		gbc_lblActionAuthor.anchor = GridBagConstraints.WEST;
		gbc_lblActionAuthor.insets = new Insets(0, 0, 5, 0);
		gbc_lblActionAuthor.gridx = 0;
		gbc_lblActionAuthor.gridy = 0;
		pnlActionAuthor.add(lblActionAuthor, gbc_lblActionAuthor);

		txtActionAuthor = new JTextField();
		txtActionAuthor.setFont(new Font("Century Gothic", Font.BOLD, 16));
		txtActionAuthor.setForeground(Base.TEXT_FIELD_COLOR);
		txtActionAuthor.setColumns(10);
		GridBagConstraints gbc_txtActionAuthor = new GridBagConstraints();
		gbc_txtActionAuthor.fill = GridBagConstraints.BOTH;
		gbc_txtActionAuthor.gridx = 0;
		gbc_txtActionAuthor.gridy = 1;
		pnlActionAuthor.add(txtActionAuthor, gbc_txtActionAuthor);
		cboStartDate.setDateToToday();

	}

	public LocalDate getCboStartDate() {
		return cboStartDate.getDate();
	}

	public LocalTime getCboStartTime() {
		return cboStartTime.getTime();
	}

	public LocalDate getCboEndDate() {
		return cboEndDate.getDate();
	}

	public LocalTime getCboEndTime() {
		return cboEndTime.getTime();
	}

	public LocalDate getDatePickerAcceptedDate() {
		return datePickerAcceptedDate.getDate();
	}

	public LocalTime getTimePickerAcceptedTime() {
		return timePickerAcceptedTime.getTime();
	}

	public String getTxtPerformer() {
		return txtPerformer.getText();
	}
	
	public void requestFocusTxtPerformer() {
		txtPerformer.requestFocus();
	}

	public String getTxtActionDescription() {
		StringBuilder sb = new StringBuilder();

		if (!txtActionDescription.getText().equals("")) {
			for (String line : txtActionDescription.getText().split("\\n")) {
				sb.append(line);
				sb.append(" ");
			}
		}

		return sb.toString();
	}
	
	public void requestFocusTxtActionDescription() {
		txtActionDescription.requestFocus();
	}

	public String getTxtActionRecommendation() {
		StringBuilder sb = new StringBuilder();

		if (!txtActionRecommendation.getText().equals("")) {
			for (String line : txtActionRecommendation.getText().split("\\n")) {
				sb.append(line);
				sb.append(" ");
			}
		}

		return sb.toString();
	}
	
	public void requestFocusTxtActionRecommendation() {
		txtActionRecommendation.requestFocus();
	}

	public String getTxtAccepted() {
		return txtAccepted.getText();
	}
	
	public void requestFocusTxtAccepted() {
		txtAccepted.requestFocus();
	}

	public String getTxtActionDowntime() {
		return txtActionDowntime.getText();
	}
	
	public void requestFocusTxtActionDowntime() {
		txtActionDowntime.requestFocus();
	}

	public String getTxtApproved() {
		return txtApproved.getText();
	}
	
	public void requestFocusTxtApproved() {
		txtApproved.requestFocus();
	}

	public String getTxtActionAuthor() {
		return txtActionAuthor.getText();
	}
	
	public void requestFocusTxtActionAuthor() {
		txtActionAuthor.requestFocus();
	}

	public void setTxtActionAuthor(String txtActionAuthor) {
		this.txtActionAuthor.setText(txtActionAuthor);
	}
}
