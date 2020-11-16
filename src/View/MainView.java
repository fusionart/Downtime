package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Base;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.apache.batik.swing.JSVGCanvas;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class MainView {

	private JFrame frmMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame(Base.FRAME_CAPTION);
		frmMain.setBounds(100, 100, Base.WIDTH, Base.HEIGHT);
		Image frameIcon = Toolkit.getDefaultToolkit().getImage(Base.icon);    
		frmMain.setIconImage(frameIcon); 
		frmMain.setResizable(false);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnCreateDowntime = new JButton(Base.CREATE_DOWNTIME_LABEL);
		btnCreateDowntime.setBackground(new Color(204, 204, 204));
		btnCreateDowntime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.dispose();
				new DowntimeView(null);
			}
		});
		btnCreateDowntime.setFont(new Font("Century Gothic", Font.BOLD, 22));
		btnCreateDowntime.setBounds(275, 525, 400, 90);
		frmMain.getContentPane().add(btnCreateDowntime);
		
		JButton btnCreateAction = new JButton("<html><center>" + Base.ACTION_LABEL.replaceAll("\\n", "<br>") + "</center></html>");
		btnCreateAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmMain.dispose();
				//new DowntimeView();
				new NumberInput(frmMain);
			}
		});
		btnCreateAction.setBackground(new Color(255, 102, 102));
		btnCreateAction.setFont(new Font("Century Gothic", Font.BOLD, 22));
		btnCreateAction.setBounds(710, 525, 400, 90);
		frmMain.getContentPane().add(btnCreateAction);
		
		JButton btnMaintenance = new JButton(Base.MAINTENANCE_LABEL);
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmMain.dispose();
				//new ÃaintenanceView();
				new PasswordInput(frmMain, Base.MAINTENANCE);
			}
		});
		btnMaintenance.setBackground(new Color(51, 153, 204));
		btnMaintenance.setFont(new Font("Century Gothic", Font.BOLD, 22));
		btnMaintenance.setBounds(275, 645, 400, 90);
		frmMain.getContentPane().add(btnMaintenance);
		
		JButton btnStatistics = new JButton("—Ú‡ÚËÒÚËÍ‡");
		btnStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frmMain.dispose();
				//new AllDowntimeView();
				new PasswordInput(frmMain, Base.STATISTICS);
			}
		});
		btnStatistics.setBackground(new Color(176, 196, 222));
		btnStatistics.setFont(new Font("Century Gothic", Font.BOLD, 22));
		btnStatistics.setBounds(710, 645, 400, 90);
		frmMain.getContentPane().add(btnStatistics);
		
		ImageIcon logoIcon = new ImageIcon(Base.logoWhite);
		JLabel lblLogo = new JLabel(logoIcon);
		lblLogo.setBounds(713, 255, 46, 14);
		frmMain.getContentPane().add(lblLogo);
		
		JSVGCanvas svgCanvas = new JSVGCanvas();
		StringBuilder sb = new StringBuilder();
		sb.append("file:/");
		sb.append(Base.logoWhite);
		
		svgCanvas.setURI(sb.toString());
		//svgCanvas.setSize(300, 80);
		svgCanvas.setBounds(450, 250, 505, 150);
		svgCanvas.setBackground(new Color(255,255,255,0));
		
		frmMain.getContentPane().add(svgCanvas);
		
		JLabel lblTopic = new JLabel("—»—“≈Ã¿ «¿ ”œ–¿¬À≈Õ»≈ Õ¿ Õ≈œ–Œƒ” “»¬ÕŒ ¬–≈Ã≈");
		lblTopic.setFont(new Font("Century Gothic", Font.BOLD, 36));
		lblTopic.setForeground(new Color(255,255,255));
		lblTopic.setBounds(204, 460, 992, 45);
		frmMain.getContentPane().add(lblTopic);

		ImageIcon imageIcon = new ImageIcon(Base.backgroundPic);
		frmMain.getContentPane().setLayout(null);
		JLabel lblBackground = new JLabel(imageIcon);
		lblBackground.setBounds(0, 0, 1384, 787);
		frmMain.getContentPane().add(lblBackground);
		
		frmMain.setVisible(true);
	}
}
