<html>
<body style="background-color: powderblue;">
	<h3>Welcome To Bell-Info INVOICE</h3>
	<form action="un.do" method="post">
	Customer Id: <br>
		<input type="text" name="id"></br>
		Customer Name: <br>
		<input type="text" name="cname"> </br>
		Product Name:<br>
		 <input type="text" name="productname"> </br> 
		  Cost: <br>
		 <input type="text" name="cost"></br>
		 Discount:<br> 
		 <input type="text" name="discount"></br>
		 <label> Choose the Type of Transaction:<br>
		  <input list="transactiontype" name="transactiontype"/>
		<datalist id="transactiontype">
			<option value="Card">
			<option value="Cash">
			<option value="Cheque">
		</datalist> 
		<br>
		<lable><B>Are you the owner</B> 
		<input type="radio" checked="checked" name="owner" value="Yes">Yes
		<input type="radio" checked="checked" name="owner" value="No">No
		</label></br>
		 <button type="submit">Submit</button>
		
		 
	</form>
	
	
</body>
</html>


