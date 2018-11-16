<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Marketplace</title>

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/shop-homepage.css" rel="stylesheet">
  <link href="css/login.css" rel="stylesheet">
  <link href="css/util.css" rel="stylesheet">

  <!-- Bootstrap core JavaScript -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js"></script>

  <!-- JavaScript files -->
  <script src="js/util.js"></script>
  <script src="js/login.js"></script>
  <script src="js/auth.js"></script>

</head>

<script>
    $(document).ready(function () {
        loginLogoutToggle();

        var userInfo;
        var userLabel = ["Email", "Firstname", "Lastname", "Date of birth", "Gender", "Username", "Contact", "Address",
        "Postal code", "Country"];
        var str = "";
        $.get("userProfile", function(output) {
            if (output != "-1") {
                userInfo = output;
                var dateFragment = userInfo.dateOfBirth.split(" ");
                var dateOfBirth = dateFragment[0] + " " + dateFragment[1] + " " + dateFragment[2];
                var userPlaceholder = [userInfo.email, userInfo.firstname, userInfo.lastname, dateOfBirth, userInfo.gender,
                    userInfo.username, userInfo.contact, userInfo.address, userInfo.postalCode, userInfo.country];

                for (var i = 0; i < userLabel.length; i++) {
                    str += '<li class="list-group-item w-25">';
                    str += userLabel[i];
                    str += '</li>';
                    str += '<li class="list-group-item w-75">';
                    str += userPlaceholder[i];
                    str += '</li>';
                }
            } else {
                str += "Please sign in first!";
            }
            $("#profileContainer").html(str);
        });

    });
</script>
<body>

  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="index.html">Marketplace</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive"
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link" href="userHome.jsp">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Services</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
          </li>
          <li class="nav-item">
            <a id="loginNav" class="nav-link" href="login.jsp">Login</a>
            <button type="button" id="logoutNav" class="btn btn-dark" onclick="logout()">Logout</button>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="card mx-auto mt-4 mb-4">
    <h5 class="card-header">Profile</h5>
    <div class="card-body">
      <ul id="profileContainer" class="list-group d-flex flex-row flex-wrap">
      </ul>
    </div>
  </div>

  <!-- Footer -->
  <footer class="footer py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
    </div>
    <!-- /.container -->
  </footer>
