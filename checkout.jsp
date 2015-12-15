<html>
 <head><title>Checkout Page</title></head>
 <body>
  <link rel="stylesheet" href="css/styles.css">
   <h2>Check Out Page</h2>
    <form method="POST" action="Confirmation.jsp">
      <table cellpadding="0" cellspacing="5">
        <tr><td colspan="2"><b>Name Information</b></td></tr>
        <tr>
          <td>First Name</td><td><input type="text" name="FirstName" size="20"/></td>
        </tr>
        
        <tr>
          <td>Last Name</td><td><input type="text" name="LastName" size="30" /></td>
        </tr>
        <tr>
          <td>Email</td><td><input type="text" name="EmailId" size="30" /></td>
        </tr>
        <tr><td colspan="2"><hr /></td></tr>
        <tr><td colspan="2"><b>Address Information</b></td></tr>
        <tr>
          <td>Address</td><td><input type="text" name="Address" size="50" /></td>
        </tr>
        <tr>
          <td>City</td><td><input type="text" name="City" size="30" /></td>
        </tr>
        <tr>
          <td>State</td>
          <td>
            <input type="text" name="State" size="15">
           
            ZIP :<input type="text" name="Zip" size="5"/>
          </td>
        </tr>
        <hr>
 <tr><td colspan="2"><b>Credit Card Information</b></td></tr>
        <tr>
          <td>Credit Card No :</td><td><input type="text" name="CreditCardNumber" size="16"/></td>
        </tr>
 <tr>
      <td>CVV :</td><td><input type="text" name="Cvv" size="5"/></td>
 </tr>
        
        <tr><td colspan="2"><hr /></td></tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <input type="submit" value="Pay" />
            <input type="hidden" name="page_name" value="login" />
          </td>
        </tr>
      </table>
    </form>
 </body>
</html>