package com.qa.bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField amount;
	private JTextField textField_1;
	private Connection connection;
	private Statement statement;
	private static int account = 0;

	/**
	 * Create the frame.
	 * @param statement 
	 * @param connection 
	 * @param lastAcc 
	 */
	public Withdraw(JFrame main, Connection connection, Statement statement, int lastAcc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{55, 242, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 31, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		this.connection = connection;
		this.statement = statement;
		account = lastAcc;
		
		JLabel label = new JLabel("Amount:");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		contentPane.add(label, gbc_label);
		
		amount = new JTextField();
		amount.setColumns(10);
		GridBagConstraints gbc_amount = new GridBagConstraints();
		gbc_amount.anchor = GridBagConstraints.NORTH;
		gbc_amount.fill = GridBagConstraints.HORIZONTAL;
		gbc_amount.insets = new Insets(0, 0, 5, 0);
		gbc_amount.gridx = 1;
		gbc_amount.gridy = 0;
		contentPane.add(amount, gbc_amount);
		
		JLabel label_1 = new JLabel("Balance:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		contentPane.add(label_1, gbc_label_1);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				setVisible(false);
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.NORTH;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		contentPane.add(button, gbc_button);
		
		JButton button_1 = new JButton("Withdraw");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw();
			}

		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 3;
		contentPane.add(button_1, gbc_button_1);
	}
	
	private void withdraw() {
		try {
			statement.execute("INSERT INTO `banking`.`withdrawals` (`AccountNumber`, `Amount`, `Date`) VALUES ('"+account+"', '"+amount.getText()+"', '"+genDate()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Date genDate() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		return date;
	}

}
