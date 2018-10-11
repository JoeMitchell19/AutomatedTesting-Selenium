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
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.qa.bank.domain.Transaction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DropMode;

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
		
		connection = connection2;
		statement = statement2;
		account = lastAcc;
		
		stateMent = new JTextArea();
		JScrollPane scrolll = new JScrollPane(stateMent);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(true);
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrolll)
				.addComponent(button)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrolll, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(button))
		);
		contentPane.setLayout(gl_contentPane);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e)
			{
				updateStatement();
			}
		});
	}
	
	private void updateStatement() {
		Transaction transaction;
		try {
			String bankStatement = "";
			int amount = 0;
			
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			
			ResultSet deposits = statement.executeQuery("SELECT * FROM banking.deposits where AccountNumber = "+account);
			
			while(deposits.next())
			{
				transaction = new Transaction(deposits.getInt("Amount"), deposits.getDate("Date"));
				amount += deposits.getInt("Amount");
				transactions.add(transaction);
			}
			
			deposits.close();
			ResultSet withdraws = statement.executeQuery("SELECT * FROM banking.withdrawals where AccountNumber = "+account);
			
			while(withdraws.next())
			{
				transaction = new Transaction(-withdraws.getInt("Amount"), withdraws.getDate("Date"));

				amount -= withdraws.getInt("Amount");
				transactions.add(transaction);
			}
			withdraws.close();
			
			transactions.sort(Comparator.comparing(Transaction::getDate));
			
			for (Transaction transaction2 : transactions) {
				bankStatement += "Amount: "+transaction2.getAmount()+"\n";
				bankStatement +=  "Date: "+transaction2.getDate()+"\n\n";
			}
			
			stateMent.setText("Bank Statement for account "+account+": \n\n"+bankStatement+"\n"+"Balance: "+amount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
