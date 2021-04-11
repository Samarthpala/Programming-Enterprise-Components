<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

.navbar {
  overflow: hidden;
  background-color: #333; 
}

.navbar a {
  float: left;
  font-size: 16px;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.subnav {
  float: left;
  overflow: hidden;
}

.subnav .subnavbtn {
  font-size: 16px;  
  border: none;
  outline: none;
  color: white;
  padding: 14px 16px;
  background-color: inherit;
  font-family: inherit;
  margin: 0;
}

.navbar a:hover, .subnav:hover .subnavbtn {
  background-color: red;
}

.subnav-content {
  display: none;
  position: absolute;
  left: 0;
  background-color: red;
  width: 100%;
  z-index: 1;
}

.subnav-content a {
  float: left;
  color: white;
  text-decoration: none;
}

.subnav-content a:hover {
  background-color: #eee;
  color: black;
}

.subnav:hover .subnav-content {
  display: block;
}
</style>
</head>
<body>

<div class="navbar">
  <a href="home.jsp">Home</a>
  <div class="subnav">
    <button class="subnavbtn">User <i class="fa fa-caret-down"></i></button>
    <div class="subnav-content">
      <a href="user-form.jsp">New</a>
      <a href="user">List</a>
    </div>
  </div> 
  <div class="subnav">
    <button class="subnavbtn">Account <i class="fa fa-caret-down"></i></button>
    <div class="subnav-content">
      <a href="account-form.jsp">New</a>
      <a href="account">List</a>
      <a href="transfer">Transfer</a>
    </div>
  </div> 
  <div class="subnav">
    <button class="subnavbtn">Transaction <i class="fa fa-caret-down"></i></button>
    <div class="subnav-content">
      <a href="transaction">History</a>
    </div>
  </div>
</div>
</body>
</html>