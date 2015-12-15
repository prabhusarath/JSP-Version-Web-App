<!DOCTYPE html>
<html lang="en"> 
<head>
  
  <title>Login Form</title>
  <link rel="stylesheet" href="css/login.css">
</head>
<body style="background-image:url(images/tr.jpg)">
  <section class="container">
    <div class="login">
      <h1>E PLAY - Manager </h1>
      <form method="post" action="checkmanager.jsp">
        <p><input type="text" name="userid" placeholder="Username or Email"></p>
        <p><input type="password" name="password" placeholder="Password"></p>
        <p class="submit"><input type="submit" name="commit" value="Login"></p>
      </form>
    </div>
  </section>

</body>
</html>

