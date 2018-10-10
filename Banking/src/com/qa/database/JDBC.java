package com.qa.database;

import java.awt.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
	
	static final String DRIVER = "com.mysql.jdbc.Driver";
	
	public static void main(String[] args)
	{
		Connection connection = null;
		Statement statement = null;
		try
		{
			Class.forName(DRIVER);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlapp","root","root");
			statement =  connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM mysqlapp.students");
			
			while(result.next())
			{
				System.out.println("ID: "+result.getInt("id"));
				System.out.println("Name: "+result.getString("name"));
				System.out.println("Mark: "+result.getInt("mark"));
			}
			result.close();
			statement.close();
			connection.close();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}