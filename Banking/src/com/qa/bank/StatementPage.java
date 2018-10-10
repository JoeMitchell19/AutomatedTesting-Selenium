package com.qa.bank;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class StatementPage extends JFrame {

	private JPanel contentPane;
	private static Connection connection;
	private static Statement statement;
	private static int account = 0;
	private JTextArea stateMent;

	/**
	 * Create the frame.
	 * @param statement 
	 * @param connection 
	 * @param lastAcc 
	 */
	public StatementPage(JFrame main, Connection connection2, Statement statement2, int lastAcc) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{424, 0};
		gbl_contentPane.rowHeights = new int[]{228, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		connection = connection2;
		statement = statement2;
		account = lastAcc;
		
		stateMent = new JTextArea();
		GridBagConstraints gbc_stateMent = new GridBagConstraints();
		gbc_stateMent.fill = GridBagConstraints.BOTH;
		gbc_stateMent.insets = new Insets(0, 0, 5, 0);
		gbc_stateMent.gridx = 0;
		gbc_stateMent.gridy = 0;
		contentPane.add(stateMent, gbc_stateMent);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				setVisible(false);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTHWEST;
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		contentPane.add(button, gbc_button);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e)
			{
				updateStatement();
			}
		});
	}
	
	private void updateStatement() {
		try {
			String bankStatement = "";
			int amount = 0;
			ResultSet deposits = statement.executeQuery("SELECT * FROM banking.deposits where AccountNumber = "+account);
			
			while(deposits.next())
			{
				bankStatement += "Amount: "+deposits.getInt("Amount")+"\n";
				bankStatement +=  "Date: "+deposits.getDate("Date")+"\n";
				amount += deposits.getInt("Amount");
			}
			
			deposits.close();
			ResultSet withdraws = statement.executeQuery("SELECT * FROM banking.withdrawals where AccountNumber = "+account);
			
			while(withdraws.next())
			{
				bankStatement += "Amount: -"+withdraws.getInt("Amount")+"\n";
				bankStatement +=  "Date: "+withdraws.getDate("Date")+"\n";
				amount -= withdraws.getInt("Amount");
			}
			withdraws.close();
			stateMent.setText("Bank Statement: \n"+bankStatement+"\n"+"Balance: "+amount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
