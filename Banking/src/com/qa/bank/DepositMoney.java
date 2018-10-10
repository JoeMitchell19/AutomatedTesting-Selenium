package com.qa.bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class DepositMoney extends JFrame {

	private JPanel contentPane;
	private JTextField amount;
	private JTextField balance;
	private Connection connection = null;
	private Statement statement = null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static int account = 0;
	
	private void updateAccountID() {
		account = Main.account;
	}

	/**
	 * Create the frame.
	 * @param lastAcc 
	 * @param statement2 
	 * @param connection2 
	 */
	public DepositMoney(JFrame main, Connection connection, Statement statement, int lastAcc) {
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e)
			{
				//displayBalance();
				updateAccountID();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{55, 357, 0};
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
		
		balance = new JTextField();
		balance.setEditable(false);
		balance.setColumns(10);
		GridBagConstraints gbc_balance = new GridBagConstraints();
		gbc_balance.anchor = GridBagConstraints.NORTH;
		gbc_balance.fill = GridBagConstraints.HORIZONTAL;
		gbc_balance.insets = new Insets(0, 0, 5, 0);
		gbc_balance.gridx = 1;
		gbc_balance.gridy = 1;
		contentPane.add(balance, gbc_balance);
		
		JButton depositButton = new JButton("Deposit");
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Deposit();
			}
		});
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_backButton = new GridBagConstraints();
		gbc_backButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_backButton.insets = new Insets(0, 0, 0, 5);
		gbc_backButton.gridx = 0;
		gbc_backButton.gridy = 3;
		contentPane.add(backButton, gbc_backButton);
		GridBagConstraints gbc_depositButton = new GridBagConstraints();
		gbc_depositButton.anchor = GridBagConstraints.NORTH;
		gbc_depositButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_depositButton.gridx = 1;
		gbc_depositButton.gridy = 3;
		contentPane.add(depositButton, gbc_depositButton);
	}
	
	private Date genDate() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		return date;
	}
	
	private void Deposit() {
		try {
			statement.execute("INSERT INTO `banking`.`deposits` (`AccountNumber`, `Amount`, `Date`) VALUES ('"+account+"', '"+amount.getText()+"', '"+genDate()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
