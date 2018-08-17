package com.heyden.teamrelationmanager.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/team-relation-manager?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String password = "hbstudent";
		try {
			System.out.println("Connecting to DB");
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("Connection successful!!!!!!!!!\n" + myConnection.toString());
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}