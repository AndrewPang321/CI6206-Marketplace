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

        var url = window.location.href;
        url = url.split("=");
        var item_id = url[1];
        var itemInfo;
        var offerInfo;
        var offerLabel = ["Buyer Id", "Item Id", "Item Title", "Offer Price", "Offer Status"];
        var itemLabel = ["Item Id", "User Id", "Item Title", "Item Category", "Item Description", "Item Condition",
        	"Item Location", "Item Delivery Mode", "Item Like Count", "Item Status", "Selling Price", "Shipping Fee",
        	"Active", "Remarks"];
        var str = "";

        $.get("itemdetails", { item_id: item_id }, function(output) {
            if (output != "-1") {
            	itemInfo = output;

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
        
        $.get("pendingoffer", { item_id: item_id }, function(output) {
            if (output != "-1") {
            	offerInfo = output;

                var offerPlaceholder = [offerInfo.buyer_id, offerInfo.item_id, offerInfo.item_title, offerInfo.offer_price, offerInfo.offer_status];

                for (var i = 0; i < offerLabel.length; i++) {
                    str += '<li class="list-group-item w-25">';
                    str += offerLabel[i];
                    str += '</li>';
                    str += '<li class="list-group-item w-75">';
                    str += offerPlaceholder[i];
                    str += '</li>';
                }
                
            } else {
                str += "Please sign in first!";
            }
            $("#offerContainer").html(str);
        });

    });
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
	<div class="card mx-auto mt-4 mb-4">
    	<h5 class="card-header">Item Details</h5>
    	<div class="card-body">
      		<ul id="itemContainer" class="list-group d-flex flex-row flex-wrap"></ul>
    	</div>
  	</div>
  	
  	<div class="card mx-auto mt-4 mb-4">
    	<h5 class="card-header">Offers List</h5>
    	<div class="card-body">
      		<ul id="offerContainer" class="list-group d-flex flex-row flex-wrap"></ul>
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