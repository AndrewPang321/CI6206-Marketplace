<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "database.User"%>
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

</head>

<script>
    $(document).ready(function () {
      $("#UserProfile").submit(function (event) {
        var form = $(this);

        $.ajax({
          url: "userProfile",
          type: "GET",
          data: form.serialize(),
          beforeSend: function () {
            $(".loader").css("display", "block");
          },
          success: function () {
            console.log("success");
          },
          error: function () {
            console.log("failure");
          },
          complete: function (res) {
            console.log(res.status);
            switch (res.status) {
              case 200:
                showAlert({
                  message: 'Making Profile Changes. Redirect in 1 second',
                  class: 'success'
                });
                setTimeout(function () {
                  // window.location.replace("index.html")
                  window.location.replace("EditUserProfile.jsp")
                }, 1500);
                break;
              case 400:
                showAlert({
                  message: 'Incorrect Email/Password',
                  class: 'danger'
                });
                break;
              default:
                showAlert({
                  message: 'Some problems occur. Please try again',
                  class: 'danger'
                });
            }
            $(".loader").css("display", "none");
          }
        });
        return false;
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
          <li class="nav-item active">
            <a class="nav-link" href="userProfile.jsp">Profile
              <span class="sr-only">(current)</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="loginContainer">
    <div class="loader"></div>
    <div class="card text-center mx-auto">
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
          <li class="nav-item">
            <a class="nav-link active" data-toggle="tab"> Profile</a>
          </li>
        </ul>
      </div>
      <div class="card-body">
        <div class="tab-content">
          <div class="tab-pane fade" id="UserProfile">
            <form id="UserProfile">
              <div class="form-group row">
                <label for="signupEmail" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="signupEmail" name="signupEmail" value = "${email}" disabled>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupPassword" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                  <input type=hidden class="form-control" id="signupPassword" name="signupPassword" placeholder="Private" disabled>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupConfirmPassword" class="col-sm-2 col-form-label">Confirm Password</label>
                <div class="col-sm-10">
                  <input type="hidden" class="form-control" id="signupConfirmPassword" name="signupConfirmPassword" placeholder="Private"
                         disabled>
                </div>
              </div>
              <hr>
              <div class="form-group row">
                <label for="signupFirstName" class="col-sm-2 col-form-label">Firstname</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="signupFirstName" name="signupFirstName" value = "${firstname}" disabled>
                </div>
                <label for="signupLastName" class="col-sm-2 col-form-label">Lastname</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="signupLastName" name="signupLastName" value = "${lastname}" disabled>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupDateOfBirth" class="col-sm-2 col-form-label">Date of birth</label>
                <div class="col-sm-4">
                  <input type="date" class="form-control" id="signupDateOfBirth" name="signupDateOfBirth" value = "${dateOfBirth}" disabled>
                </div>
                <label for="signupGender" class="col-sm-2 col-form-label">Gender</label>
                <div class="col-sm-4">
                  <select class="form-control" id="signupGender" name="signupGender" data-gender="${gender}" disabled>
                    <option value="M">M</option>
                    <option value="F">F</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupUsername" class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-4">
                  <input type="hidden" class="name" id="signupUsername" name="signupUsername" value = "<%= User.currentUser.getUserId()%>"disabled>
                </div>
                <label for="signupContact" class="col-sm-2 col-form-label">Contact</label>
                <div class="col-sm-4">
                  <input type="number" class="form-control" id="signupContact" name="signupContact" value = "${contact}" disabled>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupAddress" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="signupAddress" name="signupAddress" value = "${address}" disabled>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupPostalCode" class="col-sm-2 col-form-label">Postal code</label>
                <div class="col-sm-4">
                  <input type="number" class="form-control" id="signupPostalCode" name="signupPostalCode" value = "${postalCode}" disabled>
                </div>
                <label for="signupCountry" class="col-sm-2 col-form-label">Country</label>
                <div class="col-sm-4">
                  <select class="form-control input-medium bfh-countries" id="signupCountry" name="signupCountry" data-country="${country}" disabled></select>
                </div>
              </div>
              <button type="submit" class="btn btn-success">Edit</button>
            </form>
          </div>
        </div>
      </div>
      <div id="alertBox"></div>
    </div>
  </div>

  <!-- Footer -->
  <footer class="footer py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
    </div>
    <!-- /.container -->
  </footer>
