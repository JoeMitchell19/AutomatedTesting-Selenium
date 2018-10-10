package com.qa.bank;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Application {

	private JFrame frmMainMenu;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField address;
	private JTextField email;
	private JTextField phone;
	private JTextField accountNumber;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}
	
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
			statement.execute("INSERT INTO `banking`.`accounts` (`AccountNumber`, `First Name`, `Last Name`, `Address`, `E-mail`, `Phone`) VALUES ('"+account+"', '"+firstName.getText()+"', '"+lastName.getText()+"', '"+address.getText()+"', '"+email.getText()+"', '"+phone.getText()+"')");
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
		System.out.println(a);
		return a;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		account = retrieveLastAcc();
		frmMainMenu = new JFrame();
		frmMainMenu.setTitle("Banking");
		frmMainMenu.setBounds(100, 100, 359, 246);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel home = new JPanel();
		frmMainMenu.getContentPane().add(home, "name_5514518203599265");
		
		final JPanel createAccount = new JPanel();
		frmMainMenu.getContentPane().add(createAccount, "name_5514524265997731");
		createAccount.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblFirstName = new JLabel("First Name: ");
		createAccount.add(lblFirstName);
		
		firstName = new JTextField();
		createAccount.add(firstName);
		firstName.setColumns(10);
		
		JLabel label_1 = new JLabel("");
		createAccount.add(label_1);
		
		JLabel lblAccountNumber = new JLabel("Account Number:");
		createAccount.add(lblAccountNumber);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		createAccount.add(lblLastName);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		createAccount.add(lastName);
		
		JLabel label_2 = new JLabel("");
		createAccount.add(label_2);
		
		accountNumber = new JTextField();
		accountNumber.setEditable(false);
		createAccount.add(accountNumber);
		accountNumber.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAcc();
			}
		});
		
		JLabel lblAddress = new JLabel("Address: ");
		createAccount.add(lblAddress);
		
		address = new JTextField();
		address.setColumns(10);
		createAccount.add(address);
		
		JLabel label_3 = new JLabel("");
		createAccount.add(label_3);
		
		JLabel label_4 = new JLabel("");
		createAccount.add(label_4);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		createAccount.add(lblEmail);
		
		email = new JTextField();
		email.setColumns(10);
		createAccount.add(email);
		
		JLabel label_5 = new JLabel("");
		createAccount.add(label_5);
		
		JLabel label_6 = new JLabel("");
		createAccount.add(label_6);
		
		JLabel lblPhone = new JLabel("Phone: ");
		createAccount.add(lblPhone);
		
		phone = new JTextField();
		phone.setColumns(10);
		createAccount.add(phone);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount.setVisible(false);
				home.setVisible(true);
			}
		});
		
		JLabel label_7 = new JLabel("");
		createAccount.add(label_7);
		
		JLabel label_8 = new JLabel("");
		createAccount.add(label_8);
		
		JLabel label_9 = new JLabel("");
		createAccount.add(label_9);
		
		JLabel label_10 = new JLabel("");
		createAccount.add(label_10);
		
		JLabel label_11 = new JLabel("");
		createAccount.add(label_11);
		
		JLabel label_12 = new JLabel("");
		createAccount.add(label_12);
		createAccount.add(btnBack);
		createAccount.add(btnCreate);
		
		JLabel label_13 = new JLabel("");
		createAccount.add(label_13);
		
		JLabel label_14 = new JLabel("");
		createAccount.add(label_14);
		
		final JPanel depositMoney = new JPanel();
		frmMainMenu.getContentPane().add(depositMoney, "name_5514527953141482");
		depositMoney.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblAmount = new JLabel("Amount:");
		depositMoney.add(lblAmount);
		
		textField_6 = new JTextField();
		depositMoney.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel label_15 = new JLabel("");
		depositMoney.add(label_15);
		
		JLabel lblBalance = new JLabel("Balance:");
		depositMoney.add(lblBalance);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositMoney.setVisible(false);
				home.setVisible(true);
			}
		});
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		depositMoney.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel label_16 = new JLabel("");
		depositMoney.add(label_16);
		depositMoney.add(button);
		
		JButton btnDeposit = new JButton("Deposit");
		depositMoney.add(btnDeposit);
		
		JLabel label_17 = new JLabel("");
		depositMoney.add(label_17);
		
		final JPanel withdraw = new JPanel();
		frmMainMenu.getContentPane().add(withdraw, "name_5514533488599996");
		withdraw.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblAmount_1 = new JLabel("Amount:");
		withdraw.add(lblAmount_1);
		
		textField_8 = new JTextField();
		withdraw.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel label_18 = new JLabel("");
		withdraw.add(label_18);
		
		JLabel label = new JLabel("Balance:");
		withdraw.add(label);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw.setVisible(false);
				home.setVisible(true);
			}
		});
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		withdraw.add(textField_9);
		
		JLabel label_19 = new JLabel("");
		withdraw.add(label_19);
		
		JLabel label_20 = new JLabel("");
		withdraw.add(label_20);
		
		JLabel label_21 = new JLabel("");
		withdraw.add(label_21);
		
		JLabel label_22 = new JLabel("");
		withdraw.add(label_22);
		withdraw.add(button_1);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		withdraw.add(btnWithdraw);
		
		JLabel label_23 = new JLabel("");
		withdraw.add(label_23);
		
		final JPanel statement = new JPanel();
		frmMainMenu.getContentPane().add(statement, "name_5514536628197108");
		statement.setLayout(null);
		
		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statement.setVisible(false);
				home.setVisible(true);
			}
		});
		button_2.setBounds(10, 173, 89, 23);
		statement.add(button_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 323, 151);
		statement.add(textArea);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				createAccount.setVisible(true);
				home.setVisible(false);
			}
		});
		home.setLayout(new GridLayout(0, 1, 0, 0));
		home.add(btnCreateAccount);
		
		JButton btnDepositMoney = new JButton("Deposit Money");
		btnDepositMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depositMoney.setVisible(true);
				home.setVisible(false);
			}
		});
		home.add(btnDepositMoney);
		
		JButton btnWihdrawMoney = new JButton("Withdraw Money");
		btnWihdrawMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw.setVisible(true);
				home.setVisible(false);
			}
		});
		home.add(btnWihdrawMoney);
		
		JButton btnViewStatement = new JButton("View Statement");
		btnViewStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statement.setVisible(true);
				home.setVisible(false);
			}
		});
		home.add(btnViewStatement);
		
		
	}
}
