<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Balance</title>
    <link rel="stylesheet" href="viewbalance_css.css">
    <link rel="stylesheet" href="bankheaderfot.css">
</head>
<body>

    <header class="header">
        <div class="container">
            <h1 class="logo">Global Bank</h1>
            <nav class="navbar">
                <ul>
                    <li><a href="bankhome.html" class="active">Home</a></li>
                    <li><a href="bankabout.html">About Us</a></li>
                    <li><a href="bankhome.html#services-section">Services</a></li>
                    <li><a href="bankabout.html#contact">Contact</a></li>
                    <li><a href="login.html" class="btn-login">Log Out</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <div class="summarycontainer">
        <h2>Account Summary</h2>

        <div class="info">
            <label for="username">User Name:</label>
            <span id="username">${username}</span>
        </div>

        <div class="info">
            <label for="balance">Available Balance:</label>
            <span id="balance" class="balance">&#8377;	${balance}</span>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; 2024 Global Bank. All rights reserved.</p>
        <p>123 Bank Street, Financial City, India</p>
    </footer>
</body>
</html>
    