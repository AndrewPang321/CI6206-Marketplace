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
    
    <!-- JavaScript files -->
    <script src="js/util.js"></script>
</head>
<body>
	<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">Marketplace</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
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
              <a class="nav-link" href="login.jsp">Login
              	<span class="sr-only">(current)</span>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
    <!-- Page Content -->
    <div class="loginContainer">
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
		        <!-- TODO: add form action -->
		        <form action="login"method="post"  onsubmit="return false">
				  <div class="form-group row">
				    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="email@example.com">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password">
				    </div>
				  </div>
				  <button type="submit" class="btn btn-success" onclick="showAlert({message: 'Login Success', class: 'success'})">Login</button>
				</form>
		        <br>
		        <!-- TODO: handle forgot password action -->
		        <a href="#"><small>Forgot your password?</small></a>
		      </div>
		      <div class="tab-pane fade" id="signup">
		      	<!-- TODO: add form action -->
		        <form action="signup" method="post" onsubmit="return false">
				  <div class="form-group row">
				    <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="email@example.com">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password">
				    </div>
				  </div>
				   <div class="form-group row">
				    <label for="inputConfirmPassword" class="col-sm-2 col-form-label">Confirm Password</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="inputConfirmPassword" name="inputConfirmPassword" placeholder="Confirm Password">
				    </div>
				  </div>
				  <button type="submit" class="btn btn-success" onclick="showAlert({message: 'Sign Up Success', class: 'success'})">Sign Up</button>
				</form>
		      </div>
		    </div>
		  </div>
		  <div id="alertBox"></div>
		</div>
    </div>
    
    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="lib/jquery/jquery.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>