<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef;
            margin: 0;
            padding: 20px;
        }
        .profile-container {
            max-width: 600px;
            background: #fff;
            padding: 30px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 8px;
        }
        .profile-container h2 {
            text-align: center;
            color: #007bff;
        }
        .profile-info {
            margin: 20px 0;
        }
        .profile-info p {
            font-size: 18px;
            margin: 10px 0;
            color: #333;
        }
        .logout {
            display: block;
            width: 100px;
            margin: 20px auto;
            padding: 10px;
            text-align: center;
            background: #dc3545;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout:hover {
            background: #c82333;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h2>User Profile</h2>
        
        <%-- ✅ Check if the user is logged in --%>
        <%
            if (session.getAttribute("id") == null) {
                response.sendRedirect("login.html"); // Redirect to login page if session is empty
            }
        %>

        <div class="profile-info">
            <p><strong>ID:</strong> <%= session.getAttribute("id") %></p>
            <p><strong>Name:</strong> <%= session.getAttribute("user") %></p>
            <p><strong>Email:</strong> <%= session.getAttribute("email") %></p>
            <p><strong>Phone:</strong> <%= session.getAttribute("phone") %></p>
            <p><strong>City:</strong> <%= session.getAttribute("city") %></p>
            <p><strong>Age:</strong> <%= session.getAttribute("age") %></p>
            <p><strong>Country:</strong> <%= session.getAttribute("country") %></p>
            <p><strong>Date of Birth:</strong> <%= session.getAttribute("dob") %></p>
        </div>
        
        <a class="logout" href="logout">Logout</a>
    </div>
</body>
</html>
