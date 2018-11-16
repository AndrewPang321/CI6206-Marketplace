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

<body>
  <script>
    $(document).ready(function () {
      loginLogoutToggle();

      // LoginForm submission
      $("#loginForm").submit(function (event) {
        var form = $(this);

        $.ajax({
          url: "login",
          type: "POST",
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
                  message: 'Login success. Redirect in 1 second',
                  class: 'success'
                });
                setTimeout(function () {
                  window.location.replace("index.html")
                  // window.location.replace("userHome.jsp")
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

      // SignupForm submission
      $("#signupForm").submit(function (event) {
        var form = $(this);
        var email = $("#signupEmail").val();
        var password = $("#signupPassword").val();
        var confirmPassword = $("#signupConfirmPassword").val();
        if (!signupFormValidation(email, password, confirmPassword)) {
          showAlert({
            message: 'Passwords are not the same',
            class: 'danger'
          });
        } else {
          $.ajax({
            url: "signup",
            type: "POST",
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
                case 201:
                  showAlert({
                    message: 'Sign up success. Redirect in 1 second',
                    class: 'success'
                  });
                  setTimeout(function () {
                    window.location.replace("index.html")
                  }, 1500);
                  break;
                case 400:
                  showAlert({
                    message: 'Some problems occur. Please try again',
                    class: 'danger'
                  });
                  break;
                case 409:
                  showAlert({
                      message: 'Email already existed. Please try another one',
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
        }
        return false;
      });

    });
  </script>

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
            <a class="nav-link" href="index.html">Home</a>
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
            <a id="loginNav" class="nav-link" href="login.jsp">Login
              <span class="sr-only">(current)</span>
            </a>
            <button type="button" id="logoutNav" class="btn btn-dark" onclick="logout()">Logout</button>
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
            <a class="nav-link active" data-toggle="tab" href="#login">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#signup">Sign up</a>
          </li>
        </ul>
      </div>
      <div class="card-body">
        <div class="tab-content">
          <div class="tab-pane fade show active" id="login">
            <form id="loginForm">
              <div class="form-group row">
                <label for="loginEmail" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="loginEmail" name="loginEmail" placeholder="email@example.com" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="loginPassword" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="Password" required>
                </div>
              </div>
              <button type="submit" class="btn btn-success">Login</button>
            </form>
            <br>
            <!-- TODO: handle forgot password action -->
            <a href="#">
              <small>Forgot your password?</small>
            </a>
          </div>
          <div class="tab-pane fade" id="signup">
            <form id="signupForm">
              <div class="form-group row">
                <label for="signupEmail" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" id="signupEmail" name="signupEmail" placeholder="email@example.com" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupPassword" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="signupPassword" name="signupPassword" placeholder="Password" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupConfirmPassword" class="col-sm-2 col-form-label">Confirm Password</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" id="signupConfirmPassword" name="signupConfirmPassword" placeholder="Confirm Password"
                         required>
                </div>
              </div>
              <hr>
              <div class="form-group row">
                <label for="signupFirstName" class="col-sm-2 col-form-label">Firstname</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="signupFirstName" name="signupFirstName" required>
                </div>
                <label for="signupLastName" class="col-sm-2 col-form-label">Lastname</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="signupLastName" name="signupLastName" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupDateOfBirth" class="col-sm-2 col-form-label">Date of birth</label>
                <div class="col-sm-4">
                  <input type="date" class="form-control" id="signupDateOfBirth" name="signupDateOfBirth" required>
                </div>
                <label for="signupGender" class="col-sm-2 col-form-label">Gender</label>
                <div class="col-sm-4">
                  <select class="form-control" id="signupGender" name="signupGender" required>
                    <option value="M">M</option>
                    <option value="F">F</option>
                  </select>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupUsername" class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="signupUsername" name="signupUsername" required>
                </div>
                <label for="signupContact" class="col-sm-2 col-form-label">Contact</label>
                <div class="col-sm-4">
                  <input type="number" class="form-control" id="signupContact" name="signupContact" placeholder="12345678" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupAddress" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="signupAddress" name="signupAddress" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="signupPostalCode" class="col-sm-2 col-form-label">Postal code</label>
                <div class="col-sm-4">
                  <input type="number" class="form-control" id="signupPostalCode" name="signupPostalCode" required>
                </div>
                <label for="signupCountry" class="col-sm-2 col-form-label">Country</label>
                <div class="col-sm-4">
                  <select class="form-control input-medium bfh-countries" id="signupCountry" name="signupCountry" data-country="SG"></select>
                </div>
              </div>
              <button type="submit" class="btn btn-success">Sign Up</button>
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

</body>

</html>