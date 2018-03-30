package com.bellinfo.invoice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InvoiceStructure {
	Statement stmt = null;
	Connection con = null;
	private static final String CREATE_INVOICE_STRUCTURE = "CREATE TABLE INVOICE(id integer CONSTRAINT pmky PRIMARY KEY,cname varchar(10),product_name varchar(15),cost real,discount real,transaction_type varchar(10),owner varchar(10));";
	private static final String INSERT_INVOICE_RECORD = "INSERT INTO INVOICE VALUES(?,?,?,?,?,?,?);";
	private static final String SELECT_INVOICE_RECORD = "SELECT * FROM INVOICE;";

	private Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://bellinfo.cn3xobiomhsd.us-east-1.rds.amazonaws.com:5432/BellinfoDB", "postgres",
					"Welcome12#");

			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createInvoice() {

		try {
			con = getConnection();
			stmt = con.createStatement();
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "INVOICE", null);
			if (tables.next()) {
				System.out.println("Table already exsist..I'm skipping it");
			} else {
				boolean result = stmt.execute(CREATE_INVOICE_STRUCTURE);
				System.out.println("Table has been created successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public int insertInvoice(int id, String cname, String productname, double cost, double discount,
			String transactiontype, String owner) {
		con = getConnection();
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(INSERT_INVOICE_RECORD);
			ps.setInt(1, id);
			ps.setString(2, cname);
			ps.setString(3, productname);
			ps.setDouble(4, cost);
			ps.setDouble(5, discount);
			ps.setString(6, transactiontype);
			ps.setString(7, owner);
			result = ps.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String selectInvoice() throws SQLException {

		con = getConnection();
		String result = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_INVOICE_RECORD);
			System.out.println("Record From Database");
			result ="<table border='1' class=\"table table-striped\"><tr><th>ID</th> <th>CName</th> <th>ProductName</th> <th>Cost</th> <th>Discount</th> <th>Transaction Type</th><th>Owner</th>";
			while(rs.next()){
				result+="<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getDouble(4)+"</td><td>"+rs.getDouble(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7);
			}
			result+="</table>";
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

}
