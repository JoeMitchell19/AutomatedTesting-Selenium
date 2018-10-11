package com.qa.bank;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;
	private static Main frame;
	private JTextArea currentAccountDetails;
	private Connection connection = null;
	private Statement statement = null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	public static int account = 0;
	private JTextField retrieveAccID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e)
			{
				retrieveLastAcc();
				displayLastAccount();
			}
		});
	
		setTitle("QA Bank - The Original Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		dbConn();
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{113, 180, 0};
		gbl_contentPane.rowHeights = new int[]{23, 23, 23, 23, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton button = new JButton("Create Account");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccount createAccount = new CreateAccount(frame,connection,statement);
				createAccount.setVisible(true);
				frame.setVisible(false);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 0;
		contentPane.add(button, gbc_button);
		
		currentAccountDetails = new JTextArea();
		GridBagConstraints gbc_currentAccountDetails = new GridBagConstraints();
		gbc_currentAccountDetails.insets = new Insets(0, 0, 5, 0);
		gbc_currentAccountDetails.fill = GridBagConstraints.BOTH;
		gbc_currentAccountDetails.gridheight = 4;
		gbc_currentAccountDetails.gridx = 1;
		gbc_currentAccountDetails.gridy = 0;
		contentPane.add(currentAccountDetails, gbc_currentAccountDetails);
		
		JButton button_2 = new JButton("Withdraw Money");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Withdraw withdrawMoney = new Withdraw(frame,connection,statement,account);
				withdrawMoney.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JButton button_1 = new JButton("Deposit Money");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositMoney depositMoney = new DepositMoney(frame,connection,statement,account);
				depositMoney.setVisible(true);
				frame.setVisible(false);
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 1;
		contentPane.add(button_1, gbc_button_1);
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 0;
		gbc_button_2.gridy = 2;
		contentPane.add(button_2, gbc_button_2);
		
		JButton button_3 = new JButton("View Statement");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatementPage statementGUI = new StatementPage(frame,connection,statement,account);
				statementGUI.setVisible(true);
				frame.setVisible(false);
			}
		});
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.anchor = GridBagConstraints.NORTH;
		gbc_button_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 0;
		gbc_button_3.gridy = 3;
		contentPane.add(button_3, gbc_button_3);
		
		JButton btnRetrieveAccount = new JButton("Retrieve Account");
		btnRetrieveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				try {
					id = Integer.valueOf(retrieveAccID.getText());
					retrieveAccID.setText("");
				} catch (Exception e2) {
					retrieveAccID.setText("Error");
					id = account;
				}
				
				System.out.println(id);
				
				if(retrieveAccID.getText().equals(""))
				{
					retrieveAcc(account, false);
				}
				else
				{
					retrieveAcc(id, false);
					displayLastAccount();
				}
			}
		});
		GridBagConstraints gbc_btnRetrieveAccount = new GridBagConstraints();
		gbc_btnRetrieveAccount.insets = new Insets(0, 0, 0, 5);
		gbc_btnRetrieveAccount.gridx = 0;
		gbc_btnRetrieveAccount.gridy = 4;
		contentPane.add(btnRetrieveAccount, gbc_btnRetrieveAccount);
		
		retrieveAccID = new JTextField();
		GridBagConstraints gbc_retrieveAccID = new GridBagConstraints();
		gbc_retrieveAccID.fill = GridBagConstraints.HORIZONTAL;
		gbc_retrieveAccID.gridx = 1;
		gbc_retrieveAccID.gridy = 4;
		contentPane.add(retrieveAccID, gbc_retrieveAccID);
		retrieveAccID.setColumns(10);
		displayLastAccount();
	}
	
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
	
	private void displayLastAccount() {
		dbConn();
		ResultSet result = null;
		try {
			result = statement.executeQuery("SELECT * FROM banking.accounts where AccountNumber = "+account);
			String data = "";
			while(result.next())
			{
				data += "Account Number: "+String.valueOf(result.getInt("AccountNumber")+"\n");
				data += "First Name: "+result.getString("FirstName")+"\n";
				data += "Last Name: "+result.getString("LastName")+"\n";
				data += "Address: "+result.getString("Address")+"\n";
				data += "E-mail: "+result.getString("E-mail")+"\n";
				data += "Phone: "+result.getString("Phone")+"\n";
			}
			currentAccountDetails.setText(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int retrieveLastAcc() {
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
		account = a;
		return a;
	}
	private int retrieveAcc(int id, boolean stat)
	{
		if (id==0&&stat)
		{
			return retrieveLastAcc();
		}
		account = id;
		return id;
	}
}
