package com.goodwillcis.lcp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	public Connection conn = null;

	public Statement stat = null;

	public ResultSet rs = null;

	public Connection getConnection() {

		Properties prop = new Properties();

		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		System.out.println("in-------"+in);

		try {

			prop.load(in);

			Class.forName(prop.getProperty("Db_Driver").trim());

			conn = DriverManager.getConnection(prop.getProperty("Db_ConnString")
					.trim(),

			prop.getProperty("Db_User").trim(),

			prop.getProperty("Db_Password").trim()

			);

			return conn;

		} catch (Exception e) {
			System.out.println(e.toString());

			return null;

		}

	}
       
}
