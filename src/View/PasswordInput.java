package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.Base;
import javax.swing.JPasswordField;

public class PasswordInput extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtPassword;
	private String password = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			PasswordInput dialog = new PasswordInput();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public PasswordInput(JFrame frame, String sourceButton) {
		setResizable(false);
		setTitle(Base.FRAME_CAPTION);
		setFont(Base.DEFAULT_FONT);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		setLocationRelativeTo(frame);
		Image frameIcon = Toolkit.getDefaultToolkit().getImage(Base.icon);
		setIconImage(frameIcon);
		contentPanel.setBackground(new Color(70, 130, 180));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		switch (sourceButton) {
		case Base.MAINTENANCE:
			password = Base.maintenancePassword;
			break;
		case Base.STATISTICS:
			password = Base.statisticsPassword;
			break;

		default:
			break;
		}
		
		{
			txtPassword = new JPasswordField();
			txtPassword.setBounds(153, 116, 100, Base.ELEMENT_HEIGHT);
			contentPanel.add(txtPassword);
			txtPassword.setColumns(10);
			txtPassword.setFont(Base.DEFAULT_FONT);
			txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
		{
			JLabel lblCaption = new JLabel("Моля, въведете парола");
			lblCaption.setBounds(98, 63, 210, 21);
			lblCaption.setFont(Base.DEFAULT_FONT);
			lblCaption.setForeground(new Color(255, 255, 255));
			contentPanel.add(lblCaption);
		}
		{
			ImageIcon imageIcon = new ImageIcon(Base.backgroundPic);
			JLabel lblNewLabel = new JLabel(imageIcon);
			lblNewLabel.setBounds(0, 0, 450, 300);
			contentPanel.add(lblNewLabel);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (String.valueOf(txtPassword.getPassword()).equals(password)) {
							frame.dispose();
							dispose();
							
							switch (sourceButton) {
							case Base.MAINTENANCE:
								new МaintenanceView();
								break;
							case Base.STATISTICS:
								new AllDowntimeView();
								break;

							default:
								break;
							}
							
						} else {
							txtPassword.setText("");
							txtPassword.requestFocus();
							JOptionPane.showMessageDialog(null, "Грешна парола", "Грешка",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				okButton.setFont(Base.DEFAULT_FONT);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setFont(Base.DEFAULT_FONT);
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
		
		setVisible(true);
	}
}
