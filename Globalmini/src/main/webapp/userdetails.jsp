<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <link rel="stylesheet" href="bankheaderfot.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
        }

        .userd-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 700px;
            transition: transform 0.2s;
            margin-top: 80px; /* Adjust for fixed header */
        }

        .userd-container:hover {
            transform: scale(1.02);
        }

        .userd-container h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .user-details {
            margin-top: 20px;
            line-height: 1.8;
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-gap: 20px;
        }

        .user-details div {
            padding: 15px;
            border: 1px solid #e0e0e0;
            border-radius: 5px;
            background-color: #f9f9f9;
            transition: background-color 0.3s;
        }

        .user-details div:hover {
            background-color: #f0f0f0;
        }

        .user-details div span {
            font-weight: bold;
            color: #0055a5;
        }

        .user-details .full-width {
            grid-column: span 2;
        }

        .toggle-btn {
            display: block;
            margin: 20px auto 0;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .toggle-btn:hover {
            background-color: #0056b3;
        }
    </style>
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

    <div class="userd-container">
        <h1>USER DETAILS</h1>
        <div class="user-details">
            <div class="full-width"><span>Full Name:</span> <%= request.getAttribute("firstname") %> <%= request.getAttribute("lastname") %></div>
            <div><span>Date of Birth:</span> <%= request.getAttribute("bod") %></div>
            <div><span>Account Number:</span> <%= request.getAttribute("accountno") %></div>
            <div><span>Email:</span> <%= request.getAttribute("email") %></div>
            <div><span>Password:</span> <span id="password">********</span></div>
            <div><span>PIN:</span> <span id="pin">****</span></div>
            <div><span>Phone Number:</span> <%= request.getAttribute("phoneno") %></div>
            <div><span>Address:</span> <%= request.getAttribute("address") %></div>
            <div><span>District:</span> <%= request.getAttribute("district") %></div>
            <div><span>State:</span> <%= request.getAttribute("state") %></div>
            <div><span>Pincode:</span> <%= request.getAttribute("pincode") %></div>
        </div>

        <!-- Button to toggle password and PIN visibility -->
        <button class="toggle-btn" onclick="togglePasswordVisibility()">Show/Hide Password and PIN</button>
    </div>

    <script>
        function togglePasswordVisibility() {
            const passwordField = document.getElementById('password');
            const pinField = document.getElementById('pin');

            if (passwordField.textContent === '********') {
                passwordField.textContent = 'mypassword123';  // Replace with actual password
                pinField.textContent = '4567';  // Replace with actual PIN
            } else {
                passwordField.textContent = '********';
                pinField.textContent = '****';
            }
        }
    </script>

    <footer class="footer">
        <p>&copy; 2024 Global Bank. All rights reserved.</p>
        <p>123 Bank Street, Financial City, India</p>
    </footer>

</body>
</html>
