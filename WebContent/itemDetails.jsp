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
        
        var itemInfo;
        var itemLabel = ["item_id", "user_id", "item_title", "item_category", "item_description", "item_condition", 
        	"item_location", "item_delivery_mode", "item_like_count", "item_status", "selling_price", "shipping_fee",
        	"active", "remarks"];
        var str = "";
        $.get("itemdetails", function(output) {
            if (output != "-1") {
            	itemInfo = output;
                /* var item_id = itemInfo.item_id;
                var user_id = itemInfo.user_id;
                var item_title = itemInfo.item_title;
                var item_category = itemInfo.item_category;
                var item_description = itemInfo.item_description;
                var item_condition = itemInfo.item_condition;
                var item_location = itemInfo.item_location;
                var item_delivery_mode = itemInfo.item_delivery_mode;
                var item_like_count = itemInfo.item_like_count;
                var item_status = itemInfo.item_status;
                var selling_price = itemInfo.selling_price;
                var shipping_fee = itemInfo.shipping_fee; */
                
                var itemPlaceholder = [itemInfo.item_id, itemInfo.user_id, itemInfo.item_title, itemInfo.item_category, itemInfo.item_description,
                	itemInfo.item_condition, itemInfo.item_location, itemInfo.item_delivery_mode, itemInfo.item_like_count, itemInfo.item_status,
                	itemInfo.selling_price, itemInfo.shipping_fee, itemInfo.active, itemInfo.remarks];

                for (var i = 0; i < itemLabel.length; i++) {
                    str += '<li class="list-group-item w-25">';
                    str += itemLabel[i];
                    str += '</li>';
                    str += '<li class="list-group-item w-75">';
                    str += itemPlaceholder[i];
                    str += '</li>';
                }
                
            } else {
                str += "Please sign in first!";
            }
            $("#itemContainer").html(str);
        });

    });
    </script>
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
					<li class="nav-item active">
						<a id="loginNav" class="nav-link" href="login.jsp">Login<span class="sr-only">(current)</span></a>
						<button type="button" id="logoutNav" class="btn btn-dark" onclick="logout()">Logout
							<span class="sr-only">(current)</span></button>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="card mx-auto mt-4 mb-4">
    	<h5 class="card-header">Item Details</h5>
    	<div class="card-body">
      		<ul id="itemContainer" class="list-group d-flex flex-row flex-wrap"></ul>
    	</div>
  	</div>
  
	<div class="itemContainer2 mt-4 mb-4">
		<div class="loader"></div>
		<div class="card text-center mx-auto">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#listItem">Item Details</a></li>
				</ul>
			</div>
			<div class="card-body">
				<div class="tab-content">
					<div class="tab-pane fade show active" id="listItem">
						<form id="listItemForm" name="listItemForm" action="userHome.jsp">
							<div class="form-group row">
								<label for="item_id" class="col-sm-2 col-form-label">Item ID</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_id" name="item_id"></output>
								</div>								
								<label for="user_id" class="col-sm-2 col-form-label">User ID</label>
								<div class="col-sm-10">
									<a href="#"><output class="form-control" id="user_id" name="user_id"></output></a>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_like_count" class="col-sm-2 col-form-label">Item Like Count</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_like_count" name="item_like_count"></output>
								</div>
								<label for="item_status" class="col-sm-2 col-form-label">Item Status</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_status" name="item_status"></output>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_title" class="col-sm-2 col-form-label">Title</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_title" name="item_title"></output>
								</div>
							</div>
							<div class="form-group row">
								<label for="photo" class="col-sm-2 col-form-label">Photo</label>
								<div class="col-sm-10">
									<output class="form-control" id="photo" name="photo"></output>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_category" class="col-sm-2 col-form-label">Category</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_category" name="item_category"></output>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_description" class="col-sm-2 col-form-label">Description</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_description" name="item_description"></output>
								</div>
							</div>
							<hr>
							<div class="form-group row">
								<label for="item_condition" class="col-sm-2 col-form-label">Condition</label>
								<div class="col-sm-4">
									<output class="form-control" id="item_condition" name="item_condition"></output>
								</div>
								<label for="selling_price" class="col-sm-2 col-form-label">Selling Price</label>
								<div class="col-sm-4">
									<output class="form-control" id="selling_price" name="selling_price"></output>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_location" class="col-sm-2 col-form-label">Location</label>
								<div class="col-sm-10">
									<output class="form-control" id="item_location" name="item_location"></output>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_delivery_mode" class="col-sm-2 col-form-label">Delivery Mode</label>
								<div class="col-sm-4">
									<output class="form-control" id="item_delivery_mode" name="item_delivery_mode"></output>
								</div>
								<label for="shipping_fee" class="col-sm-2 col-form-label">Shipping Fee</label>
								<div class="col-sm-4">
									<output class="form-control" id="shipping_fee" name="shipping_fee"></output>
								</div>
							</div>
							<button type="submit" class="btn btn-success">List Item</button>
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

</body>

</html>