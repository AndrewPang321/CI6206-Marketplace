<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- JavaScript files -->
<script src="js/util.js"></script>
<script src="js/login.js"></script>


</head>

<body>
	
    
<
    	
    

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="userHome.jsp">Marketplace</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="userHome.jsp">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Services</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact</a>
					</li>
					<li class="nav-item active"><a class="nav-link"
						href="login.jsp">Logout <span class="sr-only">(current)</span>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="listItemContainer">
		<div class="loader"></div>
		<div class="card text-center mx-auto">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#buyitem">Buy Item</a></li>
				</ul>
			</div>
			<div class="card-body">
				<div class="tab-content">
					<div class="tab-pane fade show active" id="buyItem">
						<!-- <form id="listItemForm" name="listItemForm" action="userHome.jsp">  -->
						
						<form method="post" action="buyitem">
							<div class="form-group row">
								<label for="item_id" class="col-sm-2 col-form-label">Enter item ID</label>
								<div class="col-sm-10">
									<input type="hidden" class="form-control" id="item_id"
										name="item_id"  value=<%= request.getParameter("item_id") %> required>
								</div>
							</div>

							<div class="form-group row">
								<label for="offer_price" step="0.01" class="col-sm-2 col-form-label">Offer Price</label>
								<div class="col-sm-10">
									<input type="number" class="form-control" id="offer_price"
										name="offer_price" required>
								</div>
							</div>
							
							<button type="submit" class="btn btn-success" value="Submit">Buy Item</button>
						</form>
					</div>
				</div>
			</div>
			<hr>

			
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

</body>

</html>