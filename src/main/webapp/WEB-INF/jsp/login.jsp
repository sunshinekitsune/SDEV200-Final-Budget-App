<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Zero Based Budget</title>
</head>
<body style="font-family: sans-serif; max-width: 400px; margin: 40px auto; padding: 0 10px;">
<h2>Zero-Based Budget App</h2>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>
<fieldset style="margin-bottom: 24px;">
    <legend>
        <strong>Sign In</strong>
    </legend>
    <form action="/login" method="post">
        <p>
            <label>Username:</label>
            <br/>
            <input type="text" name="username" required style="width: 100%; box-sizing: border-box;"/>
        </p>
        <p>
            <label>Password:</label>
            <br/>
            <input type="password" name="password" required style="width: 100%; box-sizing: border-box;"/>
        </p>
        <button type="submit">Sign In</button>
    </form>
</fieldset>
<fieldset>
    <legend>
        <strong>New User? Register</strong>
    </legend>
    <form action="/register" method="post">
        <p>
            <label>New Username:</label>
            <br/>
            <input type="text" name="username" required style="width: 100%; box-sizing: border-box;"/>
        </p>
        <p>
            <label>New Password:</label>
            <br/>
            <input type="password" name="password" required style="width: 100%; box-sizing: border-box;"/>
        </p>
        <button type="submit">Create Account</button>
    </form>
</fieldset>
</body>
</html>