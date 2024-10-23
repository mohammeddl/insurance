<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="${pageContext.request.contextPath}/insurance/utilisateur/register" method="post">
        Name: <input type="text" name="nom" /><br>
        Address: <input type="text" name="adresse" /><br>
        Email: <input type="email" name="email" /><br>
        Phone: <input type="text" name="telephone" /><br>
        Password: <input type="password" name="password" /><br>
        <input type="submit" value="Register" />
    </form>

    <p>${message}</p>
</body>
</html>
