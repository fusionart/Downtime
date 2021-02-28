package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Controller.Base;
import Controller.BaseMethods;
import Controller.MaintenanceColorRenderer;
import Controller.WordWrapCellRenderer;
import Model.DowntimeModel;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class �aintenanceTvView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBackground;
	private JTable tbl;
	private JRadioButton rdbtnAll;
	private static DefaultTableModel defaultTableModel;
	private String header[] = new String[] { "������/������", "�����", "����", "��� �� ������������", "��������", "���",
			"�������/������", "��������", "��������" };

	private static FileTime initialTime;
	private static FileTime lastModifiedTime;

	private static int hdHeight = 1080;
	private static int hdWidth = 1920;

	public �aintenanceTvView() {
		Image frameIcon = Toolkit.getDefaultToolkit().getImage(Base.icon);
		setIconImage(frameIcon);
		setTitle(Base.FRAME_CAPTION);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, hdWidth, hdHeight);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel pnlMain = new JPanel();
		pnlMain.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(100, 149, 237), new Color(160, 160, 160)),
				"������ � �������", TitledBorder.LEADING, TitledBorder.TOP, Base.DEFAULT_FONT, null));
		pnlMain.setBounds(Base.ELEMENT_OFFSET, Base.ELEMENT_OFFSET,
				(hdWidth - Base.ELEMENT_OFFSET * 3 + Base.ELEMENT_OFFSET / 2), (hdHeight - Base.ELEMENT_OFFSET * 3));
		pnlMain.setBackground(new Color(255, 255, 255, 150));
		contentPane.add(pnlMain);
		pnlMain.setLayout(null);

		JPanel pnlRadioButtons = new JPanel();
		pnlRadioButtons.setBounds(25, 52, 379, 23);
		pnlMain.add(pnlRadioButtons);
		pnlRadioButtons.setLayout(null);

		ButtonGroup rdbtnGroup = new ButtonGroup();

		rdbtnAll = new JRadioButton("������");
		rdbtnAll.setSelected(true);
		rdbtnAll.setBounds(12, 0, 75, 23);
		rdbtnAll.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnAll);
		rdbtnGroup.add(rdbtnAll);
		rdbtnAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable(true, false, false);
				}
			}
		});

		JRadioButton rdbtnBreakdown = new JRadioButton("���� ������");
		rdbtnBreakdown.setBounds(99, 0, 121, 23);
		rdbtnBreakdown.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnBreakdown);
		rdbtnGroup.add(rdbtnBreakdown);
		rdbtnBreakdown.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable(false, true, false);
				}
			}
		});

		JRadioButton rdbtnSignal = new JRadioButton("���� �������");
		rdbtnSignal.setBounds(232, 0, 132, 23);
		rdbtnSignal.setFont(Base.RADIO_BUTTON_FONT);
		pnlRadioButtons.add(rdbtnSignal);
		rdbtnGroup.add(rdbtnSignal);
		rdbtnSignal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					FillTable(false, false, true);
				}
			}
		});

		defaultTableModel = new DefaultTableModel(0, 0);

		defaultTableModel.setColumnIdentifiers(header);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 100, pnlMain.getWidth() - 50, pnlMain.getHeight() - 150);
		pnlMain.add(scrollPane);

		JButton btnRefresh = new JButton("���������");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReloadDbs();
				FillTable(true, false, false);
				rdbtnAll.setSelected(true);
			}
		});
		btnRefresh.setBounds(1652, 52, 150, 30);
		btnRefresh.setFont(Base.DEFAULT_FONT);
		pnlMain.add(btnRefresh);

		JButton btnBack = new JButton("�������");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(1652, 928, 150, 30);
		btnBack.setFont(Base.DEFAULT_FONT);
		pnlMain.add(btnBack);
		
		tbl = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tbl.setBounds(121, 261, 903, 265);
		tbl.setFont(Base.DEFAULT_FONT);
		tbl.getTableHeader().setFont(Base.DEFAULT_FONT);
		tbl.getTableHeader().setResizingAllowed(true);
		tbl.setRowHeight(40);
		scrollPane.setViewportView(tbl);
		tbl.setModel(defaultTableModel);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		FillTable(true, false, false);

		// set coloring for the table
		MaintenanceColorRenderer colorRenderer = new MaintenanceColorRenderer();
		tbl.setDefaultRenderer(Object.class, colorRenderer);
		BaseMethods.ResizeColumnWidth(tbl);
		// word wrap for Description column
		tbl.getColumnModel().getColumn(4).setCellRenderer(new WordWrapCellRenderer());
		ReloadTimer();

		SetBackgroundPicture();
		setVisible(true);
	}

	private void FillTable(Boolean all, Boolean breakdown, Boolean signal) {
		String actionName;

		// clear the table
		defaultTableModel.setRowCount(0);

		// sort downtimeDb by key
		// Map<Integer, DowntimeModel> treeMap = new TreeMap<>(Base.downtimeDb);

		// reverse the order of the Base.downtimeDb
		List<Integer> reverseOrderedKeys = new ArrayList<Integer>(Base.downtimeDb.keySet());
		Collections.reverse(reverseOrderedKeys);

		for (Integer key : reverseOrderedKeys) {
			// Check if downtime has action
			if (!Base.actionDb.containsKey(key)) {
				DowntimeModel dtm = BaseMethods.LoadDowntimeModel(Integer.toString(key));

				if (dtm.isBreakdown() && (all || breakdown)) {
					actionName = BaseMethods.ActionName(dtm);

					defaultTableModel.addRow(new Object[] { actionName, dtm.getNumber(),
							dtm.getEntryDate().format(Base.dateFormat), dtm.getEntryTime(), dtm.getDescription(),
							dtm.getWorkshop(), dtm.getFieldMachine(), dtm.getNotified(), dtm.getNotifier() });
				}

				if (dtm.isSignal() && (all || signal)) {
					actionName = BaseMethods.ActionName(dtm);

					defaultTableModel.addRow(new Object[] { actionName, dtm.getNumber(),
							dtm.getEntryDate().format(Base.dateFormat), dtm.getEntryTime(), dtm.getDescription(),
							dtm.getWorkshop(), dtm.getFieldMachine(), dtm.getNotified(), dtm.getNotifier() });
				}
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
					FillTable(true, false, false);
					rdbtnAll.setSelected(true);
				}
			}
		});

		timer.setDelay(Base.refreshTime);
		timer.start();
	}

	private void SetBackgroundPicture() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Base.backgroundPic));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = img.getScaledInstance(hdWidth, hdHeight, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		contentPane.setLayout(null);
		lblBackground = new JLabel(imageIcon);
		lblBackground.setBounds(5, 5, hdWidth, hdHeight);
		getContentPane().add(lblBackground);
	}
}
