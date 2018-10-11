package com.qa.bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField address;
	private JTextField email;
	private JTextField phone;
	private JTextField accountNumber;
	
	Connection connection = null;
	Statement statement = null;
	static final String DRIVER = "com.mysql.jdbc.Driver";
	private static int account = 0;
	
	private void dbConn() {
		try
		{
			Class.forName(DRIVER);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root");
			statement =  connection.createStatement();
			//result.close();
			//statement.close();
			//connection.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void createAcc()
	{
		try
		{
			dbConn();
			account++;
			statement.execute("INSERT INTO `banking`.`accounts` (`AccountNumber`, `FirstName`, `LastName`, `Address`, `E-mail`, `Phone`) VALUES ('"+account+"', '"+firstName.getText()+"', '"+lastName.getText()+"', '"+address.getText()+"', '"+email.getText()+"', '"+phone.getText()+"')");
			accountNumber.setText(String.valueOf(account));
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private int retrieveLastAcc() {
		int a = 0;
		dbConn();
		ResultSet result = null;
		try {
			result = statement.executeQuery("SELECT AccountNumber FROM banking.accounts ORDER BY AccountNumber DESC LIMIT 1");
			while(result.next())
			{
				a = result.getInt("AccountNumber");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * Create the frame.
	 */
	public CreateAccount(JFrame main, Connection connection, Statement statement) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{83, 329, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 20, 31, 23, 31, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		account = retrieveLastAcc();
		this.connection = connection;
		this.statement = statement;
		
		JLabel label = new JLabel("First Name: ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		contentPane.add(label, gbc_label);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		GridBagConstraints gbc_firstName = new GridBagConstraints();
		gbc_firstName.anchor = GridBagConstraints.NORTH;
		gbc_firstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstName.insets = new Insets(0, 0, 5, 0);
		gbc_firstName.gridx = 1;
		gbc_firstName.gridy = 0;
		contentPane.add(firstName, gbc_firstName);
		
		JLabel label_1 = new JLabel("Last Name: ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		contentPane.add(label_1, gbc_label_1);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		GridBagConstraints gbc_lastName = new GridBagConstraints();
		gbc_lastName.anchor = GridBagConstraints.NORTH;
		gbc_lastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastName.insets = new Insets(0, 0, 5, 0);
		gbc_lastName.gridx = 1;
		gbc_lastName.gridy = 1;
		contentPane.add(lastName, gbc_lastName);
		
		JLabel label_2 = new JLabel("Address: ");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		contentPane.add(label_2, gbc_label_2);
		
		address = new JTextField();
		address.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.NORTH;
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		contentPane.add(address, gbc_textField_2);
		
		JLabel label_3 = new JLabel("E-mail: ");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		contentPane.add(label_3, gbc_label_3);
		
		email = new JTextField();
		email.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.NORTH;
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 3;
		contentPane.add(email, gbc_textField_3);
		
		JLabel label_4 = new JLabel("Phone: ");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 4;
		contentPane.add(label_4, gbc_label_4);
		
		phone = new JTextField();
		phone.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.NORTH;
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 4;
		contentPane.add(phone, gbc_textField_4);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 6;
		contentPane.add(button, gbc_button);
		
		JButton button_1 = new JButton("Create");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAcc();
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 6;
		contentPane.add(button_1, gbc_button_1);
		
		JLabel label_5 = new JLabel("Account Number:");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 0, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 8;
		contentPane.add(label_5, gbc_label_5);
		
		accountNumber = new JTextField();
		accountNumber.setEditable(false);
		accountNumber.setColumns(10);
		GridBagConstraints gbc_accountNumber = new GridBagConstraints();
		gbc_accountNumber.anchor = GridBagConstraints.NORTH;
		gbc_accountNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_accountNumber.gridx = 1;
		gbc_accountNumber.gridy = 8;
		contentPane.add(accountNumber, gbc_accountNumber);
	}

}
