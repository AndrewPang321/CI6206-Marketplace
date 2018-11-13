<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<c:forEach items="${sellerProfile}" var="seller"> 
		<tr>
 			 <td>${seller.email}</td>
  			 <td>${seller.firstname}</td>  
  			 <td>${seller.lastname}</td>  
  			 <td>${seller.gender}</td>  
  			 <td>${seller.country}</td>  
  		</tr>
	</c:forEach>
	
<script>
    $(document).ready(function() {
        var itemsCollection;
        var str = "";
        $.get("sellerProfile", function(output) {
            itemsCollection = output;
            console.log(itemsCollection);
            console.log(itemsCollection.length);
            itemsCollection.forEach(function(item) {
                str += '<div class="col-lg-4 col-md-6 mb-4">';
                str += '<div class="card h-100">';
                str += '<a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>';
                str += '<div class="card-body">';
                str += '<h4 class="card-title">';
                str += '<a href="#">' + item.item_title + '</a>';
                str += '</h4>';
                str += '<h5>$' + item.selling_price + '</h5>';
                str += '<p class="card-text">' + item.item_description + '</p>';
                str += '</div>';
                str += '<div class="card-footer">';
                str += '<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>';
                str += '</div>';
                str += '</div>';
                str += '</div>';
            });
            $("#collection").html(str);
        });
    });
    
    $("#itemModal").on("show.bs.modal", function(event) {
        var itemId = $(event.relatedTarget).parent().find("#itemId").text();
        var itemUserId = $(event.relatedTarget).parent().find("#itemUserId").text();
        var itemTitle = $(event.relatedTarget).parent().find("#itemTitle").text();
        $("#itemModalTitle").text(itemTitle);
        console.log("Item ID: " + itemId);
        console.log("User ID in item: " + itemUserId);
    });
});

  </script>

<body>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="#">Marketplace</a>
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
            <br>            
          </div>
          <div class="tab-pane fade" id="sell">
            <form id="sellForm">
              <div class="form-group row">
                <label for="item_title" class="col-sm-2 col-form-label">Title</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="item_title" name="item_title" placeholder="Iphone 6" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="item_category;" class="col-sm-2 col-form-label">Category</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="item_category;" name="item_category;" placeholder="Phone" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="item_description" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="item_description" name="item_description">
                </div>
              </div>
              <hr>
              <div class="form-group row">
                <label for="item_condition" class="col-sm-2 col-form-label">Condition</label>
                <div class="col-sm-4">
                  <select class="form-control" id="item_condition" name="item_condition" required>
                    <option value="New">New</option>
                    <option value="Old">Old</option>
                  </select>
                </div>                
                <label for="item_location" class="col-sm-2 col-form-label">Location</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="item_location" name="item_location" required>
                </div>
              </div>
              <div class="form-group row">
                <label for="item_delivery_mode" class="col-sm-2 col-form-label">Delivery Mode</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="item_delivery_mode" name="item_delivery_mode" required>
                </div>
                <label for="selling_price" class="col-sm-2 col-form-label">Selling Price</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="selling_price" name="selling_price" required>
                </div>
                <label for="shipping_fee" class="col-sm-2 col-form-label">Shipping Fee</label>
                <div class="col-sm-4">
                  <input type="text" class="form-control" id="shipping_fee" name="shipping_fee" required>
                </div>
              </div>
              <button type="submit" class="btn btn-success">List it...</button>
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