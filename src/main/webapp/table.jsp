<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
	String id = request.getParameter("userId");
	String driverName = "org.postgresql.Driver";
	String connectionUrl = "jdbc:postgresql://bellinfo.cn3xobiomhsd.us-east-1.rds.amazonaws.com:5432/";
	String dbName = "BellinfoDB";
	String userId = "postgres";
	String password = "Welcome12#";
	try {
		Class.forName(driverName);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>
<h2 align="center">
	<font><strong>Retrieve data from database in jsp</strong></font>
</h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
	<tr>
	</tr>
	<tr bgcolor="#A52A2A">
		<td><b>id</b></td>
		<td><b>cname</b></td>
		<td><b>ProductName</b></td>
		<td><b>Cost</b></td>
		<td><b>Discount</b></td>
		<td><b>TransactionType</b></td>
		<td><b>Owner</b></td>
	</tr>
	<%	%>
	<%
		try {
			connection = DriverManager.getConnection(connectionUrl + dbName, userId, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM Invoice";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) { 	
	%>
	<tr bgcolor="#DEB887">
		<td><%=resultSet.getInt("id")%></td>
		<td><%=resultSet.getString("cname")%></td>
		<td><%=resultSet.getString("product_name")%></td>
		<td><%=resultSet.getDouble("cost")%></td>
		<td><%=resultSet.getDouble("discount")%></td>
		<td><%=resultSet.getString("transaction_type")%></td>
		<td><%=resultSet.getString("owner")%></td>
	</tr>
	<%
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</table>