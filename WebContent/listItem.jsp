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
<script src="js/auth.js"></script>

</head>

<body>
	<script>  
    $(document).ready(function () {
        loginLogoutToggle();

      // ListItemForm submission
      $("#listItemForm").submit(function (event) {
        var form = $(this);
        var item_title = $("#item_title").val();
        var photo = $("#photo").val();
        var image = $("#image").val();
        var photo_name = $("#photo_name").val();
        var item_category = $("#item_category").val();
        var item_description = $("#item_description").val();
        var item_condition = $("#item_condition").val();
        var selling_price = $("#selling_price").val();
        var item_location = $("#item_location").val();
        var item_delivery_mode = $("#item_delivery_mode").val();
        var shipping_fee = $("#shipping_fee").val();
                
        $.ajax({
          url: "listitem",
          type: "POST",
          data: form.serialize(),
          beforeSend: function () {
            $(".loader").css("display", "block");
          },
          success: function () {
            console.log("success");
            window.location.replace("userHome.jsp");
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
	            default:
	              showAlert({
	                message: 'Some problems occur. Please try again',
	                class: 'danger'
	              });
	          }
            $(".loader").css("display", "none");
          }
        });
        });
      });
    
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader(); 
            
            reader.onload = function (e) {
                $('#image')
                    .attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
            
            var x = document.getElementById("photo").value;
            // get the file name from full path
            var y = x.replace(/^.*[\\\/]/, '')
            document.getElementById("photo_name").innerHTML = y;
        }
    }
    	
    
    </script>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="index.html">Marketplace</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="index.html">Home</a>
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
	<div class="listItemContainer mt-4 mb-4">
		<div class="loader"></div>
		<div class="card text-center mx-auto">
			<div class="card-header">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#listItem">List Item</a></li>
				</ul>
			</div>
			<div class="card-body">
				<div class="tab-content">
					<div class="tab-pane fade show active" id="listItem">
						<!-- <form id="listItemForm" name="listItemForm" action="userHome.jsp">  -->
						<form method="post" action="listitem" enctype="multipart/form-data">
							<div class="form-group row">
								<label for="item_title" class="col-sm-2 col-form-label">Title</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="item_title"
										name="item_title" placeholder="Iphone 6" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="photo" class="col-sm-2 col-form-label">Photo</label>
								<div class="col-sm-10">
									<input type="file" class="form-control" id="photo" name="photo"
										size="20" onchange="readURL(this);" /> <img id="image"
										name="image" src="" style="width: 150px" />
									<p id="photo_name"></p>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_category" class="col-sm-2 col-form-label">Category</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="item_category"
										name="item_category" placeholder="Mobile Phone" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_description" class="col-sm-2 col-form-label">Description</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="item_description"
										name="item_description">
								</div>
							</div>
							<hr>
							<div class="form-group row">
								<label for="item_condition" class="col-sm-2 col-form-label">Condition</label>
								<div class="col-sm-4">
									<select class="form-control" id="item_condition"
										name="item_condition" required>
										<option value="New">New</option>
										<option value="Old">Old</option>
									</select>
								</div>
								<label for="selling_price" class="col-sm-2 col-form-label">Selling
									Price</label>
								<div class="col-sm-4">
									<input type="number" step="0.01" class="form-control"
										id="selling_price" name="selling_price" required>
								</div>
							</div>
							<div class="form-group row">
								<label for="item_location" class="col-sm-2 col-form-label">Location</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="item_location"
										name="item_location">
								</div>
							</div>
							<div class="form-group row">
								<label for="item_delivery_mode" class="col-sm-2 col-form-label">Delivery
									Mode</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="item_delivery_mode"
										name="item_delivery_mode">
								</div>
								<label for="shipping_fee" class="col-sm-2 col-form-label">Shipping
									Fee</label>
								<div class="col-sm-4">
									<input type="number" step="0.01" class="form-control"
										id="shipping_fee" name="shipping_fee">
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
			<p class="m-0 text-center text-white">Copyright &copy; Marketplace 2018</p>
		</div>
		<!-- /.container -->
	</footer>

</body>

</html>