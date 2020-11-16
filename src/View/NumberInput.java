package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Base;
import Controller.BaseMethods;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class NumberInput extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEnterNumber;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			NumberInput dialog = new NumberInput();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public NumberInput(JFrame frame) {
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
		getRootPane().setBorder( BorderFactory.createLineBorder(Color.WHITE) );
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtEnterNumber = new JTextField();
			txtEnterNumber.setBounds(153, 116, 100, Base.ELEMENT_HEIGHT);
			contentPanel.add(txtEnterNumber);
			txtEnterNumber.setColumns(10);
			txtEnterNumber.setFont(Base.DEFAULT_FONT);
			txtEnterNumber.setBorder( BorderFactory.createLineBorder(Color.RED));
		}
		{
			JLabel lblCaption = new JLabel("Моля, въведете номер:");
			lblCaption.setBounds(98, 63, 210, 21);
			lblCaption.setFont(Base.DEFAULT_FONT);
			lblCaption.setForeground(new Color(255,255,255));
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
			buttonPane.setLocation(-14, 228);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(Base.DEFAULT_FONT);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (BaseMethods.CheckIsNumber(txtEnterNumber.getText())) {
							Boolean existsInDt = BaseMethods.CheckIfDowntimeNumberExists(txtEnterNumber.getText());
							Boolean existsInAction = BaseMethods.CheckIfActionNumberExists(txtEnterNumber.getText());
							if (existsInDt & !existsInAction) {
								frame.dispose();
								dispose();
								new DowntimeView(txtEnterNumber.getText());
							} else {
								txtEnterNumber.setText("");
								txtEnterNumber.requestFocus();
							}
						} else {
							txtEnterNumber.setText("");
							txtEnterNumber.requestFocus();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(Base.DEFAULT_FONT);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		setVisible(true);
	}

}
