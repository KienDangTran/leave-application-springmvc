package com.giong.test.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DatabaseExportSample {

	private static final String EXPORT_PATH_PREFIX = "src/test/resources/";
	private static final String EXPORT_FILE_NAME = "fullTestData.xml";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/leave_application_db";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWD = "Thuy?trieud0?";

	public static void main(String[] args) throws Exception {
		// database connection
		Class driverClass = Class.forName("com.mysql.jdbc.Driver");
		Connection jdbcConnection = DriverManager.getConnection(DatabaseExportSample.JDBC_URL, DatabaseExportSample.JDBC_USER, DatabaseExportSample.JDBC_PASSWD);
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// partial database export
		//		QueryDataSet partialDataSet = new QueryDataSet(connection);
		//		partialDataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
		//		partialDataSet.addTable("BAR");
		//		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partialTestData.xml"));

		// full database export
		IDataSet fullDataSet = connection.createDataSet();
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream(DatabaseExportSample.EXPORT_PATH_PREFIX + DatabaseExportSample.EXPORT_FILE_NAME));

		// dependent tables database export: export table X and all tables that
		// have a PK which is a FK on X, in the right order for insertion
		//		String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, "X");
		//		IDataSet depDataset = connection.createDataSet(depTableNames);
		//		FlatXmlDataSet.write(depDataset, new FileOutputStream("dependentTestData.xml"));

	}
}
