package com.bellinfo.invoice;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bellinfo.invoice.InvoiceStructure;

public class InvoiceDemo extends HttpServlet {
	Statement stmt = null;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	          throws ServletException, IOException {
		  	
	 }

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String cname = req.getParameter("cname");
		String productname = req.getParameter("productname");
		double cost = Double.parseDouble(req.getParameter("cost"));
		double discount = Double.parseDouble(req.getParameter("discount"));
		String transactiontype = req.getParameter("transactiontype");
		String owner = req.getParameter("owner");
		InvoiceStructure is = new InvoiceStructure();
		is.createInvoice();
		int value = 0;
		value = is.insertInvoice(id, cname, productname, cost, discount, transactiontype, owner);
		if (value > 0) {
			req.setAttribute("Status", "Success");
		} else {
			req.setAttribute("Status", "Failed");
		}
		/*try {
			req.setAttribute("sqltable", is.selectInvoice());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			
		
		RequestDispatcher rd = req.getRequestDispatcher("table.jsp");
		rd.forward(req, res);
		//doGet(req,res);
	}
}
